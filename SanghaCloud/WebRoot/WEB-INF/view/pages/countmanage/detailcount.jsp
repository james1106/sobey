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
    
  	<link rel="stylesheet" href="<%=basePath%>resource/jquery-ui/jquery-ui.css" />
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">统计</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	         统计
	        <small>
	            Count
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">订单</span>
            </div>
            <div class="widget-body" style="overflow: hidden;">
				<div class="col-xs-6 col-md-6">
					<div class="info-box">
						<span class="info-box-icon" ><img alt="" src="<%=basePath%>resource/img/finish.png" height="90px" width="90px" style="margin-top: -10px"></span>
						<div class="info-box-content">
							<span class="info-box-text">已完成</span> <br> <span class="info-box-number"><span  id="finished">0</span> <small>条</small></span>
						</div>
					</div>
				</div>

				<div class="col-xs-6 col-md-6">
					<div class="info-box">
						<span class="info-box-icon"><img alt="" src="<%=basePath%>resource/img/nofinish.png" height="90px" width="90px" style="margin-top: -10px"></span>
						<div class="info-box-content">
							<span class="info-box-text">未完成</span> <br> <span
								class="info-box-number"><span id="nonover">0</span> <small>条</small></span>
						</div>
					</div>
					<!-- /.info-box -->
				</div>
				
				
				
			</div>
            <div class="widget-body" style="margin-top: -1px">
            
            	<div id="registration-form">
                    <form role="form">
                        <div class="form-group">
                            <label>选择分类</label>
                            <span class="input-icon icon-right">
                            	<select id="category" class="form-control" >
                                    <option value="" >无</option>
                                    <c:forEach items="${categorys}" var="category">
										<option value="${category.id }">${category.categoryName}</option>
									</c:forEach>
                                </select>
                            </span>
                        </div>
                       
                        <div class="form-group">
                            <label>按员工统计</label>
                            <span class="input-icon icon-right">
                            	<select id="employee" class="form-control" >
                                    <option value="" >无</option>
                                     <c:forEach items="${users}" var="user">
										<option value="${user.id }|${user.roleId}">${user.realName}(${user.mobile })</option>
									</c:forEach>
                                </select>
                            </span>
                        </div> 
                        
                        <div class="form-group">
                            <label>起始日期</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		<span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                    <input class="form-control date-picker" id="date-picker" type="text">
                                </div>
                            </span>
                        </div> 
                        <div class="form-group">
                            <label>截至日期</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		<span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                    <input class="form-control date-picker" id="date-picke" type="text">
                                </div>
                            </span>
                        </div> 
                        
                        <a   id="confirmBtn" class="btn btn-info">查询</a>
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
    
	<script src="<%=basePath%>resource/jquery-ui/jquery-ui.js" ></script>
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
	<script type="text/javascript" src="<%=basePath%>resource/js/countmanage/detail.js"></script>
    <script>
		var projects = [
			{
			    value: "jquery",
			    label: "jQuery",
			    desc: "the write less, do more, JavaScript library",
			    icon: "jquery_32x32.png"
		  	},
		  	{
			    value: "jquery-ui",
			    label: "jQuery UI",
			    desc: "the official user interface library for jQuery",
			    icon: "jqueryui_32x32.png"
		  	},
		  	{
			    value: "sizzlejs",
			    label: "Sizzle JS",
			    desc: "a pure-JavaScript CSS selector engine",
			    icon: "sizzlejs_32x32.png"
		  	},
		  	{
			    value: "jquery",
			    label: "jQuery",
			    desc: "the write less, do more, JavaScript library",
			    icon: "jquery_32x32.png"
		  	},
		  	{
			    value: "jquery-ui",
			    label: "jQuery UI",
			    desc: "the official user interface library for jQuery",
			    icon: "jqueryui_32x32.png"
		  	},
		  	{
			    value: "sizzlejs",
			    label: "Sizzle JS",
			    desc: "a pure-JavaScript CSS selector engine",
			    icon: "sizzlejs_32x32.png"
		  	}
		]
		$( "#leagueName" ).autocomplete({
		  source: projects,
		  focus: function( event, ui ) {
		    $( "#leagueName" ).val( ui.item.value );
		    return false;
		  },
		  select: function( event, ui ) {
		    $( "#leagueName" ).val( ui.item.desc );		 
		    return false;
		  }
		})
        $("#category").select2({
            placeholder: "请选择分类",
            allowClear: true
        });
        $("#employee").select2({
            placeholder: "请选择员工",
            allowClear: true
        });
        
       
       
        $('#date-picker').datepicker({
        	format:"yyyy-mm-dd"
        });
        $('#time-picker').timepicker({
        	format:"yyyy-mm-dd"
        });
         $('#date-picke').datepicker({
        	format:"yyyy-mm-dd"
        });
        $('#time-picke').timepicker({
        	format:"yyyy-mm-dd"
        });
        
        $('.spinner').spinner();
        
         
      
    </script>
</body>
<!--  /Body -->
</html>
