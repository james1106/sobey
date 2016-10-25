/**
 * 
 */
// 验证title必须输入
$("#eventName").blur(function(){
	var title = $("#eventName").val();
	if(null == title || title== ''){
		alert("填写文章标题")
	}
})