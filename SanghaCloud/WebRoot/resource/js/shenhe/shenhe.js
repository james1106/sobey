
var tempFlag = null;
function initData(pageNo,flag) {
	var totalPage=1;
	tempFlag = flag;
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/user/audituser",
		data : {pageNO:pageNo,pageSize:8,flag:flag},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				if(respObj.data.dataList.length == 0){
					totalPage = 1;
				}else{
					totalPage = respObj.data.totalPage ;
				}
				buildData(respObj.data,flag);
				
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return totalPage;
}

function buildData(obj,flag) {
	var dataList = obj.dataList;
	var dataStr = "";
	var type="";
	var isPUserStr = "不可用";
	if(flag == 1){
		type="用户";
		isPUserStr = "<span class='input-icon icon-right'><select name='isPUser' class='form-control'><option value='3'>选择</option><option value='0'>是</option><option value='1'>否</option></select></span>";
	}else{
		type="员工";
	}
	
	if(dataList.length == 0){
		$("#dataBody").html("<tr><td>没有数据</td></tr>");
		return;
	}
	for(var i=0; i < dataList.length; i++){
		var temp = dataList[i];
		
		var unitName="";
		if(temp.office != null && temp.office !=undefined ){
			unitName=temp.office.officeName;
		}else if(temp.unit != null && temp.unit !=undefined){
			unitName=temp.unit.companyName;
		}else if(temp.officeName != null && temp.officeName != undefined){
			unitName=temp.officeName;
			
		}
		var role=temp.role.roleName;
		var userType = null;
		if(temp.roleId != 4){
			userType = temp.userType;
		}
		dataStr+="<tr><td>" +
				" "+temp.id+"</td><td>" +
				""+temp.realName+"</td><td>" +
				""+temp.mobile+"</td><td>"+unitName+"</td><td>"+type+"</td><td><span class='input-icon icon-right'>" +
						" <select name='roleId' onfocus='getRole("+temp.roleId+",this,"+userType+")' class='form-control'>" +
						"      <option value='"+temp.role.id+"'>"+role+"</option></select></span></td><td>"+isPUserStr+"</td><td>" +
				"     <a onclick='approvedAccount("+temp.id+",this)' id='ballBtn' class='btn btn-info'>通过</a>  " +
				"     <a href='javascript:delAccount("+temp.id+",this)' class='btn btn-danger'>不通过</a></td></tr>";
	}
	//   <a href='javascript:void(0)' target='iframe' class='btn btn-info'>详情</a>  
	// 暂时不用跳转到详情页
	//'/SanghaCloud/admin/requestpage/shenhe/detail?id="+temp.id+"'
	$("#dataBody").html(dataStr);
	
}

function getRole(roleId,obj,userType){
	if(roleId==4){
		return;
	}
	var jqObj = $(obj);
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/role/getrole",
		data:{userType:userType},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var dataList = respObj.data;
			buildSelect(dataList,jqObj,userType);
		}
	})
}

function buildSelect(dataList,obj,userType){
	// 员工类型 0 总公司 1 分公司 2 办事处
	var selectStr = "";
	for(var i=0; i<dataList.length; i++){
		var temp = dataList[i];
		selectStr+= "<option value="+temp.id+">"+temp.roleName+"</option>";
	}
	obj.html(selectStr);
}
// 通过审核
function approve(id,obj) {
	var jqObj = $(obj);
	var tds = $(obj).parent().parent().find("td");
	var roleSelect = $(tds[5]).find("select")[0];
	var role = $(roleSelect).val();
	
	var isPUser = null;
	if(tempFlag != null && tempFlag == 1){
		 var tempIsPUser = $(tds[6]).find("select")[0];
		 isPUser = $(tempIsPUser).val();
		 if(isPUser == 3){
			 Notify("没有选择是否产品用户",'top-right','5000','danger','fa-desktop',true);
			 return;
		 }
	}
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/user/approved",
		data:{id:id,roleId:role,flag:1,isPUser:isPUser},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status == 200){
				var type = $("#type").val();
				initData(1,type);
				Notify("审核成功",'top-right','5000','info','fa-desktop',true);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	})
	
}

function reject(id,obj) {
	var jqObj = $(obj);
	var tr = jqObj.prev().prev();
	var role = $("tr select").val();
	
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/user/approved",
		data:{id:id,roleId:role,flag:2},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status == 200){
				var type = $("#type").val();
				initData(1,type);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	})
}

function approvedAccount(id,obj) {
	var str='<div id="myModal">'+
  	'<div class="form-group">'+
        '<label></label>'+
        '<span class="input-icon icon-right">'+
        	'<div class="input-group">'+
        		'<span id="name" >确认通过吗？</span>'+
            '</div>'+
        '</span>'+
   '</div>'+
'</div>';
bootbox.dialog({
    message: str,
    title: "提示框",
    buttons: {
    	"关闭": {
            className: "btn-default",
            callback: function () {
            	
            }
      	},
        success: {
            label: "确认",
            className: "btn-danger",
            callback: function () {
            	approve(id,obj);
            }
        }
    }
});
}

function delAccount(id,obj) {
	var str='<div id="myModal">'+
  	'<div class="form-group">'+
        '<label></label>'+
        '<span class="input-icon icon-right">'+
        	'<div class="input-group">'+
        		'<span id="name" >此操作不可逆, 确认此操作吗?</span>'+
            '</div>'+
        '</span>'+
   '</div>'+
'</div>';
bootbox.dialog({
    message: str,
    title: "提示框",
    buttons: {
    	"关闭": {
            className: "btn-default",
            callback: function () {
            	
            }
      	},
        success: {
            label: "确认",
            className: "btn-danger",
            callback: function () {
            	reject(id,obj);
            }
        }
    }
});
}
