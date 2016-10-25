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

<style>
.uploadBox {
	width: 100%;
	-moz-background-clip: padding !important;
	border-radius: 0 !important;
	background-clip: padding-box !important;
	color: #858585;
	background-color: #fbfbfb;
	border: 1px solid #d5d5d5;
	position: relative;
	height: 34px;
}

.uploadBox p {
	margin: 0;
	line-height: 34px;
	padding: 0 10px;
	font-size: 13px;
}

.uploadBox a {
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

.uploadBox a i {
	margin-right: 10px;
}

.uploadBox input {
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
			标签 <small> Lable </small>
		</h1>
	</div>
	<div class="page-body">
		<div class="widget">
			<div class="widget-header ">
				<span class="widget-caption">设置标签</span>

			</div>
			<div class="widget-body">
				<div class="row">
					<div class="col-sm-12">
						<span class="input-icon icon-right"> <select name="type"
							class="form-control" id="type">
								<option value="0">选择设置项</option>
								<option value="3">客服标签</option>
								<option value="10">技术标签</option>
						</select>
						</span>
					</div>
				</div>
				<div id="bigDiv" style="display: none;margin-top: 40px">
					<div class="row">
						<div class="col-sm-12" id="lableTitle">客服标签</div>
					</div>
					<table class="table table-striped table-hover" id="simpledatatable">
						<thead>
							<tr>
								<th>标签管理 | 已勾选则显示在APP端</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td id="dataTD"></td>
							</tr>
						</tbody>
					</table>
					<a href="<%=basePath%>admin/requestpage/lablemanage"target="iframe" onclick="return submitInfo()" class="btn btn-info" style="margin-top: 20px">确认修改</a>
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
	<script
		src="<%=basePath%>resource/assets/js/fuelux/spinner/fuelux.spinner.min.js"></script>

	<script src="<%=basePath%>resource/fileupload/js/fileinput.js"
		type="text/javascript"></script>
	<script
		src="<%=basePath%>resource/fileupload/js/fileinput_locale_zh.js"
		type="text/javascript"></script>
	<!--Common Scripts -->
	<script src="<%=basePath%>resource/js/common.js"></script>
	<script src="<%=basePath%>resource/js/jqPaginator.js"></script>
	<script src="<%=basePath%>resource/js/jqPage.js"></script>
	<script src="<%=basePath%>resource/assets/js/bootbox/bootbox.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>resource/js/lablemanage/edit.js"></script>

	<script>
		$("#type").change(function() {

			var type = $("#type").val();
			if (type == 0) {
				$("#bigDiv").hide("slow");
			} else if (type == 3) {
				$("#lableTitle").html("客服标签");
				$("#bigDiv").show("slow");
				initData(type);
			} else if (type == 10) {
				$("#lableTitle").html("技术标签");
				$("#bigDiv").show("slow");
				initData(type);
			}
		});
	</script>
</body>
<!--  /Body -->
</html>
