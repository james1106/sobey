/*mui('.mui-scroll-wrapper').scroll({
	scrollY: true, //是否竖向滚动
	scrollX: false, //是否横向滚动
	startX: 0, //初始化时滚动至x
	startY: 0, //初始化时滚动至y
	indicators: false, //是否显示滚动条
	deceleration:0.0005, //阻尼系数,系数越小滑动越灵敏
	bounce: true //是否启用回弹
});*/
var newsId = null;
var token = null;
function initData(tempToken,tempNewsId){
	newsId = tempNewsId;
	token = tempToken;
	$.ajax({
		type:"POST",
		url:"/SanghaCloud/news/increaseReads",
		data:{newsId:tempNewsId},
		dataType:"json",
		async:true,
		timeout:5000,
		success:function(json){
			
		}
	});
	getData();
}

/*小数转换为百分数*/
Number.prototype.toPercent = function(){
	//return (Math.round(this * 10000)/100).toFixed(0) + '%';
	return (Math.round(this * 10000) / 100.00 + "%");
}
/*获得地址后的参数*/
function getUrlParam(name){
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r != null) {return unescape(r[2]);}
		else{return null;}
}
var newsId =getUrlParam("newsId");
$(".vote .vote-item label").click(function(){
	$(this).hasClass("checked")?$(this).removeClass("checked") : $(this).addClass("checked").parent().siblings().find("label").removeClass("checked");												
});
/*请求数据*/
var index;
var percent;
var totalVote=0;
var countVote;
var pwidth;
var voteId ;
function getData(){
	$.ajax({
		type:"post",
		url:"/SanghaCloud/news/getNewsById",
		async:true,
		data:{
				newsId:newsId,
				token:token
			},
		dataType:"json",
		timeout:5000,
		success:function(json){
			console.log(json);
			if(json.code==200){
				$("#button").show();
				$(".top-img").html("<img src='"+json.data.imageUrl+"'/>");
				$(".title").html(json.data.title);
				var time = new Date(json.data.createTime).Format("yyyy-MM-dd HH:mm:ss");
				$(".introduction .time").html(time);
				$(".introduction .clicklike").html('点赞&nbsp;'+json.data.likes);
				$(".introduction .read").html('阅读&nbsp;'+eval(json.data.reads+json.data.realReads));
				console.log(json.data.content);
				$(".mui-scroll .content").html(json.data.content);		
				for(var i=0;i<json.data.votes.length;i++){
					totalVote+=json.data.votes[i].countVote;
				}
				var voteStr='';
				var resultStr='';
				for(var i=0;i<json.data.votes.length;i++){					
					countVote=json.data.votes[i].countVote;
					if(totalVote==0){
						percent=0;
						percent=percent.toPercent();
					}
					else{
						percent=countVote/totalVote;
						percent=percent.toPercent();
					}				
					voteStr+='<li class="item">'+
								'<span>'+json.data.votes[i].voteDecribe+'</span>'+
								'<label data-voteid="'+json.data.votes[i].voteId+'"></label>'+
							'</li>';
							
					resultStr+='<li>'+
									'<p>'+json.data.votes[i].voteDecribe+'</p>'+
									'<div class="progress">'+
										'<div style="width:'+percent+'" class="progressbar"></div>'+
									'</div>'+
									'<span class="percent">'+percent+'</span>'+
									'<span class="voteNumber">'+countVote+'票</span>'+
								'</li>';
				}							
				$(".vote ul.vote-item").html(voteStr);
				$(".vote-result ul.result").html(resultStr);
				$(".mui-content").show();
				/*验证是否有认证*/
				if(json.data.isShowIcon==1){
					$(".introduction img").show();
				}
				else{
					$('.introduction img').hide();
				}
				
				/*验证投票环节*/
				if(json.data.isVote==1){
					/*没有token的情况*/
					if(token==null||token.trim().length==0){
						$(".vote-result").show();
						$("#button").hide();
					}
					/*有token的情况*/
					else{
						/*已投票的情况*/
						if(json.data.isVoteUser!=null){
							$(".vote-result").show();
							$("#button").hide();
						}
						/*未投票的情况*/
						else{
							$(".vote").show();
							$(".vote .vote-item label").click(function(){
								voteId=$(this).data("voteid");
								console.log('voteId:'+voteId);
								index=$(this).index();
								console.log('index:'+index);
								$(this).hasClass("checked")?$(this).removeClass("checked") : $(this).addClass("checked").parent().siblings().find("label").removeClass("checked");												
							});
							$("#button").click(function(){
								checkedsize=$(".vote .vote-item .item").find("label.checked");
								if(checkedsize.length>0){
									$.ajax({
										type:"post",
										url:"/SanghaCloud/news/increaseVotes",
										async:true,
										data:{voteId:voteId,newsId:newsId,token:token},
										dataType:"json",
										success:function(json){
											if(json.code==1005){
												$(".model").slideDown(600);
												$(".model .model-content span").click(function(){
													$(".model").fadeOut(600);
												})
											}
											else{
												RefreshVote(json); 
												$(".vote").hide();
												$(".vote-result").show();
												$("#button").addClass("checked");
												$("#button").attr("disabled",true);
											}
										}
									});
								}else{
									mui.alert('请选择投票项',"温馨提示", function() {
										
									});
									/*$(".confirm").slideDown();
									$(".confirm .cancel").on('tap',function(){
										$(".confirm").fadeOut();
									});*/
								}
							})
						}
					}
				}
				else{
					$(".vote").hide();
					$(".vote-result").hide();
					$("#button").hide();
				}
			}
			else{
				alert(json.msg);
			}
		}
	});
	//$(".zzsc8").hide();
}

function RefreshVote(json){
	totalVote ++;
	var voteStr='';
	var resultStr='';
	console.log(json);
	totalVote=0;
	for(var i=0;i<json.data.votes.length;i++){
		totalVote+=json.data.votes[i].countVote;
	}
	for(var i=0;i<json.data.votes.length;i++){
		countVote=json.data.votes[i].countVote;
		//var to = json.data.votes[i].
		console.log("countVote:"+countVote);
		percent=countVote/totalVote;
		console.log("percent:"+percent);
		percent=percent.toPercent();
		console.log("转换之后percent:"+percent);
		
		voteStr+='<li class="item">'+
					'<span>'+json.data.votes[i].voteDecribe+'</span>'+
					'<label data-voteid="'+json.data.votes[i].voteId+'"></label>'+
				'</li>';
				
		resultStr+='<li>'+
						'<p>'+json.data.votes[i].voteDecribe+'</p>'+
						'<div class="progress">'+
							'<div style="width:'+percent+'" class="progressbar"></div>'+
						'</div>'+
						'<span class="percent">'+percent+'</span>'+
						'<span class="voteNumber">'+countVote+'票</span>'+
					'</li>';		
	}							
	$(".vote ul.vote-item").html(voteStr);
	$(".vote-result ul.result").html(resultStr);
	//history.go(0);
}
Date.prototype.Format = function(fmt)   
{ //author: meizz   
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
