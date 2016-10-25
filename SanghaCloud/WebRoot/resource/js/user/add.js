
$("#type").change(function () {
	
	var type = $("#type").val();
	$("#roleId").empty();
	if(type != 0){
		// 初始化基本组件
		initBaseView();
		if(type == 1){
			// 如果选择的是用户
			$("#jobTitleDiv").hide("slow");
			$("#introduceDiv").hide("slow");
			
			$("#isPUserDiv").show("slow");
			$("#roleDiv").show("slow");
			$("#companyDiv").show("slow");
			$("#officeDiv").show("slow");
			$("#tvDiv").show("slow");
			$("#saleDiv").show("slow");
			// 验证输入选项
			//verifyUser();
		}else if(type == 2){
			// 总公司员工
			$("#companyDiv").hide("slow");
			$("#officeDiv").hide("slow");
			$("#tvDiv").hide("slow");
			$("#saleDiv").hide("slow");
			$("#isPUserDiv").hide("slow");
			
			$("#roleDiv").show("slow");
			$("#jobTitleDiv").show("slow");
			$("#introduceDiv").show("slow");
		}else if(type == 3){
			// 分公司员工
			$("#roleDiv").show("slow");
			$("#jobTitleDiv").show("slow");
			$("#introduceDiv").show("slow");
			$("#companyDiv").show("slow");
			$("#isPUserDiv").hide("slow");
			
			$("#officeDiv").hide("slow");
			$("#tvDiv").hide("slow");
			$("#saleDiv").hide("slow");
		}else if(type == 4){
			$("#jobTitleDiv").show("slow");
			$("#introduceDiv").show("slow");
			$("#companyDiv").show("slow");
			$("#officeDiv").show("slow");
			
			$("#roleDiv").show("slow");
			$("#isPUserDiv").hide("slow");
			$("#tvDiv").hide("slow");
			$("#saleDiv").hide("slow");
		}
	}
	if(type == 0){
		hideAllView();
	}
	
});

function initBaseView() {
	$("#mobileDiv").show("slow");
	$("#nameDiv").show("slow");
	$("#pwdDiv").show("slow");
	$("#emailDiv").show("slow");
	$("#qqDiv").show("slow");
	$('#confirmBtn').attr("disabled",false);
}
function hideAllView() {
	$("#mobileDiv").hide("slow");
	$("#nameDiv").hide("slow");
	$("#pwdDiv").hide("slow");
	$("#qqDiv").hide("slow");
	$("#emailDiv").hide("slow");
	$('#confirmBtn').attr("disabled",true);
	$("#roleDiv").hide("slow");
	$("#companyDiv").hide("slow");
	$("#officeDiv").hide("slow");
	$("#tvDiv").hide("slow");
	$("#saleDiv").hide("slow");
	$("#jobTitleDiv").hide("slow");
	$("#introduceDiv").hide("slow");
}

// 基本的表单验证
function verifyBase() {
	var mobile = $("#mobile").val();
	var realName = $("#realName").val();
	var pwd = $("#pwd").val();
	var email = $("#email").val();
	var roleId = $("#roleId").val();
	var mobileRex = /^1[345678]\d{9}$/;
	if(!mobileRex.test(mobile)){
		Notify("手机号码不合法", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	var nameCNRex = /^[\u4e00-\u9fa5 ]{2,5}$/;
	if(!nameCNRex.test(realName) ){
		Notify("无效的真实姓名", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	if(pwd.length < 6){
		Notify("密码必须6位以上", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	var emailRex= /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,4}){1,2})$/;
	if(!emailRex.test(email)){
		Notify("邮箱不合法", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	if(roleId == 0 || roleId == null){
		Notify("无效的角色", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	return true;
}

// 用户验证·
function verifyUser() {
	
	if(!verifyBase()){
		return false;
	}
	var tvId = $("#tvId").val();
	
	if(tvId == 0  ){
		Notify("无效的电视台", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	var isPUser = $("#isPUser").val();
	if(isPUser == 3){
		Notify("没有选择产品用户", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	return true;
}

// 验证总公司
function verifyHeadCompany(){
	if(!verifyBase()){
		return false;
	}
	return true;
}
// 验证分公司
function verifyCompany() {
	if(!verifyBase()){
		return false;
	}
	var companyId = $("#companyId").val();
	if(companyId == 0){
		Notify("无效的分公司", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	return true;
}
// 验证办事处
function verifyOffice() {
	if(!verifyBase()){
		return false;
	}
	var officeId = $("#officeId").val();
	if(officeId == 0){
		Notify("无效的办事处", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	return true;
}

$("#sale").focus(function(){
	var officeId = $("#officeId").val();
	if(officeId == 0){
		Notify("无效的办事处", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/user/getSales", // URL
		data : {officeId:officeId},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			if(respObj.code == 200){
				buildSaleSelect(respObj.data);
			}else if(respObj.code == 1006){
				buildNoSaleData();
				Notify("没有数据", 'top-right', '5000', 'info', 'fa-desktop', true);
			}else{
				Notify("操作失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
			}
		}
	});
})

function buildSaleSelect(dataList){
	var str = "";
	for(var i=0; i < dataList.length; i++){
		var obj = dataList[i];
		str +="<option value="+obj.id+">"+obj.realName+"</option>";
	}
	$("#sale").html(str);
}
function buildNoSaleData(){
	var str = "<option value=0>"+没有数据+"</option>";
	$("#sale").html(str);
}

// 提交注册信息

function submitInfo() {
	var type = $("#type").val();
	if(type == 0){
		Notify("没有选择注册类型", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	if(type == 1){
		if(verifyUser()){
			return registerUser();
		}else{
			return false;
		}
	}else if(type == 2){
		var isOK = verifyHeadCompany();
		
		if(isOK){
			return registerUser();
		}else{
			return false;
		}
	}else if(type == 3){
		if(verifyCompany()){
			return registerUser();
		}else{
			return false;
		}
	}else if(type == 4){
		if(verifyOffice()){
			
			return registerUser();
		}else{
			return false;
		}
	}
	return false;
}

// 用户注册
function registerUser() {
	var isSuccess = false;
	var type = $("#type").val();
	var mobile = $("#mobile").val();
	var realName = $("#realName").val();
	var pwd = $.md5($("#pwd").val());
	var email = $("#email").val();
	var roleId = $("#roleId").val();
	var qq = $("#qq").val();
	var isExit = verifyMobileAndEmail(mobile,email);
	
	if(isExit){
		return false;
	}
	
	var tvId = $("#tvId").val();
	var saleId = $("#sale").val();
	
	var companyId = $("#companyId").val();
	var jobTitle = $("#jobTitle").val();
	var introduce = $("#introduce").val();
	var officeId = $("#officeId").val();
	var isPUser = $("#isPUser").val();
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/user/addUser", // URL
		data : {isPUser:isPUser,type:type,mobile:mobile,realName:realName,pwd:pwd,email:email,roleId:roleId,tvId:tvId,saleId:saleId,companyId:companyId,jobTitle:jobTitle,introduce:introduce,officeId:officeId,qq:qq},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			if(respObj.code == 200){
				isSuccess = true;
				Notify("操作成功", 'top-right', '5000', 'info', 'fa-desktop', true);
			}else{
				Notify("操作失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
			}
		}
	});
	return isSuccess;
}

function verifyMobileAndEmail(mobile,email) {
	var isExit = false;
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/mobile/verifyMobileAndEmail", // URL
		data : {mobile:mobile,email:email},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			if(respObj.code == 200){
				isExit = false;
			}else{
				Notify(respObj.msg, 'top-right', '5000', 'danger', 'fa-desktop', true);
				isExit =  true;
			}
		}
	});
	return isExit;
}

// 获得角色选项
$("#roleId").focus(function () {
	var type = $("#type").val();
	var str = "";
	if(type == 0){
		Notify("选择注册类型", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return;
	}else if(type == 1){
		str += "<option value='4'>用户</option>";
	}else if(type == 2 || type == 3 || type == 4){
		var userType = null;
		if(type == 2){
			userType = 0;
		}else if(type  == 3){
			userType = 1;
		}else if(type == 4){
			userType = 2;
		}
		$.ajax({
			type : "GET",
			url : "/SanghaCloud/role/getrole",
			data:{userType:userType},
			dataType :"json",
			async : false,
			timeout : 5000,
			success : function(respObj){
				var dataList = respObj.data;
				for(var i=0; i<dataList.length; i++){
					var temp = dataList[i];
					str+= "<option value="+temp.id+">"+temp.roleName+"</option>";
				}
			}
		})
	}
	$("#roleId").empty();
	$("#roleId").html(str);
})

$("#companyId").focus(function () {
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/companyMobile/queryCompanyByType",
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				buildSelectData(respObj.data);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
})
function buildSelectData(dataList){
	
	var str = "";
	for(var i=0; i<dataList.length; i++){
		var temp = dataList[i];
		str+="<option value='"+temp.id+"'>"+temp.companyName+"</option>";
	}
	$("#companyId").html(str);
	$("#officeId").html("<option value='0'>选择办事处</option>");
	$("#tvId").html("<option value='0'>选择电视台</option>");
}

$("#officeId").focus(function () {
	var companyId = $("#companyId").val();
	if(companyId == 0){
		Notify('没有选择分公司','top-right','5000','danger','fa-desktop',true);
		return;
	}
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/officeMobile/getOfficeByCompany",
		data:{companyId:companyId},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				buildOfficeSelect(respObj.data);
			}else if(status == 1006){
				buildOfficeNoDataSelect();
				Notify("没有数据",'top-right','5000','info','fa-desktop',true);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
})
function buildOfficeNoDataSelect() {
	
		str = "<option value='0'>没有数据</option>";
		$("#officeId").html(str);
		$("#tvId").html("<option value='0'>选择电视台</option>");
}

function buildOfficeSelect(dataList){
	var str = "";
	var len = dataList.length;
	if(len != 0){
		for(var i=0; i < len; i++){
			var obj = dataList[i];
			str += "<option value="+obj.id+">"+obj.officeName+"</option>";
		}
	}else{
		str = "<option value='0'>没有数据</option>";
	}
	$("#officeId").html(str);
	$("#tvId").html("<option value='0'>选择电视台</option>");
}

$("#tvId").focus(function () {
	var officeId = $("#officeId").val();
	if(officeId == 0){
		Notify('没有选择办事处','top-right','5000','danger','fa-desktop',true);
		return;
	}
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/tv/getTVByOffice",
		data:{officeId:officeId},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				buildTVSelect(respObj.data);
			}else if(status == 1006){
				buildTVNoDataSelect();
				Notify("没有数据",'top-right','5000','info','fa-desktop',true);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
})
function buildTVNoDataSelect() {
	
		str = "<option value='0'>没有数据</option>";
		$("#tvId").html(str);
}


function buildTVSelect(dataList){
	var str = "";
	var len = dataList.length;
	if(len != 0){
		for(var i=0; i < len; i++){
			var obj = dataList[i];
			str += "<option value="+obj.id+">"+obj.tvName+"</option>";
		}
	}else{
		str = "<option value='0'>没有数据</option>";
	}
	$("#tvId").html(str);
	
}

$("#sale").change(function (){
	
	
	
});









