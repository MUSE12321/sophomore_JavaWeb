/**
 * 
 */
function allcheck(){
	var oCheck = document.getElementsByName('check');
	for(var i=0;i<oCheck.length;i++){
		oCheck[i].checked=true;
	}
}
function delAllFow(){
	var allfid = new Array();
	var flag = false;
	var oCheck = document.getElementsByName('check');
	for(var i=0;i<oCheck.length;i++){
		if(oCheck[i].checked){
		allfid.push(oCheck[i].value);
		flag = true;
		}
	}
//	alert(allfid);
	if(flag){
		if(confirm("��ȷ��Ҫɾ����Щ��¼��")){
			location.href="updateFow?s=delall&shaosen="+allfid;
		}
	}else{
		alert("������Ҫѡ��һ����¼�����ܽ�������ɾ��!");
	}
}