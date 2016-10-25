/**
 * 
 */

$("#qShoppingTemplet").click(function(){
	var companyId = $("#companySelect").val();
	var officeId = $("#officeSelect").val();
	var userNameAndRoleAndId = $("#userSelect").val();
	/*if(companyId == 0 && officeId == 0 && userNameAndRoleAndId.length == 0){
		Notify("没有选择条件", 'top-right', '5000', 'danger', 'fa-desktop',true);
		return;
	}*/
//	console.debug(userNameAndRoleAndId)
	if(userNameAndRoleAndId ==  null || userNameAndRoleAndId.length == 0){
		Notify("没有选择员工", 'top-right', '5000', 'danger', 'fa-desktop',true);
		return;
	}
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/commentCount/getCommentCount",
		data:{companyId:companyId,officeId:officeId,userNameAndRoleAndId:userNameAndRoleAndId},
		dataType : "json",
		async : false,
		timeout : 5000,
		success : function(json) {
			if (json.code == 200) {
				buildCommentData(json.data);
			}
		}
	});
});

function buildCommentData(data){
	var companyName = "-";
	var userName = "-";
	var companyId = $("#companySelect").val();
	var officeId = $("#officeSelect").val();
	var user = $("#userSelect").val();
	var reqData = null;
	var name = null;
	if( user.length != 0){
		userName = $("#userSelect").find('option:selected').text();
		name = userName;
		reqData = "type=2&data="+user;
	}
	if( officeId != 0 && user.length == 0){
		companyName = $("#officeSelect").find('option:selected').text();
		name = companyName;
		reqData = "type=1&data="+officeId;
	}
	if(companyId != 0 && officeId == 0 && user.length == 0){
		companyName = $("#companySelect").find('option:selected').text();
		name = companyName;
		reqData = "type=0&data="+companyId;
	}
	
	
	var str = "<tr><td>"+userName+"</td><td>"+companyName+"</td><td>"+data.disposeSpeedCount+"</td><td>"+data.serviceAttitudeCount+"</td><td><a href='/SanghaCloud/admin/requestpage/commentcount/detail?"+reqData+"&name="+name+"' class='btn btn-info' >详情</a></td></tr>";
	$("#dataBody").html(str);
}

function buildCompanyData(dataList){
	var str = "";
	var len = dataList.length;
	if( len== 0){
		str = "<option value='0'>没有数据</option>";
	}else{
		str = "<option value='0'>无</option>";
		for (var i = 0; i < len; i++) {
			var obj = dataList[i];
			 str += "<option value="+obj.id+">"+obj.companyName+"</option>";
		}
	}
	$("#companySelect").empty();
	$("#companySelect").html(str);
	$("#officeSelect").empty();
	$("#officeSelect").html("<option value='0'>请选择</option>");
	$("#userSelect").empty();
	$("#userSelect").html("<option value=''>请选择</option>");
}

function buildOfficeData(dataList){
	var str = "";
	var len = dataList.length;
	if( len== 0){
		str = "<option value='0'>没有数据</option>";
	}else{
		str = "<option value='0'>无</option>";
		for (var i = 0; i < len; i++) {
			var obj = dataList[i];
			 str += "<option value="+obj.id+">"+obj.officeName+"</option>";
		}
	}
	$("#officeSelect").empty();
	$("#officeSelect").html(str);
	$("#userSelect").empty();
	$("#userSelect").html("<option value=''>请选择</option>");
}

function buildUserData(dataList){
	var str = "";
	var len = dataList.length;
	if( len== 0){
		str = "<option value=''>没有数据</option>";
	}else{
		str = "<option value=''>无</option>";
		for (var i = 0; i < len; i++) {
			var obj = dataList[i];
			var temlVal = obj.roleId+"|"+obj.id+"|"+obj.realName+"|"+obj.mobile;
			 str += "<option value="+temlVal+">"+obj.realName+"("+obj.mobile+")</option>";
		}
	}
	$("#userSelect").empty();
	$("#userSelect").html(str);
}




















