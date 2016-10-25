/**
 * 
 */


function initData(pageNO,type){
	
	var totalPage=1;
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/news/getNewsList",
		data : {pageNO:pageNO,pageSize:8,typeId:type},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				buildData(respObj.data.lists,type);
				totalPage = respObj.data.totalPage == 0 ? 1 : respObj.data.totalPage;
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return totalPage;
}

function buildData(data,type) {
	
	var len = data.length;
	console.debug(len)
	var str = "";
//	if(len == 0){
//		str+="<tr><td style='width: 100%'>没有记录</td></tr>";
//	}else{
		for(var i=0; i<len; i++){
			var temp = data[i];
			var titleSub = "";
			var introductionSub = "";
			if(temp.title.length > 8){
				titleSub = temp.title.substring(0,7)+"...";
				introductionSub = temp.introduction.substring(0,7)+"...";
			}else{
				titleSub = temp.title;
				introductionSub = temp.introduction;
			}
			
			str += "<tr><td>"+temp.id+"</td>" +
					"<td><span data-toggle='popover-hover' data-title='资讯标题' data-content='"+temp.title+"'>"+titleSub+"</span></td>" +
							"<td><span data-toggle='popover-hover' data-title='资讯概要' data-content='"+temp.introduction+"'>"+introductionSub+"</span></td>" +
									"<td>"+temp.reads+"</td><td>"+temp.realReads+"</td><td>"+temp.likes+"</td><td>"+temp.userName+"</td>" +
											"<td><a href='/SanghaCloud/admin/requestpage/news/detail?newsId="+temp.id+"' target='iframe' class='btn btn-info' >详情</a>" +
											"		<a href='/SanghaCloud/admin/requestpage/news/edit?newsId="+temp.id+"' target='iframe' class='btn btn-info'>修改</a>" +
											"		<a onclick='tanc("+temp.id+")'  class='btn btn-info'>删除</a></td></tr>";
		}
		// /SanghaCloud/admin/requestpage/news/detail?newsId="+temp.id+"  修改链接
		$("#dataBody").html(str);
	}
	
	
//}
function tanc(newId) {
	$("#tempNewsId").val(newId);
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
            	deslNews();
            }
        }
    }
});
}

$("#nonlink").click(function(){
	$("#tagetLink").hide("slow");
	$("#content").show("slow");
	$("#readsDiv").show("slow");
})

$("#link").click(function(){
	$("#tagetLink").show("slow");
	$("#content").hide("slow");
	$("#readsDiv").hide("slow");
	$("#voteBigDiv").hide("slow");
})

$("#vote").click(function(){
	$("#voteDiv").show("slow");
})

$("#nonvote").click(function(){
	$("#voteDiv").hide("slow");
})

function addVote(){
	$("#voteDecriDiv").append("<span class='input-icon icon-right' ><input type='text' name='votedecri' style='width: 600px;margin-top: 10px' placeholder='投票描述'>" +
			"<a onclick='delInput(this)' class='btn btn-danger' style='margin-left: 10px'  >删除</a></span>");
}

function delInput(obj){
	var jqObj = $(obj);
	var spantemp = jqObj.parent();
	spantemp.remove();
}



function confirm(){
	var isOk = false;
	var reads = $("#reads").val();
	if(reads.length >= 8){
		Notify("阅读数无效",'top-right','5000','danger','fa-desktop',true);
		return isOk;
	}
	var introduction = $("#introduction").val();
	if(introduction.length >= 200){
		Notify("概要要求100字以内",'top-right','5000','danger','fa-desktop',true);
		return isOk;
	}
	$.ajax({
		type:"POST",
		url:"/SanghaCloud/news/addNews",
		data:$("#newsfrom").serialize(),
		dataType:"json",
		async : false,
		timeout:5000,
		success:function(json){
			if(json.code == 200){
				isOk = true;
			}else{
				Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	})
	return isOk;
}




function checkContent() {
	var content = UE.getEditor('editor').getContent();
}

function deslNews(){
	var news = $("#tempNewsId").val();
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/news/delNews",
		data : {newsId:news},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				 	var types = $("#type").val();
	            	 initData(1,types);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
}
