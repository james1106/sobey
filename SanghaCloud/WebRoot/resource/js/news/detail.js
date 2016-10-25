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
	$("#type").val(data.typeId);
	 $("#introduction").val(data.introduction);
	$("#reads").val(data.reads);
	$("input[name='isShowIco'][value="+data.isShowIcon+"]").attr("checked",true); 
	$("input[name='isBanner'][value="+data.isBanner+"]").attr("checked",true); 
	$("#oldIsBanner").val(data.isBanner);
	$("input[name='isLink'][value="+data.isUrl+"]").attr("checked",true); 
	$("input[name='isCloseVote'][value="+data.isVote+"]").attr("checked",true); 
	if(data.isUrl == 1){
		$("#tagetLink").show("slow");
		$("#tagetLink").val(data.linkUrl);
	}
	$("#reads").val(data.reads);
	var is = ue.ready( function () {
		ue.setContent(data.content, false);
		ue.setDisabled(true);
	});
	
}