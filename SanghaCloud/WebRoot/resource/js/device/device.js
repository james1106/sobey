/**
 * 
 */

function update() {

	var str = '<div id="myModal">' + '<div class="form-group">'
			+ '<label></label>' + '<span class="input-icon icon-right">'
			+ '<div class="input-group">'
			+ '<span id="name" >尽量避开用户使用高峰期执行此操作, 确认更新?</span>' + '</div>'
			+ '</span>' + '</div>' + '</div>';
	bootbox.dialog({
		message : str,
		title : "提示框",
		buttons : {
			"关闭" : {
				className : "btn-default",
				callback : function() {
					jindu();
				}
			},
			success : {
				label : "确认",
				className : "btn-danger",
				callback : function() {
					
					$.ajax({
						type : "GET",
						url : "/SanghaCloud/deviceManage/updateDevice",
						dataType : "json",
						async : false,
						timeout : 5000,
						success : function(respObj) {
							var status = respObj.code;
							if (status == 200) {
								Notify(respObj.msg, 'top-right', '5000','info', 'fa-desktop', true);
							} else {
								Notify(respObj.msg, 'top-right', '5000','danger', 'fa-desktop', true);
							}
						}
					});
				}
			}
		}
	});
}

function jindu(){
	
}


function updateReport(){
	var str = '<div id="myModal">' + '<div class="form-group">'
	+ '<label></label>' + '<span class="input-icon icon-right">'
	+ '<div class="input-group">'
	+ '<span id="name" >尽量避开用户使用高峰期执行此操作, 确认更新?</span>' + '</div>'
	+ '</span>' + '</div>' + '</div>';
		bootbox.dialog({
		message : str,
		title : "提示框",
		buttons : {
			"关闭" : {
				className : "btn-default",
				callback : function() {
		
				}
			},
			success : {
				label : "确认",
				className : "btn-danger",
				callback : function() {
					
					$.ajax({
						type : "GET",
						url : "/SanghaCloud/deviceManage/updateReport",
						dataType : "json",
						async : false,
						timeout : 5000,
						success : function(respObj) {
							var status = respObj.code;
							if (status == 200) {
								Notify(respObj.msg, 'top-right', '5000','info', 'fa-desktop', true);
							} else {
								Notify(respObj.msg, 'top-right', '5000','danger', 'fa-desktop', true);
							}
						}
					});
				}
			}
		}
	});
}

