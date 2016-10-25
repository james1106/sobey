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
            <li class="active">编辑Banner</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	 Banner
	        <small>
	            Ads
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">编辑Banner</span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form">
                       	<div class="form-group">
                            <label>Banner</label>
                            <div>
							  	<input id="iconUpload" name="res" type="file" class="file-loading" >
							</div>
                            <span class="input-icon icon-right">
                            	<div class="input-group" style="margin-top: 5px;">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<div class="form-control" id="filePath" type="text"></div>
                                </div>
                            </span>
                        </div> 
                    	<div class="form-group">
                            <label>Banner外链</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="pathName" type="text">
                                </div>
                            </span>
                        </div> 
                        <a href="<%=basePath%>admin/requestpage/ads" target="iframe"  onclick="return confirm() " class="btn btn-info">确认</a>
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
    <script>
    	var bannerId = "${bannerId}";
    	$(function(){
	        getBannerInfo();//获取数据方法
		})
		
		
    	var fillPath='',initialPreview=[];
    	var imgUrl='';
    	
    	function getBannerInfo(){
    		// 发起ajax请求，得到banner信息
    		requestGetBannerInfo();
    		
    		for(var i=0;i<1;i++){  //把图片循环读取出来
    			initialPreview[i] = '<img width="auto" height="160" class="file-preview-image"  src="'+fillPath+'">'+
	                 '<div class="file-thumbnail-footer">'+
	  				'<div class="file-caption-name" title="预览图" style="width: 250px;">预览图</div>'+
	  				'<div class="file-actions">'+
	  					'<div class="file-footer-buttons">'+
	  						'<button type="button" class="kv-file-remove btn btn-xs btn-default" title="Remove file">'+
	  							'<i class="glyphicon glyphicon-trash text-danger"></i>'+
	  						'</button>'+
	  					'</div>'+
	  					'<div class="file-upload-indicator" tabindex="-1" title="Not uploaded yet">'+
	  						'<i class="glyphicon glyphicon-hand-down text-warning"></i>'+
	  					'</div>'+
	  					'<div class="clearfix"></div>'+
	  				'</div>'+
	  			'</div>';
				$("#filePath").html(fillPath);
    		}
			initFileUpload(initialPreview);  //初始化图片上传插件方法
          	$('.kv-file-remove').each(function(index){
          		$(this).click(function(){
                	$(this).parent().parent().parent().parent().fadeOut('slow',function(){
                        var imgSrc = $(".file-preview-image").eq($(this).index()).attr("src");
    	            	$(this).remove();
    	            	$('.file-caption-name').attr('name',$(".kv-file-remove").length+' file selected'),
    	            	$('.file-caption-name').html('<span class="glyphicon glyphicon-file kv-caption-icon"></span>'+$(".kv-file-remove").length+' file selected');
    	            	$('.goodsImage').each(function() {
                            var fid = $(this).val();
                            if (imgSrc.indexOf(fid) > 0) {
                              $(this).remove();
                            }
                        });
                	});
                }) 
          	})
    	}
    	
    	
    	function initFileUpload(initialPreview) {
			$("#iconUpload").fileinput({
				language:'zh',
		      	initialPreview: initialPreview,
		    	uploadUrl: '<%=basePath%>res/upload ', // 图片上传接口
		      	allowedFileExtensions: ["jpg", "png", "gif"],
		      	maxFileSize : 1024,//上传图片的最大限制  1024KB
		      	overwriteInitial:true,
		      	initialPreviewShowDelete:true,
		      	minImageWidth: 50,
		      	minImageHeight: 50
		    }).on("fileuploaded", function (event, data, previewId, index) {
		    	if (null != data) {
	                fillPath = data.response.data.url;
	        		$("#filePath").html(fillPath);
	          	} else {
	            	Notify("上传失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
	          	}
	        });
		}
    	
       function confirm(){
        	//确认修改
        	var tempFilePath = $("#filePath").html();
        	
        	console.debug("tempFilePath: "+tempFilePath)
        	var path = $("#pathName").val();
        	
        	console.debug("path: "+path)
        	if(path == null && tempFilePath == null){
        		Notify("没有修改", 'top-right', '5000', 'info', 'fa-desktop', true);
        		return false;
        	}
        	$.ajax({
				type : "GET",
				url : "/SanghaCloud/news/updateBanner",
				data:{id:bannerId,image:tempFilePath,imgUrl:path},
				dataType :"json",
				async : false,
				timeout : 5000,
				success:function(respObj){
					
					if(respObj.code == 200){
						Notify("修改成功", 'top-right', '5000', 'info', 'fa-desktop', true);
						return true;
					}else{
						Notify("修改失败", 'top-right', '5000', 'info', 'fa-desktop', true);
						return false;
					}
				}
			});
        	
        }
        
        function requestGetBannerInfo(){
        	
        	$.ajax({
				type : "GET",
				url : "/SanghaCloud/news/getBannersById",
				data:{id:bannerId},
				dataType :"json",
				async : false,
				timeout : 5000,
				success:function(respObj){
					fillPath = respObj.data.image;
					console.debug(respObj);
					if(respObj.data.imgUrl != null){
						$("#pathName").val(respObj.data.imgUrl);
						imgUrl = respObj.data.imgUrl;
					}else{
						$("#pathName").val("");
					}
					
				}
			});
        	
        }
    </script>
</body>
<!--  /Body -->
</html>
