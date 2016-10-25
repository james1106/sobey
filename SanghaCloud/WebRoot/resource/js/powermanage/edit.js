/**
 * 
 */

$("#type").change(function(){
	var roleId = $("#type").val();
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/rolePower/getPower",
		data:{roleId:roleId},
		dataType : "json",
		async : false,
		timeout : 5000,
		success : function(json) {
			if(json.code == 200){
				var dataList = json.data;
				buildTD(dataList,roleId);
			}else{
				Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	})
});
function buildTD(dataList,roleId){
//	console.debug(dataList);
	var str = "";
	var len = dataList.length;
	if(len == 0){
		str = "没有数据";
	}else{
		
		for(var i = 0 ; i < len ; i++){
			var obj = dataList[i];
			var checked = "";
			var disable=" ";
			if(roleId == 1 && obj.id==18){
				disable=" disabled='disabled'";
			}
			if(obj.roleId == roleId){
				checked = "checked='checked'";
			}
			str += "<lable class='checkbox-inline'><input type='checkbox' name='powerId' value="+obj.id+"  "+checked+disable+">"+obj.description+"</lable>";
		}
	}
	$("#dataTD").html(str);
}

function submitInfo(){
	var isOK = false;
	var roleId = $("#type").val();
//	console.debug($("#powerFrom").serialize());
//	return false;
	if(roleId == 0){
		Notify("没有选择角色",'top-right','5000','danger','fa-desktop',true);
		return isOK;
	}
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/rolePower/updateRolePower",
		data:$("#powerFrom").serialize(),
		dataType : "json",
		async : false,
		timeout : 5000,
		success : function(json) {
			if(json.code == 200){
				isOK = true;
			}else{
				Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	})
	return isOK;
}












