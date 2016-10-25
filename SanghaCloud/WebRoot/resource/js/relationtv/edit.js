/**
 * 
 */
var roleId = null;
var userId = null;
function initData(tempUserId,tempRoleId){
	roleId = tempRoleId;
	userId = tempUserId;
}




function submitCon(){
	var isOK = false;
	var relationTV = $("#relationTV").val();
	if(relationTV == 0 || relationTV == null){
		Notify("没有选择电视台",'top-right','5000','danger','fa-desktop',true);
		return isOK;
	}
//	console.debug(relationTV);
//	return isOK;
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/relation/updateRelationTV",
		data:$("#formId").serialize(),
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(json){
			if(json.code == 200){
				isOK = true;
			}else{
				Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return isOK;
}
