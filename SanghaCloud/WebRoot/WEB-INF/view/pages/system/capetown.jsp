<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" href="<%=basePath%>resource/css/system/mui.min.css" />
		<link rel="stylesheet" href="<%=basePath%>resource/css/system/capetown.css" />
		
	</head>
	<body>
		<div class="mui-content mui-scroll-wrapper">
		    <div class="mui-scroll">
		        <div class="titleBox">
		        	<p class="title">【Capetown】好望角售后服务工作平台</p>
		        	<p class="subtitle">通往高效透明的运维航道</p>
		        	<p class="introduction">The channel will be to an efficent and ransparen to perational</p>		        	
		        </div>
		        <div class="text">
		        	<p class="paragraphone">
		        		Capetown 滥觞于非洲大陆的最南端——被称誉为“海上希望线”的好望角，
		        		旨在为技术人员打造一个简单便捷高效的售后服务工作平台，
		        		享受轻松愉悦、趣味横生的工作氛围，远离枯燥又单调的运维环境。
		        	</p>
		        	<p class="paragraptwo">
		        		细长岬角，及时触及用户现场，追求极致完美的售后服务；
		        		看尽千帆，汇聚分析数千生产网使用特点，快速透明的维护效率，
		        		致力于为售后服务打造新一代的互联网+工作平台。
		        	</p>
		        	<p class="paragrapthree">
		        		心眼所见，永难磨灭，Capetown带你身临“麒麟之范”，
		        		体验完美与智慧的结晶，领略美好希望的售后服务海角风光。
		        	</p>
		        	<img src="<%=basePath%>resource/img/7.png" alt="" />
		        </div>
		        <div class="textlist">
		       		<p class="title">全终端——连接您与索贝技术专家，免除维护之忧</p>
		        	<ul class="list">
		        		<li>贴合生产网的轻型维护监控系统</li>
		        		<li>高保密的安全互连设备</li>
		        		<li>贴心的公有云维护服务中心</li>
		        		<li>简单高效的移动工具</li>
		        	</ul>
		        	<img src="<%=basePath%>resource/img/8.png"/>
		        </div>
		         <div class="textlist">
		       		<p class="title">快操作——简单明了，即用即会</p>
		        	<ul class="list">
		        		<li>存储，数据库，交换机，专业监控</li>
		        		<li>后台软件服务实时监控</li>
		        		<li>生产网业务报表中心</li>
		        		<li>手机版生产网监控</li>
		        		<li>索贝专家远程协助</li>
		        	</ul>
		        	<img src="<%=basePath%>resource/img/9.png"/>
		        </div>
		        <div class="textlist">
		       		<p class="title">轻体验——部署轻快，一台工作站搞定一个生产网</p>
		        	<ul class="list">
		        		<li>采用服务端+客户端方式部署</li>
		        		<li>BS架构展示全网设备状态</li>
		        		<li>轻松搞定生产网监控</li>
		        	</ul>
		        	<img src="<%=basePath%>resource/img/10.png"/>
		        </div>
		        <div class="textlist">
		       		<p class="title">多维度——丰富报表，打造您的数据报告</p>
		        	<ul class="list">
		        		<li>多元化的运维贴心工具</li>
		        		<li>监控告警统计，全方位报表</li>
		        		<li>汇聚栏目素材，工作量统计不用愁</li>
		        	</ul>
		        	<img src="<%=basePath%>resource/img/11.png"/>
		        	
		        </div>
		         <div style="margin-top: 40px">
		         	<input type="button" value="体验产品" class="btn btn-info" style="width:  100%; " id="feelBtn">
		         </div>
		    </div> 
		</div>
		<div class="confirm">
		<div class="confirm-dialog">
			<div class="confirm-content">
					<ul>
						<li>您已体验过了</li>
						<li class="cancel">确定</li>
					</ul>
			</div>
		</div>
	</div>
	
	<div class="model">
		<div class="model-dialog">
			<div class="model-content">
					<ul>
						<li>体验成功</li>
						<li class="cancel">确定</li>
					</ul>
			</div>
		</div>
	</div>
	
	
		<script src="<%=basePath%>resource/js/system/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resource/js/system/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>resource/js/system/capetown.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			var token = "${token}";
			$("#feelBtn").click(function(){
				$.ajax({
					type : "POST",
					url : "/SanghaCloud/productFeel/addFeel",
					data : {token:token},
					dataType :"json",
					async : true,
					timeout : 5000,
					success : function(json){
						if(json.code == 202){
							$(".confirm").fadeIn();
							$(".confirm .cancel").on("tap",function(){
								$(".confirm").fadeOut();
							})
						}else if(json.code == 200){
							$(".model").fadeIn();
							$(".model .cancel").on("tap",function(){
								$(".model").fadeOut();
							})
						}
					}
				});
			});		
		</script>
	</body>
</html>
