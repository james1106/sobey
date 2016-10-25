/**
 * 
 */

function submitInfo(){
	var isSuccess = false;
	var isOk = verifyFrom();
	if(!isOk){
		return false;
	}
	var title = $("#title").val();
	var type = $("#type").val();
	var brief = $("#brief").val();
	var content = UE.getEditor('editor').getContent();
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/msgCenter/sendNotice", // URL
		data : $("#noticefrom").serialize(),
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			if(respObj.code == 200){
				Notify("操作成功", 'top-right', '5000', 'info', 'fa-desktop', true);
				isSuccess = true;
			}else{
				Notify("操作失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
			}
		}
	});
	return isSuccess;
}

function verifyFrom(){
	var title = $("#title").val();
	var type = $("#type").val();
	var brief = $("#brief").val();
	
	if(title == null || $.trim(title).length == 0){
		Notify("标题为空", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	if(brief == null || $.trim(brief).length == 0){
		Notify("简要为空", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return false;
	}
	return true;
}