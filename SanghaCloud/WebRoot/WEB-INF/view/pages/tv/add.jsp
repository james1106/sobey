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
    
    <link href="<%=basePath%>resource/fileupload/css/fileinput.css"  rel="stylesheet" type="text/css" />
    
    <style>
    	#filePath{
    		box-shadow: none !important;
    		-webkit-box-shadow: none !important;
    		background: #EEEEEE;
    	}
    </style>
  	
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">添加电视台</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	电视台
	        <small>
	            TV
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">添加电视台</span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" id="tvFrom">
                    	
                    	<div class="form-group">
                            <label>电视台名称</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" name="tvName" id="tvName" type="text">
                                </div>
                            </span>
                        </div> 
                        <div class="form-group">
                            <label>编码</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" name="stationCode" id="stationCode" type="text">
                                </div>
                            </span>
                        </div> 
                         <div class="form-group">
                            <label>地址</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" name="address" id="address" type="text">
                                </div>
                            </span>
                        </div> 
                        <div class="form-group">
                            <label>分公司</label>
                            <span class="input-icon icon-right">
                            	<select name="company" class="form-control" id="company">
                            		<option value="0">选择分公司</option>
                                </select>
                            </span>
                        </div> 
                        <div class="form-group">
                            <label>办事处</label>
                            <span class="input-icon icon-right">
                            	<select name="office" class="form-control" id="office">
                                    <option value="0">选择办事处</option>
                                </select>
                            </span>
                        </div> 
                        <a href="<%=basePath%>admin/requestpage/tv" target="iframe"  onclick="return commit()" class="btn btn-info">确认</a>
                    </form>
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
	<script src="<%=basePath%>resource/laydate/laydate.js"></script>
	
	<script src="<%=basePath%>resource/fileupload/js/fileinput.js" type="text/javascript"></script>
  	<script src="<%=basePath%>resource/fileupload/js/fileinput_locale_zh.js" type="text/javascript"></script>
  	<script type="text/javascript" src="<%=basePath%>resource/js/tv/add.js"></script>
    <script>
    	
    	
		
    </script>
</body>
<!--  /Body -->
</html>
