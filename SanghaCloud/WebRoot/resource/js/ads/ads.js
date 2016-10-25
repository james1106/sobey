/**
 * 
 */

var pathName=window.document.location.pathname;
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var requestHeader = window.location.protocol+"//"+window.location.host+projectName+"/";
function initData() {
	var totalPage=1;
	var len = 0;
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/news/getBanners",
		dataType :"json",
		async : false,
		timeout : 5000,
		success:function(respObj){
			if(respObj.data.lists.length == 0){
				$("#bodyData").html("<tr><td>没有数据</td></tr>");
			}else{
				len = respObj.data.lists.length;
				initBodyData(respObj.data.lists);
			}
		}
	});
	initAddBtn(len);
	return totalPage;
}

function initBodyData(obj) {
	var len = obj.length;
	var str = "";
	for(var i=0; i < len; i++){
		var temp = obj[i];
		var url;
		if(temp.imgUrl == null || temp.imgUrl.length == 0){
			if(temp.isInside == 1){
				url = "关联到内部文章";
			}else{
				url="没有外链";
			}
		}else{
			if(temp.imgUrl.length > 20){
				url=temp.imgUrl.substring(0,50)+"...";
			}else{
				url=temp.imgUrl;
			}
			
		}
		str += "<tr><td><img  src="+requestHeader+temp.image+" style='width: 300px;height: 166px'></td><td>"+url+"</td>" +
				"<td><a href='/SanghaCloud/admin/requestpage/ads/edit?bannerId="+temp.id+"' target='iframe' class='btn btn-info'>修改</a>" +
				"		<a onclick='delAds("+temp.id+")'  class='btn btn-info' class='btn btn-danger'>删除</a></td></tr>";
	}
	$("#bodyData").html(str);
}

function initAddBtn(len) {
	if(len >= 5){
		$('#addBtn').attr("disabled",true);
	}else{
		$('#addBtn').attr("disabled",false);
	}
}

function addBanner(){
	var files = $("#files").val();
	var imgUrl = $("#imgUrl").val();
	var rex = /^(upload)\S{1,}$/;
	
	if(null == files || !rex.test(files)){
		Notify("请上传图片", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/news/addBanner",
		data:{image:files,imgURL:imgUrl},
		dataType :"json",
		async : false,
		timeout : 5000,
		success:function(respObj){
			if(respObj.code == 200){
				Notify("添加成功", 'top-right', '5000', 'info', 'fa-desktop', true);
				initData();
				return true;
			}else{
				Notify("添加失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
				return false;
			}
		}
	});
}



function delAds(){
	var bannerId = $("#tempADsId").val();
	if(bannerId == 0){
		Notify("删除失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
	}else{
		$.ajax({
			type : "GET",
			url : "/SanghaCloud/news/delBanner",
			data:{bannerId:bannerId},
			dataType :"json",
			async : false,
			timeout : 5000,
			success:function(respObj){
				if(respObj.code == 200){
					initData();
					Notify("操作成功", 'top-right', '5000', 'info', 'fa-desktop', true);
					return true;
				}else{
					Notify("操作失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
					return false;
				}
			}
		});
	}
}
function delAds(id) {
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
            		url : "/SanghaCloud/news/delBanner",
            		data : {bannerId:id},
            		dataType :"json",
            		async : false,
            		timeout : 5000,
            		success : function(respObj){
            			var status = respObj.code;
            			if(status==200){
            				Notify(respObj.msg,'top-right','5000','info','fa-desktop',true);
            				initData();
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

















