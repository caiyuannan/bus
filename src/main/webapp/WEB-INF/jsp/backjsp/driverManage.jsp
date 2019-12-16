<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/10/13
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = application.getContextPath();
	String cssPath = request.getContextPath()+"/css/";
	String jsPath = request.getContextPath()+"/js/";
	String imgPath = request.getContextPath()+"/img/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>司机排班</title>
	<link rel="stylesheet" href="/bus/layui/css/layui.css">
	<script src="/bus/layui/layui.js"></script>
</head>

<body>


<%--	<table id="demo" lay-filter="test"></table>--%>
<div class="layui-container">
	<div class="layui-row">

<div class="layui-col-md12">

				<div>

<%--					<input type="hidden" id="myID" name="myID" value="${sessionScope.staffID}">--%>

	<form class="layui-form" action="">
		<div class="layui-form-item">
			<label class="layui-form-label">输入框</label>
			<div class="layui-input-block">
				<input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			</div>
		</div>
	</form>

						<table  class="layui-table" lay-skin="line">
							<thead>
							<tr>
								<th rowspan="2">
									司机
								</th>

								<th>${requestScope.week[0]}<br>星期一</th>
								<th>${requestScope.week[1]}<br>星期二</th>
								<th>${requestScope.week[2]}<br>星期三</th>
								<th>${requestScope.week[3]}<br>星期四</th>
								<th>${requestScope.week[4]}<br>星期五</th>
								<th>${requestScope.week[5]}<br>星期六</th>
								<th>${requestScope.week[6]}<br>星期日</th>
							</tr>
							</thead>
							<tbody>

							<c:if test="${requestScope.workmap !=null}">
								<c:forEach items="${requestScope.workmap}" begin="0" step="1" var="i">
									<tr>
										<td>${i.key}</td>
										<td>
											<input type="hidden" value="${requestScope.week[0]}">
											<c:forEach items="${i.value}" begin="0" step="1" var="k">
												<c:if test="${k.workTime==requestScope.week[0]}">
													<button type="button" data-toggle="modal" data-target="#myModal" class="btn1 btn-info" value="" onclick="getdoc(this)">${k.busIicense}</button>
												</c:if>
											</c:forEach>
										</td>
										<td>
											<input type="hidden" value="${requestScope.week[1]}">
											<c:forEach items="${i.value}" begin="0" step="1" var="k">
												<c:if test="${k.workTime==requestScope.week[1]}">
													<button type="button" data-toggle="modal" data-target="#myModal" class="btn1 btn-info" value="" onclick="getdoc(this)">${k.busIicense}</button>
												</c:if>
											</c:forEach>
										</td>
										<td>
											<input type="hidden" value="${requestScope.week[2]}">
											<c:forEach items="${i.value}" begin="0" step="1" var="k">
												<c:if test="${k.workTime==requestScope.week[2]}">
													<button type="button" data-toggle="modal" data-target="#myModal" class="btn1 btn-info" value="" onclick="getdoc(this)">${k.busIicense}</button>
												</c:if>
											</c:forEach>
										</td>
										<td>
											<input type="hidden" value="${requestScope.week[3]}">
											<c:forEach items="${i.value}" begin="0" step="1" var="k">
												<c:if test="${k.workTime==requestScope.week[3]}">
													<button type="button" data-toggle="modal" data-target="#myModal" class="btn1 btn-info" value="" onclick="getdoc(this)">${k.busIicense}</button>
												</c:if>
											</c:forEach>
										</td>
										<td>
											<input type="hidden" value="${requestScope.week[4]}">
											<c:forEach items="${i.value}" begin="0" step="1" var="k">
												<c:if test="${k.workTime==requestScope.week[4]}">
													<button type="button" data-toggle="modal" data-target="#myModal" class="btn1 btn-info" value="" onclick="getdoc(this)">${k.busIicense}</button>
												</c:if>
											</c:forEach>
										</td>
										<td>
											<input type="hidden" value="${requestScope.week[5]}">
											<c:forEach items="${i.value}" begin="0" step="1" var="k">
												<c:if test="${k.workTime==requestScope.week[5]}">
													<button type="button" data-toggle="modal" data-target="#myModal" class="btn1 btn-info" value="" onclick="getdoc(this)">${k.busIicense}</button>
												</c:if>
											</c:forEach>
										</td>
										<td>
											<input type="hidden" value="${requestScope.week[6]}">
											<c:forEach items="${i.value}" begin="0" step="1" var="k">
												<c:if test="${k.workTime==requestScope.week[6]}">
													<button type="button" data-toggle="modal" data-target="#myModal" class="btn1 btn-info" value="" onclick="getdoc(this)">${k.busIicense}</button>
												</c:if>
											</c:forEach>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							</tbody>
						</table>

					</div>


					<a  href="/ONECARD/AppointmentServlet?methodName=preWeek&nwd=${requestScope.week[0]}"><button class="layui-btn" type="button" style="margin-left: 36%;">上一周</button></a>
					<a  href="/ONECARD/AppointmentServlet?methodName=nextWeek"><button class="layui-btn" type="button">下一周</button></a>


			</div>
	</div>
</div>
<script>


	var date1;

	function getdoc(node) {

		var name = $(node).text();
		//alert(name);
		$("#docName").text(name);
		var doc = $("#docName").text();//获取医生名字
		date1 = $(node).parent().find("input").eq(0).val();//获取预约当天日期

		var data = doc+","+date1;

		//利用脚本的方式 创建 一个对象
		var ob = {sendMsg: data};
		//对象转换成字符串
		var str = JSON.stringify(ob);

		$.ajax({
			type: "POST",//提交的方式
			url: "AppointmentServlet?methodName=queryRecordAjax",//提交的地址
			data: "msg=" + str,//提交的数据
			dataType: "text",//希望返回的数据类型
			async: true,//异步提交
			success: function (msg) {//成功的方法 msg为返回的数据
				var ob2 = JSON.parse(msg);
				//alert(ob2[0]['UNAME']);


			},
			error: function () {//错误的方法
				alert("服务器正忙");
			}
		});


	}


</script>
</body>
</html>
