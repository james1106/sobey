/**
 * 
 */
function initData(roleId){
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/rolePower/getPower",
		data:{roleId:roleId},
		dataType : "json",
		async : false,
		timeout : 5000,
		success : function(json) {
			if(json.code == 200){
				var dataList = json.data;
				buildMenu(dataList,roleId);
			}else{
				Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	})
}
function buildMenu(dataList,roleId){
	var len = dataList.length;
	var menuStr = "";
	if(len == 0){
		return;
	}
	for(var i=0; i<len; i++){
		var obj = dataList[i];
		if(obj.roleId == roleId){
			menuStr += createMenu(obj.url,obj.description);
		}
	}
//	console.debug(menuStr);
	menuStr +=defaultMenu();
	$("#sidebar-menu").html(menuStr);
}

function createMenu(url,menuDecript){
	var menu = "<li>" +
			"				<a href='/SanghaCloud/admin/requestpage"+url+"' target='iframe'>" +
					"		 	<i class='menu-icon glyphicon glyphicon-home'></i>" +
					"			<span class='menu-text'> "+menuDecript+" </span>" +
							"</a>" +
			"			</li>";
	return menu;
}

function defaultMenu(){
	var menuStr = "<li>" +
			"					<a href='#' class='menu-dropdown'>" +
			"						<i class='menu-icon fa fa-envelope'></i>" +
			"						<span class='menu-text'> 个人中心 </span>" +
			"					</a>" +
					"			 <ul class='submenu'>" +
					"				<li>" +
					"					 <a href='/SanghaCloud/admin/requestpage/selfCenter/order' target='iframe'>" +
					"						  <span class='menu-text'>订单管理</span>" +
							"			</a>" +
							"		</li>" +
							"		<li>" +
							"			<a href='/SanghaCloud/admin/requestpage/selfCenter/editPwd' target='iframe'>" +
							"				<span class='menu-text'>修改密码</span>" +
							"			</a>" +
							"		</li>" +
							"	</ul>" +
					"		<li>" +
					"		<li>" +
					"			<a href='/SanghaCloud/admin/requestpage/loginout'>" +
					"				 <i class='menu-icon fa fa-sign-out'></i>" +
					"				<span class='menu-text'> 登出 </span>" +
					"			</a>" +
					"		</li>";
	return menuStr;
}



















