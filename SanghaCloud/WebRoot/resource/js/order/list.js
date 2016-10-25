/**
 * 
 */

function initData(pageNO,orderNumber,tvId){
	var totalPage=1;
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/order/getOrderList",
		data : {pageNO:pageNO,pageSize:8,orderNumber:orderNumber,tvId:tvId},
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
			var tscTime = "-";
			// 分技术最后反馈时间
			var tscLastTime = "-";
			var headTechTime = "-";
			// 总技术最后反馈时间
			var headTechLastTime = "-";
			// 研发最后反馈时间
			var developLastTime = "-";
			if(obj.techStartTime != null && obj.techEndTime != null){
				tscTime = countDate(obj.techStartTime,obj.techEndTime);
			}
			if(obj.headTechStartTime != null && obj.headTechEndTime != null){
				headTechTime=countDate(obj.headTechStartTime,obj.headTechEndTime);
			}
			if(obj.techLastTime != null){
				tscLastTime = new Date(obj.techLastTime).Format("yyyy-MM-dd HH:mm");
			}
			if(obj.headLastTime != null){
				headTechLastTime = new Date(obj.headLastTime).Format("yyyy-MM-dd HH:mm");
			}
			if(obj.developTime != null){
				developLastTime = new Date(obj.developTime).Format("yyyy-MM-dd HH:mm"); 
			}
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
			var isValid ="-";
			if(obj.isValid == 0){
				isValid = "否";
			}else if(obj.isValid == 1){
				isValid="是";
			}
			var time = new Date(obj.createTime).Format("yyyy-MM-dd HH:mm:ss");
			str+="<tr>" +
			"<td>"+obj.orderNumber+"</td>" +
			"<td>"+obj.tvName+"</td>" +
			"<td>"+obj.orderCategoryName+"</td>" +
			"<td>"+obj.userName+"</td>" +
			"<td>"+obj.serviceName+"</td>" +
			"<td>"+tscName+"/"+tscTime+"/"+tscLastTime+"</td>" +
			"<td>"+headTech+"/"+headTechTime+"/"+headTechLastTime+"</td>" +
			"<td>"+developName+"/"+developLastTime+"</td>" +
			"<td>"+status+"</td>" +
			"<td>"+isValid+"</td>" +
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

function countDate( start, end){
	var date1=new Date();  //开始时间
	var date2=new Date();    //结束时间
	var date3=end-start;  //时间差的毫秒数
	//计算出相差天数
	var days=Math.floor(date3/(24*3600*1000))
	//计算出小时数
	var leave1=date3%(24*3600*1000)    //计算天数后剩余的毫秒数
	var hours=Math.floor(leave1/(3600*1000))
	//计算相差分钟数
	var leave2=leave1%(3600*1000)        //计算小时数后剩余的毫秒数
	var minutes=Math.floor(leave2/(60*1000))
	//计算相差秒数
	 //var leave3=leave2%(60*1000)     计算分钟数后剩余的毫秒数
	 //var seconds=Math.round(leave3/1000)
//	alert(" 相差 "+days+"天 "+hours+"小时 "+minutes+" 分钟")
	return days+"天"+hours+"小时"+minutes+"分"
}






