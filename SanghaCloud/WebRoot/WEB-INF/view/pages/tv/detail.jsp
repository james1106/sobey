<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!--
BeyondAdmin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.2.0
Version: 1.0.0
Purchase: http://wrapbootstrap.com
-->

<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title></title>

    <meta name="description" content="Dashboard" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="<%=basePath%>/resource/assets/img/favicon.png" type="image/x-icon">

    <!--Basic Styles-->
    <link href="<%=basePath%>resource/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link id="bootstrap-rtl-link" href="" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/font-awesome.min.css" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/weather-icons.min.css" rel="stylesheet" />

    <!--Fonts-->

    <!--Beyond styles-->
    <link href="<%=basePath%>resource/assets/css/beyond.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resource/assets/css/demo.min.css" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/typicons.min.css" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/animate.min.css" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/skins/deepblue.min.css" rel="stylesheet" type="text/css" />

	<link href="<%=basePath%>resource/css/common.css" rel="stylesheet" />
    <script src="<%=basePath%>resource/assets/js/skins.min.js"></script>
    
  
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">用户详情</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	         用户
	        <small>
	            User
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">用户详情</span>
            </div>
            <div class="widget-body">
            	<div class="box-body">
	                <div align="center">
	                    <h2>西</h2>
	                </div>
	
	                <div class="form-group">
	                    <label>球队Logo</label>
	                <!--     <img src="http://admin.uhisports.com/upload/1/CR-loy9OwOZeC.png"> -->
	                </div>
	                <div class="form-group">
	                    <label>队长:<label>
	                    <label></label>
	                </div>
	                <div class="form-group">
	                    <label>球队暗号:</label>
	                    <label>西门吹雪</label>
	                </div>
	                <div class="form-group">
	                    <label>球员:</label>
	                    <label>全网球迷  拉莫斯  佩佩  马塞洛  罗德里格斯  伊斯科  本泽马  纳瓦斯  瓦拉内  达尼洛  摩德里奇  加雷斯-贝尔  济科-卡西拉  卡塞米罗  科瓦契奇  赫塞  </label>
	                </div>
	                <div align="center">
	                    <a href="edit.html" target="iframe"  id="confirmBtn" class="btn btn-info">修改</a>
	                </div>
	
	            </div>
           	</div>
        </div>
    </div>
    
    <!--Basic Scripts-->
    <script src="<%=basePath%>resource/assets/js/jquery-2.0.3.min.js"></script>
    <script src="<%=basePath%>resource/assets/js/bootstrap.min.js"></script>

    <!--Beyond Scripts-->
    <script src="<%=basePath%>resource/assets/js/beyond.min.js"></script>
    <!--Page Related Scripts-->
    <!--Jquery Select2-->
    <script src="<%=basePath%>resource/assets/js/select2/select2.js"></script>
    <!--Bootstrap Tags Input-->
    <script src="<%=basePath%>resource/assets/js/tagsinput/bootstrap-tagsinput.js"></script>
    
    <!--Bootstrap Date Picker-->
    <script src="<%=basePath%>resource/assets/js/datetime/bootstrap-datepicker.js"></script>

    <!--Bootstrap Time Picker-->
    <script src="<%=basePath%>resource/assets/js/datetime/bootstrap-timepicker.js"></script>
    
        <!--Fuelux Spinner-->
    <script src="<%=basePath%>resource/assets/js/fuelux/spinner/fuelux.spinner.min.js"></script>

	<script src="<%=basePath%>resource/assets/js/toastr/toastr.js"></script>
	<!--Common Scripts -->
	<script src="<%=basePath%>resource/js/common.js"></script>

</body>
<!--  /Body -->
</html>
