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
<link rel="shortcut icon"
	href="<%=basePath%>resource/assets/img/favicon.png" type="image/x-icon">

<!--Basic Styles-->
<link href="<%=basePath%>resource/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link id="bootstrap-rtl-link" href="" rel="stylesheet" />
<link href="<%=basePath%>resource/assets/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="<%=basePath%>resource/assets/css/weather-icons.min.css"
	rel="stylesheet" />

<!--Fonts-->

<!--Beyond styles-->
<link href="<%=basePath%>resource/assets/css/beyond.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>resource/assets/css/demo.min.css"
	rel="stylesheet" />
<link href="<%=basePath%>resource/assets/css/typicons.min.css"
	rel="stylesheet" />
<link href="<%=basePath%>resource/assets/css/animate.min.css"
	rel="stylesheet" />
<link href="<%=basePath%>resource/assets/css/skins/deepblue.min.css"
	rel="stylesheet" type="text/css" />

<link href="<%=basePath%>resource/css/common.css" rel="stylesheet" />
<script src="<%=basePath%>resource/assets/js/skins.min.js"></script>

<link href="<%=basePath%>resource/fileupload/css/fileinput.css"
	rel="stylesheet" type="text/css" />

<!-- 富文本编辑器 -->
<script src="<%=basePath%>ueditor/ueditor.config.js"></script>
<script src="<%=basePath%>ueditor/ueditor.all.js"></script>
<script src="<%=basePath%>ueditor/lang/zh-cn/zh-cn.js"></script>
<style>
#filePath {
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
			<li class="active">编辑通知</li>
		</ul>
	</div>
	<div class="header-title">
		<h1>
			通知 <small> Notice </small>
		</h1>
	</div>
	<div class="page-body">
		<div class="widget">
			<div class="widget-header ">
				<span class="widget-caption">编辑通知</span>
			</div>
			<div class="widget-body">
				<div id="registration-form">
					<form role="form">

						<div class="form-group">
							<label>标题</label> <span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control" id="title" type="text">
								</div>
							</span>
						</div>
						<div class="form-group">
							<label>发送对象</label> <span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control" id="toGroup" type="text"
										disabled="disabled">
								</div>
							</span>
						</div>
						<div class="form-group">
							<label>发送时间</label> <span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control" type="text" id="sendTime"
										disabled="disabled">
								</div>
							</span>
						</div>
						<div class="form-group">
							<label>发送者</label> <span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control" type="text" id="publisher"
										disabled="disabled">
								</div>
							</span>
						</div>
						<div class="form-group">
							<label>概要</label> <span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control" type="text" id="brief">
								</div>
							</span>
						</div>
						<div class="form-group" id="content">
							<label>内容</label> <span class="input-icon icon-right"> <script
									id="editor" type="text/plain" style="width:100%;height:500px;"></script>
								<script type="text/javascript">
									UE.getEditor("editor")
								</script>
							</span>
						</div>
						<a href="<%=basePath%>admin/requestpage/notice" target="iframe"
							onclick="return confirm()" class="btn btn-info">确认</a>
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
	<script
		src="<%=basePath%>resource/assets/js/tagsinput/bootstrap-tagsinput.js"></script>

	<!--Bootstrap Date Picker-->
	<script
		src="<%=basePath%>resource/assets/js/datetime/bootstrap-datepicker.js"></script>

	<!--Bootstrap Time Picker-->
	<script
		src="<%=basePath%>resource/assets/js/datetime/bootstrap-timepicker.js"></script>

	<!--Fuelux Spinner-->
	<script
		src="<%=basePath%>resource/assets/js/fuelux/spinner/fuelux.spinner.min.js"></script>

	<script src="<%=basePath%>resource/assets/js/toastr/toastr.js"></script>
	<!--Common Scripts -->
	<script src="<%=basePath%>resource/js/common.js"></script>
	<script src="<%=basePath%>resource/laydate/laydate.js"></script>
	<script src="<%=basePath%>resource/assets/js/bootbox/bootbox.js"></script>

	<script src="<%=basePath%>resource/fileupload/js/fileinput.js"
		type="text/javascript"></script>
	<script
		src="<%=basePath%>resource/fileupload/js/fileinput_locale_zh.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="<%=basePath%>resource/js/notice/edit.js"></script>
	<script>
		var systemId = null;
		$(function() {
			systemId = "${systemId}";
			initData(systemId);
		})
		function confirm() {
			var isOk = false;
			var tempTitle = $("#title").val();
			var tempBrief = $("#brief").val();
			var tempContent = UE.getEditor('editor').getContent();
			if (null == systemId) {
				Notify("数据异常", 'top-right', '5000', 'danger', 'fa-desktop',true);
				return false;
			}
			if (tempTitle == null || $.trim(tempTitle).length == 0) {
				Notify("标题为空", 'top-right', '5000', 'danger', 'fa-desktop',true);
				return false;
			}
			if (tempBrief == null || $.trim(tempBrief).length == 0) {
				Notify("简要为空", 'top-right', '5000', 'danger', 'fa-desktop',true);
				return false;
			}
			$.ajax({
				type : "POST",
				url : "/SanghaCloud/admin/notice/updateInfo",
				data : {
					systemInfoId : systemId,
					title : tempTitle,
					content : tempContent,
					brief : tempBrief
				},
				dataType : "json",
				async : false,
				timeout : 5000,
				success : function(respObj) {
					if (respObj.code == 200) {
						isOk = true;
					} else {
						Notify("操作失败", 'top-right', '5000', 'danger',
								'fa-desktop', true);
					}
				}
			});
			return isOk;
		}
	</script>
</body>
<!--  /Body -->
</html>
