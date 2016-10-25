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
            <li class="active">添加分类</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	分类
	        <small>
	            Category
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">添加分类</span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form">
            
                        <div class="form-group">
                            <label>选择类型</label>
                            <span class="input-icon icon-right">
                            	<select name="type" class="form-control" id="type">
                            		<option value="3">选择添加类型</option>
                                    <option value="0">软件类</option>
                                    <option value="1">硬件类</option>
                                </select>
                            </span>
                        </div> 
                        
                       	<div class="form-group" >
                            <label>填写分类(多个分类以"|"符分隔)</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="category" type="text">
                                </div>
                            </span>
                        </div>
                        <a href="<%=basePath%>admin/requestpage/ordercategorymanage" target="iframe"   onclick="return submitInfo()" class="btn btn-info" >添加</a>
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
    
        <!--Fuelux Spinner-->
    <script src="<%=basePath%>resource/assets/js/fuelux/spinner/fuelux.spinner.min.js"></script>

	<script src="<%=basePath%>resource/assets/js/toastr/toastr.js"></script>
	<!--Common Scripts -->
	<script src="<%=basePath%>resource/js/common.js"></script>
	<script src="<%=basePath%>resource/laydate/laydate.js"></script>
	
	<script src="<%=basePath%>resource/fileupload/js/fileinput.js" type="text/javascript"></script>
  	<script src="<%=basePath%>resource/fileupload/js/fileinput_locale_zh.js" type="text/javascript"></script>
    <script>
    
	   function submitInfo(){
	   		var isOk = false;
	   		var type = $("#type").val();
	   		if(type == 3){
	   			Notify("没有选择类型",'top-right','5000','danger','fa-desktop',true);
	   			return isOk;
	   		}
	   		var category = $.trim($("#category").val());
	   		if(category.length == 0){
	   			Notify("分类名称不能为空",'top-right','5000','danger','fa-desktop',true);
	   			return isOk;
	   		}
	   		$.ajax({
				type : "GET",
				url : "/SanghaCloud/admin/ordercategory/add",
				data:{type:type,categoryName:category},
				dataType :"json",
				async : false,
				timeout : 5000,
				success : function(json){
					var status = json.code;
					if(status==200){
						Notify("操作成功",'top-right','5000','info','fa-desktop',true);
						isOk = true;
					}else{
						Notify("数据异常",'top-right','5000','danger','fa-desktop',true);
					}
				}
			});
			return isOk;
	   }
        
    </script>
</body>
<!--  /Body -->
</html>
