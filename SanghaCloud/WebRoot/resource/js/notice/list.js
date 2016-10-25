/**
 * 
 */

function initData(pageNO){
	var totalPage=1;
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/msgCenter/adminGetInfo",
		data : {pageNO:pageNO,pageSize:8},
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

function buildData(dataList){
	console.debug(dataList)
	var str = "";
	var len = dataList.length;
	if(len == 0){
		str += "<tr><td>没有数据</td></tr>";
	}else{
		for(var i=0; i < len; i++){
			var obj = dataList[i];
			var titleSub = "";
			var introductionSub = "";
			if(obj.title.length > 8){
				titleSub = obj.title.substring(0,7)+"...";
			}else{
				titleSub = obj.title;
			}
			
			if(obj.brief.length > 8){
				introductionSub = obj.brief.substring(0,7)+"...";
			}else{
				introductionSub = obj.brief;
			}
			var time = new Date(obj.createTime).Format("yyyy-MM-dd HH:mm:ss");
			str+="<tr><td><span data-toggle='popover-hover' data-title='通知标题' data-content='"+obj.title+"'>"+titleSub+"</span></td>" +
					"<td><span data-toggle='popover-hover' data-title='通知概要' data-content='"+obj.brief+"'>"+introductionSub+"...</span></td><td>"+obj.publisherName+"</td><td>"+time+"</td>" +
					"<td><a href='/SanghaCloud/admin/requestpage/notice/detail?systemId="+obj.id+"' class='btn btn-info'>查看</a>" +
					"		<a href='/SanghaCloud/admin/requestpage/notice/edit?systemId="+obj.id+"' class='btn btn-info'>修改</a>" +
					"		<a onclick='delInfo("+obj.id+")' class='btn btn-danger'>删除</a></td></tr>";
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

//删除消息
function delInfo(id) {
	var str='<div id="myModal">'+
  	'<div class="form-group">'+
        '<label></label>'+
        '<span class="input-icon icon-right">'+
        	'<div class="input-group">'+
        		'<span id="name" >确认删除吗？</span>'+
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
            		url : "/SanghaCloud/admin/notice/delNotice",
            		data : {id:id},
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
            	initData(1) ;
            }
        }
    }
});
}








