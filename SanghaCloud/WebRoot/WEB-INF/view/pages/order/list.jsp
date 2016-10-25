<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<a href="<%=basePath%>admin/requestpage/count/detailcount" target="iframe" class="btn btn-info">订单统计</a>
				</div>
			</div>
			<div class="widget-body">
				<div class="row">
					<div class="col-sm-12">
						<label>订单号： <input type="text" name="orderNumber"
							id="orderNumber" class="form-control input-sm" />
						</label>  <label style="margin-left: 20px">电视台： 
								<select id="tvSelect" style="width: 300px">
								<option value="0">无</option>
								<c:forEach items="${tvs}" var="tv">
									<option value="${tv.id }">${tv.tvName}</option>
								</c:forEach>
						</select>
						</label>
						<label style="margin-left: 30px"> <a onclick='searchs()' class="btn btn-info"> 查找</a>
						</label>
					</div>

				</div>
				<table class="table table-striped table-hover" id="simpledatatable">

					<thead>
						<tr>
							<th>订单号</th>
							<th>电视台</th>
							<th>分类</th>
							<th>发起人</th>
							<th>客服</th>
							<th>分公司技术/处理时长/最后反馈</th>
							<th>总部技术/处理时长/最后反馈</th>
							<th>总部研发/最后补丁</th>
							<th>当前状态</th>
							<th>用户取消</th>
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
	<script type="text/javascript" src="<%=basePath%>resource/js/order/list.js"></script>
	<script>
		var totalpage = 10;
		$("#tvSelect").select2({
			placeholder : "请选择电视台",
			allowClear : true,
		});
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
						var orderNumber = $("#orderNumber").val();
						var tvId = $("#tvSelect").val();
						totalpage = initData(num, orderNumber, tvId);
					}
				}
			});
		})
		
		function searchs(){
	
			var orderNumber = $("#orderNumber").val();
			var tvId = $("#tvSelect").val();
		   	totalpage = initData(1,orderNumber,tvId);
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
						var tvId = $("#tvSelect").val();
						totalpage = initData(num, orderNumber, tvId);
					}
				}
			});
		}
		/* $("#tvSelect").on("select2-open", function(e) {
			$.ajax({
				type : "POST",
				url : "/SanghaCloud/admin/tv/dynamicGetTV",
				dataType : "json",
				timeout : 5000,
				async : false,
				success : function(json) {
					if (json.code == 200) {
						buildTVData(json.data);
					}
				}
			});
		})
		function buildTVData(data) {
			var len = data.length;
			var str = "";
			if (len == 0) {
				str += "<option value='0'>没有数据<option>";
			} else {
				str +="<option value='0'>无</option>";
				for (var i = 0; i < len; i++) {
					str += "<option value="+data[i].id+">" + data[i].tvName
							+ "</option>";
				}
			}
			$("#tvSelect").empty();
			$("#tvSelect").html(str);
		} */
	</script>
</body>
<!--  /Body -->
</html>
