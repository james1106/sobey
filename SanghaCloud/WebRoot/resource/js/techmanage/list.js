/**
 * 
 */
function initData(pageNO,realName){
	var totalPage = 1;
	
	$.ajax({
		type:"post",
		url:"/SanghaCloud/admin/user/getTechUser",
		data:{pageNO:pageNO,pageSize:8,realName:realName},
		dataType:"json",
		async : false,
		timeout:5000,
		success:function(json){
			if(json.code == 200){
				totalPage = json.data.totalPage == 0 ? 1 : json.data.totalPage;
				buildData(json.data.dataList);
			}else{
				Notify("数据异常",'top-right','5000','danger','fa-desktop',true);
			}
		}
	});
	
	return totalPage;
}

function buildData(data){
	
	var len = data.length;
	var str = "";
	if(len == 0){
		str = "<tr><td>没有数据</td></tr>";
	}else{
		for(var i=0; i < len; i++){
			var obj = data[i];
			var tech = "";
			if(obj.userCates.length != 0){
				for (var j =0; j < obj.userCates.length; j++){
					tech += obj.userCates[j].categoryName+" | ";
				}
			}else{
				tech = "空";
			}
			str += "<tr>" +
					"<td class='name'>"+obj.realName+"</td>" +
					"<td class='mobile'>"+obj.mobile+"</td>" +
					"<td>"+obj.role.roleName+"</td>" +
					"<td>"+tech+"</td>" +
					"<td><a href='javascript:settech("+obj.id+",this)' class='btn btn-info'>清空</a>" +
					"		 <a href='/SanghaCloud/admin/requestpage/techmanage/edit?id="+obj.id+"&mobile="+obj.mobile+"&realName="+obj.realName+"' class='btn btn-info'> 设置产品技能</a></td>" +
					"</tr>";
		}
	}
	// /SanghaCloud/admin/requestpage/techmanage/edit?id="+obj.id+"&mobile="+obj.mobile+"&realName="+obj.realName+"'
	$("#dataBody").html(str);
}
//function requestSet(id,obj){
//	var tds = $(obj).parent().parent().find("td");
//	var tempRealName = tds[0];
//	var tempMobile = tds[1];
//	var realName = $(tempRealName).text();
//	var mobile = $(tempMobile).text();
//	$.ajax({
//		type:"POST",
//		url:"/SanghaCloud/admin/requestpage/techmanage/edit",
//		data:{id:id,mobile:mobile,realName:realName},
//		dataType:"json",
//		async : false,
//		timeout:5000,
//		success:function(json){
//			
//		}
//	})
//}

function settech(id,obj){
	tanc("确认设置该用户为 非专技术 吗?",id);
}

function tanc(msg,id) {
	var str='<div id="myModal">'+
  	'<div class="form-group">'+
        '<label></label>'+
        '<span class="input-icon icon-right">'+
        	'<div class="input-group">'+
        		'<span id="name" >'+msg+'</span>'+
            '</div>'+
        '</span>'+
   '</div>'+
'</div>';
bootbox.dialog({
    message: str,
    title: "提示框",
    buttons: {
    	"取消": {
            className: "btn-default",
            callback: function () {
            	
            }
      	},
        success: {
            label: "确认",
            className: "btn-danger",
            callback: function () {
            	$.ajax({
            		type:"post",
            		url:"/SanghaCloud/admin/user/updateUserCate",
            		data:{groupUserId:id,flag:0},
            		async:false,
            		dataType:"json",
            		timeout:5000,
            		success:function(json){
            			if(json.code == 200){
            				Notify("操作成功",'top-right','5000','info','fa-desktop',true);
            				initData(1,null);
            			}else{
            				Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
            			}
            		}
            	});
            }
        }
    }
});
}