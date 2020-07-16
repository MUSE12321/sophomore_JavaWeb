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
		if(confirm("您确定要删除这些记录吗？")){
			location.href="updateFow?s=delall&shaosen="+allfid;
		}
	}else{
		alert("你至少要选择一条记录，才能进行批量删除!");
	}
}