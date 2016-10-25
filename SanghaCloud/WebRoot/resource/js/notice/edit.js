/**
 * 
 */
var title = null;
var bief = null;
var content = null;
var tempSystemId = null;
var ue = null;
function initData(systemId){
	tempSystemId = systemId;
	ue = UE.getEditor('editor');
	$.ajax({
		type:"POST",
		url:"/SanghaCloud/admin/notice/getSystemInfo",
		data:{systemId:systemId},
		dataType:"json",
		async : false,
		timeout:5000,
		success:function(respObj){
			if(respObj.code == 200){
				title = respObj.data.title;
				bief = respObj.data.brief;
				content = respObj.data.content;
				buildData(respObj.data);
			}else{
				Notify("数据异常",'top-right','5000','danger','fa-desktop',true);
			}
		}
	})
}

function buildData(data){
	$("#title").val(data.title);
	var sendGroup='';
	var group = data.toGroup;
	//推送的人群 0 全部 1 用户 2 员工
	if(group == 0){
		sendGroup = "全部";
	}else if(group == 1){
		sendGroup = "用户";
	}else if(group == 2){
		sendGroup = "员工";
	}
	$("#toGroup").val(sendGroup);
	var time = new Date(data.createTime).Format("yyyy-MM-dd HH:mm:ss");
	$("#sendTime").val(time);
	$("#publisher").val(data.publisherName);
	$("#brief").val(data.brief);
	var is = ue.ready( function () {
		ue.setContent(data.content, false);
	});
//	UE.getEditor('editor').setContent(data.content, false);
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




function msgInfo(msg) {
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
            	return false;
            }
      	},
        success: {
            label: "确认",
            className: "btn-danger",
            callback: function () {
            	return true;
            }
        }
    }
});
}





















