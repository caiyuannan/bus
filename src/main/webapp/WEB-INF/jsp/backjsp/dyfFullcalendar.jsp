<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2019/12/19
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page  isELIgnored = "false" %>
<%
	String laPath = application.getContextPath()+"/layui/";
	String path = request.getContextPath();
%>
<html>
<head>
	<title>日历插件</title>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/jquery-ui.min.css" %>>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/jquery-ui.structure.min.css" %>>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/jquery-ui.theme.min.css" %>>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/core/main.css" %>>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/daygrid/main.css" %>>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/demostyle.css" %>>
	<script type="text/javascript" src=<%=path+"/js/jquery.js" %>></script>
	<script type="text/javascript" src=<%=path+"/js/jquery-ui.min.js" %>></script>
	<script type="text/javascript" src=<%=path+"/js/core/main.js" %>></script>
	<script type="text/javascript" src=<%=path+"/js/daygrid/main.js" %>></script>
	<script type="text/javascript" src=<%=path+"/js/interaction/main.js" %>></script>
	<script type="text/javascript" src=<%=path+"/js/fullcalendar.js" %>></script>
</head>
<body>
<div id='calendar'></div>
<div id="caidan" title="请输入排班日程" hidden="hidden">
	<form class="form-inline">
		<p>
			<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：</label>
			<input type="text" class="sear datepicker" id="starttime" disabled="disabled">
			<span>至</span>
			<input type="text" class="sear datepicker" id="endtime">
		</p>
		<p>
			<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人员：</label>
			<!--医生列表通过ajax获取-->
			<select name="groups" id="groups">
				<option value="赵医生">赵医生</option>
				<option value="钱医生">钱医生</option>
				<option value="孙医生">孙医生</option>
				<option value="李医生">李医生</option>
			</select>
		</p>
	</form>
</div>

<div id="sameday" title="日程明细" hidden="hidden">
	<form class="form-inline">
		<p>
			<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人员：</label>
			<span name="groups3" id="groups3"></span>
			<%--<select name="groups2" id="groups2">
				<option value="赵医生">赵医生</option>
				<option value="钱医生">钱医生</option>
				<option value="孙医生">孙医生</option>
				<option value="李医生">李医生</option>
			</select>--%>
		</p>
		<p>
			<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：</label>
		<fieldset>
			<legend>上午时间</legend>
			<label for="checkbox-1">09:00-10:00</label>
			<input type="checkbox" name="checkbox-1" id="checkbox-1" checked="checked">
			<label for="checkbox-2">10:00-11:00</label>
			<input type="checkbox" name="checkbox-2" id="checkbox-2" checked="checked">
			<label for="checkbox-3">11:00-12:00</label>
			<input type="checkbox" name="checkbox-3" id="checkbox-3" checked="checked">
		</fieldset>
		<fieldset>
			<legend>下午时间</legend>
			<label for="checkbox-4">14:00-15:00</label>
			<input type="checkbox" name="checkbox-4" id="checkbox-4" >
			<label for="checkbox-5">15:00-16:00</label>
			<input type="checkbox" name="checkbox-5" id="checkbox-5" checked="checked">
			<label for="checkbox-6">16:00-17:00</label>
			<input type="checkbox" name="checkbox-6" id="checkbox-6" checked="checked">
		</fieldset>
		</p>
	</form>
</div>
</body>
</html>
