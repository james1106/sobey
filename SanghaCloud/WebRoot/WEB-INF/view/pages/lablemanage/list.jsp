﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
  		.uploadBox{
  			width: 100%;
  			-moz-background-clip: padding!important;
		    border-radius: 0!important;
		    background-clip: padding-box!important;
		    color: #858585;
		    background-color: #fbfbfb;
		    border: 1px solid #d5d5d5;
		    position: relative;
		    height: 34px;
  		}
  		.uploadBox p{
		    margin: 0;
		    line-height: 34px;
		    padding: 0 10px;
		    font-size: 13px;
  		}
  		.uploadBox a{
  			width: 100px;
  			display: block;
  			text-align: center;
  			position: absolute;
  			right: 0;
  			top: 0;
  			bottom: 0;
  			line-height: 34px;
		    color: #fff;
		    font-size: 13px;
		    background: #3c8dbc;
  		}
  		.uploadBox a i{
  			margin-right: 10px;
  		}
  		.uploadBox input{
  			width: 100px;
  			position: absolute;
  			right: 0;
  			top: 0;
  			bottom: 0;
  			z-index: 5;
  			opacity: 0;
  			-moz-opacity: 0;
  			-webkit-opacity: 0;
  			
  		}
  	</style>
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">标签管理</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	标签
	        <small>
	            Lable
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看标签</span>
                <div class="widget-buttons">
               <!--     <a href="javascript:void(0)" id="fastAddBtn" class="btn btn-danger">快速</a>-->
                    <a href="<%=basePath%>admin/requestpage/lablemanage/add" target="iframe" class="btn btn-warning">添加标签</a>
                   <a href="<%=basePath%>admin/requestpage/lablemanage/edit" id="importBtn"  class="btn btn-info">设置标签</a> 
                </div>
            </div>
            <div class="widget-body">
            	<div class="row">
		          <div class="col-sm-12">
		           		客服标签
		          	</div>
		        </div>
                <table class="table table-striped table-hover" id="simpledatatable">
                    <thead>
                        <tr>
                            <th>标签管理 | 已勾选则显示在APP端</th>
                        </tr>
                    </thead>
                    <tbody>
                  		<tr>
                  			<td  id="serviceDataBody">
                  				<input type="checkbox" disabled="disabled" >yi
                  			</td>
                  		</tr>
                    </tbody>
                </table>
                
                <div class="row" style="margin-top: 40px">
		          <div class="col-sm-12">
		           		技术标签
		          	</div>
		        </div>
                <table class="table table-striped table-hover" id="simpledatatable">
                    <thead>
                        <tr>
                            <th>标签管理 | 已勾选则显示在APP端</th>
                        </tr>
                    </thead>
                    <tbody >
                  		<tr>
                  			<td id="techDataBody"></td>
                  		</tr>
                    </tbody>
                </table>
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
    
    <script src="<%=basePath%>resource/fileupload/js/fileinput.js" type="text/javascript"></script>
  	<script src="<%=basePath%>resource/fileupload/js/fileinput_locale_zh.js" type="text/javascript"></script>
	<!--Common Scripts -->
	<script src="<%=basePath%>resource/js/common.js"></script>
	<script src="<%=basePath%>resource/js/jqPaginator.js"></script>
	<script src="<%=basePath%>resource/js/jqPage.js"></script>
	<script src="<%=basePath%>resource/assets/js/bootbox/bootbox.js"></script>    
	<script type="text/javascript" src="<%=basePath%>resource/js/lablemanage/lable.js"></script>
	
    <script>
    	var totalpage=10;
    	
    	
		
    	$(function(){
    		//addpage(); //加载分页方法
    		 initData();
    		
    		//返回的数据不为undefind(也就是有数据时),调用$('#pageCon').show();
			//$('#pageCon').show();


			//数据添加到页面后，调用一下方式
    	//	$.jqPaginator('#pagination', {
	   	   // 	totalPages: totalpage,  //总页数
	   	   //     visiblePages: 3,  //可见页面
	   	   //     currentPage: 1,   //当前页面
	   	   //     onPageChange: function (num, type) {
	   	    //    	$('#showing').text('共'+totalpage+'条  第'+num+'/'+totalpage+'页');
	   	    //        if(type != "init"){
	   	    //        	var type = $("#type").val();
	   	     //       	totalpage = initData(num,type,null);
	   	            	//获取第num页数据，
	   	            	//userId, searchName, wbxID, czMoney, xfMoney, syMoney, uTel, isEnabled,isOperator 条件查询的参数
	   	            	//getData(num, userId, searchName, wbxID, czMoney, xfMoney, syMoney, uTel, isEnabled,isOperator);
	   	      //      }
	   	     //   }
	   //	    });   
    //	})
    	
    
//          $("#wordUpload").fileinput({
//	    		language:'zh',
//			    uploadUrl: ' ', // 图片上传接口
//			    showPreview : false,
//			    showRemove: false,
//			    maxFileSize : 850,  //上传图片的最大限制  50KB
//			    allowedFileExtensions: ["xlsx", "xls"],
//			    initialCaption: "请选择分类Icon"
//			});
//			$("#wordUpload").on("fileuploaded", function (event, data, previewId, index) {
//			    if (null != data) {
//		        	fillPath = data.response.data[0].fid;
//		        	$("#filePath").html(fillPath);
//			    } else {
//			    	 Notify("上传失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
//			    }
//			});
        });
        
    
        
    </script>
</body>
<!--  /Body -->
</html>
