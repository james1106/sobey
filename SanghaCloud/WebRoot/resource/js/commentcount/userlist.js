$("#companyUserSelect").change(
		function() {

			var companyId = $("#companyUserSelect").val();
			if (companyId == 0) {
				Notify("没有选择分公司", 'top-right', '5000', 'danger', 'fa-desktop',
						true);
				return;
			}
			$.ajax({
				type : "POST",
				url : "/SanghaCloud/officeMobile/getOfficeByCompany",
				data : {
					companyId : companyId
				},
				dataType : "json",
				async : false,
				timeout : 5000,
				success : function(json) {
					if (json.code == 200) {
						buildUserOfficeData(json.data);
					} else {
						Notify(json.msg, 'top-right', '5000', 'danger', 'fa-desktop', true);
					}
				}
			});
		})
function buildUserOfficeData(dataList) {
	var str = "";
	var len = dataList.length;
	if (len == 0) {
		str = "<option value='0'>没有数据</option>";
	} else {
		str = "<option value='0'>无</option>";
		for (var i = 0; i < len; i++) {
			var obj = dataList[i];
			str += "<option value=" + obj.id + ">" + obj.officeName
					+ "</option>";
		}
	}
	$("#officeUserSelect").empty();
	$("#officeUserSelect").html(str);
	$("#tvSelect").html("<option value=''>请选择</option>");
	
}

$("#officeUserSelect").change(function(){
	 var officeId = $("#officeUserSelect").val();
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/tv/getTVByOffice",
		data:{officeId:officeId},
		dataType : "json",
		timeout : 5000,
		async : false,
		success : function(json) {
			if (json.code == 200) {
				buildTVData(json.data);
			}else{
				Notify("数据异常", 'top-right', '5000', 'danger', 'fa-desktop', true);
			}
		}
	});
})

function buildTVData(dataList){
	var str = "";
	var len = dataList.length;
	if (len == 0) {
		str = "<option value='0'>没有数据</option>";
	} else {
		str = "<option value='0'>无</option>";
		for (var i = 0; i < len; i++) {
			var obj = dataList[i];
			str += "<option value=" + obj.id + ">" + obj.tvName
					+ "</option>";
		}
	}
	$("#tvSelect").empty();
	$("#tvSelect").html(str);
	$("#userUserSelect").html("<option value=''>请选择</option>");
}

$("#tvSelect").change(function(){
	
	var tvId = $("#tvSelect").val();
	if(tvId == 0){
		Notify("没有选择电视台", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return;
	}
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/user/getUserByTvId",
		data:{tvId:tvId},
		dataType : "json",
		timeout : 5000,
		async : false,
		success : function(json) {
			if (json.code == 200) {
				buildUserUserData(json.data);
			}else{
				Notify("数据异常", 'top-right', '5000', 'danger', 'fa-desktop', true);
			}
		}
	});
})

function buildUserUserData(dataList){
	var str = "";
	var len = dataList.length;
	if( len== 0){
		str = "<option value=''>没有数据</option>";
	}else{
		str = "<option value=''>无</option>";
		for (var i = 0; i < len; i++) {
			var obj = dataList[i];
			 str += "<option value="+obj.id+">"+obj.realName+"("+obj.mobile+")</option>";
		}
	}
	$("#userUserSelect").empty();
	$("#userUserSelect").html(str);
}

$("#searchByUser").click(function(){
	
	var userId = $("#userUserSelect").val();
	if(userId == 0){
		Notify("没有选择用户", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return;
	}
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/commentCount/countCommentByUser",
		data:{userId:userId},
		dataType : "json",
		async : false,
		timeout : 5000,
		success : function(json) {
			if (json.code == 200) {
				console.debug("统计："+json);
				buildUserCommentData(json.data);
			}
		}
	});
})

function buildUserCommentData(data){
	console.debug(data);
	var userName = $("#userUserSelect").find('option:selected').text();
	var unit = "-";
	if(data.userId == null){
		data.userId = 0;
	}
	var str = "<tr><td>"+userName+"</td><td>"+unit+"</td><td>"+data.disposeSpeedCount+"</td><td>"+data.serviceAttitudeCount+"</td><td><a href='/SanghaCloud/admin/requestpage/commentcount/userDetail?userId="+data.userId+"&userName="+userName+"' class='btn btn-info' >详情</a></td></tr>";
	$("#userDataBody").html(str);
}
