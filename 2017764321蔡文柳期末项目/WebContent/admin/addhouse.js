
function check(){
	if (form.hid.value=="")
	{
	alert("���ӱ�Ų���Ϊ�գ�")
	form.hid.focus();
	return false;
	}
	if (form.fid.value=="")
		{
		alert("������Ų���Ϊ�գ�")
		form.fid.focus();
		return false;
		}
	if (form.username.value==""){
	    alert("������������Ϊ�գ�")
	    form.username.focus();
	    return false;
	}
	if(form.dz.value=="")
	{
	alert("��ַ����Ϊ�գ�")
	form.dz.focus();
	return false;
	}
	if(form.pri.value=="")
	{
	alert("�����Ϊ�գ�")
	form.pri.focus();
	return false;
	}
	if(form.fpn.value=="")
	{
	alert("�����뷿������ϵ��ʽ��")
	form.fpn.focus();
	return false;
	}
	var regm = /^[0-9]+$/;
	if (form.fpn.value !="" && !form.fpn.value.match(regm)){
		alert("��ϵ��ʽ��ʽ���ԣ�ֻ�����������,�����������룡");
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
	                document.all.hid.value="�÷��ӱ���Ѵ���";
	        }
    	}
    }
}

