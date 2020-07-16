
if ( typeof Object.create != 'function') {
  Object.create = function(obj) {
    function F() {};
    F.prototype = obj;
    return new F();
  }
}

// requestAnimationFrame polyfill by Erik Möller. fixes from Paul Irish and Tino Zijdel
(function() {
  var lastTime = 0;
  var vendors = ['ms', 'moz', 'webkit', 'o'];
  for(var x = 0; x < vendors.length && !window.requestAnimationFrame; ++x) {
    window.requestAnimationFrame = window[vendors[x]+'RequestAnimationFrame'];
    window.cancelAnimationFrame = window[vendors[x]+'CancelAnimationFrame'] || window[vendors[x]+'CancelRequestAnimationFrame'];
  }

  if (!window.requestAnimationFrame) {
    window.requestAnimationFrame = function(callback, element) {
      var currTime = new Date().getTime();
      var timeToCall = Math.max(0, 16 - (currTime - lastTime));
      var id = window.setTimeout(function() { callback(currTime + timeToCall); }, timeToCall);
      lastTime = currTime + timeToCall;
      return id;
    }
  }

  if (!window.cancelAnimationFrame) {
    window.cancelAnimationFrame = function(id) {
      clearTimeout(id);
    };
  }
}());


(function($, window, document, undefined) {

  function img(url) {
    var i = new Image;
    i.src = url;
    return i;
  }

  if ('naturalWidth' in (new Image)) {
    $.fn.naturalWidth  = function() { return this[0].naturalWidth; };
    $.fn.naturalHeight = function() { return this[0].naturalHeight; };
  } else {
    $.fn.naturalWidth  = function() { return img(this[0].src).width; };
    $.fn.naturalHeight = function() { return img(this[0].src).height; };
  }

  var Skidder = {

    init: function(options, elem) {
      var self = this; // instance
      window.skidder = self;
      self.elem = elem;
      self.$elem = $(elem);

      // merge options
      self.options = $.extend( {},  $.fn.skidder.options, options);

      if(!self.supportsTransitions()) {
        self.options.animationType = 'animate';
      }


     
      self.$elem.data('skidder', self);

      // determine touch support
      if (('ontouchstart' in window) ||
        (navigator.maxTouchPoints > 0) ||
        (navigator.msMaxTouchPoints > 0)) {
        self.touchdevice = true;
      }

      // store elements + create wrappers
      self.$slides = self.$elem.find(self.options.slideClass);
      self.$slides.wrapAll('<div class="skidder-wrapper"></div>').addClass('skidder-slide');
      self.$elem.wrapInner('<div class="skidder-viewport"></div>');
      self.$viewport = self.$elem.find('.skidder-viewport');
      self.$wrapper = self.$viewport.find('.skidder-wrapper');
      
      if (self.$slides.length > 1) {
        self.$viewport.append('<div class="skidder-prevwrapper skidder-clickwrapper"><div class="skidder-prev skidder-clickelement"></div></div><div class="skidder-nextwrapper skidder-clickwrapper"><div class="skidder-next skidder-clickelement"></div></div>');
        self.$clickwrappers = self.$viewport.find('.skidder-clickwrapper');
        if (self.touchdevice) {
          if (self.options.swiping) {
            // if touch support, append touchwrapper
            self.$viewport.append('<div class="skidder-touchwrapper"></div>');
            self.$touchwrapper = self.$viewport.find('.skidder-touchwrapper');
          } else { // no touch support, show click controls
            self.$clickwrappers.find('.skidder-clickelement').css('opacity', 1);
          }
        }
        self.$clickwrappers.attr({
          'data-direction' : function() {
            return $(this).hasClass('skidder-prevwrapper') ? 'prev' : 'next'; // TODO: remove direction
          },
        });
        if ((self.touchdevice || self.options.paging)) {
          self.$pager = self.$viewport.find(self.options.pagingWrapper);
          if (self.$pager.length < 1) {
            self.$viewport.append('<div class="' + self.options.pagingWrapper.replace('.', '') +  '"></div>');
            self.$pager = self.$viewport.find(self.options.pagingWrapper);
          }
        }
      } 
      
      self.leftPosition = 0;

      // establish initial dimensions

      self.refreshImages(); // select images

      if (self.$images && self.$images.length && self.options.scaleSlides) { // scaling

        self.scaleSlides();
      
      } else if (self.$images && self.$images.length) { // no scaling, slideshowheight = highest image height
        
        var newMaxHeight = 0;
        self.$images.each(function() { // TODO: for no-image slideshows
          newMaxHeight = Math.max($(this).innerHeight(), newMaxHeight);
          // console.log(newMaxHeight);
        });
        self.setSlideshowHeight(newMaxHeight);
      }

      if (self.$images && self.$slides.length > 1) {

        self.preloadSlides();
        self.centerPosition();

        if (self.options.autoplay) {
          self.autoplaying = self.autoplay();
        }
      } else if (self.$images && self.$slides.length == 1) {

        if (self.options.lazyLoad && self.options.lazyLoadAutoInit) {
          self.lazyLoadSlides();
        }

        // call init here for 1 slide shows, as no preload happens
        setTimeout( function() {
          self.$slides.addClass('active');
        }, 0);
        
        // self.$wrapper.css('opacity', 1);
        self.options.afterInit.call(this);
      } else {
        console.log('No slides found');
      }

    },

    scaleSlides: function() {

      var self = $(this).data('skidder') || this;
      var maxWidth;
      var maxHeight;
      var slideshowHeight;

      if (self.options.maxWidth > 0) {
        // set width to viewportwidth or maxWidth (if smaller)
        maxWidth = Math.min(self.$viewport.innerWidth(), self.options.maxWidth);
      } else {
        maxWidth = self.$viewport.innerWidth();
      }

      // smallest scaling mode: slideshow height and slide height are defined by smallest image height (or maxHeight if smaller)
      if (self.options.scaleTo === "smallest") { 
        
        if (self.options.maxHeight > 0) {
          maxHeight = self.options.maxHeight;
        } else {
          maxHeight = 10000000; // a.k.a infinity
        }

        var scalefactor = 1.0;
  
        self.$images.each(function() {
          if (!$(this).is('img')) {
            // test: only div slides
          } else {
            scalefactor = Math.min(1.0, maxWidth / $(this).naturalWidth()); // if image wider than viewport or maxWidth...
            maxHeight = Math.min( maxHeight, Math.ceil($(this).naturalHeight() * scalefactor));
          }
          
        });

        slideshowHeight = maxHeight;
        
        self.setSlideshowHeight(slideshowHeight);

        // scale images ---- TODO: possibly refactor with ratio --- validate condition calls for smaller images

        self.$images.each(function() {
          if ($(this).naturalHeight() > maxHeight) {
            // console.log('1');
            $(this).css({
              'width'   : Math.ceil($(this).naturalWidth() * (maxHeight / $(this).naturalHeight() )) + 2, // + 2 is lazy correction for rounding problem
              'height'  : maxHeight
            });
            $(this).closest('.skidder-slide').css({
              'width'   : (self.options.spaceSlides ? maxWidth : 'auto'),
            });
          
          } else if ($(this).naturalWidth() > maxWidth) {
            // console.log('2');
            $(this).css({
              'width'   : maxWidth,
              'height'  : Math.ceil($(this).naturalHeight() * (maxWidth / $(this).naturalWidth() )) + 2 // + 2 is lazy correction for rounding problem
            });
          
          } else {
            // console.log('3');
            $(this).css({
              'width'   : ($(this).is('img') ?  'auto' : maxWidth ),
              'height'  : maxHeight,
              'margin-left'  : 'auto',
              'margin-right'  : 'auto',
              'display' : 'block',
            });
          }
        });


      // ratio scaling mode: slideshow height defined by given ratio (or maxheight if smaller)
      } else if (self.options.scaleTo.constructor === Array) {
      
        // maxHeight = maxWidth / self.options.scaleTo[0] * self.options.scaleTo[1];

        maxHeight = self.options.scaleTo[1] / self.options.scaleTo[0] * maxWidth;

        if (self.options.maxHeight > 0) {
          // with scaleTo ratio maxheight crops images (unless preservePortrait)
          slideshowHeight = Math.min(maxHeight, self.options.maxHeight);
        } else {
          slideshowHeight = maxHeight
        }

        self.setSlideshowHeight(slideshowHeight);

        // scale images
        self.$images.each(function() { 

          var imagewidth = $(this).naturalWidth() || $(this).closest('.skidder-slide').attr('data-skidder-width');
          var imageheight = $(this).naturalHeight() || $(this).closest('.skidder-slide').attr('data-skidder-height');
          
          if (!$(this).is('img')) {
            // element is imageless slide
            // console.log('element is imageless slide');
            $(this).css({
              'width'   : maxWidth,
              'height'  : '100%',
            });
          } else if ((imagewidth / imageheight) >= self.options.scaleTo[0] / self.options.scaleTo[1]) {
            // wider than ratio: size to maxHeight, will be cropped left + right
            // console.log('wider than ratio:');
            $(this).css({
              'width'   : 'auto',
              'height'  : maxHeight,
            });
            $(this).closest('.skidder-slide').css({
              'width'   : maxWidth,
            });

          } else if ((imagewidth / imageheight) < self.options.scaleTo[0] / self.options.scaleTo[1] && imagewidth > imageheight) {
            // less wide than ratio but landscape: will be cropped top & bottom
            // console.log('less wide than ratio but landscape');
            $(this).css({
              'width'   : maxWidth,
              'height'  : 'auto',
              'margin-top': (slideshowHeight - (maxWidth / $(this).naturalWidth() * $(this).naturalHeight())) / 2
            });
          } else {
            // portrait
            if (self.options.preservePortrait) {
              // fit
              // console.log('portrait fit');
              $(this).css({
                'width'   : 'auto',
                'height'  : slideshowHeight,
                'margin-left'  : 'auto',
                'margin-right'  : 'auto',
                'display' : 'block',
              });
              $(this).closest('.skidder-slide').css({
                'width'   : (self.options.spaceSlides ? maxWidth : 'auto'),
              });
            } else {
              // console.log('portrait cover');
              // cover
              $(this).css({
                'width'   : maxWidth,
                'height'  : 'auto',
                'margin-top': (maxHeight - (maxWidth / imagewidth * imageheight)) / 2
              });
            }
          }
        });
      }

      // set new leftPosition executed in scrollWrapper // TODO: does not work on load as active slide is not set yet! do we need this to work?
      // self.refreshSlides();
      var currentactiveindex = self.$elem.find('.skidder-slide').index(self.$elem.find('.skidder-slide').filter('.active'));
      // console.log(currentactiveindex);
      // console.log(self.leftPosition);
      self.leftPosition = 0;
      for (i = 0; i < currentactiveindex; i++ ) {
        self.leftPosition -= self.$slides.eq(i).innerWidth();
      }
      // console.log(self.leftPosition);
      if (self.options.transition == "slide") {
        self.$wrapper.css('left', self.leftPosition);
      }

    },

    preloadSlides: function() {
      var self = this;
      var $activeslide = self.$slides.eq(0);
      var slidesTotalWidth = 0;

      if (self.$pager && self.$pager.length) {
        self.$pagerdots = self.$pager.find(self.options.pagingElement);
      }


      for (i = 0; i < self.$slides.length; i++ ) {
        // establish total width of wrapper
        slidesTotalWidth += self.$slides.eq(i).innerWidth();
        // append pager
        if ((self.touchdevice || self.options.paging) && self.$slides.length > 1 && (self.$pagerdots.length < self.$slides.length)) {
          self.$pager.append('<div class="' + self.options.pagingElement.replace('.', '') +  '"></div>');
        }
      }

      if (self.touchdevice || self.options.paging) {
        // if mobile, show paging
        self.$pager.find(self.options.pagingElement).css('opacity', 1);
      }

      if (self.options.transition == "slide" || ( self.options.swiping && self.touchdevice)) {
        if (self.options.cycle && self.$slides.length > 1) {
          // clone two sets of slides
          self.$slides.clone(true).addClass('skidder-clone skidder-clone-pre').prependTo(self.$wrapper);
          self.$slides.clone(true).addClass('skidder-clone skidder-clone-post').appendTo(self.$wrapper);

          // set initial left position
          self.leftPosition = -slidesTotalWidth;
          self.$wrapper.css({
            'left': self.leftPosition,
          });

          if (!self.touchdevice && self.options.animationType == 'css') { // for touch, css transitions are only active for actual scrolling
            window.requestAnimationFrame(self.applyWrapperSlideTransition());
          }

          self.refreshSlides();
          self.refreshImages();
        } else if ( self.$slides.length > 1 ){
          self.$clickwrappers.find('.skidder-prev').hide(0);
        }
      } else if (self.options.transition == "fade") {
        self.$viewport.addClass('skidder-fade');
        if (self.options.cycle && self.$slides.length > 1) {
          self.$wrapper.add(self.$slides).css("position", "absolute");
        } else if ( self.$slides.length > 1 ){
          self.$clickwrappers.find('.skidder-prev').hide(0);
        }
      }

      $activeslide.addClass('active');

      if (self.options.lazyLoad && self.options.lazyLoadAutoInit ) {
        self.lazyLoadSlides();
      }

      if (self.options.directionClasses) {
        self.addLeftAndRightClasses();
      };

      // add clickhandlers
      if (self.$slides.length > 1) {
        if (self.touchdevice || self.options.paging) {

          self.$pagerdots = self.$pager.find(self.options.pagingElement);
          self.$pagerdots.eq(0).addClass('active');
        }
        self.addEventHandlers()();
      }

      // self.$wrapper.css('opacity', 1);

      if ( $.isFunction( self.options.afterInit ) ) {
        self.options.afterInit.call(this);
      }

    },

    lazyLoadSlides: function() {

      var self = $(this).data('skidder') || this;
      var activeslideindex = self.$slides.index(self.$slides.filter('.active'));
      var $slidestoload;
      
      if (self.$slides.length > 1) {
        var $slidestoload = self.$slides.eq(activeslideindex);
        for (llw = 1; llw <= self.options.lazyLoadWindow; lww++) {
          $slidestoload = $slidestoload.add(self.$slides.eq(activeslideindex-llw), self.$slides.eq(activeslideindex+llw)) 
        }  
      } else if (self.$slides.length == 1) {
        $slidestoload = self.$slides.eq(0);
      }
      
      $slidestoload.each(function() {
        if (!$(this).hasClass('skidder-loaded') && $(this).attr('data-skidder-src')) {
          $(this)
            .prepend("<img class='skidder-loaded' src='" + $(this).attr('data-skidder-src') + "' title='" +  $(this).attr('data-skidder-title') + "' alt='" + $(this).attr('data-skidder-alt') + "' > ")
            .css({
              'width'  : 'auto', 
              'height' : 'auto'
            })
            .addClass('skidder-loaded');
          // wrap in a
          if ($(this).attr('data-skidder-href')) {
            $(this).find('.skidder-loaded').wrap("<a href='" + $(this).attr('data-skidder-href') + "'></a>");
          }
        }
      });
      self.refreshSlides();
      self.refreshImages();
      self.scaleSlides();
      self.centerPosition();
      self.$slides.find('.skidder-loaded').not('skidder-shown').fadeIn(400).addClass('skidder-shown');
      if ( $.isFunction( self.options.afterLazyLoad ) ) {
        self.options.afterLazyLoad.call(this);
      }
    
    }, 

    applyWrapperSlideTransition: function() {
      var self = this;
      return function() {
        var speedstring = "" + (self.options.speed/1000) + "s";
        self.$wrapper.css({
          '-webkit-transition': 'left ' + speedstring + ' ease-out',
          'transition': 'left ' + speedstring + ' ease-out'
        });
      }
    },

    removeWrapperSlideTransition: function() {
      var self = this;
      return function() {
        self.$wrapper.css({
          '-webkit-transition': 'none',
          'transition': 'none'
        });
      }
    },

    addEventHandlers: function() {
      var self = this;
      return function() {
        if (self.$slides.length > 1) {
          if (self.touchdevice || self.options.paging) {
            self.$pagerdots.on('click touchend', function(e){self.clickHandlerPaging(e)});
          }
          if (self.options.swiping && self.touchdevice) {
            self.$touchwrapper.on('touchstart touchmove touchend', function(e){self.swipeHandler(e)});
          } else {
            self.$clickwrappers.on('click', function(e){self.clickHandlerLeftRight(e)});
          }
        }
      }
    },

    removeEventHandlers: function() {
      var self = this;
      if (self.$slides.length > 1) {
        if (self.touchdevice || self.options.paging) {
          self.$pagerdots.off('click touchend');
        }
        if (self.options.swiping && self.touchdevice) {
          self.$touchwrapper.off('touchstart touchmove touchend');
        } else {
          self.$clickwrappers.off('click');
        }
      }
    },

    swipeHandler: function(e) {
      var self = this;
      var diffX = 0;
      var diffY = 0;
      var touchinterval;
      // var velocity;
      var $activeslide = self.$slides.filter('.active');
      if (self.touchdevice || self.options.paging) {
        var $activedot = self.$pagerdots.filter('.active');
      }


      if (e.type == "touchstart") {
        window.clearTimeout(self.autoplaying);
        self.windowY = window.pageYOffset;
        self.initialX = e.originalEvent.changedTouches[0].clientX;
        self.initialY = e.originalEvent.changedTouches[0].clientY;
        self.touchtime = new Date().getTime();

        self.removeWrapperSlideTransition()();

      } else if (e.type == "touchmove") {

        diffX = e.originalEvent.changedTouches[0].clientX - self.initialX;
        diffY = e.originalEvent.changedTouches[0].clientY - self.initialY;

        // console.log([diffX,diffY]);

        if (Math.abs(diffX) > Math.abs(diffY)) {
          e.stopPropagation();
          e.preventDefault();
          self.$wrapper.css('left', self.leftPosition + diffX);
        }

      } else if (e.type == "touchend") {

        self.finalX = e.originalEvent.changedTouches[0].clientX;
        self.finalY = e.originalEvent.changedTouches[0].clientY;
        self.totaldiffX = self.finalX - self.initialX;
        self.totaldiffY = self.finalY - self.initialY;
        touchinterval = new Date().getTime() - self.touchtime;
        // console.log('totaldiffX: ' + self.totaldiffX + ' totaldiffY: ' + self.totaldiffY);
        // console.log('touchinterval: ' + touchinterval);

        // TODO: DRY left<>right sliding

        if (self.totaldiffX > $activeslide.innerWidth()/2 || self.totaldiffX > 0 && touchinterval < 350) { // replace interval by velocity for long fast swipes?

          self.removeEventHandlers();
          $activeslide.prev().addClass('active');
          $activeslide.removeClass('active').addClass('disengage');
          if (self.touchdevice || self.options.paging) {
            self.$pagerdots.removeClass('active');
            $activedot.is(':first-child') ? self.$pagerdots.eq(-1).addClass('active') : $activedot.prev().addClass('active');
          }
          if (self.options.animationType == 'css') { // for touch, css transitions are only active for actual scrolling
            self.applyWrapperSlideTransition()();
          }
          self.transitionWrapper('prev', -1, self.totaldiffX, 'easeOutSkidder');
          if (self.options.lazyLoad && self.options.lazyLoadAutoInit ) {
            self.lazyLoadSlides();
          }
          if ( $.isFunction( self.options.afterSliding ) ) {
           self.options.afterSliding.call(this);
          }

        } else if (self.totaldiffX < -($activeslide.innerWidth()/2) || self.totaldiffX < 0 && touchinterval < 350) { // replace interval by velocity for long fast swipes?

          self.removeEventHandlers();
          $activeslide.next().addClass('active');
          $activeslide.removeClass('active').addClass('disengage');
          if (self.touchdevice || self.options.paging) {
            self.$pagerdots.removeClass('active');
            $activedot.is(':last-child') ? self.$pagerdots.eq(0).addClass('active') : $activedot.next().addClass('active');
          }
          if (self.options.animationType == 'css') { // for touch, css transitions are only active for actual scrolling
            self.applyWrapperSlideTransition()();
          }
          self.transitionWrapper('next', 1, self.totaldiffX, 'easeOutSkidder');
          if (self.options.lazyLoad && self.options.lazyLoadAutoInit ) {
            self.lazyLoadSlides();
          }
          if ( $.isFunction( self.options.afterSliding ) ) {
           self.options.afterSliding.call(this);
          }

        } else if (self.totaldiffX < 5 && self.totaldiffY < 5 && $activeslide.attr('data-href')) { // it's a click!
          self.$wrapper.css({
            'left': self.leftPosition
          });
          window.clearTimeout(self.autoplaying);
          window.location.href = $activeslide.attr('data-href');

        } else { // return to original position
          if (self.options.animationType == 'animate') {
            self.$wrapper.animate({
              'left': self.leftPosition
            }, self.options.speed, self.removeWrapperSlideTransition() );

          } else if (self.options.animationType == 'css'){
            self.applyWrapperSlideTransition()();
            self.$wrapper.on("transitionend", function(e) { if ($(e.target).is(self.$wrapper)) { self.removeWrapperSlideTransition() } } );
            self.$wrapper.css({ 'left': self.leftPosition});
          }

          if (self.options.autoplay && self.options.autoplayResume) {
            self.autoplaying = self.autoplay();
          }

        }
      }
    },

    clickHandlerLeftRight: function(e) {

      var self = this;
      var direction = $(e.target).closest('[data-direction]').addBack().attr('data-direction');
      var $fromSlide = self.$slides.filter('.active');
      var $toSlide;
      var jumpsize;

      if (direction == 'next') {
        if (((self.options.jumpback) || self.options.transition == "fade") && self.$slides.eq(-1).hasClass('active')) { // last slide
          $toSlide = self.$slides.eq(0);
          jumpsize = -self.$slides.index($fromSlide);
        } else {
          $toSlide = $fromSlide.next();
          jumpsize = 1;
        }
      } else {
        if (self.options.transition == "fade" && self.$slides.eq(0).hasClass('active')) {
          $toSlide = self.$slides.eq(-1); // first slide
          jumpsize = self.$slides.length -1;
        }  else {
          $toSlide = $fromSlide.prev();
          jumpsize = -1;
        }
      }

      $toSlide.addClass('active');
      $fromSlide.addClass('disengage').removeClass('active');

      self.removeEventHandlers();
      self.transitionWrapper(direction, jumpsize);

      //update paging
      if ((self.touchdevice || self.options.paging) && self.$slides.length > 1) {
        var $activedot = self.$pagerdots.filter('.active');
        self.$pagerdots.removeClass('active');
        if (direction == 'next') {
          $activedot.is(':last-child') ? self.$pagerdots.eq(0).addClass('active') : $activedot.next().addClass('active');
        } else if (direction == 'prev') {
          $activedot.is(':first-child') ? self.$pagerdots.eq(-1).addClass('active') : $activedot.prev().addClass('active');
        }
      }

      if (self.options.lazyLoad && self.options.lazyLoadAutoInit ) {
        self.lazyLoadSlides();
      }

      // direction is not updateted for edge cases, because action functions don't use it

      if ( $.isFunction( self.options.afterSliding ) ) {
        self.options.afterSliding.call(this);
      }
    },

    clickHandlerPaging: function(e) {
      var self = this;
      var activeindex = self.$pagerdots.index(self.$pagerdots.filter('.active'));
      var jumpindex = self.$pagerdots.index($(e.target));
      var jumpsize = jumpindex - activeindex;
      var direction = (activeindex > jumpindex ? 'prev' : 'next');
      var $fromSlide = self.$slides.filter('.active');
      var $toSlide = self.$slides.eq(self.$slides.index($fromSlide)+jumpsize);

      e.stopPropagation();
      e.preventDefault();

      // update paging
      self.$pagerdots.removeClass('active').eq(jumpindex).addClass('active');

      $fromSlide.addClass('disengage').removeClass('active');
      $toSlide.addClass('active').removeClass('disengage');

      self.removeEventHandlers();

      if (self.touchdevice && self.options.swiping) {
        self.applyWrapperSlideTransition()();
      }

      self.transitionWrapper(direction, jumpsize);

      if (self.options.lazyLoad && self.options.lazyLoadAutoInit ) {
        self.lazyLoadSlides();
      }

      if ( $.isFunction( self.options.afterSliding ) ) {
        self.options.afterSliding.call(this);
      }
    },

    autoplay: function() {
      var self = this;

      var $fromSlide = self.$slides.filter('.active');
      var $toSlide = ((self.options.leftaligned && self.options.jumpback) || self.options.transition == "fade") && self.$slides.eq(-1).hasClass('active') ?
          self.$slides.eq(0) : $fromSlide.next();

      return window.setTimeout(function(){

        self.removeEventHandlers();

        $fromSlide.addClass('disengage').removeClass('active');
        $toSlide.addClass('active').removeClass('disengage');

        //update paging
        if ((self.touchdevice || self.options.paging) && self.$slides.length > 1) {
          var $activedot = self.$pagerdots.filter('.active');
          self.$pagerdots.removeClass('active');
          $activedot.is(':last-child') ? self.$pagerdots.eq(0).addClass('active') : $activedot.next().addClass('active');

        }

        self.transitionWrapper('next', 1);

        if (self.options.lazyLoad && self.options.lazyLoadAutoInit ) {
          self.lazyLoadSlides();
        }

        if ( $.isFunction( self.options.afterSliding ) ) {
          self.options.afterSliding.call(this);
        }

      }, self.options.interval);

    },

    transitionWrapper: function(direction, jumpsize, dragoffset, easingfunction) {
      var self = this;
      if (self.options.transition == "slide" || ( self.options.swiping && self.touchdevice)) {
        self.scrollWrapper(direction, jumpsize, dragoffset, easingfunction);
      } else if (self.options.transition == "fade"){
        self.fadeWrapper(direction, jumpsize);
      }
    },

    scrollWrapper: function(direction, jumpsize, dragoffset, easingfunction) {

      var self = this;
      var touchoffset = dragoffset || 0;
      var easing = easingfunction || 'swing';

      window.clearTimeout(self.autoplaying);

      if (jumpsize != 0) {
        // rewrite with slideindex? -> could jump to 0 on resize
        // console.log(direction + ' ' + jumpsize);
        var xoffset = 0;
        var $disengagingSlide = self.$slides.filter('.disengage');

        if (self.options.leftaligned && self.options.jumpback && self.$slides.eq(0).hasClass('active') && self.$slides.eq(-1).hasClass('disengage')) {
          for (var x=0; x<self.$slides.length-1; x++) {
            xoffset -= self.$slides.eq(x).innerWidth()*-1;
          }
        } else if (self.options.leftaligned) {
          xoffset = (jumpsize > 0 ? (-$disengagingSlide.innerWidth() * jumpsize) : (-self.$slides.filter('.active').innerWidth() * jumpsize));

        } else { //centered
          for (j = 0; j <= Math.abs(jumpsize); j++) {
            // get total offset by iterating through slides between disengaging and active slides
            // add only half disengaging and active slides' width
            xoffset += (self.$slides.eq(self.$slides.index($disengagingSlide) + j * (jumpsize > 0  ? 1 : -1 )).innerWidth()/(j==0 || j==Math.abs(jumpsize) ? 2 : 1) * (jumpsize > 0  ? -1 : 1 ));
          }
        }
      } else { // jumpsize == 0
        xoffset = 0;
      }

      self.leftPosition = self.$wrapper.position().left + xoffset - touchoffset;

      // move slide and callback

      var callbackfunction = function(e) {

        // turn off transition before reordering
        if (self.options.animationType == 'css') {
          self.$wrapper.off();
          self.removeWrapperSlideTransition()();
        }

        function reorderDOM(timestamp) {
          // reapply click handlers
          var $slidestomove = $();
          window.requestAnimationFrame(self.addEventHandlers());
          self.refreshSlides();
          self.refreshImages();

          self.$slides.removeClass('disengage');

          // reorder slides
          if (jumpsize > 0 && self.options.cycle) {
            for (x = 0; x < jumpsize; x++) {
              self.leftPosition += self.$slides.eq(x).innerWidth();
              $slidestomove = $slidestomove.add(self.$slides.eq(x));
            }
            $slidestomove.appendTo(self.$wrapper);
            self.$wrapper.css('left', self.leftPosition );
            
          } else if (jumpsize < 0 && self.options.cycle) {
            for (x = -1; x >= jumpsize; x--) {
              self.leftPosition -= self.$slides.eq(x).innerWidth();
              $slidestomove = $slidestomove.add(self.$slides.eq(x));
            }
            $slidestomove.prependTo(self.$wrapper);
            self.$wrapper.css('left', self.leftPosition );
          }

          // handle jumpback option
          if (self.options.jumpback) {
            if (self.$slides.eq(-1).hasClass('active')) {
              self.$clickwrappers.find('.skidder-next').addClass('jumpback');
            } else {
              self.$clickwrappers.find('.skidder-next').removeClass('jumpback');
            }
          }

          if (self.options.autoplay && self.options.autoplayResume) {
            self.autoplaying = self.autoplay();
          }

          if (self.options.directionClasses) {
            self.addLeftAndRightClasses();
          };

          if (self.options.animationType == 'css') { // for touch, css transitions are only active for actual scrolling
            window.requestAnimationFrame(self.applyWrapperSlideTransition());
          } 
        }

        window.requestAnimationFrame(reorderDOM);
      }

      // actual scrolling

      if (self.options.animationType == 'animate') {
        self.$wrapper.animate({
          'left': self.leftPosition
        }, self.options.speed, easing, callbackfunction);

      } else if (self.options.animationType == 'css') {
        self.$wrapper.on("transitionend", function(e) { if ($(e.target).is(self.$wrapper)) { callbackfunction(e) } } ); // has to be on instead one, bacause multiple (bubbling) transition events are fired, and we don't know which is first one
        self.$wrapper.css('left', self.leftPosition);
      }

    },

    fadeWrapper: function(direction, jumpsize) {
      var self = this;

      window.clearTimeout(self.autoplaying);

      self.addEventHandlers()();

      // handle jumpback option - TODO: test with fade
      if (self.options.jumpback) {
        if (self.$slides.eq(-1).hasClass('active')) {
          self.$clickwrappers.find('.skidder-next').addClass('jumpback');
          } else {
            self.$clickwrappers.find('.skidder-next').removeClass('jumpback');
          }
        }

      if (self.options.autoplay && self.options.autoplayResume) {
        self.autoplaying = self.autoplay();
      }

      self.$slides.removeClass('disengage');
      if (self.options.directionClasses) {
        self.addLeftAndRightClasses();
      };
    },

    centerPosition: function() {
      // console.log('[centerPosition]');
      var self = this;

      if (self.options.leftaligned) {
        // self.$wrapper.css('margin-left', (self.$viewport.innerWidth() - 940)/2 -35); // TODO!!!!!!
        self.$wrapper.css('margin-left', Math.max(0, self.$viewport.innerWidth() - self.options.maxWidth)/2);
      } else {
        if (self.options.lazyLoad) { // dirty fix for Firefox, which responds with crazy values to initial innerWidth polling
          window.setTimeout(function() {
            var activeslidewidth = self.$slides.filter('.active').innerWidth() > 0 ? self.$slides.filter('.active').innerWidth() : self.$viewport.innerWidth(); // lazyload doesn't know innerWidth while loading!
            var leftmargin = (self.$viewport.innerWidth() - activeslidewidth)/2;
            self.$wrapper.css({
              'margin-left': leftmargin
            });
          }, 100);
        } else {
          var activeslidewidth = self.$slides.filter('.active').innerWidth() > 0 ? self.$slides.filter('.active').innerWidth() : self.$viewport.innerWidth(); // lazyload doesn't know innerWidth while loading!
          var leftmargin = (self.$viewport.innerWidth() - activeslidewidth)/2;
          self.$wrapper.css({
            'margin-left': leftmargin
          });
        }
      }
    },

    setSlideshowHeight: function(newMaxHeight) {
      var self = this;
      // note: skidder-clickelements don't need to be sized if it weren't for #%@&% IE8
      self.$elem.add(self.$viewport).add(self.$wrapper).add(self.$slides).add(self.$viewport.find('.skidder-clickelement')).css('height', newMaxHeight);
    },

    refreshSlides: function() {
      var self = this;
      self.$slides = self.$wrapper.find('.skidder-slide');
    },

    refreshImages: function() {
      var self = this;
      self.$images = $();

      self.$slides.each(function() {
        // if  slide has image, add image, else add slide
        if ($(this).has('img').length > 0) {
          self.$images = self.$images.add($(this).find('img').eq(0)); // perhaps exempt noScaleClass here 
        } else {
          self.$images = self.$images.add($(this));
        }
      });
    },

    resize: function() {
      var self = $(this).data('skidder');

      if (self && self.options ) { // make sure skidder has been attached for ie8, who fires resize on page load
        if (self.$images && self.$images.length && self.options.scaleSlides) {
          self.scaleSlides();
        }
        self.centerPosition();
        if ( $.isFunction( self.options.afterResize ) ) {
          self.options.afterResize.call(this);
        }
      }
    },
    stopAutoplay: function() {
      var self = $(this).data('skidder');

      if (self && self.options ) { // make sure skidder has been attached for ie8, who fires resize on page load
        window.clearTimeout(self.autoplaying);
      }
    },
    addLeftAndRightClasses: function() {

      var self = this;

      self.$slides.removeClass('left-from-active right-from-active');
      self.refreshSlides();

      var activeindex = self.$slides.index(self.$slides.filter('.active').eq(0));

      self.$slides.each(function(i, elem) {
        if (self.$slides.index($(elem)) < activeindex ) {
          $(elem).addClass('left-from-active');
        } else if (self.$slides.index($(elem)) > activeindex ) {
          $(elem).addClass('right-from-active');
        }
      });
    },
    supportsTransitions: function() {
      var s = document.createElement('p').style;
      var supportsTransitions = 'transition' in s || 'WebkitTransition' in s ;
      return supportsTransitions; 
    }
  }

  $.fn.skidder = function(options) {
    var method = arguments[0];

    if(Skidder[method]) {
      method = Skidder[method];
      arguments = Array.prototype.slice.call(arguments, 1);
      return this.each(function(){
        method.apply(this, arguments);
      });
    } else if( typeof(method) == 'object' || !method ) {
      return this.each(function() {
        var skidder = Object.create(Skidder);
        skidder.init(options, this);
      });
    }
  };

  $.fn.skidder.options = {
    slideClass      : ".slide",
    imageClass      : "",
    animationType   : "css",
    lazyLoad        : false,
    lazyLoadAutoInit: true,
    lazyLoadWindow  : 1,
    scaleSlides     : true,
    maxWidth        : 800,
    maxHeight       : 500,
    scaleTo         : "smallest",  
    preservePortrait: false,
    spaceSlides     : false,
    noScaleClass    : ".skidder-no-scale",
    paging          : true,
    pagingWrapper   : ".skidder-pager",
    pagingElement   : ".skidder-pager-dot",
    swiping         : true,
    leftaligned     : false,
    cycle           : true,
    jumpback        : false,
    speed           : 400,
    autoplay        : false,
    autoplayResume  : false,
    interval        : 4000,
    transition      : "slide",
    directionClasses: false,
    afterSliding    : function() {},
    afterInit       : function() {},
    afterResize     : function(instance) {},
    afterLazyLoad   : function() {}
  };

  $.extend($.easing, {
    easeOutSkidder: function (x, t, b, c, d) {
      return -c *(t/=d)*(t-2) + b;
    },
  });


}(jQuery, window, document));
