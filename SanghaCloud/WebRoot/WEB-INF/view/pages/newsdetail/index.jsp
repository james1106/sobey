<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta name="app-mobile-web-app-capable" content="yes"/>
    	<meta name="app-mobile-web-app-status-bar-style" content="black"/>
		<title></title>
		<link rel="stylesheet" href="<%=basePath%>resource/css/news/mui.min.css" />
		<link rel="stylesheet" href="<%=basePath%>resource/css/news/detail.css" />
		<link rel="stylesheet" href="<%=basePath%>resource/css/news/zzsc.css" />
		<style>
			video::-webkit-media-controls {
				opacity: 0.5 !important;
			}
			html,body{
				width:100%;
				background: #fff !important;
/* 				overflow: auto !important; */
/* 				overflow-x:hidden !important; */
/* 				-webkit-overflow-scrolling : touch !important;  */
			}
			.mui-scroll-wrapper{
				background: #fff !important;
/* 				overflow: auto !important; */
/* 				overflow-x:hidden !important; */
/* 				-webkit-overflow-scrolling : touch !important; */
			}
			#button{
				display: none;
			}
		</style>
	</head>
	<body>
		<div class="zzsc8">
		  <div class="zzsc8-container container1">
		    <div class="circle1"></div>
		    <div class="circle2"></div>
		    <div class="circle3"></div>
		    <div class="circle4"></div>
		  </div>
		  <div class="zzsc8-container container2">
		    <div class="circle1"></div>
		    <div class="circle2"></div>
		    <div class="circle3"></div>
		    <div class="circle4"></div>
		  </div>
		  <div class="zzsc8-container container3">
		    <div class="circle1"></div>
		    <div class="circle2"></div>
		    <div class="circle3"></div>
		    <div class="circle4"></div>
		  </div>
		</div>
		<div class="mui-scroll">
			<div class="top-img"></div>			
			<div class="title">	</div>
			<div class="introduction">				
				<img src="<%=basePath%>resource/img/5.png" alt="" />
				<span class="time"></span>
				<span class="clicklike"></span>
				<span class="read"></span>
			</div>
			<div class="content"></div>		
			<div class="vote">
				<ul class="vote-item">			
				</ul>			
			</div>
			<div class="vote-result">
				<ul class="result">				
				</ul>
			</div>
			<div style="width: 100%;text-align: center;"><button id="button" type="button"></button></div>			
		</div>	
		<div class="confirm">
			<div class="confirm-dialog">
				<div class="confirm-content">
					<ul>
						<li>请选择投票项</li>
						<li class="cancel">取消</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="model">
			<div class="model-dialog">
				<div class="model-content">
					<span>x</span>
					<a>请前往应用市场下载索贝app</a>
				</div>
			</div>
		</div>
		<script src="<%=basePath%>resource/js/news/detail/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resource/js/news/detail/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resource/js/news/detail/ip.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resource/js/news/detail/detail.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			var token = "${token}";
			var newsId = "${newsId}";
			$(function (){
				initData(token,newsId);
				$(".zzsc8").hide();
			})
		</script>
	</body>
</html>
