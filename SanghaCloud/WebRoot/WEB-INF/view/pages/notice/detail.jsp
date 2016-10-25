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
    <meta charset="utf-8" />
    <base href="<%=basePath%>">
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
    
    <!-- 富文本编辑器 -->
    <script src="<%=basePath%>ueditor/ueditor.config.js"></script>
    <script src="<%=basePath%>ueditor/ueditor.all.js"></script>
    <script src="<%=basePath%>ueditor/lang/zh-cn/zh-cn.js"></script>
    
  
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">公告详情</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	         公告
	        <small>
	            Notice
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">公告详情</span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form">
                       	
                    	<div class="form-group">
                            <label>标题</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="title" type="text"  disabled="disabled">
                                </div>
                            </span>
                        </div> 
                        <div class="form-group">
                            <label>发送对象</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="toGroup"  type="text" disabled="disabled">
                                </div>
                            </span>
                        </div> 
                         <div class="form-group">
                            <label>发送时间</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control"  type="text" id="sendTime" disabled="disabled">
                                </div>
                            </span>
                        </div> 
                        <div class="form-group">
                            <label>发送者</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control"  type="text" id="publisher" disabled="disabled">
                                </div>
                            </span>
                        </div> 
                        <div class="form-group">
                            <label>概要</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control"  type="text" id="brief"  disabled="disabled">
                                </div>
                            </span>
                        </div> 
                         <div class="form-group" id="content">
                            <label>内容</label>
                            <span class="input-icon icon-right">
                            	<script id="editor" type="text/plain" style="width:100%;height:500px;"></script>
                            	<script type="text/javascript">
                            		UE.getEditor("editor")
                            	</script>
                            </span>
                        </div>
                        <a href="<%=basePath%>admin/requestpage/notice/edit?systemId=${systemId}" target="iframe"   class="btn btn-info">修改</a>
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
	<script type="text/javascript" src="<%=basePath%>resource/js/notice/detail_notice.js"></script>
	
    <script>
    	$(function(){
    		var systemId = "${systemId}";
    		
			initData(systemId);
		})
    	
    </script>
</body>
<!--  /Body -->
</html>
