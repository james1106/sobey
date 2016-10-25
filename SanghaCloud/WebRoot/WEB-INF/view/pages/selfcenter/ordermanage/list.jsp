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


</head>
<!-- /Head -->
<!-- Body -->
<body>
	<div class="page-breadcrumbs">
		<ul class="breadcrumb">
			<li><a href="javascript:getPage('main.html')">首页</a></li>
			<li class="active">订单管理</li>
		</ul>
	</div>
	<div class="header-title">
		<h1>
			订单 <small> Order </small>
		</h1>
	</div>
	<div class="page-body">
		<div class="widget">
			<div class="widget-header ">
				<span class="widget-caption">订单管理</span>
				<div class="widget-buttons">
				<!-- 	<a href="<%=basePath%>admin/requestpage/count/detailcount" target="iframe" class="btn btn-info">订单统计</a> -->
				</div>
			</div>
			<div class="widget-body">
				<div class="row">
					<div class="col-sm-12">
						<label>订单号：
							 <input type="text" name="orderNumber" id="orderNumber" class="form-control input-sm" />
						</label>  
						<label style="margin-left: 30px"> 
							<a onclick="searchss()" class="btn btn-info"> 查找</a>
						</label>
					</div>

				</div>
				<table class="table table-striped table-hover" id="simpledatatable">

					<thead>
						<tr>
							<th>订单号</th>
							<th>分类</th>
							<th>发起人</th>
							<th>客服</th>
							<th>TSC</th>
							<th>总部技术</th>
							<th>总部研发</th>
							<th>当前状态</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<tr>
							<td>没有数据</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!--Basic Scripts-->
	<script src="<%=basePath%>resource/assets/js/jquery-2.0.3.min.js"></script>
	<script src="<%=basePath%>resource/assets/js/bootstrap.min.js"></script>

	<script src="<%=basePath%>resource/assets/js/select2/select2.js"></script>

	<!--Beyond Scripts-->
	<script src="<%=basePath%>resource/assets/js/beyond.min.js"></script>
	<script src="<%=basePath%>resource/assets/js/toastr/toastr.js"></script>
	<!--Common Scripts -->
	<script src="<%=basePath%>resource/js/common.js"></script>
	<script src="<%=basePath%>resource/js/jqPaginator.js"></script>
	<script src="<%=basePath%>resource/js/jqPage.js"></script>
	<script src="<%=basePath%>resource/laydate/laydate.js"></script>
	<script src="<%=basePath%>resource/assets/js/bootbox/bootbox.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/selfcenter/ordermanage/list.js"></script>
	<script>
		var totalpage = 10;
		
		$(function() {
			addpage(); //加载分页方法

			totalpage = initData(1, null);
			console.debug(totalpage)
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
						var orderNumber = $("#orderNumber").val();
						totalpage = initData(num, orderNumber);
					}
				}
			});
		})
		
		function searchss(){
			var orderNumber = $("#orderNumber").val();
			if(orderNumber.length == 0){
				return;
			}
			totalpage = initData(1, orderNumber);
				$.jqPaginator('#pagination', {
				totalPages : totalpage, //总页数
				visiblePages : 3, //可见页面
				currentPage : 1, //当前页面
				onPageChange : function(num, type) {
					$('#showing').text(
							'共' + totalpage + '条  第' + num + '/' + totalpage
									+ '页');
					if (type != "init") {
						var orderNumber = $("#orderNumber").val();
						totalpage = initData(num, orderNumber);
					}
				}
			});
		}
		
	</script>
</body>
<!--  /Body -->
</html>
