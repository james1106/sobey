
function initData(){
	$.ajax({
		type:"POST",
		url:"/SanghaCloud/admin/order/countIndex",
		dataType:"json",
		timeout:5000,
		success:function(json){
			if(json.code == 200){
				$("#finished").html(json.data.over);
				$("#nonover").html(json.data.nonOver);
				$("#userNonPass").html(json.data.userData.nonPass);
				$("#userAll").html(json.data.userData.all);
				$("#groupUserNonPass").html(json.data.groupUserData.nonPass);
				$("#groupUserAll").html(json.data.groupUserData.all);
			}
		}
	});
}