


function commit(){
	var isOK = false;
	var officeId = $("#office").val();
	var tvName = $.trim($("#tvName").val());
	if(tvName.length == 0){
		Notify("无效的名称",'top-right','5000','danger','fa-desktop',true);
		return isOK;
	}
	var stationCode = $.trim($("#stationCode").val());
//	if(stationCode.length == 0){
//		Notify("无效的编码",'top-right','5000','danger','fa-desktop',true);
//		return isOK;
//	}
	if(officeId == 0){
		Notify("无效的办事处",'top-right','5000','danger','fa-desktop',true);
		return isOK;
	}
	
	$.ajax({
		type:"post",
		url:"/SanghaCloud/admin/tv/addTV",
		data:$('#tvFrom').serialize(),
		dataType:"json",
		timeout:5000,
		async:false,
		success:function(json){
			if(json.code == 200){
				Notify("操作成功",'top-right','5000','info','fa-desktop',true);
				isOK = true;
			}else{
				Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return isOK;
}










$("#company").focus(function () {
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/companyMobile/queryCompanyByType",
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				buildSelectData(respObj.data);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
})
function buildSelectData(dataList){
	
	var str = "";
	for(var i=0; i<dataList.length; i++){
		var temp = dataList[i];
		str+="<option value='"+temp.id+"'>"+temp.companyName+"</option>";
	}
	$("#company").html(str);
}

$("#office").focus(function () {
	var companyId = $("#company").val();
	if(companyId == 0){
		Notify('没有选择分公司','top-right','5000','danger','fa-desktop',true);
		return;
	}
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/officeMobile/getOfficeByCompany",
		data:{companyId:companyId},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				buildOfficeSelect(respObj.data);
			}else if(status == 1006){
				buildOfficeNoDataSelect();
				Notify("没有数据",'top-right','5000','info','fa-desktop',true);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
})
function buildOfficeNoDataSelect() {
	
		str = "<option value='0'>没有数据</option>";
		$("#office").html(str);
}

function buildOfficeSelect(dataList){
	var str = "";
	var len = dataList.length;
	if(len != 0){
		for(var i=0; i < len; i++){
			var obj = dataList[i];
			str += "<option value="+obj.id+">"+obj.officeName+"</option>";
		}
	}else{
		str = "<option value='0'>没有数据</option>";
	}
	$("#office").html(str);
	
}