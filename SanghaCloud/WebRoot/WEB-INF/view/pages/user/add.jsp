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
    
    <link href="<%=basePath%>resource/fileupload/css/fileinput.css"  rel="stylesheet" type="text/css" />
    
    <style>
    	#filePath{
    		box-shadow: none !important;
    		-webkit-box-shadow: none !important;
    		background: #EEEEEE;
    	}
    </style>
  	
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">添加用户</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	用户
	        <small>
	            User
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">添加用户&nbsp;&nbsp;<span style="font-size: 12px">(以下选项必填)</span></span>
            </div>
            <div class="widget-body">
            	<div id="registration-form">
                    <form role="form">
             <!--    		<div class="form-group">
                            <label>头像</label>
                            <div>
							  	<input id="headUpload" name="res" type="file" class="file-loading" >
							</div>
                            <span class="input-icon icon-right">
                            	<div class="input-group" style="margin-top: 5px;">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<div class="form-control" id="headFilePath" type="text"></div>
                                </div>
                            </span>
                        </div>                 --> 	
                        <div class="form-group">
                            <label>类型</label>
                            <span class="input-icon icon-right">
                            	<select name="type" class="form-control" id="type">
                            		<option value="0">选择注册类型</option>
                                    <option value="1">用户</option>
                                    <option value="2">总公司员工</option>
                                    <option value="3">分公司员工</option>
                                     <option value="4">办事处员工</option>
                                </select>
                            </span>
                        </div> 
                        <div class="form-group" id="mobileDiv" style="display: none">
                            <label>手机号码</label>
                            <span class="input-icon icon-right">
                            <!-- 	<div class="spinner"> -->
	                            	<div class="input-group">
	                            		 <span class="input-group-addon">
	                                        <i class="fa fa-laptop"></i>
	                                    </span>
	                            		<input class="form-control spinner-input" id="mobile" type="text">
	                                </div>
	                      <!--       </div> -->
                            </span>
                        </div> 
                       	<div class="form-group" style="display: none" id="nameDiv">
                            <label>姓名</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="realName" type="text">
                                </div>
                            </span>
                        </div>
                        <div class="form-group" style="display: none" id="pwdDiv">
                            <label>密码</label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="pwd" type="password" value="111111" disabled="disabled">
                                </div>
                            </span>
                        </div>
                        <div class="form-group" style="display: none" id="emailDiv">
                            <label>邮箱</label>
                            <span class="input-icon icon-right">
	                            	<div class="input-group">
	                            		 <span class="input-group-addon">
	                                        <i class="fa fa-laptop"></i>
	                                    </span>
	                            		<input class="form-control spinner-input" id="email" type="text">
	                                </div>
                            </span>
                        </div>
                        <div class="form-group" style="display: none" id="isPUserDiv">
							<label>产品用户</label> 
								<span class="input-icon icon-right">
									<select name="isPUser" class="form-control" id="isPUser" >
									<option value="3">请选择</option>
									<option value="0">是</option>
									<option value="1">否</option>
									</select>
								</span>
						</div>
                        <div class="form-group" style="display: none" id="qqDiv">
                            <label>QQ&nbsp;&nbsp;<span style="font-size: 12px">(可选)</span></label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="qq" type="text">
                                </div>
                            </span>
                        </div>
                        <div class="form-group" style="display: none" id="jobTitleDiv">
                            <label>职位&nbsp;&nbsp;<span style="font-size: 12px">(可选)</span></label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="jobTitle" type="text">
                                </div>
                            </span>
                        </div>
                        <div class="form-group" style="display: none" id="introduceDiv">
                            <label>责任描述&nbsp;&nbsp;<span style="font-size: 12px">(可选)</span></label>
                            <span class="input-icon icon-right">
                            	<div class="input-group">
                            		 <span class="input-group-addon">
                                        <i class="fa fa-laptop"></i>
                                    </span>
                            		<input class="form-control" id="introduce" type="text">
                                </div>
                            </span>
                        </div>
                        <div class="form-group" style="display: none" id="roleDiv">
                            <label>角色</label>
                            <span class="input-icon icon-right">
                            	<select name="roleId" class="form-control" id="roleId">
                            		<option value="0">请分配角色</option>
                                </select>
                            </span>
                        </div> 
                        
                        <div class="form-group" style="display: none" id="companyDiv">
                            <label>分公司</label>
                            <span class="input-icon icon-right">
                            	<select name="companyId" class="form-control" id="companyId">
                            		<option value="0">选择分公司</option>
                                </select>
                            </span>
                        </div> 
                        <div class="form-group" style="display: none" id="officeDiv">
                            <label>办事处</label>
                            <span class="input-icon icon-right">
                            	<select name="officeId" class="form-control" id="officeId">
                            		<option value="0">选择办事处</option>
                                </select>
                            </span>
                        </div> 
                         <div class="form-group" style="display: none" id="tvDiv">
                            <label>电视台</label>
                            <span class="input-icon icon-right">
                            	<select name="officeId" class="form-control" id="tvId">
                            		<option value="0">选择电视台</option>
                                </select>
                            </span>
                        </div> 
                        <div class="form-group" style="display: none" id="saleDiv">
                            <label>销售接洽&nbsp;&nbsp;<span style="font-size: 12px">(可选)</span></label>
                            <span class="input-icon icon-right">
                            	<select name="sale" class="form-control" id="sale">
                            		<option value="0">选择该用户的销售接洽</option>
                                </select>
                            </span>
                        </div> 
                        <!-- 
                        <div class="form-group">
                            <span class="input-icon icon-right">
                            	<div class="checkbox">
                            		<label>
	                            		<input type="checkbox" checked="checked">是否发送短信通知
                            		</label>
                            	</div>
                            </span>
                        </div>  -->
                        <a href="<%=basePath%>admin/requestpage/user/list" target="iframe"  id="confirmBtn" onclick="return submitInfo()" class="btn btn-info" disabled=true>确认</a>
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
    
        <!--Fuelux Spinner-->
    <script src="<%=basePath%>resource/assets/js/fuelux/spinner/fuelux.spinner.min.js"></script>

	<script src="<%=basePath%>resource/assets/js/toastr/toastr.js"></script>
	<!--Common Scripts -->
	<script src="<%=basePath%>resource/js/common.js"></script>
	<script src="<%=basePath%>resource/laydate/laydate.js"></script>
	
	<script src="<%=basePath%>resource/fileupload/js/fileinput.js" type="text/javascript"></script>
  	<script src="<%=basePath%>resource/fileupload/js/fileinput_locale_zh.js" type="text/javascript"></script>
  	<script type="text/javascript" src="<%=basePath%>resource/js/user/add.js"></script>
  	<script src="<%=basePath%>resource/assets/js/jQuery.md5.js"></script>
    <script>
    
    $(function (){
    	
    	// 初始化组件
    	//initView();
    	
    });
    
    //	$("#headUpload").fileinput({
    //		language:'zh',
	//	    uploadUrl: ' ', // 图片上传接口
	//    showPreview : true,
	//	    showRemove: false,
	//	    maxFileSize : 850,  //上传图片的最大限制  50KB
	//	    allowedFileExtensions: ["jpg", "png", "gif"],
	//	    initialCaption: "请选择分类Icon"
	//	});
	//	$("#headUpload").on("fileuploaded", function (event, data, previewId, index) {
	//	    if (null != data) {
	//        	fillPath = data.response.data[0].fid;
	//        	$("#filePath").html(fillPath);
	//	    } else {
	//	    	 Notify("上传失败", 'top-right', '5000', 'danger', 'fa-desktop', true);
	//	    }
	//	});
		
		
		$('.spinner').spinner();
        
    </script>
</body>
<!--  /Body -->
</html>
