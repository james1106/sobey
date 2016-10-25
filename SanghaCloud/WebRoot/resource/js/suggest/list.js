
function initData(pageNO) {
	var totalPage = 1;
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/suggest/getSuggest",
		data : {
			pageNO : pageNO,
			pageSize : 8
		},
		dataType : "json",
		async : false,
		timeout : 5000,
		success : function(json) {
			if (json.code == 200) {
				totalPage = json.data.totalPage == 0 ? 1 : json.data.totalPage;
				buildData(json.data.dataList);
			} else {
				Notify("数据异常", 'top-right', '5000', 'danger', 'fa-desktop',true);
			}
		}
	});
	return totalPage;
}

function buildData(data) {
	var str = "";
	var len = data.length;
	if (len == 0) {
		str = "<td>没有数据</td>"
	} else {
		for (var i = 0; i < len; i++) {
			var obj = data[i];
			var time  = new Date(obj.createDate).Format("yyyy-MM-dd HH:mm:ss");
			var realName = "";
			var email = "";
			var mobile = "";
			var content = obj.content;
			if(null != obj.realName){
				realName = obj.realName;
				email = obj.email;
				mobile = obj.mobile;
			}else{
				realName = obj.userName;
				email = obj.userEmail;
				mobile = obj.userMobile;
			}
			str += "<tr><td>"+realName+"</td>" +
					"		  <td>"+mobile+"</td>" +
							"<td>"+email+"</td>" +
							"<td>"+content+"</td>" +
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