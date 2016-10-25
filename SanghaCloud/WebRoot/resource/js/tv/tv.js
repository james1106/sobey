/**
 * 
 */

function initData(pageNO,type,tvName){
	var totalPage = 1;
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/tv/getTVList",
		data : {pageNO:pageNO,pageSize:8,officeStatus:type,tvName:tvName},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				buildData(respObj.data);
				totalPage = respObj.data.totalPage;
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	return totalPage;
}

function buildData(obj){
	var dataList = obj.dataList;
	var str = "";
	for(var i=0; i<dataList.length; i++){
		var temp = dataList[i];
		var officeName = "----------";
		if(temp.officeName != null){
			officeName = temp.officeName;
		}
		str+= "<tr><td>"+temp.id+"</td><td>"+temp.tvName+"</td><td>"+temp.address+"</td>" +
				"<td><span class='input-icon icon-right'><select name='company' onfocus='getCompany(this)' class='form-control'>" +
					"<option value='0'>----------</option></select></span></td><td>" +
					" <span class='input-icon icon-right'><select name='office' onfocus='getOffice(this)' class='form-control'>" +
					"	<option value='0'>"+officeName+"</option></select></span></td><td>" +
					"<a onclick='updateTV(this,"+temp.id+")' class='btn btn-info'>确认</a>" +
					"<a href='/SanghaCloud/admin/requestpage/tv/edit?id="+temp.id+"&tvName="+temp.tvName+"&stationCode="+temp.stationCode+"' class='btn btn-info'>修改</a></td></tr>";
	}
	$("#dataBody").html(str);
}

function getCompany(obj){
	// 获取分公司列表
	var jqObj = $(obj);
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/companyMobile/queryCompanyByType",
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				buildSelectData(respObj.data,jqObj);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
}

function buildSelectData(dataList,jqObj){
	
	var str = "";
	for(var i=0; i<dataList.length; i++){
		var temp = dataList[i];
		str+="<option value='"+temp.id+"'>"+temp.companyName+"</option>";
	}
	jqObj.html(str);
	
}

function getOffice(obj){
	// 获取办事处列表
	var jqObj = $(obj);
	var comId = jqObj.parent().parent().prev().find("select").eq(0);
	var companyId = comId.val();
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
				buildOfficeSelect(respObj.data,jqObj);
			}else if(status == 1006){
				Notify("没有数据",'top-right','5000','info','fa-desktop',true);
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
}

function buildOfficeSelect(dataList,jqObj){
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
	jqObj.html(str);
	
}

function updateTV(obj,tvId){
	
	var jqObj = $(obj);
	var office = jqObj.parent().prev().find("select").eq(0).val();
	if(office == 0){
		Notify("请选择所属机构",'top-right','5000','danger','fa-desktop',true);
		return;
	}
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/tv/updateOffice",
		data:{officeId:office,tvId:tvId},
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==200){
				Notify("修改成功",'top-right','5000','info','fa-desktop',true);
				initData(1,$("#type").val());
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	
}

function update(){
	// 更新电视台列表
		var str='<div id="myModal">'+
	  	'<div class="form-group">'+
	        '<label></label>'+
	        '<span class="input-icon icon-right">'+
	        	'<div class="input-group">'+
	        		'<span id="name" >尽量在用户使用闲时执行此操作，确认更新吗？</span>'+
	            '</div>'+
	        '</span>'+
	   '</div>'+
	'</div>';
	bootbox.dialog({
	    message: str,
	    title: "提示框",
	    buttons: {
	    	"关闭": {
	            className: "btn-default",
	            callback: function () {
	            	
	            }
	      	},
	        success: {
	            label: "确认",
	            className: "btn-danger",
	            callback: function () {
	            	exUpdate();
	            }
	        }
	    }
	});
	
}

function exUpdate(){
	$.ajax({
		type : "GET",
		url : "/SanghaCloud/admin/tv/updateTVList",
		dataType :"json",
		async : false,
		timeout : 5000,
		success : function(respObj){
			var status = respObj.code;
			if(status==1006){
				alert("已是最新数据");
			}else if(status == 200){
				alert("更新成功");
			}else{
				Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
}
























