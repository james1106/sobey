/**
 * 
 */

//$("#category").on("select2-open", function(e) {
//	$.ajax({
//		type:"POST",
//		url:"/SanghaCloud/admin/order/getcategorys",
//		dataType:"json",
//		timeout:5000,
//		success:function(json){
//			if(json.code == 200){
//				console.debug(json);
//				buildData(json.data);
//			}
//		}
//	});
//})
//
//function buildData(dataList){
//	var len = dataList.length;
//	var str = "";
//	if(len == 0){
//		str ="<option value=''>没有数据</option>";
//	}else{
//		str +="<option value=''>无</option>";
//		for(var i=0; i < len; i++){
//			var obj = dataList[i];
//			str += "<option value="+obj.id+">"+obj.categoryName+"</option>";
//		}
//	}
//	$("#category").html(str);
//	
//}
//
//$("#employee").on("select2-open", function(e) {
//	$.ajax({
//		type:"POST",
//		url:"/SanghaCloud/admin/order/getemployee",
//		dataType:"json",
//		timeout:5000,
//		success:function(json){
//			if(json.code == 200){
//				buildEmployeeData(json.data);
//			}
//		}
//	});
//})
//function buildEmployeeData(dataList){
//	var len = dataList.length;
//	var str = "";
//	if(len == 0){
//		str ="<option value=''>没有数据</option>";
//	}else{
//		str +="<option value=''>无</option>";
//		for(var i=0; i < len; i++){
//			var obj = dataList[i];
//			str += "<option value="+obj.id+"|"+obj.roleId+">"+obj.realName+"("+obj.mobile+")</option>";
//		}
//	}
//	$("#employee").html(str);
//}

$('#confirmBtn').click(function(){
	var employeeId = $("#employee").val();
	var categoryId = $("#category").val();
	var startDate = $("#date-picker").val();
	var endDate = $("#date-picke").val();
	$.ajax({
		type:"POST",
		url:"/SanghaCloud/admin/order/countByItem",
		data:{userId:employeeId,categoryId:categoryId,startDate:startDate,endDate:endDate},
		dataType:"json",
		timeout:5000,
		success:function(json){
			if(json.code == 200){
				$("#finished").html(json.data.over);
				$("#nonover").html(json.data.nonOver);
			}
		}
	});
})

