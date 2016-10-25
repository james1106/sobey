/**
 * 
 */
function initData(pageNO,orderNumber){
	var totalPage=1;
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/selfCenter/getOrders",
		data : {pageNO:pageNO,pageSize:8,orderNumber:orderNumber},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				buildData(respObj.data.dataList);
				totalPage = respObj.data.totalPage == 0 ? 1 : respObj.data.totalPage;
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return totalPage;
}

function buildData(data){
	var str = "";
	var len = data.length;
	if(len == 0){
		str += "<tr><td>没有数据</td></tr>";
	}else{
		for(var i=0 ; i < len; i++){
			var obj = data[i];
			var tscName = "无";
			var headTech = "无";
			var developName = "无";
			if(obj.tscName != null){
				tscName = obj.tscName;
			}
			if(obj.headTechName != null){
				headTech = obj.headTechName;
			}
			if(obj.developName != null){
				developName = obj.developName;
			}
			var status = setStatus(obj.status);
			var time = new Date(obj.createTime).Format("yyyy-MM-dd HH:mm:ss");
			str+="<tr>" +
			"<td>"+obj.orderNumber+"</td>" +
			"<td>"+obj.orderCategoryName+"</td>" +
			"<td>"+obj.userName+"</td>" +
			"<td>"+obj.serviceName+"</td>" +
			"<td>"+tscName+"</td>" +
			"<td>"+headTech+"</td>" +
			"<td>"+developName+"</td>" +
			"<td>"+status+"</td>" +
			"<td>"+time+"</td></tr>";
		}
	}
	$("#dataBody").html(str);
}

function setStatus(status){
	var strStatus = "";
	if(status == 2001){
		strStatus = "待处理";
	}else if(status == 2002){
		strStatus = "处理中";
	}else if(status == 2003){
		strStatus = "待验证";
	}else if(status == 2004){
		strStatus = "待评价";
	}else if(status == 2005){
		strStatus = "已完成";
	}else{
		strStatus = "未知状态";
	}
	return strStatus;
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