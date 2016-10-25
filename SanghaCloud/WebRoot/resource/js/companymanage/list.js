/**
 * 
 */
function initData(pageNO, companyName, flag) {
	var totalPage = 1;
	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/company/getcompany",
		data : {
			pageNO : pageNO,
			pageSize : 8,
			flag : flag,
			companyName : companyName
		},
		dataType : "json",
		async : false,
		timeout : 5000,
		success : function(json) {
			if (json.code == 200) {
				totalPage = json.data.totalPage == 0 ? 1 : json.data.totalPage;
				buildData(json.data.dataList,flag);
			} else {
				Notify(json.msg, 'top-right', '5000', 'danger', 'fa-desktop',true);
			}
		}
	});
	return totalPage;
}

function buildData(data,flag){
	
	var len = data.length;
	var str = "";
	if(len == 0){
		str = "<tr><td>没有数据</td></tr>";
	}else{
		var upName = "";
		if(flag == 1){
			for(var i=0 ;i < len; i++){
				var tempobj = data[i];
				if(tempobj.type == 0){
					upName = tempobj.companyName;
					continue;
				}
			}
		}
		for(var i=0; i < len ; i++){
			var obj = data[i];
			if(obj.type == 0){
				continue;
			}
			var name = obj.companyName;
			if(obj.type == 2 ){
				upName = obj.company.companyName;
			}
			str += "<tr><td>"+name+"</td><td>"+upName+"</td><td><a href='/SanghaCloud/admin/requestpage/companymanage/edit?id="+obj.id+"&upName="+upName+"&company="+name+"&type="+obj.type+"' class='btn btn-info'>修改</a></td></tr>";
		}
	}
	$("#dataBody").html(str);
}