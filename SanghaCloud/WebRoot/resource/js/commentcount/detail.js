/**
 * 
 */
function initData(pageNO,type,data){
	
	var totalPage=1;
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/commentCount/getDetailComment",
		data : {pageNO:pageNO,pageSize:8,type:type,data:data},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(json){
			var status = json.code;
			if(status==200){
				
				totalPage = json.data.totalPage == 0 ? 1 : json.data.totalPage;
				buildData(json.data.dataList);
			}else{
				Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return totalPage;
}
function buildData(dataList){
	var len = dataList.length;
	var str = "";
	
	if(len == 0 ){
		str = "<tr><td>没有数据</td></tr>"
	}else{
		
		for(var i = 0; i < len ; i++){
			var obj = dataList[i];
			var content = "无";
			if(obj.content != null){
				content = obj.content;
			}
			var lableStr = "";
			if(obj.lables.length != 0){
				for (var j=0; j<obj.lables.length; j++){
					lableStr += obj.lables[j].lable+ "|  ";
				}
			}else{
				lableStr = "无";
			}
			var time = new Date(obj.createDate).Format("yyyy-MM-dd HH:mm:ss");
			str += "<tr><td>"+obj.orderNumber+"</td><td>"+obj.serviceAttitudeCount+"</td><td>"+obj.disposeSpeedCount+"</td><td>"+content+"</td>" +
					"<td>"+lableStr+"</td><td>"+time+"</td></tr>";
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













