<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2019/12/29
  Time: 22:44
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
	<title>车辆救援管理表格界面</title>
	<link rel="stylesheet" href="<%=laPath+"css/layui.css"%>">
	<script src="<%=laPath+"layui.js"%>"></script>
	<script src=<%=jspath + "jquery-3.4.1.js"%>></script>
</head>
<body>
<table align="center" style="margin-left: 200px;margin-top:100px">
	<tr>
		<td>
			<%--			车牌号码input--%>
			<div class="layui-form-item">
				<div class="layui-input-inline">
					<label class="layui-form-label">*站点:</label>
					<div class="layui-input-block" style="width: 120px">
						<input type="text" class="layui-input" id="station">
					</div>
				</div>
			</div>
		</td>
		<td>
			<%--			使用年限input--%>
				<div class="layui-form-item">
				<div class="layui-input-inline">
					<label class="layui-form-label">*车牌:</label>
					<div class="layui-input-block" style="width: 120px">
						<input type="text" class="layui-input" id="lisenceCard" >
					</div>

			</div>
				</div>
		</td>
		<td style="padding-left:130px">
			<input class="layui-btn  layui-btn-primary layui-btn-lg" type="button" value="*提交" style="margin-top: -10px" onclick="goController()">
		</td>
	</tr>
</table>
<table id="demo" lay-filter="test"></table>
<script id="barDemo" type="text/html">
	<a class="layui-btn layui-btn-xs" lay-event="edit" onclick="helpGotoMove()">*救援调度</a>
</script>
<script>
	layui.use('table', function () {
		var table = layui.table;
//第一个实例
		table.render({
			elem: '#demo'
			, height: 312
			, url: 'http://localhost:8080/bus/helpAllTime' //数据接口
			, page: false
			, cols: [[ //表头
				{ type: 'numbers', title: '序号', width: 180  }
				,{field: 'liscense', title: '车牌', align: "center"}
				, {field: 'stopStation', title: '停靠站点', align: "center"}
				, {field: 'jionTime', title: '进站时间', align: "center"}
				, {field: 'newTime', title: '已停靠', align: "center"}
				, {field: 'beginTime', title: '临近排班发车时间', align: "center"}
				, {field: 'newStation', title: '临近排班发车站点', align: "center"}
				, {fixed: 'right', title: '操作', toolbar: '#barDemo', align: "center"}
			]]
			, skin: 'row' //表格风格
			, even: true
			, limits: [1, 5, 10]
			, limit: 1 //每页默认显示的数量

		})

	});
	function goController() {
		var layer = layui.layer;
		var table = layui.table;
		var station = $("#station").val();
		var lisenceCard = $("#lisenceCard").val();
		if (lisenceCard&&station){
			layer.msg("搜索值不能为空")
		} else {
				table.reload('demo', {
					url: 'http://localhost:8080/bus/helpAllTime'
					,where: {
						station:station,
						lisenceCard:lisenceCard
					} //设定异步数据接口的额外参数
					//,height: 300
				});
		}
	}
	function helpGotoMove() {
		layui.use('layer', function () {
			var layer = layui.layer;
			layer.msg("前往目的地")
		});
	}
</script>

</body>
</html>
