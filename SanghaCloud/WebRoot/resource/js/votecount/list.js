/**
 * 
 */
function initData(pageNO, title) {
	var totalPage = 1;

	$.ajax({
		type : "POST",
		url : "/SanghaCloud/admin/vote/getVotes",
		data : {
			pageNO : pageNO,
			pageSize : 8,
			title : title
		},
		dataType : "json",
		async : false,
		timeout : 5000,
		success : function(json) {
			if (json.code == 200) {
				totalPage = json.data.totalPage == 0 ? 1 : json.data.totalPage;
				buildData(json.data.dataList);
			} else {
				Notify("数据异常", 'top-right', '5000', 'danger', 'fa-desktop',
						true);
			}
		}
	});
	return totalPage;
}

function buildData(data) {
	var len = data.length;
	var str = "";
	if (len == 0) {
		str = "<tr><td>没有数据</td></tr>";
	} else {
		for (var i = 0; i < len; i++) {
			var obj = data[i];
			str += "<tr><td>"+obj.title+"</td><td>"+obj.publisherName+"</td><td>"+obj.type+"</td>" +
					"		  <td><a href='/SanghaCloud/admin/requestpage/votecount/detail?newsId="+obj.newsId+"' class='btn btn-info'>详情</a></td></tr>";
		}
	}
	$("#dataBody").html(str);
}