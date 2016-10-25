/**
 * 
 */

function initData(type) {

	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/lable/getLablesByRole",
		data : {
			type : type
		},
		dataType : "json",
		async : false,
		timeout : 5000,
		success : function(json) {
			var status = json.code;
			if (status == 200) {
				buildData(json.data);
			} else {
				Notify(respObj.msg, 'top-right', '5000', 'danger','fa-desktop', true);
			}
		}
	});

}
function buildData(dataList) {
	var len = dataList.length;
	var str = "";
	if (len == 0) {
		str = "<tr><td>没有数据</td></tr>";
	} else {
		for (var i = 0; i < len; i++) {

			var obj = dataList[i];
			var checked = "";
			if (obj.number != null) {
				checked = "checked";
			}
			str += "<input type='checkbox'  " + checked
					+ " name='lables' value=" + obj.id + ">" + obj.lable
					+ "&nbsp;&nbsp;&nbsp;";
		}
	}
	$("#dataTD").html(str);
}

function submitInfo() {
	var isOK = false;
	var type = $("#type").val();
	if (type == 0) {
		Notify("没有选择设置项", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return isOK;
	}
	var boxArray = document.getElementsByName('lables');
	var total = 0;
	for (var i = 0; i < boxArray.length; i++) {
		if (boxArray[i].checked) {
			total++;
		}
	}
	if (total > 6) {
		Notify("只能选择5项", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return isOK;
	}
	if (total == 0) {
		Notify("至少选择一项", 'top-right', '5000', 'danger', 'fa-desktop', true);
		return isOK;
	}

	var lables = $("input[name='lables']:checked").serialize();
	console.debug(lables);
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/lable/updateLables",
		data : lables + "&type=" + type,
		dataType : "json",
		async : false,
		timeout : 5000,
		success : function(json) {
			var status = json.code;
			if (status == 200) {
				isOK = true;
			} else {
				Notify(json.msg, 'top-right', '5000', 'danger', 'fa-desktop',
						true);
			}
		}
	});
	return isOK;
}



