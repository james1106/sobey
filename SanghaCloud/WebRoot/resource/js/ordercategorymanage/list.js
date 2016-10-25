/**
 * 
 */

function initData(pageNO,type) {
	
	var totalPage=1;
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/ordercategory/getcategory",
		data : {pageNO:pageNO,pageSize:8,type:type},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				totalPage = respObj.data.totalPage == 0 ? 1 : respObj.data.totalPage ;
				if(respObj.data.dataList.length == 0){
					$("#dataBody").html("<tr><td>没有数据</td></tr>");
				}else{
					buildData(respObj.data.dataList,type);
				}
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return totalPage;
}
function buildData(data,type){
	var str ="";
	for(var i =0 ; i < data.length; i++){
		var obj = data[i];
		var upname = "硬件类";
		if(obj.type == 0){
			upname = "软件类";
		}
		str += "<tr><td>"+obj.categoryName+"</td>" +
				"		  <td>"+upname+"</td>" +
						"<td><a href='/SanghaCloud/admin/requestpage/ordercategorymanage/edit?id="+obj.id+"&categoryName="+obj.categoryName+"&upName="+upname+"' class='btn btn-info'>修改</a></td></tr>";
	}
	
	$("#dataBody").html(str);
}