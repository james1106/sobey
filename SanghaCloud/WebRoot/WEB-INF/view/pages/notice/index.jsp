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
	</head>
	<body>
		
	<div class="mui-content mui-scroll-wrapper">
		<div class="mui-scroll">
			<div class="top-img"></div>			
			<div class="title">	</div>
			<div class="introduction">				
				<span class="time"></span>
			</div>
			<div class="content"></div>
		
		</div>	
	</div>
		<script src="<%=basePath%>resource/js/news/detail/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resource/js/news/detail/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resource/js/notice/detail.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			
			var systemId = "${systemId}";
			
			$(function (){
				initData(systemId);
				
			})
		</script>
</body>
	
</html>
