/**
 * 
 */
function allcheck(){
	var oCheck = document.getElementsByName('check');
	for(var i=0;i<oCheck.length;i++){
		oCheck[i].checked=true;
	}
}
function delAllHou(){
	var allhid = new Array();
	var flag = false;
	var oCheck = document.getElementsByName('check');
	for(var i=0;i<oCheck.length;i++){
		if(oCheck[i].checked){
		allhid.push(oCheck[i].value);
		flag = true;
		}
	}
	if(flag){
		if(confirm("��ȷ��Ҫɾ����Щ��¼��")){
			location.href="updateHouse?s=delall&shaosen="+allhid;
		}
	}else{
		alert("������Ҫѡ��һ����¼�����ܽ�������ɾ��!");
	}
}