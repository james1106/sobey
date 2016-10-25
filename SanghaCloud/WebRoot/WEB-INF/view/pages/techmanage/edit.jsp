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
#filePath {
	box-shadow: none !important;
	-webkit-box-shadow: none !important;
	background: #EEEEEE;
}

.col-xs-12 {
	padding-left: 0;
	padding-right: 0;
}

.col-sm-6:first-child {
	padding-left: 0;
	padding-right: 15px;
}

.col-sm-6:last-child-child {
	padding-right: 0;
	padding-left: 15px;
}
</style>
</head>
<!-- /Head -->
<!-- Body -->
<body>
	<div class="page-breadcrumbs">
		<ul class="breadcrumb">
			<li><a href="javascript:getPage('main.html')">首页</a></li>
			<li class="active">编辑技术</li>
		</ul>
	</div>
	<div class="header-title">
		<h1>
			技术 <small> Technic </small>
		</h1>
	</div>
	<div class="page-body">
		<div class="widget">
			<div class="widget-header ">
				<span class="widget-caption">编辑技术</span>
			</div>
			<div class="widget-body">
				<div id="registration-form">
					<form role="form" id="cateFrom">

						<div class="form-group">
							<label>姓名</label> <span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control" id="name"  type="text" disabled="disabled">
								</div>
							</span>
						</div>
						<div class="form-group">
							<label>手机号码</label> <span class="input-icon icon-right">
								<div class="spinner">
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="fa fa-laptop"></i>
										</span> <input class="form-control spinner-input" id="mobile" type="text" disabled="disabled" >
										
									</div>
								</div>
							</span>
						</div>
						<div class="form-group">
							<label>设置专长</label> 
							<span class="input-icon icon-right">
										<br>
										<span id="dataSpan">
											<label class="checkbox-inline">
											<input type="checkbox" name="tech" value="0" checked="checked">1技能列表
											</label>
											
										</span>
							</span>
						</div>
						<br>
						<a href="<%=basePath%>admin/requestpage/techmanage" target="iframe" onclick="return commit()" class="btn btn-info">提交修改</a>
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

	<!--Fuelux Spinner-->
	<script
		src="<%=basePath%>resource/assets/js/fuelux/spinner/fuelux.spinner.min.js"></script>

	<script src="<%=basePath%>resource/assets/js/toastr/toastr.js"></script>
	<!--Common Scripts -->
	<script src="<%=basePath%>resource/js/common.js"></script>
	<script src="<%=basePath%>resource/laydate/laydate.js"></script>

	<script src="<%=basePath%>resource/fileupload/js/fileinput.js"
		type="text/javascript"></script>
	<script
		src="<%=basePath%>resource/fileupload/js/fileinput_locale_zh.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="<%=basePath%>resource/js/techmanage/edit.js"></script>
	<script>
		$(function() {
			$('.spinner').spinner();
			var id = "${id}";
			var realName = "${realName }";
			var mobile = "${mobile}";
			$("#name").val(realName);
			$("#mobile").val(mobile);
			initData(id);
		})
	</script>
</body>
<!--  /Body -->
</html>
