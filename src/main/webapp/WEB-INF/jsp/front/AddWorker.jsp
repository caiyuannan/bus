<%--
  Created by IntelliJ IDEA.
  User: 21
  Date: 2019/11/18
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--增加后台人员--%>
<%
	String path=request.getContextPath();
	String cssPath=request.getContextPath()+"/css/";
	String jsPath=request.getContextPath()+"/js/";
	String imgPath=request.getContextPath()+"/img/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>增加管理员用户</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="/bus/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href=<%=cssPath + "admin.css"%> />
	<link rel="stylesheet" type="text/css" href=<%=cssPath + "login1.css"%> />
	<link rel="stylesheet" type="text/css" href=<%=cssPath + "style.css"%> />

	<script type="text/javascript" src=<%=jsPath+"jquery-3.4.1.js" %>></script>
	<style>
		body {
			height: 100%;
			background: #213838;
			overflow: hidden;
		}

		canvas {
			z-index: -1;
			position: absolute;
		}
	</style>
	<script src=<%=jsPath+"jquery.js" %>></script>
	<script src=<%=jsPath+"verificationNumbers.js" %>></script>
	<script src=	<%=jsPath+"Particleground.js" %>></script>
	<style type="text/css">
		* {
			margin: 0;
			padding: 0;
		}

		body {
			height: 100vh;
			background-position: center;
			overflow: hidden;
			background-color: white;
		}

		h1 {
			color: #fff;
			text-align: center;
			font-weight: 100;
			margin-top: 40px;
		}

		#media {
			width: 294px;
			height: 282px;
			margin: 5px auto 0;
			border-radius: 168px;
			overflow: hidden;
			opacity: 50.7;
		}

		#video {
			width: 294px;
			height: 226px;
			margin-top: 3px;
			border-radius: 60%;
		}

		#canvas {
			display: none;
		}

		#btn {
			width: 160px;
			height: 50px;
			background: #f9ffed;
			margin: 70px auto 0;
			text-align: center;
			line-height: 50px;
			color: #fff;
			border-radius: 40px;
		}
	</style>
</head>
<body>
<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="">
	<div class="layadmin-user-login-box layadmin-user-login-body layui-form">

		<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
			<div class="layadmin-user-login-main">

<%--				<div class="layui-form-item">--%>
<%--					<label class="layui-form-label">用户名</label>--%>
<%--					<div class="layui-input-inline">--%>
<%--						<input type="text" name="workeraccount" id="workeraccount" lay-verify="required"  autocomplete="off" class="layui-input"  >--%>
<%--					</div>--%>
<%--				</div>--%>
<%--				<div class="layui-form-item">--%>
<%--					<label class="layui-form-label">密码</label>--%>
<%--					<div class="layui-input-inline">--%>
<%--						<input type="password" name="workerpass" id="workerpass" lay-verify="required"  autocomplete="off" class="layui-input"  >--%>
<%--					</div>--%>
<%--				</div>--%>

				<div id="media">
					<video id="video" width="530" height="300" autoplay></video>
					<canvas id="canvas" width="400" height="300"></canvas>
				</div>
				<dd>
					<input class="layui-btn layui-btn-fluid" type="button" onclick="query()" value="立即添加"
					       class="submit_btn" />
				</dd>




				<%--			<div class="layui-form-item">--%>
				<%--				<button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-reg-submit" >注 册</button>--%>
				<%--			</div>--%>



			</div>
		</div>
	</div>
</div>


<script type="text/javascript" src=<%=jsPath+"jquery-3.1.1.min.js" %>></script>
<script type="text/javascript">
	//var 是定义变量
	var video = document.getElementById("video"); //获取video标签
	var context = canvas.getContext("2d");
	var con  ={
		audio:false,
		video:{
			width:1980,
			height:1024,
		}
	};

	//导航 获取用户媒体对象
	navigator.mediaDevices.getUserMedia(con)
		.then(function(stream){
			video.srcObject = stream;
			video.onloadmetadate = function(e){
				video.play();
			}
		});


	function query(){
		//把流媒体数据画到convas画布上去
		context.drawImage(video,0,0,400,300);
		var base = getBase64();
		var workeraccount=$("#workeraccount").val();
		var workerpass=$("#workerpass").val();

		$.ajax({
			type:"post",
			url:"/bus/addface",
			data:{"face":base,"workeraccount":workeraccount,"workerpass":workerpass},
			success:function(data){
				if(data.msg==="1"){
					alert("添加成功");
					parent.location.reload();
					// dicTable.reload();
					// layer.close(index); //关闭弹窗
				}else if(data.msg==="2"){
					alert("添加失败");

				}

			}
		});

	}
	function getBase64() {
		var imgSrc = document.getElementById("canvas").toDataURL(
			"image/png");
		alert(imgSrc);
		return imgSrc.split("base64,")[1];

	};
</script>



<%--<script src=<%=servletPath+"/layui/layui.js"%>></script>
<script>
	layui.use(['form', 'jquery'], function() {
		var form = layui.form
			, $ = layui.$;
		form.render();
		form.on('radio', function (data) {
			//console.log(data.elem); //得到radio原始DOM对象
			//console.log(data.value); //被点击的radio的value值
			if (data.value === "男") {
				$("#sex").val("男")
			} else if (data.value === "女") {
				$("#sex").val("女")
			}


		});
		form.on('submit(LAY-user-reg-submit)', function (obj) {


			var tb_name=$("#tb_name").val()
			var tb_pass=$("#tb_pass").val()
			var tb_sex=$("#sex").val()
			var tb_study=$("#tb_study").val()
			var tb_professional=$("#tb_professional").val()
			var tb_phone=$("#tb_phone").val()
			var email=$("#email").val()
			var myDate = new Date();
			var tb_time= myDate.toLocaleDateString();
			var ob = {tb_name:tb_name,tb_pass:tb_pass,tb_sex:tb_sex,tb_study:tb_study,tb_professional:tb_professional,tb_phone:tb_phone,email:email,tb_time:tb_time};
			alert(ob);
			$.ajax({
				type:"POST",//提交的方式
				url:"/Demo13/AddServlet.action",//提交的地址
				data:ob,//提交的数据
				dataType:"json",//希望返回的数据类型
				async: true,//异步操作
				success:function (msg) {//成功的方法  msg为返回数据
					if(msg.msg==="1"){
						alert("增加成功")

					}else if(msg.msg==="2"){
						alert("增加失败");

					}

				},
				error:function () {//错误的方法
					alert("服务器正忙")
				}
			});


		});
	});

</script>
--%>
</body>
</html>
