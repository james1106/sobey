<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<!--
Beyond Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3
Version: 1.0.0
Purchase: http://wrapbootstrap.com
-->

<html xmlns="http://www.w3.org/1999/xhtml">
<!--Head-->
<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>Login Page</title>

    <meta name="description" content="login page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="<%=basePath%>resource/assets/img/favicon.png" type="image/x-icon">

    <!--Basic Styles-->
    <link href="<%=basePath%>resource/assets/css/bootstrap.min.css" rel="stylesheet" />

    <!--Fonts
    <link href="http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300" rel="stylesheet" type="text/css">
-->
    <!--Beyond styles-->
    <link id="beyond-link" href="<%=basePath%>resource/assets/css/beyond.min.css" rel="stylesheet" />
    <link href="<%=basePath%>resource/assets/css/animate.min.css" rel="stylesheet" />

    
    <style>
    	html,body,.login-container{
    		width: 100%;
    		height: 100%;
    	}
    	.login-container{
    		margin: auto;
    	}
    	.login-container .loginbox{
    		width: 400px!important;
    		height: 320px !important;
    		position: absolute;
    		left: 50%;
    		top: 50%;
    		margin-top: -250px;
    		margin-left: -200px;
    	}
    	.login-container .loginbox .social-buttons{
    		height: auto !important;
    	}
    	.login-container .loginbox .social-buttons img{
    		width: 100%;
    		height: 50px;
    	}
    </style>
</head>
<!--Head Ends-->
<!--Body-->
<body>
    <div class="login-container animated fadeInDown">
        <div class="loginbox bg-white">
            <div class="loginbox-title">SIGN IN</div>
            <div class="loginbox-social">
                <div class="social-buttons">
                    <img src="<%=basePath%>resource/img/logo.png" alt="该位置放置logo  logo高度为50px" />
                </div>
            </div>
            <div class="loginbox-or">
                <div class="or-line"></div>
                <div class="or">OR</div>
            </div>
            <div class="loginbox-textbox">
                <input type="text" class="form-control" id="mobile" placeholder="手机号" />
            </div>
            <div class="loginbox-textbox">
                <input type="password" class="form-control" id="password" placeholder="密码" />
            </div>
            <div class="loginbox-submit">
                <input type="button" class="btn btn-primary btn-block" id="loginBtn" value="登录">
            </div>
        </div>
    </div>
    <!--Basic Scripts-->
    <script src="<%=basePath%>resource/assets/js/jquery-2.0.3.min.js"></script>
    <script src="<%=basePath%>resource/assets/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resource/bootstrap/js/beyond.js"></script>
    <script src="<%=basePath%>resource/bootstrap/js/beyond.min.js"></script>
    <script src="<%=basePath%>resource/bootstrap/js/toastr/toastr.js"></script>
    <script src="<%=basePath%>resource/assets/js/jQuery.md5.js"></script>
    <script type="text/javascript">
    
    	if(window.top != window.self){
				window.top.location = window.location;
			}
    	
    	$("#loginBtn").click(function(){
    		var mobile = $("#mobile").val();
    		var password = $.md5($("#password").val());
    		if(mobile != "" && password!=""){
    			$.ajax({
    			type : "GET",
    			url : "<%=basePath%>group/pc/login",
    			data : {mobile:mobile,password:password},
    			dataType :"json",
    			async : false,
    			timeout : 5000,
    			success : function(respObj){
    				var status = respObj.code;
    				if(status==200){
    					var roleId = respObj.data.role.id;
    					location.href="/SanghaCloud/admin/requestpage/index?roleId="+roleId+"";
    				}else{
    					Notify(respObj.msg,'top-right','5000','danger','fa-desktop',true);
    				}
    			}
    		});
    	}else{
    		Notify("请输入手机号或密码",'top-right','5000','danger','fa-desktop',true);
    	}
    		
    })
    </script>
</body>
</html>
