<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2019/12/20
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = application.getContextPath();
	String laPath = application.getContextPath()+"/layui/";
	String csspath = application.getContextPath()+"/css/";
	String viewPath = application.getContextPath()+"/jsp/";
	String jspath = application.getContextPath()+"/js/";
%>
<html>
<head>
	<title>车辆排班表格</title>
	<link rel="stylesheet" href="<%=laPath+"css/layui.css"%>">
	<script src="<%=laPath+"layui.js"%>"></script>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<script src=<%=jspath+"jquery-3.4.1.js"%>></script>
</head>
<body>
<form class="layui-form" action="">
<div class="layui-form-item">
	<div class="layui-input-inline">
	<label class="layui-form-label">行车线路:</label>
	<div class="layui-input-block" style="width: 120px">
		<select name="driverState" id="beforeBusState" lay-filter="required">
			<c:if test="${requestScope.tbRoute !=null}">
				<c:forEach items="${requestScope.tbRoute}" begin="0" step="1" var="item">
					<option value="${item.routeName}">${item.routeName}</option>
				</c:forEach>
			</c:if>
		</select>
	</div>
	</div>
	<div class="layui-input-inline" style="margin-left: 30px">
	<label class="layui-form-label">*排班日期:</label>
	<div class="layui-input-block" style="width: 120px">
		<input type="text" class="layui-input" id="dateTime">
	</div>
	</div>
<%--	<div class="layui-input-inline" style="margin-left: 30px">--%>
<%--		<label class="layui-form-label">注意，排班功能实现了</label>--%>
<%--	</div>--%>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-inline" style="float: left">
				<label class="layui-form-label" >始程</label>
				<div class="layui-input-block">
					<table id="demo" lay-filter="test"></table>
				</div>
		</div>
		<div class="layui-input-inline" style="float: right;margin-right: 400px">
<%--			style="float: right;margin-top: -830px"--%>
				<label class="layui-form-label" >返程</label>
				<div class="layui-input-block">
					<table id="test" lay-filter="test" ></table>
				</div>
		</div>
		</div>


	<script id="barDemo" type="text/html">
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="check1" >排班/替换排班</a>
	</script>
	<script id="barDemo1" type="text/html">
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="check2" >排班/替换排班</a>
	</script>

</form>
<script>
	var tempDate = new Date();
	var year=tempDate.getFullYear();
	var month=tempDate.getMonth()+1;
	var day=tempDate.getDate();


	layui.use('table', function () {
		var table = layui.table;
		//第一个实例
		table.render({
			elem: '#test'
			, height: 800
			,width: 400
			, url: 'http://localhost:8080/bus/busShfitEnd' //数据接口
			, page: false
			, cols: [[ //表头
				{field: 'dateBusId', title: '序号', align: "center",width:60}
				, {field: 'dateBusTime', title: '发车时间', align: "center",width:80}
				, {field: 'busLicense', title: '车牌', align: "center",width:120}
				, {fixed: 'right', title: '操作', minWidth: "120px", toolbar: '#barDemo1', align: "center"}

			]]
			, skin: 'sm' //表格风格
			, even: true
			,initSort: {
				field: 'dateBusId' //排序字段，对应 cols 设定的各字段名
				,type: 'asc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
			}

		});

		table.render({
			elem: '#demo'
			, height: 800
			,width: 400
			, url: 'http://localhost:8080/bus/busShfitAllInforMation' //数据接口
			, page: false
			, cols: [[ //表头
				{field: 'dateBusId', title: '序号', align: "center",width:60}
				, {field: 'dateBusTime', title: '发车时间', align: "center",width:80}
				, {field: 'busLicense', title: '车牌', align: "center",width:120}
				, {fixed: 'right', title: '操作', minWidth: "120px", toolbar: '#barDemo', align: "center"}

			]]
			, skin: 'sm' //表格风格
			, even: true
			,initSort: {
				field: 'dateBusId' //排序字段，对应 cols 设定的各字段名
				,type: 'asc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
			}

		});

		//时间选择后刷新
		layui.use('laydate', function(){
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem: '#dateTime' //指定元素
				,min: 0 //7天前
				,done: function(value, date, endDate){
					console.log(value); //得到日期生成的值，如：2017-08-18
					console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
					console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
					var shfitBusLine = $("#beforeBusState").val();
					table.reload('demo', {
						url: 'http://localhost:8080/bus/busShfitAllInforMation'
						,where: {
							shfitBusLine :shfitBusLine,
							d0:value
						} //设定异步数据接口的额外参数
						//,height: 300
					});
					table.reload('test', {
						url: 'http://localhost:8080/bus/busShfitEnd'
						,where: {
							shfitBusLine :shfitBusLine,
							d0:value
						} //设定异步数据接口的额外参数
						//,height: 300
					});

				}
			});
		});
		//Demo
		layui.use('form', function(){
			var form = layui.form;
			form.on('select(required)', function (data) {
				var shfitBusLine = $("#beforeBusState").val();
				table.reload('demo', {
					url: 'http://localhost:8080/bus/busShfitAllInforMation'
					,where: {
						shfitBusLine :shfitBusLine
					} //设定异步数据接口的额外参数
					//,height: 300
				});
				table.reload('test', {
					url: 'http://localhost:8080/bus/busShfitEnd'
					,where: {
						shfitBusLine :shfitBusLine
					} //设定异步数据接口的额外参数
					//,height: 300
				});
			});
			table.on('tool(test)', function (obj) {
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				var busLicense = data['busLicense'];
				if (layEvent === 'check1') { //查看    发车表
					// if (busLicense!=null&&busLicense.length>0) {
					//
					// 	layer.msg(busLicense)
					// }else {
						openDilog(data,form,1,1,busLicense);
					// }
				}
				if (layEvent==='check2') {  //往返表
					// if (busLicense!=null&&busLicense.length>0) {
					// 	layer.msg(busLicense)
					// }else {
						openDilog(data,form,0,0,busLicense);
					// }
				}
			})
			//监听提交
			form.on('submit(formDemo)', function(data){
				layer.msg(JSON.stringify(data.field));
				return false;
			});
		});
	});
	//线路下拉框的数据更新
	// $("#beforeBusState").change(function () {
	// 	alert(123)
	// })

function openDilog(data,form,routeOrder,shfitBusStartStation,busLicense) {
	var dateBusId = data['dateBusId'];
	var dateBusTime = data['dateBusTime'];
	var shfitBusLine = $("#beforeBusState").val();
	var d0 = $("#dateTime").val();
	if (d0==null||d0.length<5){
		var date = new Date();
		d0 = date .getFullYear()+"-"+(date .getMonth()+1)+"-"+date .getDate();
	}
	$.ajax({
		url :'http://localhost:8080/bus/canRepaitBus',
		type: "post",
		data: {dateBusId:dateBusId,dateBusTime:dateBusTime,routeOrder:routeOrder,shfitBusLine:shfitBusLine,d0:d0,shfitBusStartStation:shfitBusStartStation,busLicense:busLicense},
		datatype: "json",
		async: true,
		success:function (rel) {
			if (rel!=null && rel !='') {
				$("#beforeBusState1").empty();//清空下拉框
				for(var i = 0; i < rel.length; i++) {
					var item=rel[i];
					$("#beforeBusState1").append("<option value='" + item.busId + "'>" +  item.busLicense + "</option>");//新增
				}
				form.render();//注意渲染页面表单，否则不显示数据
				onAddBtn(data,routeOrder,shfitBusStartStation);
			}else {
				layer.msg("没有可供选择的车辆，请联系管理员")
			}

		},
		error:function () {
			layer.msg("数据异常请重试")
		}
	})
}

</script>
<div id="busShfit" style="display: none;" align="center">
	<h2 style="color: #2D93CA;margin-top: 20px" align="center">可排班及替换车辆</h2>
	<div class="layui-form">
		<label class="layui-form-label" style="width: 100px"><i class="asterisk">*</i>可排班车辆:</label>
		<div class="layui-inline" style="margin-right: 50px;width: 200px">
			<select name="driverState" id="beforeBusState1" lay-filter="require1d">
			</select>
		</div>
	</div>


</div>
	<script>
		function onAddBtn(data,routeOrder,shfitBusStartStation) {
			var table = layui.table;
			//页面层-自定义
				layer.open({
					type: 1,
					title: "车辆排班",
					closeBtn: 1,
					shift: 2,
					btnAlign: 'c',
					// area: ['500px', '600px'],
					shadeClose: true,
					btn: ['确认', '取消'],
					// btnAlign: 'c',
					content: $("#busShfit"),
					anim: 5,
					success: function (layero, index) {

					},
					yes: function (index) {
						var license = $("#beforeBusState1").val();
						var dateBusId = data['dateBusId'];
						var dateBusTime = data['dateBusTime'];
						var oldBusLicense = data['busLicense'];
						var shfitBusLine = $("#beforeBusState").val();
						var d0 = $("#dateTime").val();
						if (d0==null||d0.length<5){
							var date = new Date();
							d0 = date .getFullYear()+"-"+(date .getMonth()+1)+"-"+date .getDate();
						}
						$.ajax({
							url :'http://localhost:8080/bus/addShfitServlet',
							type: "post",
							data: {dateBusId:dateBusId,dateBusTime:dateBusTime,license:license,shfitBusLine:shfitBusLine,d0:d0,routeOrder:routeOrder,shfitBusStartStation:shfitBusStartStation,oldBusLicense:oldBusLicense},
							datatype: "json",
							async: true,
							success:function (res) {
								if (res==="success") {
									layer.msg("排班成功");
								} else if (res==='success1') {
									layer.msg("换班成功")
								}else {
									layer.msg("排班失败，请重试")
								}
								layer.close(index);
								table.reload('test', {
									url: 'http://localhost:8080/bus/busShfitEnd'
									,where: {
										d0:d0,
										license:license
									} //设定异步数据接口的额外参数
									//,height: 300
								});
								table.reload('demo', {
									url: 'http://localhost:8080/bus/busShfitAllInforMation'
									,where: {
										d0:d0,
										license:license
									} //设定异步数据接口的额外参数
									//,height: 300
								});
							},
							error:function () {
								layer.msg("服务器繁忙，请稍后再试")
							}
						})
					},
					cancel: function(index, layero){
						if(confirm('确定要关闭么')){ //只有当点击confirm框的确定时，该层才会关闭
							layer.close(index)
						}
						return false;
					}

				});


		}


	</script>
</body>
</html>
