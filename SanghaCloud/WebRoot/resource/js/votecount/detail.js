/**
 * 
 */

function initData(newsId) {

	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/vote/getVoteDetail",
		data : {
			newsId : newsId
		},
		dataType : "json",
		async : false,
		timeout : 5000,
		success : function(json) {
			if (json.code == 200) {
				buildData(json.data);
			} else {
				Notify("数据异常", 'top-right', '5000', 'danger', 'fa-desktop',
						true);
			}
		}
	});
}

function buildData(data) {

	var len = data.length;
	var str = "";
	if (len == 0) {
		str = "<tr><td>没有数据</td></tr>";
	} else {
		for (var i = 0; i < len; i++) {
			var obj = data[i];
			var time = new Date(obj.createTime).Format("yyyy-MM-dd HH:mm:ss");
			str += "<tr><td>"+obj.describe+"</td><td>"+obj.count+"</td><td>"+time+"</td></tr>";
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