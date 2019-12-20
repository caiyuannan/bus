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
	<script src=<%=jsPath + "jquery.js"%>></script>
	<script src="/bus/layui/layui.js"></script>

	<style>
		.layui-form-item {
			clear: none;
		}

		.layui-container{
			margin-top: 100px;
		}
	</style>
</head>

<body>


<%--	<table id="demo" lay-filter="test"></table>--%>
<div class="layui-container">
	<div class="layui-row">

<div class="layui-col-md12">

				<div>

<%--					<input type="hidden" id="myID" name="myID" value="${sessionScope.staffID}">--%>

	<form id="driverSearch" class="layui-form" action="/bus/findPlateNumber" method="post">
		<div class="layui-row">
		<div class="layui-col-md6" >

				<div class="layui-input-inline">
<%--					<input type="text" class="layui-input" id="test6" placeholder=" - ">--%>
				开始日期:<input type="date" name="start" id="start">
					结束日期:<input type="date" name="end" id="end">
				</div>
		</div>
		<div class="layui-form-item layui-col-md6">
			<label class="layui-form-label">车牌搜索:</label>
			<div class="layui-input-block">
				<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入车牌号" class="layui-input" style="width: 200px;display: inline-block;margin-right: 10px;">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
			</div>
		</div>

		</div>
	</form>

			<table id="main"  class="layui-table" lay-skin="line" onclick="doclick()">

				<tr>
					<td>
						司机
					</td>

					<td>${requestScope.week[0]}<br>星期一</td>
					<td>${requestScope.week[1]}<br>星期二</td>
					<td>${requestScope.week[2]}<br>星期三</td>
					<td>${requestScope.week[3]}<br>星期四</td>
					<td>${requestScope.week[4]}<br>星期五</td>
					<td>${requestScope.week[5]}<br>星期六</td>
					<td>${requestScope.week[6]}<br>星期日</td>
				</tr>



				<c:if test="${requestScope.workmap !=null}">
					<c:forEach items="${requestScope.workmap}" begin="0" step="1" var="i">
						<tr>
							<td id="dvkey">${i.key}</td>
							<td>
								<input type="hidden" value="${requestScope.week[0]}">
								<c:forEach items="${i.value}" begin="0" step="1" var="k">
									<c:if test="${k.workTime==requestScope.week[0]}">
										<button type="button" class="layui-btn layui-btn-sm" title="${k.workId}" onclick="selectRole(this)">${k.workType}</button>

									</c:if>
								</c:forEach>
							</td>
							<td>
								<input type="hidden" value="${requestScope.week[1]}">
								<c:forEach items="${i.value}" begin="0" step="1" var="k">
									<c:if test="${k.workTime==requestScope.week[1]}">
										<button type="button" class="layui-btn layui-btn-sm" title="${k.workId}" onclick="selectRole(this)">${k.workType}</button>
									</c:if>
								</c:forEach>
							</td>
							<td>
								<input type="hidden" value="${requestScope.week[2]}">
								<c:forEach items="${i.value}" begin="0" step="1" var="k">
									<c:if test="${k.workTime==requestScope.week[2]}">
										<button type="button" class="layui-btn layui-btn-sm" title="${k.workId}" onclick="selectRole(this)">${k.workType}</button>

									</c:if>
								</c:forEach>
							</td>
							<td>
								<input type="hidden" value="${requestScope.week[3]}">
								<c:forEach items="${i.value}" begin="0" step="1" var="k">
									<c:if test="${k.workTime==requestScope.week[3]}">
										<button type="button" class="layui-btn layui-btn-sm" title="${k.workId}" onclick="selectRole(this)">${k.workType}</button>

									</c:if>
								</c:forEach>
							</td>
							<td>
								<input type="hidden" value="${requestScope.week[4]}">
								<c:forEach items="${i.value}" begin="0" step="1" var="k">
									<c:if test="${k.workTime==requestScope.week[4]}">
										<button type="button" class="layui-btn layui-btn-sm" title="${k.workId}" onclick="selectRole(this)">${k.workType}</button>

									</c:if>
								</c:forEach>
							</td>
							<td>
								<input type="hidden" value="${requestScope.week[5]}">
								<c:forEach items="${i.value}" begin="0" step="1" var="k">
									<c:if test="${k.workTime==requestScope.week[5]}">
										<button type="button" class="layui-btn layui-btn-sm" title="${k.workId}" onclick="selectRole(this)">${k.workType}</button>
									</c:if>
								</c:forEach>
							</td>
							<td>
								<input type="hidden" value="${requestScope.week[6]}">
								<c:forEach items="${i.value}" begin="0" step="1" var="k">
									<c:if test="${k.workTime==requestScope.week[6]}">

<%--										<button type="button" class="layui-btn layui-btn-sm" title="${k.workId}" onclick="selectRole(this)">${k.busIicense == null?'操作':k.busIicense}</button>--%>
										<button type="button" class="layui-btn layui-btn-sm" title="${k.workId}" onclick="selectRole(this)">${k.workType}</button>

									</c:if>
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
				</c:if>

			</table>

		</div>


		<a  href="/bus/toPreWeek?nwd=${requestScope.week[0]}" id="preWk"><button class="layui-btn" type="button" style="margin-left: 36%;">上一周</button></a>
		<a  href="/bus/toNextWeek"><button class="layui-btn" type="button">下一周</button></a>


		</div>
	</div>
</div>
<form id="editForm" class="layui-form" action="" method="post" hidden= "hidden">

	<div class="layui-form-item">
		<label class="layui-form-label">司机状态</label>
		<div class="layui-input-block">
			<input type="checkbox" checked="checked" value="排班" name="open" lay-skin="switch" lay-filter="switchTest" title="开关" lay-text="休息|排班">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">请选择车牌</label>
		<div class="layui-input-block">
			<select id="cnum" name="carNumber" lay-verify="required">
				<option value=""></option>
				<option value="闽D.99999">闽D.99999</option>
				<option value="闽D.88888">闽D.88888</option>
				<option value="闽D.77777">闽D.77777</option>
			</select>
		</div>
	</div>

</form>
<%
		String mg = (String)request.getAttribute("msg1");
		if (mg!=null)
		{
//			out.write("<script>alert('"+mg+"')</script>");
			out.write("<script>$(\'#preWk\').attr(\"disabled\",true);</script>");
		}
%>
<%--event.srcElement.cellIndex+1--%>
<script>

	function doclick()
	{
		var td = event.srcElement; // 通过event.srcElement 获取激活事件的对象 td
		//alert("行号：" + (td.parentElement.rowIndex + 1) + "，内容：" + td.title+td.innerText.length);
		var j = td.cellIndex;
		var data=document.getElementById("main").rows[0].cells[j].innerHTML;
		var str = data.split('<');
		//alert(str[0]);



		if(td.innerText.length == 0){
			//var workid=$(node).attr('title');
			var workname=td.parentNode.children[0].innerHTML;
			var worktime=str[0];
			//alert("排班id"+td.title);
			layui.use(['layer','form'], function(){
				var layer = layui.layer;
				var form = layui.form;

				var ck;
				//监听指定开关
				form.on('switch(switchTest)', function(data){

					ck = this.checked ? '休息' : '排班';
					alert.msg(ck);

				});

				layer.open({
					title : '司机排班',
					type : 1,
					area : [ '30%', '40%' ],
					maxmin : true,
					shadeClose : true,
					content : $('#editForm'),
					btn: ['确定', '取消'],
					shade: [0.8, '#393D49'],
					success : function(layero, index) {

						// $("#uname").val(name);
						// $("#upwd").val(pwd);
						// $("#usex").val(sex);
						// $("#career").val(career);
						// $("#state").val(state);
					},
					btn1:function(index,layero){

						//alert(ck+","+$("#cnum").val()+","+workname);

						var str = ck+","+$("#cnum").val()+","+workname+","+worktime;
						$.ajax({
							url: "/bus/toAddBlankWork",
							type: "POST",
							data: "msg=" + str,
							success: function (msg) {

								alert(msg);
								if (msg == '排班'|| msg == '休息') {

									//关闭弹框
									if(msg == '排班'){
										layer.close(index);

										layer.msg("排班成功", {icon:6,time:500},function(){
											setTimeout('location.reload()',300);
											//location.reload();
										});

									}else{

										td.innerHTML="休息";
										//$(node).text("休息");
										layer.close(index);
										layer.msg("排班成功", {icon:6,time:500},function(){
											setTimeout('location.reload()',300);
										});
									}

									//layer.msg("修改成功", {icon: 6});
									//parent.location.reload();
								} else {
									layer.msg("排班失败", {icon: 5});
								}
							}
						});
						return false;

					}
				});

			});

		}
	}
	function selectRole(node) {
		var workid=$(node).attr('title');
		layui.use(['layer','form'], function(){
			var layer = layui.layer;
			var form = layui.form;

			var ck;
			//监听指定开关
			form.on('switch(switchTest)', function(data){

				ck = this.checked ? '休息' : '排班';
				alert.msg(ck);

			});

			layer.open({
				title : '司机排班',
				type : 1,
				area : [ '30%', '40%' ],
				maxmin : true,
				shadeClose : true,
				content : $('#editForm'),
				btn: ['确定', '取消'],
				shade: [0.8, '#393D49'],
				success : function(layero, index) {

					// $("#uname").val(name);
					// $("#upwd").val(pwd);
					// $("#usex").val(sex);
					// $("#career").val(career);
					// $("#state").val(state);
				},
				btn1:function(index,layero){

					alert(ck+","+$("#cnum").val()+","+workid);

					var str = ck+","+$("#cnum").val()+","+workid;
					$.ajax({
						url: "/bus/toAddWork",
						type: "POST",
						data: "msg=" + str,
						success: function (msg) {

							alert(msg);
							if (msg == '排班'|| msg == '休息') {

								//关闭弹框
								if(msg == '排班'){
									layer.close(index);

									layer.msg("排班成功", {icon:6,time:500},function(){
										setTimeout('window.location.reload()',500);
									});

								}else{

									$(node).text("休息");
									layer.close(index);
									layer.msg("排班成功", {icon:6,time:500},function(){
										setTimeout('window.location.reload()',500);
									});
								}

								//layer.msg("修改成功", {icon: 6});
								//parent.location.reload();
							} else {
								layer.msg("排班失败", {icon: 5});
							}
						}
					});
					return false;

				}
			});

		});
	}


	// layui.use('form', function(){
	// 	var form = layui.form;
	//
	// 	//监听提交
	// 	form.on('submit(formDemo)', function(data){
	// 		var str = JSON.stringify(data.field);
	// 		$.ajax({
	// 			url: "/bus/findPlateNumber",
	// 			type: "POST",
	// 			data: "msg=" + str,
	// 			success: function (msg) {
	//
	// 				if (msg == 200) {
	// 					//删除这一行
	// 					obj.del();
	// 					//关闭弹框
	// 					layer.close(index);
	// 					layer.msg("搜索成功", {icon: 6});
	//
	// 				} else {
	// 					layer.msg("搜索失败", {icon: 5});
	// 				}
	// 			}
	// 		});
	// 		return false;
	// 	});
	// });

	layui.use('laydate', function(){
		var laydate = layui.laydate;
	//日期范围
	laydate.render({
		elem: '#test6'
		,range: true
	});
	});
</script>
</body>
</html>
