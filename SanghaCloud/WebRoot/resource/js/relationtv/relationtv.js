/**
 * 
 */
function initData(pageNO,realName,roleId) {
	
	var totalPage=1;
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/user/getUsers",
		data : {pageNO:pageNO,pageSize:8,flag:2,realName:realName,roleId:roleId},
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
					buildData(respObj.data);
				}
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return totalPage;
	
}
function buildData(data) {
	var len = data.dataList.length;
	var str = "";
	for(var i=0; i<len; i++ ){
		var obj = data.dataList[i];
		var officeName= '';
		var email = '';
		if(obj.email != null){
			email = obj.email;
		}
		if(obj.companyName != undefined && obj.companyName != null){
			officeName = obj.companyName;
		}
		if(obj.officeName != undefined && obj.officeName != null ){
			officeName = obj.officeName;
		}
		
		var time = new Date(obj.updateDate).Format("yyyy-MM-dd HH:mm:ss");
		var createDate = new Date(obj.createDate).Format("yyyy-MM-dd HH:mm:ss");
		str += "<tr><td>"+obj.realName+"</td><td>"+obj.mobile+"</td><td>"+email+"</td><td>"+officeName+"</td><td>"+obj.role.roleName+"</td>" +
				"<td>"+time+"</td>" +
						"<td><a href='/SanghaCloud/admin/requestpage/relationTV/detail?userId="+obj.id+"&realName="+obj.realName+"' target='iframe' class='btn btn-info'>查看绑定</a>" +
						"		<a href='/SanghaCloud/admin/requestpage/relationTV/edit?userId="+obj.id+"&realName="+obj.realName+"&roleId="+obj.roleId+"' target='iframe' class='btn btn-info'>设置绑定</a>" +
						"		<a  onclick='clearData("+obj.id+")' target='iframe' class='btn btn-info'>清空绑定</a>"+
						"</td></tr>";
		
	}
	$("#dataBody").html(str);
}



Date.prototype.Format = function(fmt)   
{    
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "H+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}  
