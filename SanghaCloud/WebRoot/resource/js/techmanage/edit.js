/**
 * 
 */
var groupUserId;
function initData(id){
	groupUserId = id;
	$.ajax({
		type:"post",
		url:"/SanghaCloud/admin/user/getallcate",
		data:{id:id},
		dataType:"json",
		async : false,
		timeout:5000,
		success:function(json){
			console.debug(json);
			if(json.code == 200){
				buildData(json.data,id);
			}else{
				Notify("数据异常",'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
}

function buildData(data,id){
	if(data.length == 0){
		Notify("数据异常且数据为空",'top-right','5000','danger','fa-desktop',true);
		return;
	}
	var str ="";
	for(var i=0; i < data.length; i++){
		var obj = data[i];
		var checked = "";
		if(obj.groupuserId == id){
			checked = "checked='checked'";
		}
		str += "<lable class='checkbox-inline'>" +
				"	<input type='checkbox' name='categoryId' value="+obj.categoryId+"  "+checked+">"+obj.categoryName+"</lable>"
	}
	$("#dataSpan").html(str);
}


function commit(){
	var isOK = false;
	$.ajax({
		type:"post",
		url:"/SanghaCloud/admin/user/updateUserCate",
		data:$("#cateFrom").serialize()+"&flag=1&groupUserId="+groupUserId,
		dataType:"json",
		async : false,
		timeout:5000,
		success:function(json){
			if(json.code == 200){
				isOK = true;
				Notify("修改成功",'top-right','5000','info','fa-desktop',true);
			}else{
				Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return isOK;
}


