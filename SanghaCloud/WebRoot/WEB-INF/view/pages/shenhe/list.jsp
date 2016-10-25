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
            <li class="active">审核用户</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	审核
	        <small>
	            Audit
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">查看用户</span>
                <!-- <div class="widget-buttons">
                    <a href="add.html" target="iframe" class="btn btn-info">添加球队</a>
                </div>
                 -->
            </div>
            <div class="widget-body">
            	<div class="row">
		          <div class="col-sm-12">
		            <label>
		             	<select class="form-control input-sm" name="type"  id="type" >
		              		<option value="2">员工</option>
					        <option value="1">用户</option>
		              	</select>
		            </label>
		       <!--      <label>
		            	<a onclick='queryuser()' class="btn btn-info"  id="qShoppingTemplet"> 查找</a>
		            </label> -->
		          	</div>
		        </div>
                <table class="table table-striped table-hover" id="simpledatatable">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>姓名</th>
                            <th>手机</th>
                            <th>单位</th>
                            <th>类型</th>
                            <th>角色</th>
                            <th>产品用户</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody id="dataBody">
                  <!--       <tr>
                            <td>35</td>
                            <td>谢启谋</td>
                            <td>18783810615</td>
                            <td>成都魔豆</td>
                            <td>员工</td>
                            <td>
                            	<span class="input-icon icon-right">
                            	<select name="isdel" class="form-control">
                                    <option value="0">客服</option>
                                    <option value="1">销售</option>
                                    <option value="2">技术</option>
                                    <option value="3">研发</option>
                                </select>
                            </span>
                            </td>
							<td>
								<a href="javascript:void(0)" id="ballBtn" class="btn btn-info">通过</a>
								<a href="<%=basePath%>admin/requestpage/shenhe/detail" target="iframe" class="btn btn-info">详情</a>
								<a href="javascript:void(0)" class="btn btn-danger">不通过</a>
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
    <script src="<%=basePath%>resource/assets/js/fuelux/spinner/fuelux.spinner.min.js"></script>
	<!--Common Scripts -->
	<script src="<%=basePath%>resource/js/common.js"></script>
	<script src="<%=basePath%>resource/js/jqPaginator.js"></script>
	<script src="<%=basePath%>resource/js/jqPage.js"></script>
	<script src="<%=basePath%>resource/assets/js/bootbox/bootbox.js"></script>    
	<script src="<%=basePath%>resource/js/shenhe/shenhe.js"></script>
    <script>
    	var totalpage=10;
    	$(function(){
    		addpage(); //加载分页方法
    		
    		totalpage = initData(1,2);
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
	   	           		 var types = $("#type").val();
	   	            	totalpage = initData(num,types);
	   	            }
	   	        }
	   	    });   
	   	    
	   	    $("#type").change(function(){
	   	    	var type = $("#type").val();
	   	    	totalpage = initData(1,type);
	   	    	$.jqPaginator('#pagination', {
	   	    	totalPages: totalpage,  //总页数
	   	        visiblePages: 3,  //可见页面
	   	        currentPage: 1,   //当前页面
	   	        onPageChange: function (num, type) {
	   	        	$('#showing').text('共'+totalpage+'条  第1/'+totalpage+'页');
	   	            if(type != "init"){
	   	           		 var types = $("#type").val();
	   	            	totalpage = initData(num,types);
	   	            }
	   	        }
	   	    });   
	   	  });
   	})
    	
    	$("#ballBtn").on('click', function () {
    		var str='<div id="myModal">'+
				      	'<table class="table table-striped table-hover" id="simpledatatable">'+
				            '<thead>'+
				                '<tr>'+
				                    '<th width="25%">姓名</th>'+
				                    '<th width="25%">球号</th>'+
				                    '<th>修改</th>'+
				                '</tr>'+
				            '</thead>'+
				            '<tbody>'+
				                '<tr>'+
				                    '<td>张毅鸿 </td>'+
				                    '<td>12</td>'+
				                    '<td><input class="form-control" name="ballNum" id="ballNum" type="text"></td>'+
				                '</tr>'+
				                '<tr>'+
				                    '<td>张毅鸿 </td>'+
				                    '<td>12</td>'+
				                    '<td><input class="form-control" name="ballNum" type="text"></td>'+
				                '</tr>'+
				                '<tr>'+
				                    '<td>张毅鸿 </td>'+
				                    '<td>12</td>'+
				                    '<td><input class="form-control" name="ballNum" type="text"></td>'+
				                '</tr>'+
				            '</tbody>'+
				        '</table>'+
				    '</div>';
            bootbox.dialog({
                message: str,
                title: "修改球号",
                buttons: {
                	"关闭": {
                        className: "btn-default",
                        callback: function () {
                        	
                        }
                  	},
                    success: {
                        label: "确定修改",
                        className: "btn-primary",
                        callback: function () {
                        	var $parent=parent.document.getElementById('myModal');
							var $input=$parent.getElementsByTagName("input");
							var tagArr=[];//用于返回类名为className的元素
					        for(var i=0;i < $input.length; i++){
					            if($input[i].name == "ballNum"){
					            	console.log($input[i].value);
					            }
					        }
                        }
                    }
                    
                }
            });
            
           
        });
        
        function queryuser(){
        	 var types = $("#type").val();
	   	      totalpage = initData(1,types);
        }

    </script>
</body>
<!--  /Body -->
</html>
