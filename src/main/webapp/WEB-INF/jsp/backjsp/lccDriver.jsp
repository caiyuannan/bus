<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/19
  Time: 0:18
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
	<title>查看司机</title>
	<link rel="stylesheet" href="/bus/layui/css/layui.css">
	<script src=<%=jsPath + "jquery.js"%>></script>
	<script src="/bus/layui/layui.js"></script>

</head>
<body class="layui-layout-body">

<div class="layui-body">
	<!-- 内容主体区域 -->
	<div style="padding: 15px;">

		<%--        <table id="demo" lay-filter="test"></table>--%>
		<div class="layui-row">
			<div class="demoTable layui-col-xs12">
				姓名：
				<div class="layui-inline">
					<input class="layui-input" name="name" id="demoReload" autocomplete="off">
				</div>
				&nbsp;&nbsp;&nbsp;
				手机号:
				<div class="layui-inline">
					<input class="layui-input" name="cp" id="cpReload" autocomplete="off">
				</div>
				&nbsp;&nbsp;&nbsp;
				站点:
				<div class="layui-inline">
					<input class="layui-input" name="st" id="stReload" autocomplete="off">
				</div>
				<button class="layui-btn" data-type="reload">搜索</button>

			</div>

		</div>
		<table class="layui-hide" id="LAY_table_user" lay-filter="user"></table>
		<script type="text/html" id="barDemo">
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
			<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">工作量查看</a>
<%--			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
		</script>

	</div>
</div>
<form id="editForm" action="" method="post" hidden= "hidden">
	<table align="center">
		<tr>
			<td><label for="dname">用户名：</label></td>
			<td><input type="text" id="dname" name="dname" value=""></td>
		</tr>
		<tr>
			<td><label for="cphone">电话：</label></td>
			<td><input type="text" id="cphone" name="cphone" value=""></td>
		</tr>
		<tr>
			<td><label for="stn">上班站点：</label></td>
			<td><input type="text" id="stn" name="stn" value=""></td>
		</tr>

	</table>

</form>
</div>

<script>
	layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
		var table = layui.table//表格
			,laydate = layui.laydate //日期
			,laypage = layui.laypage //分页
			,layer = layui.layer //弹层
			,upload = layui.upload //上传
			,element = layui.element //元素操作
			,slider = layui.slider //滑块

		//第一个实例
		table.render({
			elem: '#LAY_table_user'
			,height: 420
			,url: '/bus/showDriverTable' //数据接口
			,limits:[5,10,20,30,40,50]
			,limit: 5
			//,contentType:'application/json'
			,id: 'testReload'
			//, where: {'token': token}
			//, method: 'post'
			,page: true //开启分页
			,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
			,cols: [[ //表头
				// {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
				,{field: 'driverId', title: '序号', width:100}
				,{field: 'driverName', title: '姓名', width:80}
				,{field: 'driverCellphone', title: '电话', width:80}
				,{field: 'stationName', title: '上班站点', width:80}
				,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
				// ,{field: 'sign', title: '签名', width: 177}
				// ,{field: 'experience', title: '积分', width: 80, sort: true}
				// ,{field: 'score', title: '评分', width: 80, sort: true}
				// ,{field: 'classify', title: '职业', width: 80}
				// ,{field: 'wealth', title: '财富', width: 135, sort: true}
			]]
		});


		var $ = layui.$, active = {
			reload: function(){
				var demoReload = $('#demoReload');
				var cpReload = $('#cpReload');
				var stReload = $('#stReload');
				//alert(demoReload.val());
				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					,where:{
						//'token': token,
						driverName:demoReload.val(),
						driverCellphone:cpReload.val(),
						stationName:stReload.val()
					}
				}, 'data');
			}
		};

		$('.layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});

		//监听行工具事件
		table.on('tool(user)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			var data = obj.data //获得当前行数据
				,layEvent = obj.event; //获得 lay-event 对应的值
			if(layEvent === 'detail'){
				//layer.msg('查看操作');
				layer.open({

					type: 2,
					title: '工作量查看',
					fix: false,
					maxmin: true,
					shadeClose: true,
					shade: 0.8,
					area: ['50%', '50%'],
					content: '/bus/toCheckWorkload?did='+data['driverId'],
					end: function () {
						location.reload();
					}
				});
			} else if(layEvent === 'del'){

				//alert(obj.data['fileid']);
				layer.confirm('真的删除行么', function(index){
					//利用脚本的方式 创建 一个对象
					var ob = data['fileid'];
					//对象转换成字符串
					var str = JSON.stringify(ob);

					$.ajax({
						url: "/springboot/toDelete",
						type: "POST",
						data: "msg=" + str,
						success: function (msg) {

							if (msg == 200) {
								//删除这一行
								obj.del();
								//关闭弹框
								layer.close(index);
								layer.msg("删除成功", {icon: 6});

							} else {
								layer.msg("删除失败", {icon: 5});
							}
						}
					});
					return false;

				});
			}else if(layEvent === 'edit'){

				var name = data['driverName']
					,dphone = data['driverCellphone']
					,dstation = data['stationName'];
					//,state = data['dstate'];
				//alert(name+","+pwd+","+state+","+sex+","+career);
				// var str = name+","+pwd+","+state+","+sex+","+career;

				layer.open({
					title : '修改用户',
					type : 1,
					area : [ '30%', '40%' ],
					maxmin : true,
					shadeClose : true,
					content : $('#editForm'),
					btn: ['确定', '取消'],
					shade: [0.8, '#393D49'],
					success : function(layero, index) {
						$("#dname").val(name);
						$("#cphone").val(dphone);
						$("#stn").val(dstation);
					},
					btn1:function(index,layero){
						var str = $("#dname").val()+","+$("#cphone").val()+","+$("#stn").val()+","+data['driverId'];
						$.ajax({
							url: "/bus/toUpdateDriver",
							type: "POST",
							data: "msg=" + str,
							success: function (msg) {

								if (msg == 200) {

									//关闭弹框
									layer.close(index);

									layer.msg("修改成功", {icon:6,time:500},function(){
										setTimeout('window.location.reload()',500);
									});

									//layer.msg("修改成功", {icon: 6});
									//parent.location.reload();
								} else {
									layer.msg("修改失败", {icon: 5});
								}
							}
						});
						return false;
					}
				});
			}
		});

	});



	//JavaScript代码区域
	// layui.use('element', function(){
	//   var element = layui.element;
	//
	// });
</script>
</body>
</html>
