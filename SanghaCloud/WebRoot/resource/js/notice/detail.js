var options = {
	scrollY : true, // 是否竖向滚动
	scrollX : false, // 是否横向滚动
	startX : 0, // 初始化时滚动至x
	startY : 0, // 初始化时滚动至y
	indicators : false, // 是否显示滚动条
	deceleration : 0.0005, // 阻尼系数,系数越小滑动越灵敏
	bounce : true
// 是否启用回弹
};
mui('.mui-scroll-wrapper').scroll(options);

var systemId = null;

function initData(tempSystemId) {
	systemId = tempSystemId;
	getData();
}

var progressbarwidth = 0;
var progresspercent = 0;
function getData() {
	$.ajax({
		type : "post",
		url : "/SanghaCloud/msgCenter/getMsgById",
		async : true,
		data : {
			systemId : systemId
		},
		dataType : "json",
		success : function(json) {
			if (json.code == 200) {
				console.log(json);
				var time = new Date(json.data.createTime)
						.Format("yyyy-MM-dd HH:mm:ss");
				$(".title").html(json.data.title);
				$(".introduction .time").html(time);
				$(".mui-scroll .content").html(json.data.content);
				$(".mui-content").show();
			}
		}
	});
}

Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
