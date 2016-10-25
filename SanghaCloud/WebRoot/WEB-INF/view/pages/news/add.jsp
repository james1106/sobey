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
    <title></title>
	<base href="<%=basePath%>">
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
    <!-- 富文本编辑器 -->
    <script src="<%=basePath%>ueditor/ueditor.config.js"></script>
    <script src="<%=basePath%>ueditor/ueditor.all.js"></script>
    <script src="<%=basePath%>ueditor/lang/zh-cn/zh-cn.js"></script>
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
            <li class="active">添加资讯</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	资讯
	        <small>
	            News
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">添加资讯</span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form" id="newsfrom">
                    	<input type="hidden" name="filePath" id="files" value="">
                    	<div class="form-group">
                            <label>标题</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="eventName" name="title" type="text" placeholder="输入标题(必填)">
                                </div>
                            </span>
                        </div> 
                    	<div class="form-group">
                            <label>资讯图片&nbsp;&nbsp;<span style="font-size: 12px; color: red">(gif等动态图片会导致，性能降低)</span></label>
                            <div>
							  	<input id="iconUpload"  type="file" class="file-loading" >
							</div>
                            <span class="input-icon icon-right">
                            	<div class="input-group" style="margin-top: 5px;">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<div class="form-control" id="filePath"  type="text"></div>
                            		
                                </div>
                            </span>
                        </div> 
                    	
                        <div class="form-group">
                            <label>类型</label>
                            <span class="input-icon icon-right">
                            	<select id="team" name="type" class="form-control">
                                    <option value="1" >互动圈</option>
                                    <option value="2" >产品介绍</option>
                                    <option value="3" >行业新闻</option>
                                </select>
                            </span>
                        </div>
                        <div class="form-group">
                        	<label>概要</label>
                        	<span class="input-icon icon-right">
                        		<textarea rows="3" cols="50" style="width: 100%; resize: none;" placeholder="200字以内" id="introduction" name="introduction"></textarea>
                        	</span>
                        </div>
                        <div class="form-group">
                        	<label>Banner置顶</label>
                        	<span class="input-icon icon-right">
                        		<input type="radio" name="isBanner" value="0" checked="checked"/>否
                        		<input type="radio" name="isBanner" value="1"/>是
                        	</span>
                        </div>
                        <div class="form-group">
                        	<label>显示官方图标</label>
                        	<span class="input-icon icon-right">
                        		<input type="radio" name="isShowIco" value="0" checked="checked"/>否
                        		<input type="radio" name="isShowIco" value="1"/>是
                        	</span>
                        </div>
                        <div class="form-group">
                        	<label>跳转第三方链接</label>
                        	<span class="input-icon icon-right">
                        		<input type="radio" name="isLink" value="0" checked="checked" id="nonlink"/>否
                        		<input type="radio" name="isLink" value="1" id="link"/>是
                        	</span>
                        	<span class="input-icon icon-right" >
                        		<input type="url" style="width: 100%;display: none" name="tagetLink" placeholder="第三方链接地址" id="tagetLink" >
                        	</span>
                        </div>
                         <div class="form-group" id="readsDiv">
                        	<label>设置阅读数</label>
                        	<span class="input-icon icon-right">
                        		<input type="number" id="reads" name="reads" value="0" placeholder="默认为初始值" style="width: 100%"/>
                        	</span>
                        </div>
                        <div class="form-group" id="content">
                            <label>内容</label>
                            <span class="input-icon icon-right">
                            	<textarea id="editor" type="text/plain" name="content" style="width:100%;height:500px;"></textarea>
                            	<script type="text/javascript">
                            		UE.getEditor("editor")
                            	</script>
                            </span>
                        </div>
                         <div class="form-group" id="voteBigDiv">
                        	<label>开启投票</label>
                        	<span class="input-icon icon-right">
                        		<input type="radio" name="isVote" value="0" checked="checked" id="nonvote"/>否
                        		<input type="radio" name="isVote" value="1" id="vote"/>是
                        	</span>
                        	<div style="display: none" id="voteDiv">
                        		<div id="voteDecriDiv">
		                        	<span class="input-icon icon-right" id="fristVote">
		                        		<input type="text" name="votedecri" style="width: 600px;margin-top: 10px" placeholder="投票描述"  >
		                        	</span>
	                        	</div>
	                        	<span class="input-icon icon-right" style="margin-top: 10px" >
	                        		<a onclick="addVote()" class="btn btn-info">新增一项</a>
	                        	</span>
                        	</div>
                        </div>
                    </form>
                    <a href="<%=basePath%>admin/requestpage/news" onclick="return confirm()" class="btn btn-info">添加资讯</a>
                <!--     <input type="button" id="confirmBtn" class="btn btn-info" value="添加资讯"> -->
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
  	<script type="text/javascript" src="<%=basePath%>resource/js/news/news.js"></script>
    <script>
    	$(function(){
			
		})
    	$("#iconUpload").fileinput({
    		language:'zh',
		    uploadUrl: '<%=basePath%>res/upload ', // 图片上传接口
		    showPreview : true,
		    showRemove: false,
		    maxFileSize : 850,  //上传图片的最大限制  50KB
		    allowedFileExtensions: ["jpg", "png", "gif"],
		    initialCaption: "请选择图片"
		});
		$("#iconUpload").on("fileuploaded", function (event, data, previewId, index) {
		    if (null != data) {
	        	fillPath = data.response.data.url;
	        	$("#filePath").html(fillPath);
	        	$("#files").val(fillPath);
		    } else {
		    	 Notify("上传失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
		    }
		});
		
		
    </script>
</body>
<!--  /Body -->
</html>
