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
    <title>Sobey管理后台</title>

    <meta name="description" content="Dashboard" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="<%=basePath%>resource/resource/assets/img/favicon.png" type="image/x-icon">


    <!--Basic Styles-->
    <link href="<%=basePath%>resource/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link id="bootstrap-rtl-link" href="" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/font-awesome.min.css" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/weather-icons.min.css" rel="stylesheet" />

    <!--Fonts-->
    <!--Beyond styles-->
    <link id="beyond-link" href="<%=basePath%>resource/assets/css/beyond.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resource/assets/css/demo.min.css" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/typicons.min.css" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/animate.min.css" rel="stylesheet" />
    <link id="skin-link" href="<%=basePath%>resource/assets/css/skins/deepblue.min.css" rel="stylesheet" type="text/css" />

	<link href="<%=basePath%>resource/css/common.css" rel="stylesheet" />
    <!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
    <script src="<%=basePath%>resource/assets/js/skins.min.js"></script>
    <script>
    	function resizeHeight() {
	
	        var ifm= document.getElementById("iframe");
	        var subWeb = document.frames ? document.frames["iframe"].document : ifm.contentDocument;
	        if (ifm != null && subWeb != null) {
	            ifm.height = subWeb.body.scrollHeight;
	        }
	    }

    </script>
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <!-- Loading Container -->
    <div class="loading-container">
        <div class="loading-progress">
            <div class="rotator">
                <div class="rotator">
                    <div class="rotator colored">
                        <div class="rotator">
                            <div class="rotator colored">
                                <div class="rotator colored"></div>
                                <div class="rotator"></div>
                            </div>
                            <div class="rotator colored"></div>
                        </div>
                        <div class="rotator"></div>
                    </div>
                    <div class="rotator"></div>
                </div>
                <div class="rotator"></div>
            </div>
            <div class="rotator"></div>
        </div>
    </div>
    <!--  /Loading Container -->
    <!-- Navbar -->
    <div class="navbar">
        <div class="navbar-inner">
            <div class="navbar-container">
                <!-- Navbar Barnd -->
                <div class="navbar-header pull-left">
                    <a href="#" class="navbar-brand">
                        <span class="logo-mini"><b>Sobey</b></span>
            			<span class="logo-lg"><b>Sobey后台</b>管理</span>
                    </a>
                </div>
                <!-- /Navbar Barnd -->

                <!-- Sidebar Collapse -->
                <div class="sidebar-collapse" id="sidebar-collapse">
                    <i class="collapse-icon fa fa-bars"></i>
                </div>
                <!-- /Sidebar Collapse -->
                <!-- Account Area and Settings --->
                <div class="navbar-header pull-right">
                   
                </div>
                <!-- /Account Area and Settings -->
            </div>
        </div>
    </div>
    <!-- /Navbar -->
    <!-- Main Container -->
    <div class="main-container container-fluid">
        <!-- Page Container -->
        <div class="page-container">
            <!-- Page Sidebar -->
            <div class="page-sidebar" id="sidebar">
                <!-- Page Sidebar Header-->
                <div class="sidebar-header-wrapper">功能</div>
                <!-- /Page Sidebar Header -->
                <!-- Sidebar Menu -->
                <ul class="nav sidebar-menu" id="sidebar-menu">
                    <!--Dashboard-->
                    <!-- 
                     <li>
                        <a href="<%=basePath%>admin/requestpage/user/list" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 用户管理 </span>
                        </a>
                    </li> 
                    <li>
                        <a href="<%=basePath%>admin/requestpage/servicemanage" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 客服管理 </span>
                        </a>
                    </li> 
                    <li>
                        <a href="<%=basePath%>admin/requestpage/techmanage" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 技术管理 </span>
                        </a>
                    </li> 
                    <li>
                        <a href="<%=basePath%>admin/requestpage/shenhe" target="iframe">
                            <i class="menu-icon fa fa-circle-o"></i>
                            <span class="menu-text"> 审核用户 </span>
                        </a>
                    </li>
                     <li>
                        <a href="<%=basePath%>admin/requestpage/tv" target="iframe">
                            <i class="menu-icon fa fa-qq"></i>
                            <span class="menu-text"> 电视台管理 </span>
                        </a>
                    </li>
                     <li>
                        <a href="<%=basePath%>admin/requestpage/companymanage" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 单位管理 </span>
                        </a>
                    </li>
                     <li>
                        <a href="<%=basePath%>admin/requestpage/news" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 资讯管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/requestpage/ads" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 广告管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/requestpage/device" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 设备管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/requestpage/order" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 订单管理 </span>
                        </a>
                    </li>
                     <li>
                        <a href="<%=basePath%>admin/requestpage/lablemanage" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 标签管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/requestpage/ordercategorymanage" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 产品分类管理 </span>
                        </a>
                    </li>
                     <li>
                        <a href="<%=basePath%>admin/requestpage/powermanage" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text">权限管理 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/requestpage/productfeel" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 产品体验申请 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/requestpage/notice" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 系统公告 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/requestpage/commentcount" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 评价统计 </span>
                        </a>
                    </li>
                     <li>
                        <a href="<%=basePath%>admin/requestpage/complaincount" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text">投诉统计 </span>
                        </a>
                    </li>
                     <li>
                        <a href="<%=basePath%>admin/requestpage/sign" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text">签到统计 </span>
                        </a>
                    </li>
                     <li>
                        <a href="<%=basePath%>admin/requestpage/patch" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text">补丁统计 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/requestpage/votecount" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text">投票统计 </span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=basePath%>admin/requestpage/count/countmanage" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 统计 </span>
                        </a>
                    </li>
                     <li>
                        <a href="<%=basePath%>admin/requestpage/suggest" target="iframe">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text">意见反馈 </span>
                        </a>
                    </li> -->
                    <li>
                        <a href="#" class="menu-dropdown">
                            <i class="menu-icon fa fa-envelope"></i>
                            <span class="menu-text"> 个人中心 </span>
                        </a>
                        <ul class="submenu">
                            <li>
                                <a href="<%=basePath%>admin/requestpage/selfCenter/order" target="iframe">
                                    <span class="menu-text">订单管理</span>
                                </a>
                            </li>
                            <li>
                                <a href="<%=basePath%>admin/requestpage/selfCenter/editPwd" target="iframe">
                                    <span class="menu-text">修改密码</span>
                                </a>
                            </li>
                        </ul>
                    </li> 
                    <li>
                        <a href="<%=basePath%>admin/requestpage/loginout">
                            <i class="menu-icon fa fa-sign-out"></i>
                            <span class="menu-text"> 登出 </span>
                        </a>
                    </li>
                    <!--UI Elements-->
                    
                </ul>
                <!-- /Sidebar Menu -->
            </div>
            <!-- /Page Sidebar -->
            <!-- Page Content -->
            <div class="page-content" id="main-content">
            	<iframe id="iframe" name="iframe" onload="resizeHeight()" src="<%=basePath%>admin/requestpage/defaultPage/index" frameborder="0" style="width: 100%;height: 1000px;"></iframe>
            </div>
            <!-- /Page Content -->
        </div>
        <!-- /Page Container -->
        <!-- Main Container -->

    </div>

    <!--Basic Scripts-->
    <script src="<%=basePath%>resource/assets/js/jquery-2.0.3.min.js"></script>
    <script src="<%=basePath%>resource/assets/js/bootstrap.min.js"></script>

    <!--Beyond Scripts-->
    <script src="<%=basePath%>resource/assets/js/beyond.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/index/index.js"></script>
	<script>
	
		var roleId = "${roleId}";
		
		$(function(){
			initData(roleId);
		});
		
		
		if(window.top != window.self){
				window.top.location = window.location;
			}
	    var secthei = document.documentElement.clientHeight || document.body.clientHeight;
	    var headerh = 55;
	    //        document.getElementById('sect').style.height = (secthei-headerh) + "px";
	    document.getElementById("iframe").style.height = (secthei-headerh) + "px";
	    $('#sidebar-collapse').click(function(){
	    	if($(this).hasClass('active')){
	    		$('.navbar-brand .logo-lg').hide();
	    		$('.navbar-brand .logo-mini').show();
	    	}else{
	    		$('.navbar-brand .logo-lg').show();
	    		$('.navbar-brand .logo-mini').hide();
	    	}
	    })
	    
	    $('.sidebar-menu li').each(function(){
	    	var $this=$(this).find('a');
	    	$(this).click(function(){
	    		$('.sidebar-menu li').removeClass('active');
	    		$(this).addClass('active');
	    	})
	    })
	</script>
</body>
<!--  /Body -->
</html>
