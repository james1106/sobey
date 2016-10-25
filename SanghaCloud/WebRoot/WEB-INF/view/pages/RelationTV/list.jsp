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
			<li class="active">关联TV</li>
		</ul>
	</div>
	<div class="header-title">
		<h1>
			关联TV <small> Relation TV </small>
		</h1>
	</div>
	<div class="page-body">
		<div class="widget">
			<div class="widget-header ">
				<span class="widget-caption">查看用户</span>
				<div class="widget-buttons">
					<!--     <a href="javascript:void(0)" id="fastAddBtn" class="btn btn-danger">快速</a>
                    <a href="<%=basePath%>admin/requestpage/user/add" target="iframe" class="btn btn-warning">添加用户</a>-->
					<!--    <a href="javascript:void(0)" id="importBtn"  class="btn btn-info">导入</a> -->
				</div>
			</div>
			<div class="widget-body">
				<div class="row">
					<div class="col-sm-12">
						<label> <input type="text"
							class="form-control spinner-input" placeholder="通过姓名查找"
							id="realName">
						</label> <label> <select class="form-control " name="roleId"
							id="role" style="margin-left: 10px;width: 200px">
								<option value="0">选择角色快速查询</option>
						</select>
						</label> <label> <a href="javascript:void(0);"
							class="btn btn-info" id="qShoppingTemplet"
							style="margin-left: 10px"> 查找</a>
						</label>
					</div>
				</div>
				<table class="table table-striped table-hover" id="simpledatatable">
					<thead>
						<tr>
							<th>姓名</th>
							<th>手机号码</th>
							<th>邮箱</th>
							<th>单位</th>
							<th>角色</th>
							<th>最后登录</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<!--      <tr>
                            <td>周芷若</td>
                            <td>13533404186</td>
                            <td>524585455@qq.com</td>
                            <td>四川经视</td>
                            <td>用户</td>
                            <td>2016-07-26 16:43:26</td>
							<td>
								<a href="detail.html" target="iframe" class="btn btn-info">详情</a>
								<a href="edit.html" target="iframe" class="btn btn-info">修改</a>
								<a href="javascript:void(0)" class="btn btn-danger">删除</a>
							</td>                           
                        </tr>
                         -->
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
		src="<%=basePath%>resource/js/relationtv/relationtv.js"></script>

	<script>
		var totalpage = 10;

		$(function() {
			addpage(); //加载分页方法
			totalpage = initData(1, null, null);

			//返回的数据不为undefind(也就是有数据时),调用$('#pageCon').show();
			$('#pageCon').show();

			//数据添加到页面后，调用一下方式
			$.jqPaginator('#pagination', {
				totalPages : totalpage, //总页数
				visiblePages : 3, //可见页面
				currentPage : 1, //当前页面
				onPageChange : function(num, type) {
					$('#showing').text(
							'共' + totalpage + '条  第' + num + '/' + totalpage
									+ '页');
					if (type != "init") {
						var realName = $("#realName").val();
						var roleId = $("#role").val();
						totalpage = initData(num, realName, roleId);
						//获取第num页数据，
						//userId, searchName, wbxID, czMoney, xfMoney, syMoney, uTel, isEnabled,isOperator 条件查询的参数
						//getData(num, userId, searchName, wbxID, czMoney, xfMoney, syMoney, uTel, isEnabled,isOperator);
					}
				}
			});
		})

	/* 

		$("#type").change(
				function() {
					$("#role").empty();
					$("#role").html("<option value='0'>选择角色快速查询</option>");
					var type = $("#type").val();
					totalpage = initData(1, type, null);
					//数据添加到页面后，调用一下方式
					$.jqPaginator('#pagination', {
						totalPages : totalpage, //总页数
						visiblePages : 3, //可见页面
						currentPage : 1, //当前页面
						onPageChange : function(num, type) {
							$('#showing').text(
									'共' + totalpage + '页  第1/' + totalpage
											+ '页');
							if (type != "init") {
								var type = $("#type").val();
								var roleId = $("#role").val();
								initData(num, type, roleId);
							}
						}
					});
				}) */

		$("#qShoppingTemplet").click(function() {
			var realName = $("#realName").val();
			var roleId = $("#role").val();
			totalpage = initData(1, realName, roleId);
			$.jqPaginator('#pagination', {
						totalPages : totalpage, //总页数
						visiblePages : 3, //可见页面
						currentPage : 1, //当前页面
						onPageChange : function(num, type) {
							$('#showing').text( '共' + totalpage + '页  第1/' + totalpage + '页');
							if (type != "init") {
								var realName = $("#realName").val();
								var roleId = $("#role").val();
								initData(num, realName, roleId);
							}
						}
					});
		})

		$("#role").focus(
				function() {
					var str = "";

					$.ajax({
						type : "GET",
						url : "/SanghaCloud/role/getrole",
						dataType : "json",
						async : false,
						timeout : 5000,
						success : function(respObj) {
							var dataList = respObj.data;
							str += "<option value='0''>无</option>";
							for (var i = 0; i < dataList.length; i++) {
								var temp = dataList[i];
								str += "<option value="+temp.id+">"
										+ temp.roleName + "</option>";
							}
						}
					})

					$("#role").empty();
					$("#role").html(str);
				})

		function delRelationTV(userId) {

			$.ajax({
				type : "GET",
				url : "/SanghaCloud/admin/relation/clearAll",
				data : {
					userId : userId
				},
				dataType : "json",
				async : false,
				timeout : 5000,
				success : function(respObj) {
					var status = respObj.code;
					if (status == 200) {
						var realName = $("#realName").val();
						var roleId = $("#role").val();
						totalpage = initData(1, realName, roleId);
						$.jqPaginator('#pagination', {
						totalPages : totalpage, //总页数
						visiblePages : 3, //可见页面
						currentPage : 1, //当前页面
						onPageChange : function(num, type) {
							$('#showing').text(
									'共' + totalpage + '页  第1/' + totalpage
											+ '页');
							if (type != "init") {
								var realName = $("#realName").val();
								var roleId = $("#role").val();
								initData(num, realName, roleId);
							}
						}
					});
					} else {
						Notify(respObj.msg, 'top-right', '5000', 'danger','fa-desktop', true);
					}
				}
			});
		}

		function clearData(userId) {
			//href='/SanghaCloud/admin/relation/clearAll?userId="+obj.id+"'
			var str = '<div id="myModal">' + '<div class="form-group">'
					+ '<label></label>'
					+ '<span class="input-icon icon-right">'
					+ '<div class="input-group">'
					+ '<span id="name" >确认清空吗？</span>' + '</div>' + '</span>'
					+ '</div>' + '</div>';
			bootbox.dialog({
				message : str,
				title : "提示框",
				buttons : {
					"关闭" : {
						className : "btn-default",
						callback : function() {

						}
					},
					success : {
						label : "确认",
						className : "btn-danger",
						callback : function() {
							delRelationTV(userId);
						}
					}
				}
			});
		}
	</script>
</body>
<!--  /Body -->
</html>
