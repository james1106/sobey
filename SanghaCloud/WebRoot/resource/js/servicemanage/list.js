
function initData(pageNO){
	var totalPage = 1;
	$.ajax({
		type:"POST",
		url:"/SanghaCloud/admin/user/getServiceUserList",
		data:{pageNO:pageNO,pageSize:8},
		dataType:"json",
		async : false,
		timeout:5000,
		success:function(json){
			if(json.code == 200){
				totalPage = json.data.totalPage == 0 ? 1 : json.data.totalPage ;
				buildData(json.data.dataList);
			}else{
				Notify("数据异常",'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return totalPage;
}

function buildData(obj){
	var str = "";
	var len = obj.length;
	if(len == 0){
		str  = "<td>没有数据</td>"
	}else{
		for(var i = 0; i < len; i++){
			var data = obj[i];
			var online = data.isOnLine;
			var isOnLineStatus = "";
			if(online == 0){
				isOnLineStatus = "在线";
			}else if(online == 1){
				isOnLineStatus = "离线";
			}
			var time  = new Date(data.updateDate).Format("yyyy-MM-dd HH:mm:ss");
			str += "<tr><td>"+data.realName+"</td>" +
					"	<td>"+data.mobile+"</td>" +
					"	<td>"+data.email+"</td>" +
					"	<td>客服</td>" +
					"	<td>"+isOnLineStatus+"</td>" +
					"	<td>"+time+"</td>" +
					"	<td><a href='javascript:setstatus("+data.id+",0,this)' class='btn btn-info'>设置在线</a>" +
					"			<a href='javascript:setstatus("+data.id+",1)' class='btn btn-info'>设置离线</a></td></tr>";
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

function setstatus(id,type,obj){
	//  type 0 设置在线   1 设置离线
	if(type == 0){
		tanc("确认设置 在线 吗?",id,type,obj);
	}else{
		tanc("确认设置 离线 吗?",id,type,obj);
	}
	
}

function tanc(msg,id,type,obj) {
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
            	
            }
      	},
        success: {
            label: "确认",
            className: "btn-danger",
            callback: function () {
            	$.ajax({
            		type:"post",
            		url:"/SanghaCloud/admin/user/servicemanage/setonline",
            		data:{userId:id,type:type},
            		async:false,
            		dataType:"json",
            		timeout:5000,
            		success:function(json){
            			if(json.code == 200){
            				Notify("操作成功",'top-right','5000','info','fa-desktop',true);
            				var totalpage = initData(1);
            				$.jqPaginator('#pagination', {
            		   	    	totalPages: totalpage,  //总页数
            		   	        visiblePages: 3,  //可见页面
            		   	        currentPage: 1,   //当前页面
            		   	        onPageChange: function (num, type) {
            		   	        	$('#showing').text('共'+totalpage+'条  第'+num+'/'+totalpage+'页');
            		   	            if(type != "init"){
            		   	            	totalpage = initData(num);
            		   	            }
            		   	        }
            		   	    });   
            			}else{
            				Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
            			}
            		}
            	});
            }
        }
    }
});
}