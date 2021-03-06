
function initData(pageNO,type,realName,roleId) {
	
	var totalPage=1;
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/user/getUsers",
		data : {pageNO:pageNO,pageSize:8,flag:type,realName:realName,roleId:roleId},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				totalPage = respObj.data.totalPage == 0 ? 1 : respObj.data.totalPage ;
				if(respObj.data.dataList.length == 0){
					$("#dataBody").html("<tr><td>没有数据</td></tr>");
				}else{
					buildData(respObj.data,type);
				}
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return totalPage;
	
}

function buildData(data,type) {
	var len = data.dataList.length;
	var str = "";
	for(var i=0; i<len; i++ ){
		var obj = data.dataList[i];
		var officeName= '';
		var email = '';
		if(obj.email != null){
			email = obj.email;
		}
		if(obj.companyName != undefined && obj.companyName != null){
			officeName = obj.companyName;
		}
		if(obj.officeName != undefined && obj.officeName != null ){
			officeName = obj.officeName;
		}
		var dongjieBtn = "<a href='javascript:frozenAccount("+obj.id+",0)'  class='btn btn-danger'>冻结</a>";
		if(obj.status == 3){
			dongjieBtn = "<a href='javascript:frozenAccount("+obj.id+",1)'  class='btn btn-danger'>解冻</a>"
		}
		var time = new Date(obj.updateDate).Format("yyyy-MM-dd HH:mm:ss");
		var createDate = new Date(obj.createDate).Format("yyyy-MM-dd HH:mm:ss");
		str += "<tr><td>"+obj.realName+"</td><td>"+obj.mobile+"</td><td>"+email+"</td><td>"+officeName+"</td><td>"+obj.role.roleName+"</td>" +
				"<td>"+createDate+"</td><td>"+time+"</td>" +
						"<td><a href='/SanghaCloud/admin/requestpage/user/detail?userId="+obj.id+"&type="+type+"' target='iframe' class='btn btn-info'>详情</a>" +
						"		<a href='/SanghaCloud/admin/requestpage/user/edit?userId="+obj.id+"&type="+type+"' target='iframe' class='btn btn-info'>修改</a>" +
						"		"+dongjieBtn+"</td></tr>";
		
	}
	$("#dataBody").html(str);
}

// 冻结账户
function frozenAccount(userId,flag) {
	var type = $("#type").val();
	var msg = "";
	if(flag == 0){
		msg = "确认冻结此账户吗？";
	}else{
		msg = "确认解冻此账户吗？";
	}
	var str='<div id="myModal">'+
  	'<div class="form-group">'+
        '<label></label>'+
        '<span class="input-icon icon-right">'+
        	'<div class="input-group">'+
        		'<span id="name" >'+msg+'</span>'+
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
            	$.ajax({
            		type : "GET",
            		url : "/SanghaCloud/admin/user/frozenAccount",
            		data : {flag:flag,type:type,userId:userId},
            		dataType :"json",
            		async : false,
            		timeout : 5000,
            		success : function(respObj){
            			var status = respObj.code;
            			if(status==200){
            				Notify(respObj.msg,'top-right','5000','info','fa-desktop',true);
            			}else{
            				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
            			}
            		}
            	});
            	initData(1,type,null) ;
            }
        }
    }
});
}

Date.prototype.Format = function(fmt)   
{    
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "H+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}  











