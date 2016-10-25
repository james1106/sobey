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
    <link rel="shortcut icon" href="<%=basePath%>resource/assets/img/favicon.png" type="image/x-icon">

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
            <li class="active">统计</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	统计
	        <small>
	            Count
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">用户</span>
            </div>
            <div class="widget-body" style="overflow: hidden;">
                <div class="col-xs-6 col-md-6">
                    <div class="info-box">
                        <span class="info-box-icon bg-aqua"><i class="fa fa-qq"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text">当前用户</span>
                            <br>
                            <span class="info-box-number"  id="test">6886
                            <small>人</small></span>
                        </div>
                    </div>
                </div>

                <div class="col-xs-6 col-md-6">
                    <div class="info-box">
                        <span class="info-box-icon bg-red"><i class="fa fa-qq"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">当前比赛</span>
                            <br>
                            <span class="info-box-number">883
                            <small>场</small></span>
                        </div>
                    </div>
                    <!-- /.info-box -->
                </div>

            </div>
        </div>
        <div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">球队</span>
            </div>
            <div class="widget-body" style="overflow: hidden;">
                <div class="col-xs-4 col-md-4">
                    <div class="info-box">
                        <span class="info-box-icon bg-green"><i class="fa fa-circle-o-notch"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">球队(5人以上)</span>
                            <br>
                            <span class="info-box-number">348
                                <small>支</small></span>
                        </div>
                    </div>
                </div>

                <div class="col-xs-4 col-md-4">
                    <div class="info-box">
                        <span class="info-box-icon bg-yellow"><i class="fa fa-qq"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">球队(7人以上)</span>
                            <br>
                            <span class="info-box-number">343
                                <small>支</small></span>
                        </div>
                    </div>
                </div>

                <div class="col-xs-4 col-md-4">
                    <div class="info-box">
                        <span class="info-box-icon bg-aqua"><i class="fa fa-qq"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">球队(11人以上)</span>
                            <br>
                            <span class="info-box-number">337
                                <small>支</small></span>
                        </div>
                    </div>
                    <!-- /.info-box -->
                </div>
            	
            </div>
        </div>
    </div>
    
    <!--Basic Scripts-->
    <script src="<%=basePath%>resource/assets/js/jquery-2.0.3.min.js"></script>
    <script src="<%=basePath%>resource/assets/js/bootstrap.min.js"></script>

    <!--Beyond Scripts-->
    <script src="<%=basePath%>resource/assets/js/beyond.min.js"></script>
	<script src="<%=basePath%>resource/assets/js/toastr/toastr.js"></script>
	<!--Fuelux Spinner-->
    <script src="<%=basePath%>resource/assets/js/fuelux/spinner/fuelux.spinner.min.js"></script>
	<!--Common Scripts -->
	<script src="<%=basePath%>resource/js/common.js"></script>
	<script src="<%=basePath%>resource/assets/js/bootbox/bootbox.js"></script>    
	
    <script>
    	$(function(){
    		
    		
    	})
    	
    	
    </script>
</body>
<!--  /Body -->
</html>
