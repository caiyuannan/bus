<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2019/12/26
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = application.getContextPath();
	String laPath = application.getContextPath() + "/layui/";
	String csspath = application.getContextPath() + "/css/";
	String viewPath = application.getContextPath() + "/jsp/";
	String jspath = application.getContextPath() + "/js/";
%>
<html>
<head>
	<title>时间轴查看</title>
	<link rel="stylesheet" href="<%=laPath+"css/layui.css"%>">
	<script src="<%=laPath+"layui.js"%>"></script>
	<script src=<%=jspath + "jquery-3.4.1.js"%>></script>
	<style>
		.layui-form-item .layui-input-inline {
			float: left;
			width: 27px;
			margin-right: 0px;
		}

	</style>
</head>
<body>
<form action="" method="post">


	<div class="layui-form-item" style="margin-top: 70px">
		<label class="layui-form-label">*请选择时间:</label>
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="dateTime" style="width: 120px">
			<%--	</div>
			<div class="layui-input-block" style="width: 120px">--%>
		</div>
		<label class="layui-form-label" style="margin-left: 30%;width: 300px" id="license">*车辆:</label>
	</div>
	<div style="margin-top: 10%;" id="lableDiv">
		<label style="margin-left: 3%;" id="j1">6:00</label>
		<label style="margin-left: 25px" id="j2">7:00</label>
		<label style="margin-left: 25px" id="j3">8:00</label>
		<label style="margin-left: 25px" id="j4">9:00</label>
		<label style="margin-left: 15px" id="j5">10:00</label>
		<label style="margin-left: 15px" id="j6">11:00</label>
		<label style="margin-left: 15px" id="j7">12:00</label>
		<label style="margin-left: 15px" id="j8">13:00</label>
		<label style="margin-left: 15px" id="j9">14:00</label>
		<label style="margin-left: 15px" id="j10">15:00</label>
		<label style="margin-left: 15px" id="j11">16:00</label>
		<label style="margin-left: 15px" id="j12">17:00</label>
		<label style="margin-left: 15px" id="j13">18:00</label>
		<label style="margin-left: 15px" id="j14">19:00</label>
		<label style="margin-left: 15px" id="j15">20:00</label>
		<label style="margin-left: 15px" id="j16">21:00</label>
		<label style="margin-left: 15px" id="j17">22:00</label>
		<label style="margin-left: 15px" id="j18">23:00</label>
	</div>
	<div class="layui-form-item" style="margin-left: 3%;" align="center">
		<c:forEach var="item" begin="1" end="34" varStatus="status">
			<div class="layui-input-inline">
				<div class="layui-progress layui-progress-big" id="${status.index}"
				     style="width: 55px;background-color: green">
					<div class="layui-progress-bar" lay-percent="0%"></div>
				</div>
			</div>
		</c:forEach>

	</div>
</form>
<script>
	var lisecenId = parent.partitionDate;
	$("#license").text('当前车辆牌照为:' + parent.parLices);
	console.log($("#license"));
	layui.use('laydate', function () {
		var laydate = layui.laydate;
		//执行一个laydate实例
		laydate.render({
			elem: '#dateTime' //指定元素
			, min: 0 //7天前
			, done: function (value, date, endDate) {
				var shfitBusLine = $("#beforeBusState").val();

				$.ajax({
					type: "post",
					url: "http://localhost:8080/bus/selectTimer",
					data: {value: value, lisecenId: lisecenId},
					datatype: "json",
					async: true,
					success: function (res) {
						if (null != res && res.length > 0) {
							var labelList = $("label");
							var list = [];
							for (var i = 0; i < labelList.length; i++) {
								var labelId = labelList[i].getAttribute("id");
								if (null != labelId) {
									list.push(labelId);
								}

							}
							for (var j = 0; j < res.length; j++) {
								for (var i = 0; i < list.length; i++) {

									var shfitTime = res[j].shfitStartTime.split(":");
									var listTime = $("#" + list[i]).text().split(":");
									if (shfitTime[0] === listTime[0]) {
										if (shfitTime[1] > listTime[1]) {
											var divID = list[i].slice(1);
											var divIdTwo = divID * 2;
											var divIdOne = divID * 2 - 1;
											$("#" + divIdTwo).css("background-color", "red");
											$("#" + divIdOne).css("background-color", "red");
										} else if (shfitTime[1] === listTime[1]) {
											var divID1 = list[i].slice(1);
											var divIdTwo = divID1 * 2;
											var divIdOne = divID1 * 2 - 1;
											$("#" + divIdTwo).css("background-color", "red");
											$("#" + divIdOne).css("background-color", "red");
										}

									}

								}
							}

						} else {
							layui.use('layer', function () {
								var layer = layui.layer;
								layer.msg("该车当天没有排版记录")
							});

						}
					},
					error: function () {
						layui.use('layer', function () {
							var layer = layui.layer;
							layer.msg("数据异常，请联系管理员")
						});
					}
				})

			}
		});
	});
	//注意进度条依赖 element 模块，否则无法进行正常渲染和功能性操作
	layui.use('element', function () {
		var element = layui.element;
	});
</script>
</body>
</html>
