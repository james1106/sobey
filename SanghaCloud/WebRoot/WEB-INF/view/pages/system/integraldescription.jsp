<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" href="<%=basePath%>resource/css/system/mui.min.css" />
		<style>
			.mui-content{
				background: #fff;
			}
			.mui-content .mui-scroll{
				padding: 20px;
			}
			.mui-content .mui-scroll p.title{
				text-align: center;
				color: #4D4D4D;
				font-size: 18px;
			}
			p.introduction{
				color: #4D4D4D;
				text-align: left;
				font-size: 14px;
			}
			p.paragraphone{
				color: #4D4D4D;
				text-align: left;
				font-size: 14px;
				margin-bottom: 4px;
			}
			p.paragraptwo{
				color: #4D4D4D;
				text-align: left;
				font-size: 14px;
				margin-bottom: 4px;
			}
			p.paragrapthree{
				color: #4D4D4D;
				text-align: left;
				font-size: 14px;
				margin-bottom: 4px;
			}
			ul{
				margin-top: 0px;
				list-style: none;
				padding-left: 0px;
				font-size: 13px;
				color:#4D4D4D ;
			}
		</style>
	</head>
	<body>
			<div class="mui-content mui-scroll-wrapper">
			    <div class="mui-scroll">
			        <div class="integral">
						<p class="title">积分说明</p>
						<p class="introduction">哈喽，贝粉们，欢迎来到口袋姐的签到墙。</p>	
						<p class="paragraphone">一、如何获取积分？</p>
						<ul>
							<li>1.每日签到获取15积分</li>
							<li>2.每完成一个评价获取20积分</li>
							<li>3.节假日签到获取3倍积分（二期功能）</li>
							<li>4.做任务赢得积分</li>
							<li>5.互动圈发布原创帖可赚得20积分</li>
							<li>6.入选精华帖可赚得40积分</li>
							<li>7.担任明显用户，每月可额外奖励500积分</li>
						</ul>
						
						<p class="paragraptwo">二、积分的扣除</p>
						<ul>
							<li>1.原创帖因内容违规被删除扣除50积分</li>
							<li>2.被管理员判定为不实的评价扣除50积分</li>
							<li>3.封ID一次扣除200积分</li>
						</ul>
						
						
						<p class="paragrapthree">三、积分的使用</p>
						<ul>
							<li>1.每个月口袋姐将举办一次抽奖活动，所有当月积分达到300分以上的用户都有机会参加，每次抽奖消耗50积分，奖品可转让，但不能兑换成现金</li>
							<li>2.积分换礼，口袋姐会为大家奉上一些有用的精美礼品，用相应积分可以兑换相应礼品。</li>
							<li>3.积分兑换特权，你想成为口袋小贝某个领域的达人么？你想成为口袋小贝的明星用户吗？口袋姐将推出多种特权，为口袋走的更远而树立自己的旗帜</li>
						</ul>
					</div>
			    </div>
			</div>
			
		<script src="<%=basePath%>resource/js/system/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resource/js/system/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			var  options={
				scrollY:true,
				scrollX:false,
				startY:0,
				startX:0,
				indicators:false,
				deceleration:0.0005,
				bounce:true
			}
			mui('.mui-scroll-wrapper').scroll(options);
			function getData(){
				$.ajax({
					type:"get",
					url:"",
					async:true,
					data:{},
					dataType:'json',
					success:function(){
						
					}
				});
			}
		</script>
	</body>
</html>
