/**
 * 
 */
function allcheck(){
	var oCheck = document.getElementsByName('check');
	for(var i=0;i<oCheck.length;i++){
		oCheck[i].checked=true;
	}
}
function delAllCus(){
	var allcid = new Array();
	var flag = false;
	var oCheck = document.getElementsByName('check');
	for(var i=0;i<oCheck.length;i++){
		if(oCheck[i].checked){
		allcid.push(oCheck[i].value);
		flag = true;
		}
	}
//	alert(allfid);
	if(flag){
		if(confirm('��ȷ��Ҫɾ����Щ��¼��')){
			location.href="updatecus?s=delall&allcid="+allcid;
		}
	}else{
		alert('������Ҫѡ��һ����¼�����ܽ�������ɾ��!');
	}
}