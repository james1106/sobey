/**
 * 
 */

function initData(pageNO,startTime,endTime){
	var totalPage = 1;
	$.ajax({
		type:"POST",
		url:"/SanghaCloud/admin/complain/getpageComplain",
		data:{pageNO:pageNO,pageSize:8,startTime:startTime,endTime:endTime},
		dataType:"json",
		async : false,
		timeout:5000,
		success:function(json){
			if(json.code == 200){
				totalPage = json.data.totalPage == 0 ? 1 : json.data.totalPage;
				buildData(json.data.dataList);
			}else{
				Notify("数据异常",'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return totalPage;
}

function buildData(data){
	
	var len = data.length;
	var str = "";
	if(len == 0){
		str = "<tr><td>没有数据</td></tr>";
	}else{
		for(var i=0 ; i < len; i++){
			var obj = data[i];
			var complainObj = "";
			if(obj.type == 0){
				complainObj = "无投诉对象";
			}else if(obj.type == 1){
				complainObj = "客服";
			}else if(obj.type == 2){
				complainObj = "技术";
			}else if(obj.type == 3){
				complainObj = "客服 | 技术";
			}
			var time = new Date(obj.createTime).Format("yyyy-MM-dd HH:mm:ss");
			str += "<tr><td>"+obj.orderNumber+"</td>" +
					"		  <td>"+obj.content+"</td>" +
							"<td>"+complainObj+"</td>" +
							"<td>"+time+"</td></tr>";
		}
	}
	$("#dataBody").html(str);
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