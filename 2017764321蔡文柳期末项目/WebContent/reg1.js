
function check(){
	if (form.cid.value=="")
		{
		alert("用户编号不能为空！")
		form.cid.focus();
		return false;
		}
	if (form.username.value==""){
	    alert("用户名不能为空！")
	    form.username.focus();
	    return false;
	}
	var regm = /^[a-z0-9]+$/;
	if (form.username.value !="" && !form.username.value.match(regm)){
		alert("用户名格式不对，用户名只能由小写字母与数字组成,检查后重新输入！");
		form.username.focus();
		return false;
	}
	if(form.password1.value=="")
	{
	alert("请输入密码！")
	form.password1.focus();
	return false;
	}
	if(form.password2.value=="")
	{
	alert("请再次输入密码！")
	form.password2.focus();
	return false;
	}
	if(form.password1.value!=form.password2.value)
	{
	alert("两次输入的密码不一致！")
	form.password2.focus();
	return false;
	}
	if(form.cpn.value=="")
	{
	alert("请输入您的联系方式！")
	form.cpn.focus();
	return false;
	}
	var regm = /^[0-9]+$/;
	if (form.cpn.value !="" && !form.cpn.value.match(regm)){
		alert("联系方式格式不对，只能由数字组成,检查后重新输入！");
		form.cpn.focus();
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
    var username = document.all.username.value;
    request.open("post","nameCheckServlet?username="+username);
    request.send();
    request.onreadystatechange = function(){
	if(request.readyState==4){
	        var value = request.responseText;
	        if(value=="true"){
	                document.all.username.value="该用户名已存在";
	        }
    	}
    }
}

