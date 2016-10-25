/**
 * 
 */
function initData(){
	
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/lable/getLables",
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(json){
			var status = json.code;
			if(status==200){
				
				buildServiceData(json.data.serviceLable);
				buildTechData(json.data.techLable);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
}

function buildServiceData(dataList){
	var len = dataList.length;
	var str = "";
	if(len == 0){
		str = "<tr><td>没有数据</td></tr>";
	}else{
		for(var i=0; i < len ; i++){
			
			var obj = dataList[i];
			var checked = "";
			if(obj.number != null){
				checked = "checked";
			}
			str += "<input type='checkbox'  disabled='disabled' "+checked+">"+obj.lable+"&nbsp;&nbsp;&nbsp;";
		}
	}
	$("#serviceDataBody").html(str);
}
function buildTechData(dataList){
	var len = dataList.length;
	var str = "";
	if(len == 0){
		str = "<tr><td>没有数据</td></tr>";
	}else{
		for(var i=0; i < len ; i++){
			
			var obj = dataList[i];
			var checked = "";
			if(obj.number != null){
				checked = "checked";
			}
			str += "<input type='checkbox'  disabled='disabled' "+checked+">"+obj.lable+"&nbsp;&nbsp;&nbsp;";
		}
	}
	$("#techDataBody").html(str);
}