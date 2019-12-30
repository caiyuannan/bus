<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/16
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cssPath = request.getContextPath()+"/css/" ;
	String jsPath =application.getContextPath()+"/js/" ;
	String path = application.getContextPath();
	String uicssPath = request.getContextPath()+"/layui/" ;
	String uiPath = request.getContextPath()+"/layui/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>合作商配置</title>
	<link rel="stylesheet" href=<%=uicssPath +"css/layui.css"%> media="all">
	<script src="<%=jsPath+"jquery-3.4.1.js"%>"></script>
	<script src=<%=uiPath+"layui.js"%>></script>
<%--	<script src=<%=uiPath+"layui.all.js"%>></script>&ndash;%&gt;--%>
</head>
<body>

<div >
	<div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">
		<button data-method="notice" class="layui-btn">增加合作商</button>
	</div>
	<form class="layui-form layui-col-md12 x-so" id="complain_search" style=" margin-top: 6%;">

	<label class="layui-form-label" style="margin-left: 8%">合作商地区</label>
<div class="layui-input-inline">
	<input id="cooperativeAreaa" type="text" name="cooperativeAreaa" class="layui-input" id="test1" >
</div>
		<label>合作商类型：</label>
		<div class="layui-input-inline" >
		<select id="roleSelect" name="roleSelect"  style="margin-left: 3%;">
			<option value=""></option>
			<c:if test="${null != requestScope.roleSelect}">

				<c:forEach items="${requestScope.roleSelect}" begin="0" step="1" var="i">
					<option  value=${i.cooperativeType}>${i.cooperativeType}</option>
				</c:forEach>
			</c:if>
		</select>
		</div>

<label class="layui-form-label2" style="margin-left: 3%;">用户名:</label>
<div class="layui-input-inline" >
	<input id="uername" type="text" name="uername"   placeholder="请输入标题" autocomplete="off" class="layui-input">
</div>
	<button id="search" class="layui-btn" lay-submit
	        lay-filter="provinceSearch">
		<i class="layui-icon">&#xe615;</i>
	</button>
	</form>
</div>
<div style=" padding-top: 10%">
<table id="demo" lay-filter="test" style="margin: auto"></table>

	<script type="text/html" id="barDemo">
		<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		<a  id="state" lay-event="state">启用</a>
		<a class="layui-btn  layui-btn-xs" id="state2" lay-event="state2">禁用</a>
	</script>

<script >

	layui.use(['table','layer','form','laypage','element'], function(){

		var table = layui.table,
			layer = layui.layer,
			form = layui.form,
			laypage = layui.laypage;
		 // $ = layui.jquery;

		//第一个实例
		table.render({
			elem: '#demo'
			// ,height: 312
			,url: '/bus/showCooperativeTable' //数据接口
			,page: true //开启分页
			,limit: 3 //开启分页
			,limits: [1,5,10] //开启分页
			,id: 'idTest'

			,cols: [[ //表头
				{field: 'cooperativeId', title: 'id', sort: true ,hide:true}
				,{type: 'numbers', title: '序号'}
				,{field: 'cooperativeName', title: '合作商名称'}
				,{field: 'cooperativeType', title: '合作商类型', sort: true}
				,{field: 'cooperativeCoordinate', title: '合作商坐标'}
				,{field: 'cooperativeArea', title: '合作商地区' }
				,{field: 'stateName', title: '状态' }
				,{field: 'stateId', title: '状态id',hide:true }
				,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
			]]

		});

		var $ = layui.jquery;

		//监听行工具事件
		table.on('tool(test)', function(obj){//注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			var data = obj.data;

			if(obj.event === 'del'){

				layer.confirm('真的删除行么', function(index){
					// time: 1000 ,//2秒自动关闭
					var ob={cooperativeId:data.cooperativeId};
					// var userid=data['userid'];
					// alert(ob);

					$.ajax({
						url:"/bus/deleteCooperativeTable",
						data: ob,
						type:"Post",
						dataType:"text",
						async: true,

						success: function (msg) {
							alert(msg);
							if(msg==="删除成功"){
								obj.del();
								alert("删除成功");
								location.reload(); //删除成功后再刷新
							}else{
								alert("删除失败");
							}
						},
						error:function(msg){
							alert("服务器正忙");
						}
					});
					layer.close(index);
				});
			}

			else if (obj.event === 'edit') {
				var cooperativeId=data['cooperativeId'];
				//打开模态框
				layer.open({
					type: 1,
					title:"修改界面",
					closeBtn: false,
					shift: 2,
					area: ['400px', '300px'],
					shadeClose: true,
					content: $("#update"),
					success: function(layero, index){},
					yes:function(){
					}
				});
				$("#upcooperativeId").val(cooperativeId);
			}

			else if(obj.event === 'state'){

             if (data['stateName']==="启用") {

	             // $("#state").attr({"disabled":"disabled"});
	             $("#state").disabled();
	             // alert( $("#state").disabled());
             }else{

				layer.confirm('真的要启用吗', function(index){
					// time: 1000 ,//2秒自动关闭
					// var ob={cooperativeId:data.cooperativeId,stateId:data['stateName']};
					// var userid=data['userid'];
					// alert(ob);

					$.ajax({
						url:"/bus/updateCooperativeTableState?stateName=启用"+"&cooperativeId="+data['cooperativeId'],
						// data: ob,
						type:"Post",
						dataType:"text",
						async: true,

						success: function (msg) {
							// alert(msg);
							if(msg==="修改成功1"){
								obj.del();
								alert("修改成功");
								location.reload(); //删除成功后再刷新
							}else{
								alert("修改失败");
							}
						},
						error:function(msg){
							alert("服务器正忙");
						}
					});
					layer.close(index);
				});
             }
			}

			else if(obj.event === 'state2') {

				if (data['stateName'] === "禁用") {
					$("#state").disabled();
				} else {

				layer.confirm('真的要禁用吗', function (index) {
					// time: 1000 ,//2秒自动关闭
					$.ajax({
						url: "/bus/updateCooperativeTableState?stateName=禁用" + "&cooperativeId=" + data['cooperativeId'],
						type: "Post",
						dataType: "text",
						async: true,

						success: function (msg) {
							// alert(msg);
							if (msg === "修改成功1") {
								obj.del();
								alert("修改成功");
								location.reload();
							} else {
								alert("修改失败");
							}
						},
						error: function (msg) {
							alert("服务器正忙");
						}
					});
					layer.close(index);
				});
			  }
			}
		});

         // var roleSelect;
		form.on('select', function(data){
			console.log(data.elem); //得到select原始DOM对象
			console.log(data.value); //得到被选中的值]
			$("#roleSelect").val(data.value);
			console.log(data.othis); //得到美化后的DOM对象
		});

	//搜索按钮监听提交
	form.on('submit(provinceSearch)', function(data){
		// option.where.title = data.field.title;
		/*更新渲染表格*/

		// mytable.reload(option);

		var formData = data.field;
		console.debug(formData);
		// alert(formData);

		var uername = formData.uername,
			roleSelect = $("#roleSelect").val(),
			cooperativeAreaa = formData.cooperativeAreaa;
		// $('select[name="roleSelect"] option:selected').text(),
        //        alert(roleSelect);
		table.reload('idTest', {
			page: {
				curr: 1
			},
			where: {
				uername:uername,
				roleSelect:roleSelect,
				cooperativeAreaa:cooperativeAreaa

			},
			// method: 'post',
			// contentType: "application/json;charset=utf-8",
			// url: '/LayuiHw/UsertableServlet'
		});
		return false;
	});

	});
</script>
</div>





<script>
	layui.use('layer', function(){ //独立版的layer无需执行这一句
		var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

		//触发事件
		var active = {
			setTop: function(){
				var that = this;

			}

			,notice: function(){
				//示范一个公告层
				layer.open({
					type: 1
					,title: false //不显示标题栏
					,closeBtn: false
					,area: ['370px', '250px']
					,shade: 0.8
					,id: 'LAY_layuipro' //设定一个id，防止重复弹出
					,btn: ['火速围观', '残忍拒绝']
					,btn1:function addUser() {
						// alert(1);
						var addcooperativeName = $("#addcooperativeName").val();
						var addcooperativeType = $("#addcooperativeType").val();
						var addcooperativeX = $("#addcooperativeX").val();
						var addcooperativeY = $("#addcooperativeY").val();
						var addcooperativeArea = $("#addcooperativeArea").val();
						if (addcooperativeX != null && addcooperativeX.length > 0 && addcooperativeY != null && addcooperativeY.length > 0 && addcooperativeName != null && addcooperativeName.length > 0 && addcooperativeType != null && addcooperativeType.length > 0 && addcooperativeArea != null && addcooperativeArea.length > 0) {

								var ob={cooperativeName:addcooperativeName,cooperativeType:addcooperativeType,cooperativeX:addcooperativeX,cooperativeY:addcooperativeY,cooperativeArea:addcooperativeArea};
								$.ajax({
									url: "/bus/addCooperativeTable",
									data: ob,
									type: "Post",
									dataType: "text",
									async: true,

									success: function (msg) {
										if (msg === "添加成功") {
											// obj.del();
											alert("添加成功");
											location.reload(); //删除成功后再刷新

										} else {
											alert("添加失败");
										}
									},
									error: function (msg) {
										alert("服务器正忙");
									}
								});

						} else  {
							alert("输入的信息不能为空");
						}
						}
					,btnAlign: 'c'
					,moveType: 1 //拖拽模式，0或者1
					,content: '<div style="padding: 50px; line-height: 33px; background-color: #393D49; color: #fff; font-weight:800;">' +
						'合作商名称：<input type="text" name="addcooperativeName" id="addcooperativeName"><br>' +
						'合作商类型:<input type="text" name="addcooperativeType" id="addcooperativeType"><br>' +
						'合作商X坐标:<input type="text" name="addcooperativeX" id="addcooperativeX"><br>' +
						'合作商Y坐标:<input type="text" name="addcooperativeY" id="addcooperativeY"><br>' +
						'合作商地区:<input type="text" name="addcooperativeArea" id="addcooperativeArea"><br>' +
						'</div>'
					,success: function(layero){

						}
				});
			}

		};

		$('#layerDemo .layui-btn').on('click', function(){
			var othis = $(this), method = othis.data('method');
			active[method] ? active[method].call(this, othis) : '';
		});

	});


	//关闭模态框
	function closeMethod() {
		layer.close(layer.index);
		tableReload();
	}
	//修改的方法
	function updateMethod() {
		var upcooperativeName = $("#upcooperativeName").val();
		var upcooperativeType = $("#upcooperativeType").val();
		var upcooperativeX = $("#upcooperativeX").val();
		var upcooperativeY = $("#upcooperativeY").val();
		var upcooperativeArea = $("#upcooperativeArea").val();
		var upcooperativeId = $("#upcooperativeId").val();
		var ob={cooperativeName:upcooperativeName,cooperativeType:upcooperativeType,cooperativeX:upcooperativeX,cooperativeY:upcooperativeY,cooperativeArea:upcooperativeArea,cooperativeId:upcooperativeId};

			$.ajax({
				type: "POST",
				url: "/bus/updateCooperativeTable",//提交的地址
				data: ob,
				dataType: "text",//希望返回的数据类型
				async: true,//异步操作
				success: function (msg) {//成功的方法  msg为返回数据
					if(msg==="修改成功"){
						layer.close(layer.index);
						location.reload(); //删除成功后再刷新
					}else{
						alert("修改失败");
					}
				},
				error: function () {//错误的方法
				}
			});
	}
</script>

<div id="update" style="display: none;">
	<div style="background-color: #ffffff;height: 50px"></div>
	<div class="layui-form-item center">

		<div class="layui-input-block">
			<input type="text" name="upcooperativeId" id="upcooperativeId" hidden="true" ><br>
		合作商名称：<input type="text" name="upcooperativeName" id="upcooperativeName"><br>
		合作商类型:<input type="text" name="upcooperativeType" id="upcooperativeType"><br>
		合作商X坐标:<input type="text" name="upcooperativeX" id="upcooperativeX"><br>
		合作商Y坐标:<input type="text" name="upcooperativeY" id="upcooperativeY"><br>
		合作商地区:<input type="text" name="upcooperativeArea" id="upcooperativeArea"><br>
		</div>

	</div>
	<div class="layui-form-item">

		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="save" onclick="updateMethod()">确定修改
			</button>
			<button type="reset" class="layui-btn layui-btn-primary" onclick="closeMethod()">取消
			</button>
		</div>

	</div>
</div>


</body>
</html>
