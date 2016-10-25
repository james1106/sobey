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
		<link rel="stylesheet" href="<%=basePath%>resource/css/system/mui.min.css "/>
		<style>
			.mui-content{
				background: #fff;
			}
			.mui-content .mui-scroll{
				padding: 20px;
			}
			.mui-content .mui-scroll p.title{
				text-align: center;
				color: #000;
				font-size: 18px;
			}
			.paragraphone{
				font-size: 15px;				
				color: #000;
				text-indent: 2em;
			}
			.version{
				margin-top: 20px;
				color: #000;
				font-size: 18px;
			}
			.easy{
				margin-top: 14px;
				font-size: 17px;
				color: #000;
				font-weight: bold;
				margin-bottom: 4px;
			}
			.contact{
				margin-top: 0px;
				font-size: 15px;				
				color: #000;
			}
			img{
				width: 100%;
			}
		</style>
	</head>
	<body>
		<div class="mui-content mui-scroll-wrapper">
		    <div class="mui-scroll">
		        <div class="aboutus">
		        	<p class="title">关于我们</p>
		        	<p class="paragraphone">
		        		口袋小贝旨在为技术人员打造一个简单便捷高效的售后服务工作平台，为您开启轻快愉悦的全新工作方式，高效透明的售后服务新感受，享受轻松愉悦、趣味横生的工作氛围，远离枯燥又单调的运维环境。
		        	</p>
		        <!-- 	<p class="version">版本信息</p>
		        	<p class="easy">简单</p>
		        	<p class="contact">一键申请售后维护，随时与索贝联系</p>
		        	<img src="<%=basePath%>resource/img/12.png" alt="" />
		        	<p class="easy">及时</p>
		        	<p class="contact">专属贴心客服，为您的维护保驾护航</p>
		        	<img src="<%=basePath%>resource/img/13.png" alt="" />
		        	<p class="easy">轻便</p>
		        	<p class="contact">全网告警随手知，把运维揣兜里</p>
		        	<img src="<%=basePath%>resource/img/14.png" alt="" />
		        	<p class="easy">趣味</p>
		        	<p class="contact">口袋姐精选，属于您的广电分享交流平台</p>
		        	<img src="<%=basePath%>resource/img/15.png" alt="" /> -->
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
