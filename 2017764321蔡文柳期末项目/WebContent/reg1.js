
function check(){
	if (form.cid.value=="")
		{
		alert("�û���Ų���Ϊ�գ�")
		form.cid.focus();
		return false;
		}
	if (form.username.value==""){
	    alert("�û�������Ϊ�գ�")
	    form.username.focus();
	    return false;
	}
	var regm = /^[a-z0-9]+$/;
	if (form.username.value !="" && !form.username.value.match(regm)){
		alert("�û�����ʽ���ԣ��û���ֻ����Сд��ĸ���������,�����������룡");
		form.username.focus();
		return false;
	}
	if(form.password1.value=="")
	{
	alert("���������룡")
	form.password1.focus();
	return false;
	}
	if(form.password2.value=="")
	{
	alert("���ٴ��������룡")
	form.password2.focus();
	return false;
	}
	if(form.password1.value!=form.password2.value)
	{
	alert("������������벻һ�£�")
	form.password2.focus();
	return false;
	}
	if(form.cpn.value=="")
	{
	alert("������������ϵ��ʽ��")
	form.cpn.focus();
	return false;
	}
	var regm = /^[0-9]+$/;
	if (form.cpn.value !="" && !form.cpn.value.match(regm)){
		alert("��ϵ��ʽ��ʽ���ԣ�ֻ�����������,�����������룡");
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
	                document.all.username.value="���û����Ѵ���";
	        }
    	}
    }
}

