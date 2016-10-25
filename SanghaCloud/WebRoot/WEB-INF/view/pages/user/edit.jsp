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
			<li class="active">编辑用户</li>
		</ul>
	</div>
	<div class="header-title">
		<h1>
			用户 <small> User </small>
		</h1>
	</div>
	<div class="page-body">
		<div class="widget">
			<div class="widget-header ">
				<span class="widget-caption">编辑用户</span>
			</div>
			<div class="widget-body">
				<div id="registration-form">
					<form role="form">
						

						<div class="form-group" id="mobileDiv" style="display: none">
							<label>手机号码</label> <span class="input-icon icon-right"> <!-- 	<div class="spinner"> -->
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control spinner-input" id="mobile" type="text" disabled="true">
								</div> <!--       </div> -->
							</span>
						</div>
						<div class="form-group" style="display: none" id="nameDiv">
							<label>姓名</label> <span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control" id="realName" type="text">
								</div>
							</span>
						</div>
				<!-- 		<div class="form-group" style="display: none" id="pwdDiv">
							<label>密码</label> <span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control" id="pwd" type="password">
								</div>
							</span>
						</div> -->
						<div class="form-group" style="display: none" id="emailDiv">
							<label>邮箱</label> <span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control spinner-input" id="email" type="text">
								</div>
							</span>
						</div>
						<div class="form-group" style="display: none" id="isPUserDiv">
							<label>产品用户</label> 
								<span class="input-icon icon-right">
									<select name="isPUser" class="form-control" id="isPUser" >
									<option value="0">是</option>
									<option value="1">否</option>
									</select>
								</span>
						</div>
						<div class="form-group" style="display: none" id="qqDiv">
							<label>QQ&nbsp;&nbsp;<span style="font-size: 12px">(可选)</span></label>
							<span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control" id="qq" type="text">
								</div>
							</span>
						</div>
						
						<div class="form-group" style="display: none" id="unitDiv">
							<label>单位&nbsp;&nbsp;<span style="font-size: 8px; color: red">(如单位被修改，最终修改后的结果以此为准)</span></label> <span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control" id="officeName" type="text" disabled="true">
								</div>
							</span>
						</div>
						<div class="form-group" style="display: none" id="isOfficeDiv">
							<label>是否修改所属单位</label> <span class="input-icon icon-right">
								<select name="roleId" class="form-control" id="isOfficeSelect" >
									<option value="0">否</option>
									<option value="1">是</option>
							</select>
							</span>
						</div>
						<div class="form-group" style="display: none" id="headCompanyDiv">
                            <label>总公司</label>
                            <span class="input-icon icon-right">
                            	<select name="companyId" class="form-control" id="headCompanyId">
                            		<option value="0">选择总公司</option>
                                </select>
                            </span>
                        </div> 
						<div class="form-group" style="display: none" id="companyDiv">
							<label>分公司</label> <span class="input-icon icon-right"> <select
								name="companyId" class="form-control" id="companyId">
									<option value="0">选择分公司</option>
							</select>
							</span>
						</div>
						<div class="form-group" style="display: none" id="officeDiv">
							<label>办事处</label> <span class="input-icon icon-right"> 
							<select name="officeId" class="form-control" id="officeId">
									<option value="0">选择办事处</option>
							</select>
							</span>
						</div>
						<div class="form-group" style="display: none" id="tvDiv">
							<label>电视台</label> <span class="input-icon icon-right"> <select
								name="officeId" class="form-control" id="tvId">
									<option value="0">选择电视台</option>
							</select>
							</span>
						</div>

						<div class="form-group" style="display: none" id="introduceDiv">
							<label>责任描述&nbsp;&nbsp;<span style="font-size: 12px">(可选)</span></label>
							<span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control" id="introduce" type="text">
								</div>
							</span>
						</div>
						<div class="form-group" style="display: none" id="jobTitleDiv">
							<label>职位&nbsp;&nbsp;<span style="font-size: 12px">(可选)</span></label>
							<span class="input-icon icon-right">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-laptop"></i>
									</span> <input class="form-control" id="jobTitle" type="text">
								</div>
							</span>
						</div>
						<div class="form-group" style="display: none" id="roleDiv">
							<label>角色</label> <span class="input-icon icon-right"> <select name="roleId" class="form-control" id="roleId" >
									<option value="0">请分配角色</option>
							</select>
							</span>
						</div>
						
						<div class="form-group" style="display: none" id="saleDiv">
							<label>销售接洽&nbsp;&nbsp;<span style="font-size: 12px">(可选)</span></label>
							<span class="input-icon icon-right"> <select name="sale" class="form-control" id="sale">
									<option value="0">选择该用户的销售接洽</option>
							</select>
							</span>
						</div>


						<a href="<%=basePath%>admin/requestpage/user/list" target="iframe" id="confirmBtn" class="btn btn-info">修改</a>
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
	<script src=".<%=basePath%>resource/laydate/laydate.js"></script>

	<script src="<%=basePath%>resource/fileupload/js/fileinput.js"
		type="text/javascript"></script>
	<script
		src="<%=basePath%>resource/fileupload/js/fileinput_locale_zh.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="<%=basePath%>resource/js/user/edit.js"></script>
	<script>
		var userId = "${userId}";
		var type = "${type}";
		
		$(function() {

			$.ajax({
				type : "POST",
				url : "/SanghaCloud/admin/user/getUserById", // URL
				data : {userId:userId,type:type},
				dataType : "json",
				async : false,
				timeout : 5000,
				success : function(respObj) {
					if (respObj.code == 200) {
						// 请求成功后 初始化组建
						
						initView(type, userId,respObj.data);
						
					} else {
						Notify("获取失败", 'top-right', '5000', 'danger','fa-desktop', true);
					}
				}
			});

			

			$('.spinner').spinner();

		

			getBannerInfo();//获取数据方法
		})
		var fillPath = '', initialPreview = [];
		function getBannerInfo() {
			for (var i = 0; i < 1; i++) { //把图片循环读取出来
				fillPath = 'http://admin.uhisports.com/upload/3/CR-tFH8pp2veg.jpg';

				initialPreview[i] = '<img width="auto" height="160" class="file-preview-image"  src="'+fillPath+'">'
						+ '<div class="file-thumbnail-footer">'
						+ '<div class="file-caption-name" title="teacher2.jpg" style="width: 250px;">teacher2.jpg</div>'
						+ '<div class="file-actions">'
						+ '<div class="file-footer-buttons">'
						+ '<button type="button" class="kv-file-remove btn btn-xs btn-default" title="Remove file">'
						+ '<i class="glyphicon glyphicon-trash text-danger"></i>'
						+ '</button>'
						+ '</div>'
						+ '<div class="file-upload-indicator" tabindex="-1" title="Not uploaded yet">'
						+ '<i class="glyphicon glyphicon-hand-down text-warning"></i>'
						+ '</div>'
						+ '<div class="clearfix"></div>'
						+ '</div>'
						+ '</div>';
				$("#headFilePath").html(fillPath);
				$("#diplomaFilePath").html(fillPath);
			}
			initFileUpload(initialPreview); //初始化图片上传插件方法
			$('.kv-file-remove')
					.each(
							function(index) {
								$(this)
										.click(
												function() {
													$(this)
															.parent()
															.parent()
															.parent()
															.parent()
															.fadeOut(
																	'slow',
																	function() {
																		var imgSrc = $(
																				".file-preview-image")
																				.eq(
																						$(
																								this)
																								.index())
																				.attr(
																						"src");
																		$(this)
																				.remove();
																				$(
																						'.file-caption-name')
																						.attr(
																								'name',
																								$(".kv-file-remove").length
																										+ ' file selected'),
																				$(
																						'.file-caption-name')
																						.html(
																								'<span class="glyphicon glyphicon-file kv-caption-icon"></span>'
																										+ $(".kv-file-remove").length
																										+ ' file selected');
																		$(
																				'.goodsImage')
																				.each(
																						function() {
																							var fid = $(
																									this)
																									.val();
																							if (imgSrc
																									.indexOf(fid) > 0) {
																								$(
																										this)
																										.remove();
																							}
																						});
																	});
												})
							})
		}

		function initFileUpload(initialPreview) {
			$("#headUpload").fileinput({
				language : 'zh',
				initialPreview : initialPreview,
				uploadUrl : ' ', // 图片上传接口
				allowedFileExtensions : [ "jpg", "png", "gif" ],
				maxFileSize : 50,//上传图片的最大限制  50KB
				overwriteInitial : true,
				initialPreviewShowDelete : true,
				minImageWidth : 50,
				minImageHeight : 50
			}).on(
					"fileuploaded",
					function(event, data, previewId, index) {
						if (null != data) {
							fillPath = data.response.data[0].fid;
							$("#headFilePath").html(fillPath);
						} else {
							Notify("上传失败", 'top-right', '5000', 'danger',
									'fa-desktop', true);
						}
					});
			$("#diplomaUpload").fileinput({
				language : 'zh',
				initialPreview : initialPreview,
				uploadUrl : ' ', // 图片上传接口
				allowedFileExtensions : [ "jpg", "png", "gif" ],
				maxFileSize : 50,//上传图片的最大限制  50KB
				overwriteInitial : true,
				initialPreviewShowDelete : true,
				minImageWidth : 50,
				minImageHeight : 50
			}).on(
					"fileuploaded",
					function(event, data, previewId, index) {
						if (null != data) {
							fillPath = data.response.data[0].fid;
							$("#diplomaFilePath").html(fillPath);
						} else {
							Notify("上传失败", 'top-right', '5000', 'danger',
									'fa-desktop', true);
						}
					});

		}

		$('#addMemberBtn').click(function() {
			var $addMemberBox = $('#addMemberBox');
			if ($addMemberBox.css('display') == 'none') {
				$addMemberBox.slideDown();
				$(this).html("关闭快速创建球员");
			} else {
				$addMemberBox.slideUp();
				$(this).html("快速创建球员");
			}
		})

		
		
		
	</script>
</body>
<!--  /Body -->
</html>
