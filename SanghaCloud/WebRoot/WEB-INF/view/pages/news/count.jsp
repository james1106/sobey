<!DOCTYPE html>
<!--
BeyondAdmin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.2.0
Version: 1.0.0
Purchase: http://wrapbootstrap.com
-->

<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
    <meta charset="utf-8" />
    <title></title>

    <meta name="description" content="Dashboard" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="../../resource/resource/assets/img/favicon.png" type="image/x-icon">

    <!--Basic Styles-->
    <link href="../../resource/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link id="bootstrap-rtl-link" href="" rel="stylesheet" />
    <link href="../../resource/assets/css/font-awesome.min.css" rel="stylesheet" />
    <link href="../../resource/assets/css/weather-icons.min.css" rel="stylesheet" />

    <!--Fonts-->

    <!--Beyond styles-->
    <link href="../../resource/assets/css/beyond.min.css" rel="stylesheet" type="text/css" />
    <link href="../../resource/assets/css/demo.min.css" rel="stylesheet" />
    <link href="../../resource/assets/css/typicons.min.css" rel="stylesheet" />
    <link href="../../resource/assets/css/animate.min.css" rel="stylesheet" />
    <link href="../../resource/assets/css/skins/deepblue.min.css" rel="stylesheet" type="text/css" />

	<link href="../../resource/css/common.css" rel="stylesheet" />
    <script src="../../resource/assets/js/skins.min.js"></script>
    
  
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <div class="page-breadcrumbs">
        <ul class="breadcrumb">
            <li><a href="javascript:getPage('main.html')">首页</a></li>
            <li class="active">赛事统计</li>
        </ul>
    </div>
    <div class="header-title">
	    <h1>
	    	赛事
	        <small>
	            Play
	        </small>
	    </h1>
	</div>
    <div class="page-body">
		<div class="widget">
			<div class="widget-header ">
                <span class="widget-caption">赛事统计</span>
                <div class="widget-buttons">
                    <a href="add.html" target="iframe" class="btn btn-info">导出</a>
                </div>
            </div>
            <div class="widget-body">
            	<div class="tabbable">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a data-toggle="tab" href="#home">积分榜</a></li>
                        <li class="tab-red"><a data-toggle="tab" href="#shooter">射手榜</a></li>
                        <li class="tab-red"><a data-toggle="tab" href="#assists">助攻榜</a></li>
                        <li class="tab-red"><a data-toggle="tab" href="#save">扑救榜</a></li>
                        <li class="tab-red"><a data-toggle="tab" href="#card">红黄牌榜</a></li>
                    </ul>

                    <div class="tab-content">
                        <div id="home" class="tab-pane in active">
                        	<table class="table table-striped table-hover">
                                <thead>
	                                <tr>
	                                    <th>排名</th>
	                                    <th>球队</th>
	                                    <th>场次</th>
	                                    <th>胜</th>
	                                    <th>平</th>
	                                    <th>负</th>
	                                    <th>净胜</th>
	                                    <th>积分</th>
	                                </tr>
                                </thead>
                                <tbody>
                                	<tr>
                                		<td>1</td>
                                		<td>西安车商联</td>
                                		<td>1</td>
                                		<td>0</td>
                                		<td>1</td>
                                		<td>0</td>
                                		<td>0</td>
                                		<td>1</td>
                                	</tr>
                                	<tr>
                                		<td>2</td>
                                		<td>西安达升汽车金融足球队</td>
                                		<td>1</td>
                                		<td>0</td>
                                		<td>1</td>
                                		<td>0</td>
                                		<td>0</td>
                                		<td>1</td>
                                	</tr>
                                </tbody>
                            </table>
                        </div>
                        <div id="shooter" class="tab-pane">
                        	<table class="table table-striped table-hover">
                                <thead>
	                                <tr>
	                                    <th>排名</th>
	                                    <th>球员</th>
	                                    <th>球队</th>
	                                    <th>进球</th>
	                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    	<td>1</td>
                                    	<td>关振宇</td>
                                    	<td>西安车商联</td>
                                    	<td>1</td>
                                    </tr>
                                    <tr>
                                    	<td>2</td>
                                    	<td>马鸣</td>
                                    	<td>西安车商联</td>
                                    	<td>1</td>
                                    </tr>
                                    <tr>
                                    	<td>3</td>
                                    	<td>海洋</td>
                                    	<td>西安达升汽车金融足球队</td>
                                    	<td>1</td>
                                    </tr>
                                    <tr>
                                    	<td>4</td>
                                    	<td>王超</td>
                                    	<td>西安达升汽车金融足球队</td>
                                    	<td>1</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div id="assists" class="tab-pane">
                        	<table class="table table-striped table-hover">
                                <thead>
	                                <tr>
	                                    <th>排名</th>
	                                    <th>球员</th>
	                                    <th>球队</th>
	                                    <th>次数</th>
	                                </tr>
                                </thead>
                                <tbody>
                                	<tr>
                                		<td>1</td>
                                		<td>关振宇</td>
                                		<td>西安车商联</td>
                                		<td>1</td>
                                	</tr>
                                	<tr>
                                		<td>2</td>
                                		<td>马鸣</td>
                                		<td>西安车商联</td>
                                		<td>1</td>
                                	</tr>
                                	<tr>
                                		<td>3</td>
                                		<td>王超</td>
                                		<td>西安达升汽车金融足球队</td>
                                		<td>1</td>
                                	</tr>
                                </tbody>
                            </table>
                        </div>
                        <div id="save" class="tab-pane">
                        	<table class="table table-striped table-hover">
                                <thead>
	                                <tr>
	                                    <th>排名</th>
	                                    <th>球员</th>
	                                    <th>球队</th>
	                                    <th>次数</th>
	                                </tr>
                                </thead>
                                <tbody>
                                	<tr>
                                		<td colspan="4">暂时没有数据</td>
                                	</tr>
                                </tbody>
                            </table>
                        </div>
                        <div id="card" class="tab-pane">
                        	<table class="table table-striped table-hover">
                                <thead>
	                                <tr>
	                                    <th>排名</th>
	                                    <th>球员</th>
	                                    <th>红牌</th>
	                                    <th>黄牌</th>
	                                    <th>双黄牌</th>
	                                </tr>
                                </thead>
                                <tbody>
                                	<tr>
                                		<td>1</td>
                                		<td>海洋</td>
                                		<td>0</td>
                                		<td>1</td>
                                		<td>0</td>
                                	</tr>
								</tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!--Basic Scripts-->
    <script src="../../resource/assets/js/jquery-2.0.3.min.js"></script>
    <script src="../../resource/assets/js/bootstrap.min.js"></script>

    <!--Beyond Scripts-->
    <script src="../../resource/assets/js/beyond.min.js"></script>
	<script src="../../resource/assets/js/toastr/toastr.js"></script>
	<!--Common Scripts -->
	<script src="../../resource/js/common.js"></script>
	<script src="../../resource/laydate/laydate.js"></script>
    <script>
    	var totalpage=10;
    	$(function(){

    	})
    </script>
</body>
<!--  /Body -->
</html>
