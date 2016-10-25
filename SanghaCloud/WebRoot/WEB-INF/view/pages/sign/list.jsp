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
    
  
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">查看签到</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	         签到
	        <small>
	            Sign
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看签到</span>
               
            </div>
            <div class="widget-body">
            	<div class="row">
		          <div class="col-sm-12">
		          <label>
		             	<select class="form-control input-sm" name="type"  id="type" >
		              		<option value="0">员工</option>
					        <option value="1">用户</option>
		              	</select>
		            </label>
		            <label>姓名：
		             	<input type="text" name="name" id="name" class="form-control input-sm" />
		            </label>
		            <label>
		            	<a  class="btn btn-info"  id="qShoppingTemplet"> 查找</a>
		            </label>
		          	</div>
		        </div>
                <table class="table table-striped table-hover" id="simpledatatable">
                
                    <thead>
                        <tr>
                            <th>姓名</th>
                            <th>手机号码</th>
                            <th>类型</th>
                            <th>签到积分</th>
                            <th>累计签到</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody id="dataBody">
                        <tr>
                            <td>张三</td>
                            <th>15232655525</th>
                            <td>用户</td>
                            <td>120</td>
                            <td>6</td>
							<td>
								<a href="detail-list.html" target="iframe" class="btn btn-info">详情</a>
								
							</td>                           
                        </tr>
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
	<!--Common Scripts -->
	<script src="<%=basePath%>resource/js/common.js"></script>
	<script src="<%=basePath%>resource/js/jqPaginator.js"></script>
	<script src="<%=basePath%>resource/js/jqPage.js"></script>
	<script src="<%=basePath%>resource/laydate/laydate.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/sign/list.js"></script>
    <script>
    	var totalpage=10;
    	$(function(){
    		addpage(); //加载分页方法
    		var type = $("#type").val();
    		totalpage = initData(1,null,type);
    		
    		//返回的数据不为undefind(也就是有数据时),调用$('#pageCon').show();
			$('#pageCon').show();


			//数据添加到页面后，调用一下方式
    		$.jqPaginator('#pagination', {
	   	    	totalPages: totalpage,  //总页数
	   	        visiblePages: 3,  //可见页面
	   	        currentPage: 1,   //当前页面
	   	        onPageChange: function (num, type) {
	   	        	$('#showing').text('共'+totalpage+'条  第1/'+totalpage+'页');
	   	            if(type != "init"){
	   	            	var type = $("#type").val();
    					var realName = $("#name").val();
	   	            	totalpage = initData(num,realName,type);
	   	            	//获取第num页数据，
	   	            	//userId, searchName, wbxID, czMoney, xfMoney, syMoney, uTel, isEnabled,isOperator 条件查询的参数
	   	            	//getData(num, userId, searchName, wbxID, czMoney, xfMoney, syMoney, uTel, isEnabled,isOperator);
	   	            }
	   	        }
	   	    });   
    	})
    	$("#qShoppingTemplet").click(function(){
    		var type = $("#type").val();
    		var realName = $("#name").val();
    		totalpage = initData(1,realName,type);
    		$('#pageCon').show();
    		$.jqPaginator('#pagination', {
	   	    	totalPages: totalpage,  //总页数
	   	        visiblePages: 3,  //可见页面
	   	        currentPage: 1,   //当前页面
	   	        onPageChange: function (num, type) {
	   	        	$('#showing').text('共'+totalpage+'条  第1/'+totalpage+'页');
	   	            if(type != "init"){
	   	            	var type = $("#type").val();
    					var realName = $("#name").val();
	   	            	totalpage = initData(num,realName,type);
	   	            	//获取第num页数据，
	   	            	//userId, searchName, wbxID, czMoney, xfMoney, syMoney, uTel, isEnabled,isOperator 条件查询的参数
	   	            	//getData(num, userId, searchName, wbxID, czMoney, xfMoney, syMoney, uTel, isEnabled,isOperator);
	   	            }
	   	        }
    	})
    	})
    </script>
</body>
<!--  /Body -->
</html>
