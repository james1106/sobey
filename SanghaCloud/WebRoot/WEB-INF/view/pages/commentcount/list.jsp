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
			<li class="active">查看评价</li>
		</ul>
	</div>
	<div class="header-title">
		<h1>
			评价 <small> Evaluation </small>
		</h1>
	</div>
	<div class="page-body">
		<div class="widget">
			<div class="widget-header ">
				<span class="widget-caption">查看员工评价</span>

			</div>
			<div class="widget-body">
				<div class="row">
					<div class="col-sm-12">
						<label>分公司： <select id="companySelect" style="width: 200px">
								<option value="0">无</option>
								<c:forEach items="${coms}" var="company">
									<option value="${company.id }">${company.companyName}</option>
								</c:forEach>
						</select>
						</label> <label style="margin-left: 20px">办事处： <select
							id="officeSelect" style="width: 200px">
								<option value="0">选择办事处快速查询</option>
						</select>
						</label> <label style="margin-left: 20px">员工用户： <select
							id="userSelect" style="width: 300px">
								<option value="">选择用户快速查询</option>
						</select>
						</label> <label style="margin-left: 20px"> <a  class="btn btn-info" id="qShoppingTemplet"> 查找</a>
						</label>
					</div>
				</div>
				<table class="table table-striped table-hover" id="simpledatatable">

					<thead>
						<tr>
							<th>姓名</th>
							<th>单位</th>
							<th>处理速度总分</th>
							<th>服务态度总分</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<tr>
							<td>请按条件查询</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	
	<div class="page-body">
		<div class="widget">
			<div class="widget-header ">
				<span class="widget-caption">查看用户评价</span>

			</div>
			<div class="widget-body">
				<div class="row">
					<div class="col-sm-12">
						<label>分公司： <select id="companyUserSelect" style="width: 200px">
								<option value="0">无</option>
								<c:forEach items="${coms}" var="company">
									<option value="${company.id }">${company.companyName}</option>
								</c:forEach>
						</select>
						</label> <label style="margin-left: 20px">办事处： <select
							id="officeUserSelect" style="width: 200px">
								<option value="0">选择办事处快速查询</option>
						</select>
						</label> <label style="margin-left: 20px">电视台： <select
							id="tvSelect" style="width: 200px">
								<option value="">选择电视台快速查询</option>
						</select>
						</label> 
						<label style="margin-left: 20px">用户： <select
							id="userUserSelect" style="width: 300px">
								<option value="">选择用户快速查询</option>
						</select>
						</label>
						<label style="margin-left: 20px"> <a  class="btn btn-info" id="searchByUser" > 查找</a>
						</label>
					</div>
				</div>
				<table class="table table-striped table-hover" id="simpledatatable">

					<thead>
						<tr>
							<th>姓名</th>
							<th>单位</th>
							<th>处理速度总分</th>
							<th>服务态度总分</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="userDataBody">
						<tr>
							<td>请按条件查询</td>
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
	<script type="text/javascript" src="<%=basePath%>resource/js/commentcount/list.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/commentcount/userlist.js"></script>
	<script>
		$(function() {
		})

		$("#companySelect").select2({
			placeholder : "请选择分公司",
			allowClear : true,
		});
		$("#officeSelect").select2({
			placeholder : "请选择办事处",
			allowClear : true,
		});
		$("#userSelect").select2({
			placeholder : "请选择用户",
			allowClear : true,
		});
		
		//
		$("#companyUserSelect").select2({
			placeholder : "请选择分公司",
			allowClear : true,
		});
		$("#officeUserSelect").select2({
			placeholder : "请选择办事处",
			allowClear : true,
		});
		$("#tvSelect").select2({
			placeholder : "请选择电视台",
			allowClear : true,
		});
		$("#userUserSelect").select2({
			placeholder : "请选择用户",
			allowClear : true,
		});
		
		$("#companySelect").on("select2-open", function(e) {
			$.ajax({
				type : "POST",
				url : "/SanghaCloud/companyMobile/queryCompanyByType",
				dataType : "json",
				async : false,
				timeout : 5000,
				success : function(json) {
					if (json.code == 200) {
						buildCompanyData(json.data);
					}
				}
			});
		}) 
		/* $("#companySelect").change(function(){
		
			var companyId = $("#companySelect").val();
			if(companyId == 0){
				Notify("没有选择分公司",'top-right','5000','danger','fa-desktop',true);
				return;
			}
			$.ajax({
				type : "POST",
				url : "/SanghaCloud/officeMobile/getOfficeByCompany",
				data:{companyId:companyId},
				dataType : "json",
				async : false,
				timeout : 5000,
				success : function(json) {
					if (json.code == 200) {
						buildOfficeData(json.data);
					}else{
						Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
					}
				}
			});
		})
		$("#officeSelect").change(function(){
			var companyId = $("#companySelect").val();
			 var officeId = $("#officeSelect").val();
			$.ajax({
				type : "POST",
				url : "/SanghaCloud/admin/user/getUserByItem",
				data:{companyId:companyId,officeId:officeId},
				dataType : "json",
				timeout : 5000,
				async : false,
				success : function(json) {
					if (json.code == 200) {
						buildUserData(json.data);
					}
				}
			});
		}) */
		$("#officeSelect").on("select2-open", function(e) {
			var companyId = $("#companySelect").val();
			if(companyId == 0){
				/*Notify("没有选择分公司",'top-right','5000','danger','fa-desktop',true);  */
				var str = "<option value='0'>无</option>";
				$("#officeSelect").html(str);
				return;
			}
			$.ajax({
				type : "POST",
				url : "/SanghaCloud/officeMobile/getOfficeByCompany",
				data:{companyId:companyId},
				dataType : "json",
				async : false,
				timeout : 5000,
				success : function(json) {
					if (json.code == 200) {
						buildOfficeData(json.data);
					}else{
						Notify(json.msg,'top-right','5000','danger','fa-desktop',true);
					}
				}
			});
		}) 
	$("#userSelect").on("select2-open", function(e) {
			var companyId = $("#companySelect").val();
			 var officeId = $("#officeSelect").val();
			$.ajax({
				type : "POST",
				url : "/SanghaCloud/admin/user/getUserByItem",
				data:{companyId:companyId,officeId:officeId},
				dataType : "json",
				timeout : 5000,
				async : false,
				success : function(json) {
					if (json.code == 200) {
						buildUserData(json.data);
					}
				}
			});
		}) 
	</script>
</body>
<!--  /Body -->
</html>
