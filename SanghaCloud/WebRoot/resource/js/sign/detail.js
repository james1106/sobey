/**
 * 
 */
function initData(id, type, pageNO, realName,mobile) {
	
	var totalPage = 1;

	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/sign/getDatailSign",
		data : {
			pageNO : pageNO,
			pageSize : 8,
			type : type,
			id:id
		},
		dataType : "json",
		async : false,
		timeout : 5000,
		success : function(json) {
			if (json.code == 200) {
				totalPage = json.data.totalPage == 0 ? 1 : json.data.totalPage;
				buildData(json.data.dataList, realName,mobile);
			} else {
				Notify("数据异常", 'top-right', '5000', 'danger', 'fa-desktop',
						true);
			}
		}
	});
	return totalPage;
}

function buildData(data,realName,mobile){
	var len = data.length;
	var str = "";
	if(len == 0){
		str = "<tr><td>没有数据</td></tr>";
	}else{
		for(var i=0; i< len ; i++){
			var obj = data[i];
			var time = new Date(obj.createTime).Format("yyyy-MM-dd HH:mm:ss");
			str += "<tr><td>"+realName+"</td>" +
					"		  <td>"+mobile+"</td>" +
							"<td>"+obj.bonus+"</td>" +
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