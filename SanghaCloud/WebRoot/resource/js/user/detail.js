/**
 * 
 */
var userId = null;
function initData(id,type){
	initView(type);
	initUser(id,type);
}

function initUser(id,type){
	
	$.ajax({
		type:"POST",
		url:"/SanghaCloud/admin/user/getUser",
		data:{userId:id,type:type},
		dataType:"json",
		timeout:5000,
		success:function(respObj){
			if(respObj.code == 200){
				console.debug(respObj)
				buildData(respObj.data,type);
			}else{
				Notify("数据异常",'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	
}

function initView(type){
	var zerenDiv = $("#zerenDiv");
	var jobTitleDiv = $("#jobTitleDiv");
	var salsDiv = $("#salsDiv");
	if(type == 1){
		// 用户
		$("#zerenDiv").hide();
		$("#jobTitleDiv").hide();
		$("#salsDiv").show();
	}else if(type == 2){
		$("#zerenDiv").show();
		$("#jobTitleDiv").show();
		$("#salsDiv").hide();
	}
}
function buildData(data,type){
	$("#userName").html("用户 "+data.realName+"");
	if(type == 1){
		$("#userType").html("(用户)");
	}else if(type == 2){
		$("#userType").html("(员工)");
	}
	$("#mobile").html(data.mobile);
	
	var path = '/SanghaCloud/resource/img/defalt_header.png';
	if(data.avatar != null){
		path = data.avatar;
	}
	$("#photo").attr('src',path); 
	$("#role").html(data.role.roleName);
	$("#officeName").html(data.officeName);
	$("#email").html(data.email);
	$("#qq").html(data.qq);
	
	if(type == 1){
		if(data.sale != null){
			$("#sale").html(data.sale.realName);
		}
	}else if(type == 2){
		if(data.jobTitle != null){
			$("#jobTitle").html(data.jobTitle);
		}
		if(data.introduce != null){
			$("#zeren").html(data.introduce);
		}
	}
	var time = new Date(data.createDate).Format("yyyy-MM-dd HH:mm:ss");
	$("#createTime").html(time);
}
Date.prototype.Format = function(fmt)   
{ //author: meizz   
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









