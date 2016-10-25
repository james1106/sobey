/**
 * 
 */

function initData(pageNO,realName,type){
	
	var totalPage = 1;
	
	$.ajax({
		type:"POST",
		url:"/SanghaCloud/admin/sign/getSignList",
		data:{pageNO:pageNO,pageSize:8,realName:realName,type:type},
		dataType:"json",
		async : false,
		timeout:5000,
		success:function(json){
			if(json.code == 200){
				totalPage = json.data.totalPage == 0 ? 1 : json.data.totalPage;
				buildData(json.data.dataList,type);
			}else{
				Notify("数据异常",'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return totalPage;
}

function buildData(data,tempType){
	var len = data.length;
	var str = "";
	if(len == 0){
		str = "<tr><td>没有数据</td></tr>";
	}else{
		for(var i=0; i < len ; i++){
			var obj = data[i];
			var type ="用户";
			if(tempType == 0){
				//type  0 员工 1 用户
				type = "员工";
			}
			var bonus = 0;
			if(obj.countBonus != null){
				bonus = obj.countBonus;
			}
			str += "<tr><td>"+obj.realName+"</td>" +
					"		  <td>"+obj.mobile+"</td>" +
							"<td>"+type+"</td>" +
							"<td>"+bonus+"</td>" +
							"<td>"+obj.countDays+"(天)</td>" +
							"<td><a href='/SanghaCloud/admin/requestpage/sign/detail?id="+obj.id+"&type="+tempType+"&realName="+obj.realName+"&mobile="+obj.mobile+"'  target='iframe' class='btn btn-info'>详情</a></td></tr>";
		}
	}
	$("#dataBody").html(str);
}