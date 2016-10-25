/**
 * 
 */

function initData(newsId,ue){
	
	$.ajax({
		type:"post",
		url:"/SanghaCloud/news/getNewsById",
		async:false,
		data:{newsId:newsId},
		dataType:"json",
		timeout:5000,
		success:function(json){
			if(json.code == 200){
				buildData(json.data,ue);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
}

function buildData(data,ue){
	$("#title").val(data.title);
	$("#imgId").attr('src',data.imageUrl); 
	$("#oldImgUrl").val(data.imageUrl);
	$("#type").val(data.typeId);
	 $("#introduction").val(data.introduction);
	$("#reads").val(data.reads);
	$("input[name='isShowIco'][value="+data.isShowIcon+"]").attr("checked",true); 
	$("input[name='isBanner'][value="+data.isBanner+"]").attr("checked",true); 
	$("#oldIsBanner").val(data.isBanner);
	$("input[name='isLink'][value="+data.isUrl+"]").attr("checked",true); 
	if(data.isUrl == 1){
		$("#tagetLink").show("slow");
		$("#tagetLink").val(data.linkUrl);
	}
	$("#reads").val(data.reads);
	if(data.isVote == 1){
		$("#closeVoteDiv").show("slow");
	}
	var is = ue.ready( function () {
		ue.setContent(data.content, false);
	});
	
}

$("#nonEditImg").click(function(){
	$("#uploadImageDiv").hide("slow");
	$("#imgDiv").show("slow");
});

$("#editImg").click(function(){
	$("#uploadImageDiv").show("slow");
	$("#imgDiv").hide("slow");
});

$("#nonlink").click(function(){
	$("#tagetLink").hide("slow");
	$("#tagetLink").val("");
});
$("#link").click(function(){
	$("#tagetLink").show("slow");
});


function confirm(newsId){
	var isOK = false;
	var title = $.trim($("#title").val());
	if(title.length == 0){
		Notify("标题不能为空",'top-right','5000','danger','fa-desktop',true);
		return isOK;
	}
	var introduction = $.trim($("#introduction").val());
	if(introduction.length == 0){
		Notify("概要不能为空",'top-right','5000','danger','fa-desktop',true);
		return isOK;
	}
	$.ajax({
		type:"POST",
		url:"/SanghaCloud/admin/news/edit",
		data:$("#newsfrom").serialize()+"&newsId="+newsId,
		dataType:"json",
		async : false,
		timeout:5000,
		success:function(json){
			if(json.code == 200){
				isOK = true;
			}else{
				Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return isOK;
}




























