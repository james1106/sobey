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
	                    <h2 id="userName">用户 叶智源</h2><span style="font: 12px;color: #AAAAAA" id="userType">(员工)</span>
	                </div>
	
	                <div class="form-group">
	                    <label >手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机 :</label>
	                    <label id="mobile"></label>
	                </div>
	
	                <div class="form-group">
	                    <label>用户头像 :</label>
	                    <img src="http://football.9026.com/api/avatar/avatar.png" width="50px" height="50px" style="margin-left: 10px" id="photo">
	                </div>
	
	                <div class="form-group">
	                    <label>角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色 :</label>
	                    <label id="role"></label>
	                </div>
	
	                <div class="form-group">
	                    <label>单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位 :</label>
	                    <label id="officeName"></label>
	
	                </div>
	
	                <div class="form-group">
	                    <label id>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱 :</label>
	                    <label id="email"></label>
	                </div>
	
	
	                <div class="form-group">
	                    <label>QQ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
	                    <label style="margin-left: 10px" id="qq"></label>
	                </div>
	
	                <div class="form-group" id="salsDiv" style="display: none">
	                    <label>销&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;售 :</label>
	                    <label style="margin-left: 10px" id="sale"></label>
	                </div>
	
	
	                <div class="form-group" id="jobTitleDiv" style="display: none">
	                    <label >职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位 :</label>
	                    <label id="jobTitle"></label>
	                </div>
	
	                <div class="form-group" id="zerenDiv" style="display: none">
	                    <label>责任描述 :</label>
	                    <label id="zeren"></label>
	                </div>
	                <div class="form-group" >
	                    <label>注册日期 :</label>
	                    <label id="createTime"></label>
	                </div>
	
	                <div align="center">
	                    <a href="<%=basePath%>admin/requestpage/user/edit?userId=${userId}&type=${type}" target="iframe"  id="confirmBtn" class="btn btn-info">修改</a> 
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
	<script type="text/javascript" src="<%=basePath%>resource/js/user/detail.js"></script>
	
	<script type="text/javascript">
		var userId ="${userId}";
		var type = "${type}";
		$(function(){
			initData(userId,type);
		})
		
	</script>

</body>
<!--  /Body -->
</html>
