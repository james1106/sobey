/**
 * 
 */

var object=null;
var tempType = null;
function initView(type,userId,user) {
	object = user;
	tempType = type;
	initBaseView();
	if(type == 1){
		// 用户
		initUserView();
		initBaseData(user);
		initUserData(user);
	}else{
		initGroupUserView(user);
		initBaseData(user);
		
	}
}

function initUserData(user){
	$("#isPUser").val(user.isPUser)
	if(user.sale == null){
		$("#sale").html("<option value='0'>没有数据<option>");
	}else{
		$("#sale").html("<option value="+user.sale.id+">"+user.sale.realName+"<option>");
	}
}



function initBaseView() {
	$("#mobileDiv").show("slow");
	$("#nameDiv").show("slow");
	$("#pwdDiv").show("slow");
	$("#emailDiv").show("slow");
	$("#qqDiv").show("slow");
	$("#roleDiv").show("slow");
	$("#isOfficeDiv").show("slow");
	$('#confirmBtn').attr("disabled",false);
	$("#unitDiv").show("slow");
	if(object.companyType != 0 && object.companyType !=1 && object.companyType != 2){
		$("#saleDiv").show("slow");
		
		 $("#jobTitleDiv").hide("slow");
		$("#introduceDiv").hide("slow");
	}else{
		$("#saleDiv").hide("slow");
		
		 $("#jobTitleDiv").show("slow");
		$("#introduceDiv").show("slow");
	}
}

function initUserView() {
	$("#isPUserDiv").show("slow");
}
function initGroupUserView(user) {
	$("#introduce").val(user.introduce);
	$("#jobTitle").val(user.jobTitle);
}

$("#isOfficeSelect").change(function () {
	var isEditOffice = $("#isOfficeSelect").val();
	if(isEditOffice == 1){
		
		if(null == object){
			return;
		}
		 if(object.companyType == 0){
			 // 总公司
			 
			 $("#jobTitleDiv").show("slow");
			$("#introduceDiv").show("slow");
			$("#saleDiv").hide("slow");
			$("#tvDiv").hide("slow");
		 }else if(object.companyType == 1){
			 // 分公司
			 $("#jobTitleDiv").show("slow");
			$("#introduceDiv").show("slow");
			$("#saleDiv").hide("slow");
			$("#tvDiv").hide("slow");
		 }else if(object.companyType == 2){
			 // 办事处
			 $("#jobTitleDiv").show("slow");
			$("#introduceDiv").show("slow");
			$("#saleDiv").hide("slow");
			$("#tvDiv").hide("slow");
		 }else{
			 // 用户
			 $("#jobTitleDiv").hide("slow");
			$("#introduceDiv").hide("slow");
			$("#saleDiv").show("slow");
			$("#tvDiv").show("slow");
			initUserData(object);
		 }
		 $("#headCompanyDiv").show("slow");
		$("#companyDiv").show("slow");
		$("#officeDiv").show("slow");
		
		
	}else{
		$("#officeName").val(object.officeName);
		$("#headCompanyDiv").hide("slow");
		$("#companyDiv").hide("slow");
		$("#officeDiv").hide("slow");
		$("#tvDiv").hide("slow");
		initUserData(object);
		if(object.companyType != 0 && object.companyType !=1 && object.companyType != 2){
			$("#saleDiv").show("slow");
		}else{
			$("#saleDiv").hide("slow");
		}
		return;
	}
})


function initBaseData(user) {
	
	if(null == user || user.length == 0){
		Notify('数据异常','top-right','5000','danger','fa-desktop',true);
		return;
	}
	$("#mobile").val(user.mobile);
	$("#realName").val(user.realName);
	$("#email").val(user.email);
	$("#roleId").html("<option value='"+user.role.id+"'>"+user.role.roleName+"</option>");
	if(user.qq != null){
		$("#qq").val(user.qq);
	}
	$("#officeName").val(user.officeName);
//	var officeId = $("#officeId").val();
	
}

//手机号码框， 失去焦点事件
$("#mobile").blur(function(){
//	var mobile = $("#mobile").val();
//	if(object.mobile == mobile){
//		return;
//	}
//	var mobileRex = /^1[345678]\d{9}$/;
//	if(!mobileRex.test(mobile)){
//		Notify("手机号码不合法", 'top-right', '5000', 'danger', 'fa-desktop', true);
//		return;
//	}
//	$.ajax({
//		type : "POST",
//		url : "/SanghaCloud/mobile/verifiMobile", // URL
//		data : {mobile:mobile},
//		dataType :"json",
//		async : false,
//		timeout : 5000,
//		success : function(respObj){
//			if(respObj.code == 200){
//				
//			}else{
//				Notify(respObj.msg, 'top-right', '5000', 'danger', 'fa-desktop', true);
//				return;
//			}
//		}
//	})
	
});

// 加载角色
$("#roleId").focus(function () {
	
	var str = "";
	if(tempType == 0 || tempType == null){
		Notify("数据异常", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return;
	}else if(tempType == 1){
		$("#roleId").html("<option value='4'>普通用户</option>");
		return;
	}else if(type == 2 || type == 3 || type == 4){
		var userType = null;
		
		var isOfficeSelect =  $("#isOfficeSelect").val();
		if(isOfficeSelect == 0){
			userType = object.companyType;
		}else{
			var officeId = $("#officeId").val();
			var companyId = $("#companyId").val();
			var headCompanyId = $("#headCompanyId").val();
			if(officeId != 0){
				userType = 2;
			}
			if(companyId != 0 && officeId == 0){
				userType = 1;
			}
			if(headCompanyId != 0 && officeId == 0 && companyId == 0 ){
				userType = 0;
			}
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
	$("#roleId").html(str);
})

$("#headCompanyId").focus(function (){
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/companyMobile/queryHeadCompany",
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				buildHeadCompanySelectData(respObj.data);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
})

function buildHeadCompanySelectData(data){
	var str = '';
	if(data.length == 0){
		str = "<option value='0'>没有数据</option>";
	}else{
		for(var i=0 ; i < data.length; i++){
			var temp = data[i];
			str += "<option value="+temp.id+">"+temp.companyName+"</option>";
		}
	}
	$("#headCompanyId").html(str);
	$("#officeName").val(data[0].companyName);
	var clearSelect = "<option value='0'>您已经选择了"+data[0].companyName+"</option>";
	$("#companyId").html(clearSelect);
	$("#officeId").html(clearSelect);
	$("#tvId").html(clearSelect);
	$("#roleId").html("<option value='0'>选择角色</option>");
}

$("#companyId").focus(function () {
	var headCom = $("#headCompanyId").val();
	if(headCom == 0){
		Notify('没有选择总公司','top-right','5000','danger','fa-desktop',true);
		return;
	}
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
	$("#officeName").val(dataList[0].companyName);
	var clearSelect = "<option value='0'>请选择</option>";
	$("#officeId").html(clearSelect);
	$("#tvId").html(clearSelect);
	$("#roleId").html("<option value='0'>选择角色</option>");
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
		$("#roleId").html("<option value='0'>选择角色</option>");
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
		buildOfficeNoDataSelect();
		return;
	}
	$("#officeId").html(str);
	$("#officeName").val(dataList[0].officeName);
	var clearSelect = "<option value='0'>请选择</option>";
	$("#tvId").html(clearSelect);
	$("#roleId").html("<option value='0'>选择角色</option>");
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
		$("#roleId").html("<option value='0'>选择角色</option>");
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
	$("#officeName").val(dataList[0].tvName);
}

$("#tvId").change(function () {
	var options=$("#tvId option:selected"); 
	$("#officeName").val(options.text());
})

$("#officeId").change(function () {
	var options=$("#officeId option:selected"); 
	$("#officeName").val(options.text());
})

$("#companyId").change(function(){
	var options=$("#companyId option:selected"); 
	$("#officeName").val(options.text());
})
//$("#headCompanyId").change(function(){
//	var options=$("#headCompanyId option:selected"); 
//	$("#officeName").val(options.text());
//})


// 提交修改
$('#confirmBtn').click(function() {
		var isSuccess = false;
		
		var obj = getBaseData();
		
		if(obj == false){
			return false;
		}
		var tvId = null;
		var sale = null;
		var typeAndId = {type:null,id:null};
		var isOfficeSelect = $("#isOfficeSelect").val();
		if(tempType == 1){
			// 用户
			if(isOfficeSelect == 1){
				tvId = $("#tvId").val();
				if(tvId == 0){
					Notify("没有选择电视台", 'top-right', '5000', 'danger', 'fa-desktop', true);
					return false;
				}
			}
			 sale = $("#sale").val();
		}else if(tempType == 2){
			//员工
			if(isOfficeSelect == 1){
				typeAndId = selectCompany();
			}
		}
		var isPUser = $("#isPUser").val();
		var introduce = $("#introduce").val();
		var jobTitle = $("#jobTitle").val();
		var isOfficeSelect = $("#isOfficeSelect").val();
		$.ajax({
			type : "POST",
			url : "/SanghaCloud/admin/user/updateUser", // URL
			data : {isPUser:isPUser,isOfficeSelect:isOfficeSelect,id:object.id,type:tempType,mobile:obj.mobile,realName:obj.realName,email:obj.email,qq:obj.qq,roleId:obj.roleId,tvId:tvId,sale:sale,companyType:typeAndId.type,companyId:typeAndId.id,introduce:introduce,jobTitle:jobTitle},
			dataType :"json",
			async : false,
			timeout : 5000,
			success : function(respObj){
				if(respObj.code == 200){
					isSuccess = true;
				}else{
					Notify(respObj.msg, 'top-right', '5000', 'danger', 'fa-desktop', true);
				}
			}
		});
		
		return isSuccess;
})

function selectCompany(){
	var officeId = $("#officeId").val();
	var companyId = $("#companyId").val();
	var headCompanyId = $("#headCompanyId").val();
	if(officeId != 0){
		type = 2;
		id = officeId;
	}
	if(officeId == 0 && companyId != 0){
		type = 1;
		id = companyId;
	}
	if(officeId == 0 && companyId == 0 && headCompanyId != 0){
		type = 0;
		id = headCompanyId;
	}
	tempObject = {type:type,id:id};
	return tempObject;
}

function getBaseData(){
	var mobile = $("#mobile").val();
	var realName = $("#realName").val();
	var email = $("#email").val();
	var qq = $("#qq").val();
	var roleId = $("#roleId").val();
	var isVerify = verifyBase();
	if(!isVerify){
		return false;
	}
	if(object.email != email){
		var isExit = checkMobileAndEmail(mobile,email);
		if(isExit){
			return false;
		}
	}
	baseData = {mobile:mobile,realName:realName,email:email,qq:qq,roleId:roleId};
	return baseData;
}

function checkMobileAndEmail(mobile,email){
	var isExit = false;
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/mobile/verifyEmail", // URL
		data : {email:email},
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

//基本的表单验证
function verifyBase() {
	var mobile = $("#mobile").val();
	var realName = $("#realName").val();
	var email = $("#email").val();
	var roleId = $("#roleId").val();
	var mobileRex = /^1[123456789]\d{9}$/;
	if(!mobileRex.test(mobile)){
		Notify("手机号码不合法", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	var nameCNRex = /^[\u4e00-\u9fa5 ]{2,7}$/;
	if(!nameCNRex.test(realName) ){
		Notify("无效的真实姓名", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	var emailRex= /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,4}){1,2})$/;
	if(!emailRex.test(email)){
		Notify("邮箱不合法", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	if(roleId == 0){
		Notify("无效的角色", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	return true;
}

$("#sale").focus(function(){
	
	var tvId = null;
	var isOfficeSelect = $("#isOfficeSelect").val();
	if(isOfficeSelect == 1){
		var tv = $("#tvId").val();
		if(tv == 0 || tv == null){
			Notify("无效的所属单位", 'top-right', '5000', 'danger', 'fa-desktop', true);
			return false;
		}
		tvId = tv;
	}else{
		tvId = object.tvId;
	}
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/user/getSales", // URL
		data : {tvId:tvId},
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











