<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2020/1/5
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String laPath = application.getContextPath()+"/layui/";
	String path=request.getContextPath();
	String cssPath=request.getContextPath()+"/css/";
	String jsPath=request.getContextPath()+"/js/";
	String imgPath=request.getContextPath()+"/img/";
%>
<html>
<head>
	<title>验证码</title>
	<script src=<%=jsPath + "jquery.js"%>></script>
	<script type="text/javascript" src=<%=jsPath+"jquery.qrcode.min.js"%>></script>
	<style>
		*{padding:0;margin:0;}
		#code{
			position:fixed;
			top:0;right:0;bottom:0;left:0;
			margin:auto;
		}
		#code{
			position: absolute;
			top: 50%;
			left: 55%;
			-webkit-transform: translate(-50%, -50%);
			-moz-transform: translate(-50%, -50%);
			-ms-transform: translate(-50%, -50%);
			-o-transform: translate(-50%, -50%);
			transform: translate(-50%, -50%);
		}
	</style>

</head>
<body>
<div id="inforMation" style="margin-top:30px;margin-left:50px"><h1>*扫码乘车</h1></div>
<div id="money" style="margin-top:20px;margin-left:70px">当前总余额：${requestScope.userBean.userBalance}</div>
<div id="code"></div>
<input type="hidden" id="phoneNum" value="${requestScope.userBean.userPhoneNumber}">
<script>
	/*$(function () {
		var str = toUtf8("iloveyou");
	});*/
	$("#code").qrcode({
		render: "table", //table方式
		width: 200, //宽度
		height:200, //高度
		text: $("#phoneNum").val()+"/"+"-1" //任意内容
	});

	function toUtf8(str) {

		var out, i, len, c;

		out = "";

		len = str.length;

		for(i = 0; i < len; i++) {

			c = str.charCodeAt(i);

			if ((c >= 0x0001) && (c <= 0x007F)) {

				out += str.charAt(i);

			} else if (c > 0x07FF) {

				out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));

				out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));

				out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));

			} else {

				out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));

				out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));

			}

		}

		return out;

	}

</script>
</body>
</html>
