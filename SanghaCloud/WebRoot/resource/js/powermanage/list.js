/**
 * 
 */

function initData(){
	
	$.ajax({
		type:"post",
		url:"/SanghaCloud/rolePower/getRolePower",
		dataType:"json",
		timeout:5000,
		success:function(json){
			buildData(json.data);
		}
	});
}

function buildData(data){
	var len = data.length;
	var str = "";
	if(len == 0){
		str = "<tr><td>没有数据</td></tr>";
	}else{
		for(var i=0; i < len ; i++){
			var obj = data[i];
			var powerStr = "";
			if(obj.powers != null && obj.powers.length > 0){
				for(var j=0 ; j < obj.powers.length; j++){
					powerStr += obj.powers[j].power.description+"   |   ";
				}
			}else{
				powerStr = "无";
			}
			str += "<tr><td>"+obj.roleName+"</td><td>"+powerStr+"</td></tr>";
		}
	}
	$("#dataBody").html(str);
}