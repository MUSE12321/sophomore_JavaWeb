
function check(){
	if (form.hid.value=="")
	{
	alert("房子编号不能为空！")
	form.hid.focus();
	return false;
	}
	if (form.fid.value=="")
		{
		alert("房东编号不能为空！")
		form.fid.focus();
		return false;
		}
	if (form.username.value==""){
	    alert("房东姓名不能为空！")
	    form.username.focus();
	    return false;
	}
	if(form.dz.value=="")
	{
	alert("地址不能为空！")
	form.dz.focus();
	return false;
	}
	if(form.pri.value=="")
	{
	alert("租金不能为空！")
	form.pri.focus();
	return false;
	}
	if(form.fpn.value=="")
	{
	alert("请输入房东的联系方式！")
	form.fpn.focus();
	return false;
	}
	var regm = /^[0-9]+$/;
	if (form.fpn.value !="" && !form.fpn.value.match(regm)){
		alert("联系方式格式不对，只能由数字组成,检查后重新输入！");
		form.fpn.focus();
		return false;
	}
	
}

function createXmlHttpRequest(){
    var xmlreq = false;
    if (window.XMLHttpRequest){
        xmlreq = new XMLHttpRequest();
    }else
        if (window.ActiveXObject){
    	try{
    	    xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
    	}catch(e1){
    	    try{
    	        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
    	    }catch(e2){
    	}
         }
    }
    return xmlreq;   	
}

function userNameCheck(){
    var request = createXmlHttpRequest();
    var hid = document.all.hid.value;
    request.open("post","nameCheckServlet?hid="+hid);
    request.send();
    request.onreadystatechange = function(){
	if(request.readyState==4){
	        var value = request.responseText;
	        if(value=="true"){
	                document.all.hid.value="该房子编号已存在";
	        }
    	}
    }
}

