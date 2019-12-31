<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/26
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String cssPath=request.getContextPath()+"/css/";
	String jsPath=request.getContextPath()+"/js/";
	String imgPath=request.getContextPath()+"/img/";
%>
<html>
<head>
	<title>智慧公交系统</title>
	<meta name="keywords" content="智慧公交系统" />
	<meta name="description" content="智慧公交系统" />

	<link rel="stylesheet" href=<%=path + "/bootstrap/css/bootstrap.min.css"%>>
	<link rel="stylesheet" href=<%=cssPath + "login.css"%>>
	<script src=<%=jsPath + "jquery.js"%>></script>

	<script src=<%=path + "/bootstrap/js/bootstrap.min.js"%>></script>
	<script>
		$(document).ready(function() {
			//打开会员登录
			$("#Login_start_").click(function() {
				$("#regist_container").hide();
				$("#_close").show();
				$("#_start").animate({
					left: '350px',
					height: '520px',
					width: '400px'
				}, 500, function() {
					$("#login_container").show(500);
					$("#_close").animate({
						height: '40px',
						width: '40px'
					}, 500);
				});
			});
			//打开会员注册
			$("#Regist_start_").click(function() {
				$("#login_container").hide();
				$("#_close").show();
				$("#_start").animate({
					left: '350px',
					height: '520px',
					width: '400px'
				}, 500, function() {
					$("#regist_container").show(500);
					$("#_close").animate({
						height: '40px',
						width: '40px'
					}, 500);
				});
			});
			//关闭
			$("#_close").click(function() {

				$("#_close").animate({
					height: '0px',
					width: '0px'
				}, 500, function() {
					$("#_close").hide(500);
					$("#login_container").hide(500);
					$("#regist_container").hide(500);
					$("#_start").animate({
						left: '0px',
						height: '0px',
						width: '0px'
					}, 500);
				});
			});
			//去 注册
			$("#toRegist").click(function(){
				$("#login_container").hide(500);
				$("#regist_container").show(500);
			});
			//去 登录
			$("#toLogin").click(function(){
				$("#regist_container").hide(500);
				$("#login_container").show(500);
			});
		});
	</script>

</head>
<%--<body style="background:url(<%=imgPath + "bg1.jpg"%>);">--%>
<body>
<div class="container">
	<div class="panel-heading">
		<h3 class="panel-title" style="font-size: 45px;">
			智慧公交系统
		</h3>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">Brand</a>
				</div>

				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active">
							<a href="#">Link</a>
						</li>
						<li>
							<a href="#">Link</a>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="#">Action</a>
								</li>
								<li>
									<a href="#">Another action</a>
								</li>
								<li>
									<a href="#">Something else here</a>
								</li>
								<li class="divider">
								</li>
								<li>
									<a href="#">Separated link</a>
								</li>
								<li class="divider">
								</li>
								<li>
									<a href="#">One more separated link</a>
								</li>
							</ul>
						</li>
					</ul>
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" />
						</div> <button type="submit" class="btn btn-default">Submit</button>
					</form>
					<ul class="nav navbar-nav navbar-right login_reg">
						<li>
							<button type="button" id="Login_start_" class="btn btn-primary" style="width:100px;height:40px;">登陆</button>
						</li>
						<li class="dropdown">
							<button type="button"  id="Regist_start_" class="btn btn-success" style="width:100px;height:40px;margin-left: 5px;">注册</button>
						</li>
					</ul>
				</div>

			</nav>

			<div id='_start' style="position:absolute;left:40%;top:50px;z-index:9999;">
				<div id='_close' style="display: none;">
					<span class="glyphicon glyphicon-remove"></span>
				</div>
				<br />
				<!--登录框-->
				<div id="login_container">
					<div id="lab11">
						<span id="lab_login1">会员登录</span>
						<span id="lab_toRegist">
						&emsp;还没有账号&nbsp;
						<span id='toRegist' style="color: #EB9316;cursor: pointer;">立即注册</span>
					</span>
					</div>
					<div style="width:330px;">
						<span id="lab_type1">手机号/账号登陆</span>
					</div>
					<div id="form_container1">
						<br />
						<input type="text" class="form-control" placeholder="手机号/用户名" id="login_number" value="jsdaima.com" />
						<input type="password" class="form-control" placeholder="密码" id="login_password" />
						<input type="button" value="登录" class="btn btn-success" id="login_btn" />
						<span id="rememberOrfindPwd">
						<span>
							<input id="remember" type="checkbox" style="margin-bottom: -1.5px;"/>
						</span>
					<span style="color:#000000">
							记住密码&emsp;&emsp;&emsp;&emsp;
						</span>
					<span style="color:#000000">
							忘记密码
						</span>
					</span>
					</div>

					<div style="display:block;width:330px;height:40px;">
						<span id="lab_type2">使用第三方直接登陆</span>
					</div>
					<div style="width:330px;height:100px;border-bottom: 1px solid #FFFFFF;">
						<br />
						<button id="login_QQ" type="button" class="btn btn-info">
							<img src="img/qq32.png" style="width:20px;margin-top:-4px;" />&emsp;QQ登录
						</button>
						<button id="login_WB" type="button" class="btn btn-danger">
							<img src="img/sina32.png" style="width:20px;margin-top:-4px;" />&emsp;微博登录
						</button>
					</div>
				</div>
				<!-- 会员注册 -->
				<div id='regist_container' style="display: none;">
					<div id="lab1">
						<span id="lab_login">会员注册</span>
						<span id="lab_toLogin">
						&emsp;已有账号&nbsp;
						<span id='toLogin' style="color: #EB9316;cursor: pointer;">立即登录</span>
					</span>
					</div>
					<div id="form_container2" style="padding-top: 25px;">

						<input type="text" class="form-control" value="jsdaima.com"  placeholder="用户名" id="regist_account"/>
						<input type="password" class="form-control" placeholder="密码" id="regist_password1" />
						<input type="password" class="form-control" placeholder="确认密码" id="regist_password2" />
						<input type="text" class="form-control" placeholder="手机号" id="regist_phone" />
						<input type="text" class="form-control" placeholder="验证码" id="regist_vcode" />
						<!--<button id="getVCode" type="button" class="btn btn-success" >获取验证码</button>-->
						<input id="getVCode" type="button" class="btn btn-success" value="点击发送验证码" onclick="sendCode(this)" />

					</div>
					<input type="button" value="注册" class="btn btn-success" id="regist_btn" />
				</div>
			</div>
			<div class="carousel slide" id="carousel-322317">
				<ol class="carousel-indicators">
					<li class="active" data-slide-to="0" data-target="#carousel-322317">
					</li>
					<li data-slide-to="1" data-target="#carousel-322317">
					</li>
					<li data-slide-to="2" data-target="#carousel-322317">
					</li>
				</ol>
				<div class="carousel-inner">
					<div class="item active">
						<img alt="" src="img/default.jpg" />
						<div class="carousel-caption">
							<h4>
								First Thumbnail label
							</h4>
							<p>
								Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
							</p>
						</div>
					</div>
					<div class="item">
						<img alt="" src="img/default1.jpg" />
						<div class="carousel-caption">
							<h4>
								Second Thumbnail label
							</h4>
							<p>
								Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
							</p>
						</div>
					</div>
					<div class="item">
						<img alt="" src="img/default2.jpg" />
						<div class="carousel-caption">
							<h4>
								Third Thumbnail label
							</h4>
							<p>
								Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
							</p>
						</div>
					</div>
				</div> <a class="left carousel-control" href="#carousel-322317" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-322317" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
			</div>
			<div class="panel-group" id="panel-587675">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a class="panel-title" data-toggle="collapse" data-parent="#panel-587675" href="#panel-element-780726">Collapsible Group Item #1</a>
					</div>
					<div id="panel-element-780726" class="panel-collapse collapse in">
						<div class="panel-body">
							Anim pariatur cliche...
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a class="panel-title" data-toggle="collapse" data-parent="#panel-587675" href="#panel-element-785086">Collapsible Group Item #2</a>
					</div>
					<div id="panel-element-785086" class="panel-collapse collapse">
						<div class="panel-body">
							Anim pariatur cliche...
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-4 column">
			<h2>
				Heading
			</h2>
			<p>
				Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
			</p>
			<p>
				<a class="btn" href="#">View details »</a>
			</p>
		</div>
		<div class="col-md-4 column">
			<h2>
				Heading
			</h2>
			<p>
				Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
			</p>
			<p>
				<a class="btn" href="#">View details »</a>
			</p>
		</div>
		<div class="col-md-4 column">
			<h2>
				Heading
			</h2>
			<p>
				Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
			</p>
			<p>
				<a class="btn" href="#">View details »</a>
			</p>
		</div>
	</div>
	<div class="panel-footer">
		联系地址:XXXXX
	</div>
</div>


</body>
<script type="text/javascript">
	var clock = '';
	var nums = 30;
	var btn;
	function sendCode(thisBtn) {
		btn = thisBtn;
		btn.disabled = true; //将按钮置为不可点击
		btn.value = '重新获取（'+nums+'）';
		clock = setInterval(doLoop, 1000); //一秒执行一次
	}

	function doLoop() {
		nums--;
		if (nums > 0) {
			btn.value = '重新获取（'+nums+'）';
		} else {
			clearInterval(clock); //清除js定时器
			btn.disabled = false;
			btn.value = '点击发送验证码';
			nums = 10; //重置时间
		}
	}

	$(document).ready(function(){
		$("#login_QQ").click(function(){
			alert("暂停使用！");
		});
		$("#login_WB").click(function(){
			alert("暂停使用！");
		});
	});
</script>
</html>