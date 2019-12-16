<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fullcalendar日历插件使用案例</title>

<link rel="shortcut icon" href=<%=path+"/images/favicon.ico"%> >
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
<script type="text/javascript" src=<%=path+"/js/demo.js" %>></script>

</head>
<body>
	<div id='calendar'></div>
	<div id="caidan" title="请输入排班日程" hidden="hidden">
		<form class="form-inline">
			<p>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：</label>
				<input type="text" class="sear datepicker" id="starttime" disabled="disabled">
<%--				<span>至</span>--%>
<%--				<input type="text" class="sear datepicker" id="endtime">--%>
			</p>
			<p>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人员：</label>
				<!--医生列表通过ajax获取-->

			<select name="groups" id="groups">
				<c:forEach items="${slist}" var="i" begin="0" step="1">
					<option value="${i.driverId}" selected="selected">${i.driverName}</option>
				</c:forEach>
			</select>
			</p>
		</form>      
	</div>
	
	<div id="sameday" title="日程明细" hidden="hidden">
		<form class="form-inline">
			<p>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人员：</label>
				<span id="gs2"></span>
			</p>
			<p>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：</label>
				<span id="gs3"></span>
				<fieldset>
					<legend>上午时间</legend>
					<label>09:00-10:00</label>
<%--					<input type="checkbox" name="checkbox-1" id="checkbox-1" disabled="true">--%>
					<label>10:00-11:00</label>
<%--					<input type="checkbox" name="checkbox-2" id="checkbox-2" disabled="true" >--%>
					<label>11:00-12:00</label>
<%--					<input type="checkbox" name="checkbox-3" id="checkbox-3" disabled="true" >--%>
				</fieldset>
				<fieldset>
					<legend>下午时间</legend>
					<label>14:00-15:00</label>
<%--					<input type="checkbox" name="checkbox-4" id="checkbox-4" disabled="true">--%>
					<label>15:00-16:00</label>
<%--					<input type="checkbox" name="checkbox-5" id="checkbox-5" disabled="true">--%>
					<label>16:00-17:00</label>
<%--					<input type="checkbox" name="checkbox-6" id="checkbox-6" disabled="true">--%>
				</fieldset>
			</p>
		</form>      
	</div>
</body>
</html>