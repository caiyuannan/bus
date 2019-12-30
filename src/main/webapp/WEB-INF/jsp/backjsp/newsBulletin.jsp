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
		<button data-method="notice" class="layui-btn" onclick="addDiv()">增加新闻事件</button>
	</div>
	<form class="layui-form layui-col-md12 x-so" id="complain_search" style=" margin-top: 6%;">

	<label class="layui-form-label" style="margin-left: 8%">新闻标题</label>
<div class="layui-input-inline">
	<input id="newsBulletinTitlee" type="text" name="newsBulletinTitlee" class="layui-input" id="test1" >
</div>
		<label>新闻类型：</label>
		<div class="layui-input-inline" >
		<select id="newsBulletinTypeSelect" name="newsBulletinTypeSelect"  style="margin-left: 3%;">
			<option value=""></option>
			<option value="线路变更">线路变更</option>
			<option value="站点更名">站点更名</option>
			<option value="普通新闻">普通新闻</option>
<%--			<c:if test="${null != requestScope.roleSelect}">--%>

<%--				<c:forEach items="${requestScope.roleSelect}" begin="0" step="1" var="i">--%>
<%--					<option  value=${i.cooperativeType}>${i.cooperativeType}</option>--%>
<%--				</c:forEach>--%>
<%--			</c:if>--%>
		</select>
		</div>

<label class="layui-form-label2" style="margin-left: 3%;">新闻创建时间:</label>
<div class="layui-input-inline" >
	<input  id="newsBulletinTimee" type="date" name="newsBulletinTimee"    autocomplete="off" class="layui-input">
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
<%--		<a  id="state" lay-event="state">启用</a>--%>
<%--		<a class="layui-btn  layui-btn-xs" id="state2" lay-event="state2">禁用</a>--%>
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
			,url: '/bus/showNewsBulletinTable' //数据接口
			,page: true //开启分页
			,limit: 3 //开启分页
			,limits: [1,5,10] //开启分页
			,id: 'idTest'

			,cols: [[ //表头
				{field: 'newsBulletinId', title: 'id', sort: true ,hide:true}
				,{type: 'numbers', title: '序号'}
				,{field: 'newsBulletinTime', title: '新闻创建时间'}
				,{field: 'newsBulletinType', title: '新闻类型', sort: true}
				,{field: 'newsBulletinTitle', title: '新闻标题'}
				,{field: 'newsBulletinContent', title: '新闻内容' }
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
					var ob={NewsBulletinId:data.newsBulletinId};
					// var userid=data['userid'];
					// alert(ob);

					$.ajax({
						url:"/bus/deleteNewsBulletinTable",
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
				var NewsBulletind=data['newsBulletinId'];
				//打开模态框
				layer.open({
					type: 1,
					title:"修改界面",
					closeBtn: false,
					shift: 2,
					area: ['400px', '300px'],
					shadeClose: true,
					content: $("#update"),
					success: function(layero, index){
						$("#upnewsBulletinTitle").val(data['newsBulletinTitle'])
						$("#upnewsBulletinContent").val(data['newsBulletinContent'])
					},
					yes:function(){
					}
				});
				$("#upnewsBulletinId").val(NewsBulletind);
			}

			// else if(obj.event === 'state'){
			//
            //  if (data['stateName']==="启用") {
			//
	        //      // $("#state").attr({"disabled":"disabled"});
	        //      $("#state").disabled();
	        //      // alert( $("#state").disabled());
            //  }else{
			//
			// 	layer.confirm('真的要启用吗', function(index){
			// 		// time: 1000 ,//2秒自动关闭
			// 		// var ob={cooperativeId:data.cooperativeId,stateId:data['stateName']};
			// 		// var userid=data['userid'];
			// 		// alert(ob);
			//
			// 		$.ajax({
			// 			url:"/bus/updateNewsBulletinTableState?stateName=启用"+"&NewsBulletinId="+data['cooperativeId'],
			// 			// data: ob,
			// 			type:"Post",
			// 			dataType:"text",
			// 			async: true,
			//
			// 			success: function (msg) {
			// 				// alert(msg);
			// 				if(msg==="修改成功1"){
			// 					obj.del();
			// 					alert("修改成功");
			// 					location.reload(); //删除成功后再刷新
			// 				}else{
			// 					alert("修改失败");
			// 				}
			// 			},
			// 			error:function(msg){
			// 				alert("服务器正忙");
			// 			}
			// 		});
			// 		layer.close(index);
			// 	});
            //  }
			// }
			//
			// else if(obj.event === 'state2') {
			//
			// 	if (data['stateName'] === "禁用") {
			// 		$("#state").disabled();
			// 	} else {
			//
			// 	layer.confirm('真的要禁用吗', function (index) {
			// 		// time: 1000 ,//2秒自动关闭
			// 		$.ajax({
			// 			url: "/bus/updateNewsBulletinTableState?stateName=禁用" + "&NewsBulletinId=" + data['newsBulletinId'],
			// 			type: "Post",
			// 			dataType: "text",
			// 			async: true,
			//
			// 			success: function (msg) {
			// 				// alert(msg);
			// 				if (msg === "修改成功1") {
			// 					obj.del();
			// 					alert("修改成功");
			// 					location.reload();
			// 				} else {
			// 					alert("修改失败");
			// 				}
			// 			},
			// 			error: function (msg) {
			// 				alert("服务器正忙");
			// 			}
			// 		});
			// 		layer.close(index);
			// 	});
			//   }
			// }
		});

         // var roleSelect;
		form.on('select', function(data){
			console.log(data.elem); //得到select原始DOM对象
			console.log(data.value); //得到被选中的值]
			$("#newsBulletinTypeSelect").val(data.value);
			$("#addNewsBulletinTypeSelect").val(data.value);
			$("#upNewsBulletinTypeSelect").val(data.value);
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

		var newsBulletinTimee = formData.newsBulletinTimee,
			newsBulletinTypeSelect = $("#newsBulletinTypeSelect").val(),
			newsBulletinTitlee = formData.newsBulletinTitlee;
		// $('select[name="roleSelect"] option:selected').text(),
        //        alert(roleSelect);
		table.reload('idTest', {
			page: {
				curr: 1
			},
			where: {
				newsBulletinTimee:newsBulletinTimee,
				newsBulletinTypeSelect:newsBulletinTypeSelect,
				newsBulletinTitlee:newsBulletinTitlee

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
	// layui.use('layer', function(){ //独立版的layer无需执行这一句
	// 	var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

		//触发事件
		// var active = {
		// 	setTop: function(){
		// 		var that = this;
		//
		// 	}
		//
		// 	,notice: function(){
		// 		//示范一个公告层
		// 		layer.open({
		// 			type: 1
		// 			,title: false //不显示标题栏
		// 			,closeBtn: false
		// 			,area: ['370px', '250px']
		// 			,shade: 0.8
		// 			,id: 'LAY_layuipro' //设定一个id，防止重复弹出
		// 			,btn: ['火速围观', '残忍拒绝']
		// 			,btn1:function addUser() {
		// 				// alert(1);
		// 				var addcooperativeName = $("#addcooperativeName").val();
		// 				var addnewsBulletinType = $("#addnewsBulletinType").val();
		// 				var addnewsBulletinTitle = $("#addnewsBulletinTitle").val();
		// 				var addnewsBulletinTitle = $("#addnewsBulletinTitle").val();
		// 				var addcooperativeArea = $("#addcooperativeArea").val();
		// 				if (addcooperativeX != null && addcooperativeX.length > 0 && addcooperativeY != null && addcooperativeY.length > 0 && addcooperativeName != null && addcooperativeName.length > 0 && addcooperativeType != null && addcooperativeType.length > 0 && addcooperativeArea != null && addcooperativeArea.length > 0) {
		//
		// 						var ob={cooperativeName:addcooperativeName,cooperativeType:addcooperativeType,cooperativeX:addcooperativeX,cooperativeY:addcooperativeY,cooperativeArea:addcooperativeArea};
		// 						$.ajax({
		// 							url: "/bus/addNewsBulletinTable",
		// 							data: ob,
		// 							type: "Post",
		// 							dataType: "text",
		// 							async: true,
		//
		// 							success: function (msg) {
		// 								if (msg === "添加成功") {
		// 									// obj.del();
		// 									alert("添加成功");
		// 									location.reload(); //删除成功后再刷新
		//
		// 								} else {
		// 									alert("添加失败");
		// 								}
		// 							},
		// 							error: function (msg) {
		// 								alert("服务器正忙");
		// 							}
		// 						});
		//
		// 				} else  {
		// 					alert("输入的信息不能为空");
		// 				}
		// 				}
		// 			,btnAlign: 'c'
		// 			,moveType: 1 //拖拽模式，0或者1
		// 			,content: '<div style="padding: 50px; line-height: 33px; background-color: #393D49; color: #fff; font-weight:800;">' +
		// 				'合作商名称：<input type="text" name="addcooperativeName" id="addcooperativeName"><br>' +
		// 				'合作商类型:<input type="text" name="addcooperativeType" id="addcooperativeType"><br>' +
		// 				'合作商X坐标:<input type="text" name="addcooperativeX" id="addcooperativeX"><br>' +
		// 				'合作商Y坐标:<input type="text" name="addcooperativeY" id="addcooperativeY"><br>' +
		// 				'合作商地区:<input type="text" name="addcooperativeArea" id="addcooperativeArea"><br>' +
		// 				'</div>'
		// 			,success: function(layero){
		//
		// 				}
		// 		});
		// 	}
		//
		// };
		//
		// $('#layerDemo .layui-btn').on('click', function(){
		// 	var othis = $(this), method = othis.data('method');
		// 	active[method] ? active[method].call(this, othis) : '';
		// });

	// });
	function addDiv() {
		// var advertisementId=data['advertisementId'];
		//打开模态框
		layer.open({
			type: 1,
			title:"增加界面",
			closeBtn: false,
			shift: 2,
			area: ['400px', '250px'],
			shadeClose: true,
			content: $("#add"),
			success: function(layero, index){
				$("#addnewsBulletinTitle").val("");
				$("#addnewsBulletinContent").val("");
				$("#addNewsBulletinTypeSelect").val("");
				//获取当前时间
				var date = new Date();
				var seperator1 = "-";
				var year = date.getFullYear();
				var month = date.getMonth() + 1;
				var strDate = date.getDate();
				if (month >= 1 && month <= 9) {
					month = "0" + month;
				}
				if (strDate >= 0 && strDate <= 9) {
					strDate = "0" + strDate;
				}
				var currentdate = year + seperator1 + month + seperator1 + strDate;

				$("#addnewsBulletinTime").val(currentdate);
			},
			yes:function(){
			}
		});
		// $("#advertisementId").val(advertisementId);
	}

	//增加方法
	function addMethod() {

		var addnewsBulletinTitle = $("#addnewsBulletinTitle").val();
		var addnewsBulletinContent = $("#addnewsBulletinContent").val();
		var addNewsBulletinTypeSelect = $("#addNewsBulletinTypeSelect").val();
		var addnewsBulletinTime = $("#addnewsBulletinTime").val();
		if (addnewsBulletinTitle != null && addnewsBulletinTitle.length > 0 && addnewsBulletinContent != null && addnewsBulletinContent.length > 0 && addNewsBulletinTypeSelect != null && addNewsBulletinTypeSelect.length > 0 ) {

			var ob={newsBulletinTitle:addnewsBulletinTitle,newsBulletinContent:addnewsBulletinContent,newsBulletinType:addNewsBulletinTypeSelect,newsBulletinTime:addnewsBulletinTime};
			$.ajax({
				url: "/bus/addNewsBulletinTable",
				data: ob,
				type: "Post",
				dataType: "text",
				async: true,
				success: function (msg) {
					// alert(msg);
					if (msg === "添加成功") {
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

	//关闭模态框
	function closeMethod() {
		layer.close(layer.index);

	}
	//修改的方法
	function updateMethod() {
		var upnewsBulletinTitle = $("#upnewsBulletinTitle").val();
		var upnewsBulletinContent = $("#upnewsBulletinContent").val();
		var upNewsBulletinTypeSelect = $("#upNewsBulletinTypeSelect").val();
		var upnewsBulletinId = $("#upnewsBulletinId").val();
		if (upnewsBulletinTitle != null && upnewsBulletinTitle.length > 0 && upnewsBulletinContent != null && upnewsBulletinContent.length > 0 && upNewsBulletinTypeSelect != null && upNewsBulletinTypeSelect.length > 0 ) {

			var ob={newsBulletinTitle:upnewsBulletinTitle,newsBulletinContent:upnewsBulletinContent,newsBulletinType:upNewsBulletinTypeSelect,newsBulletinId:upnewsBulletinId};

			$.ajax({
				type: "POST",
				url: "/bus/updateNewsBulletinTable",//提交的地址
				data: ob,
				dataType: "text",//希望返回的数据类型
				async: true,//异步操作
				success: function (msg) {//成功的方法  msg为返回数据
					if(msg==="修改成功"){
						alert("修改成功");
						layer.close(layer.index);
						location.reload(); //删除成功后再刷新
					}else{
						alert("修改失败");
					}
				},
				error: function () {//错误的方法
				}
			});
		} else  {
			alert("输入的信息不能为空");
		}
	}
</script>

<div id="add" style="display: none;">
	<div style="background-color: #ffffff;height: 25px"></div>
	<div class="layui-form-item center">

		<div style="padding: 50px; line-height: 33px; background-color: #393D49; color: #fff; font-weight:800;">
			新闻标题:<input type="text" name="addnewsBulletinTitle" id="addnewsBulletinTitle"><br>
			新闻内容:<input type="text" name="addnewsBulletinContent" id="addnewsBulletinContent"><br>
			发布时间:<input readonly type="date" name="addnewsBulletinTime" id="addnewsBulletinTime"><br>
			新闻类型:<select id="addNewsBulletinTypeSelect" name="addNewsBulletinTypeSelect"  style="margin-left: 3%;">
			<option value="线路变更">线路变更</option>
			<option value="站点更名">站点更名</option>
			<option value="普通新闻">普通新闻</option>
		</select>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button id="add-btn" class="layui-btn" lay-submit lay-filter="save"
				        onclick="addMethod()">提交添加
				</button>
				<button type="reset" class="layui-btn layui-btn-primary"
				        onclick="closeMethod()">取消
				</button>
			</div>
		</div>
	</div>
</div>


<div id="update" style="display: none;">
	<div style="background-color: #ffffff;height: 50px"></div>
	<div class="layui-form-item center">

		<div class="layui-input-block">
			<input type="text" name="upnewsBulletinId" id="upnewsBulletinId" hidden="true" ><br>
		    新闻标题:<input type="text" name="upnewsBulletinTitle" id="upnewsBulletinTitle"><br>
		    新闻内容:<input type="text" name="upnewsBulletinContent" id="upnewsBulletinContent"><br>
		    新闻类型:<select id="upNewsBulletinTypeSelect" name="upNewsBulletinTypeSelect"  style="margin-left: 3%;">
			<option value="线路变更">线路变更</option>
			<option value="站点更名">站点更名</option>
			<option value="普通新闻">普通新闻</option>
		</select>
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
