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
	String path = request.getContextPath();
	String uicssPath = request.getContextPath()+"/layui/" ;
	String uiPath = request.getContextPath()+"/layui/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>广告配置</title>
	<link rel="stylesheet" href=<%=uicssPath +"css/layui.css"%> media="all">
	<script src="<%=jsPath+"jquery-3.4.1.js"%>"></script>
	<script src=<%=uiPath+"layui.js"%>></script>
	<script src=<%=jsPath+"advertisement.js"%>></script>

</head>
<body>

<div >
	<div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">
		<button data-method="notice" class="layui-btn" onclick="x()">增加广告</button>
	</div>
	<form class="layui-form layui-col-md12 x-so" id="complain_search" style=" margin-top: 6%; margin-left:12% ;">

<%--	<label class="layui-form-label" style="margin-left: 8%">广告类型</label>--%>
<%--<div class="layui-input-inline">--%>
<%--	<input id="cooperativeAreaa" type="text" name="cooperativeAreaa" class="layui-input" id="test1" >--%>
<%--</div>--%>
	<label>广告类型</label>
	<div class="layui-input-inline" >
		<select id="SelectType" name="SelectType"  style="margin-left: 3%;">
<%--可以用--%>
<%--			<c:if test="${null != requestScope.roleSelect}">--%>
<%--				<option></option>--%>
<%--				<c:forEach items="${requestScope.roleSelect}" begin="0" step="1" var="i">--%>
<%--					<option  value="">${i.advertisingType}</option>--%>
<%--				</c:forEach>--%>
<%--			</c:if>--%>
	<option></option>
	<option>轮播广告</option>
	<option>车厢广告</option>
	<option>墙面广告</option>
		</select>

	</div>



		<label>状态：</label>
		<div class="layui-input-inline" >
		<select id="roleSelect" name="roleSelect"  style="margin-left: 3%;">
			<option  value=""></option>
			<option  value="启用">启用</option>
			<option  value="禁用">禁用</option>
			<option  value="已删除">已删除</option>
<%--			<c:if test="${null != requestScope.roleSelect}">--%>
<%--				<option></option>--%>
<%--				<c:forEach items="${requestScope.roleSelect}" begin="0" step="1" var="i">--%>
<%--					<option  value="">${i.stateName}</option>--%>
<%--				</c:forEach>--%>
<%--			</c:if>--%>
		</select>
		</div>

<label class="layui-form-label2" style="margin-left: 3%;">合作商:</label>
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
<%--		<a  id="look" lay-event="looks">查看</a>--%>
	</script>

<script >

	layui.use(['laydate','table','layer','form','laypage','element'], function(){

		var table = layui.table,
			layer = layui.layer,
			form = layui.form,
			laypage = layui.laypage,
		 // $ = layui.jquery;
		laydate = layui.laydate;
		//第一个实例
		table.render({
			elem: '#demo'
			// ,height: 312
			,url: '/bus/showAdvertisementTable' //数据接口
			,page: true //开启分页
			,limit: 3 //开启分页
			,limits: [1,5,10] //开启分页
			,id: 'idTest'

			,cols: [[ //表头
				{field: 'cooperativeId', title: 'id', sort: true ,hide:true}
				,{field: 'advertisementId', title: 'id', sort: true ,hide:true}
				,{type: 'numbers', title: '序号'}
				,{field: 'cooperativeName', title: '合作商名称'}
				,{field: 'advertisingHeadlines', title: '广告标题', sort: true}
				,{field: 'advertisingType', title: '广告类型',width:90}
				,{field: 'releaseTime', title: '发布时间' ,width:90}
				,{field: 'deadline', title: '截止时间',width:90 }
				,{field: 'stateName', title: '状态' ,width:70 }
				,{field: 'stateId', title: '状态id',hide:true }
				<%--,{field: 'advertisingImgurl', title: 'tu',templet:'<div><img  src="<%=path + "{{ d.advertisingImgurl }}"%>"></div>' }可以用 --%>
				,{field: 'advertisingImgurl', title: 'tu',templet:'<div><img  src="<%=path + "{{ d.advertisingImgurl }}"%>"></div>' }
				,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
			]]
			,done:function(res,curr,count){
				hoverOpenImg();//显示大图
				$('table tr').on('click',function(){
					$('table tr').css('background','');
					<%--$(this).css('background','<%=PropKit.use("config.properties").get("table_color")%>');--%>
				});
			}

		});

		//放大图片
		function hoverOpenImg(){
			var img_show = null; // tips提示
			$('td img').hover(function(){
				//alert($(this).attr('src'));
				var img = "<img class='img_msg' src='"+$(this).attr('src')+"' style='width:340px;' />";
				img_show = layer.tips(img, this,{
					tips:[2, 'rgba(41,41,41,.5)']
					,area: ['350px'],time:10000
				});
			},function(){
				layer.close(img_show);
			});
			$('td img').attr('style','max-width:70px');
		}

		var $ = layui.jquery;
		//监听行工具事件
		table.on('tool(test)', function(obj){//注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			var data = obj.data;

			if(obj.event === 'del'){

				layer.confirm('真的删除行么', function(index){
					// time: 1000 ,//2秒自动关闭
					var ob={advertisementId:data.advertisementId};
					// var userid=data['userid'];
					// alert(data.advertisementId);

					$.ajax({
						url:"/bus/deleteAdvertisementTable",
						data: ob,
						type:"Post",
						dataType:"text",
						async: true,

						success: function (msg) {
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
				var advertisementId=data['advertisementId'];
				var releaseTime=data['releaseTime'];
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
				$("#upadvertisementId").val(advertisementId);
				$("#updatereleaseTime").val(releaseTime);

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
					// alert(data['advertisementId']+"----");

					$.ajax({
						url:"/bus/updateAdvertisementTableState?stateName=启用"+"&advertisementId="+data['advertisementId'],
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
					alert(data['advertisementId']+"----"+data['stateName']);
					$.ajax({
						url: "/bus/updateAdvertisementTableState?stateName=禁用" + "&advertisementId=" + data['advertisementId'],
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

			// else if (obj.event === 'looks') {
			// 	// var advertisementId=data['advertisementId'];
			// 	// var releaseTime=data['releaseTime'];
			// 	//打开模态框
			// 	layer.open({
			// 		type: 1,
			// 		title:"查看界面界面",
			// 		closeBtn: false,
			// 		shift: 1,
			// 		area: ['500px', '400px'],
			// 		shadeClose: true,
			// 		content: $("#lookss"),
			// 		success: function(layero, index){},
			// 		yes:function(){
			// 		}
			// 	});
			//
			// 	// $("#upadvertisementId").val(advertisementId);
			// 	// $("#updatereleaseTime").val(releaseTime);
			//
			// }
		});

		form.on('select', function(data){
			console.log(data.elem); //得到select原始DOM对象
			console.log(data.value); //得到被选中的值]
			$("#roleSelect").val(data.value);
			$("#SelectType").val(data.value),
			$("#addAdvertisingTypeSelect").val(data.value),
				$("#upadvertisingType").val(data.value),
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
			// roleSelect = $("#roleSelect").find("option:selected").text(),
			SelectType = $("#SelectType").val();

		// $('select[name="roleSelect"] option:selected').text(),
		table.reload('idTest', {
			page: {
				curr: 1
			},
			where: {
				uername:uername,
				roleSelect:roleSelect,
				SelectType:SelectType

			},
			// method: 'post',
			// contentType: "application/json;charset=utf-8",
			// url: '/LayuiHw/UsertableServlet'
		});
		return false;
	});

	});
<%--</script>--%>
<%--</div>--%>


     function x() {
	     // var advertisementId=data['advertisementId'];
	     //打开模态框
	     layer.open({
		     type: 1,
		     title:"增加界面",
		     closeBtn: false,
		     shift: 2,
		     area: ['400px', '300px'],
		     shadeClose: true,
		     content: $("#add"),
		     success: function(layero, index){
			     // laydate.render({
				 //     elem: '#adddeadline'
				 //     ,type: 'datetime'
				 //     ,min: 0
				 //     ,trigger: 'click'
			     // });

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

			     $("#addreleaseTime").val(currentdate);
		     },
		     yes:function(){
		     }
	     });
	     // $("#advertisementId").val(advertisementId);
     }





		// //触发事件
		// var active = {
		// 	setTop: function(){
		// 		var that = this;
		//
		// 	}
		//
		// 	,notice: function(){





				// //示范一个公告层
				// layer.open({
				// 	type: 1
				// 	,title: false //不显示标题栏
				// 	,closeBtn: false
				// 	,area: ['270px', '250px']
				// 	,shade: 0.8
				// 	,id: 'LAY_layuipro' //设定一个id，防止重复弹出
				// 	,btn: ['火速围观', '残忍拒绝']
				// 	,btn1:function addUser() {
				// 		// alert(1);
				// 		var addcooperativeName = $("#addcooperativeName").val();
				// 		var addadvertisingHeadlines = $("#addadvertisingHeadlines").val();
				// 		var addadvertisingType = $("#addadvertisingType").val();
				// 		var adddeadline = $("#adddeadline").val();
				// 		// var addcooperativeArea = $("#addcooperativeArea").val();
				// 		if (addadvertisingHeadlines != null && addadvertisingHeadlines.length > 0 && addadvertisingType != null && addadvertisingType.length > 0 && addcooperativeName != null && addcooperativeName.length > 0 && adddeadline != null && adddeadline.length > 0) {
				//
				// 				var ob={cooperativeName:addcooperativeName,advertisingHeadlines:addadvertisingHeadlines,advertisingType:addadvertisingType,deadline:adddeadline};
				// 				$.ajax({
				// 					url: "/bus/addAdvertisementTable",
				// 					data: ob,
				// 					type: "Post",
				// 					dataType: "text",
				// 					async: true,
				//
				// 					success: function (msg) {
				// 						if (msg === "添加成功") {
				// 							// obj.del();
				// 							alert("添加成功");
				// 							location.reload(); //删除成功后再刷新
				//
				// 						} else {
				// 							alert("添加失败");
				// 						}
				// 					},
				// 					error: function (msg) {
				// 						alert("服务器正忙");
				// 					}
				// 				});
				//
				// 		} else  {
				// 			alert("输入的信息不能为空");
				// 		}
				// 		}
				// 	,btnAlign: 'c'
				// 	,moveType: 1 //拖拽模式，0或者1
				// 	,content:
				// 	'<div style="padding: 50px; line-height: 33px; background-color: #393D49; color: #fff; font-weight:800;">' +
				// 		'合作商名称：<input type="text" name="addcooperativeName" id="addcooperativeName"><br>' +
				// 		'广告标题:<input type="text" name="addadvertisingHeadlines" id="addadvertisingHeadlines"><br>' +
				// 		'广告类型:<input type="text" name="addadvertisingType" id="addadvertisingType"><br>' +
				// 		// '发布时间:<input type="text" name="releaseTime" id="releaseTime"><br>' +
				// 		// '合作商地区:<input type="text" name="addcooperativeArea" id="addcooperativeArea"><br>' +
				// 		'截止时间：<input type="date" class="layui-input" id="adddeadline" placeholder="yyyy-MM-dd"  onblur="adddeadline()"><br>' +
				// 		'</div>'
				// 	,success: function(layero){
				//
				// 		// //常规用法日期
				// 		// laydate.render({
				// 		// 	elem: '#adddeadline'
				// 		// 	,type: 'datetime'
				// 		// 	,min: 0
				// 		// 	,trigger: 'click'
				// 		// });
				// 		}
				// });
		// 	}
		//
		// };

		// $('#layerDemo .layui-btn').on('click', function(){
		// 	var othis = $(this), method = othis.data('method');
		// 	active[method] ? active[method].call(this, othis) : '';
		// });

	// });



	//关闭模态框
	function closeMethod() {
		layer.close(layer.index);
	}
	//修改的方法
	function updateMethod() {
		// var upcooperativeName = $("#upcooperativeName").val();
		var upadvertisingHeadlines = $("#upadvertisingHeadlines").val();
		var upadvertisingType = $("#upadvertisingType").val();
		var upreleaseTime = $("#updatereleaseTime").val();

		var updeadline = $("#updeadline").val();
		var upadvertisementId = $("#upadvertisementId").val();
		var ob={advertisingHeadlines:upadvertisingHeadlines,advertisingType:upadvertisingType,deadline:updeadline,advertisementId:upadvertisementId,releaseTime:upreleaseTime};
			$.ajax({
				type: "POST",
				url: "/bus/updateAdvertisementTable",//提交的地址
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
	}

	// //增加方法
	// function addMethod() {
	//
	// 	var addcooperativeName = $("#addcooperativeName").val();
	// 	var addadvertisingHeadlines = $("#addadvertisingHeadlines").val();
	// 	var addAdvertisingTypeSelect = $("#addAdvertisingTypeSelect").val();
	// 	var adddeadline = $("#adddeadline").val();
	// 	var addreleaseTime = $("#addreleaseTime").val();
	// 	alert(addcooperativeName+addadvertisingHeadlines+addAdvertisingTypeSelect+adddeadline+addreleaseTime)
	// 	if (addadvertisingHeadlines != null && addadvertisingHeadlines.length > 0 && addAdvertisingTypeSelect != null && addAdvertisingTypeSelect.length > 0 && addcooperativeName != null && addcooperativeName.length > 0 && adddeadline != null && adddeadline.length > 0) {
	//
	// 		var ob={cooperativeName:addcooperativeName,advertisingHeadlines:addadvertisingHeadlines,advertisingType:addAdvertisingTypeSelect,deadline:adddeadline,releaseTime:addreleaseTime};
	// 		$.ajax({
	// 			url: "/bus/addAdvertisementTable",
	// 			data: ob,
	// 			type: "Post",
	// 			dataType: "text",
	// 			async: true,
	// 			success: function (msg) {
	// 				alert(msg);
	// 				if (msg === "添加成功") {
	// 					alert("添加成功");
	// 					location.reload(); //删除成功后再刷新
	// 				} else {
	// 					alert("添加失败");
	// 				}
	// 			},
	// 			error: function (msg) {
	// 				alert("服务器正忙");
	// 			}
	// 		});
	// 	} else  {
	// 		alert("输入的信息不能为空");
	// 	}
	// }


	function adddeadline() {
		//两个时间对比
		var startDate = new Date($("#addreleaseTime").val()).getTime();
		var endTime = new Date($("#adddeadline").val()).getTime();

		if (endTime < startDate) {
			layer.msg('结束时间不能小于开始时间');
			$('#adddeadline').val('');
		}
	}

	function updeadline() {
		//两个时间对比
		var startDate = new Date($("#updatereleaseTime").val()).getTime();
		var endTime = new Date($("#updeadline").val()).getTime();

		if (endTime < startDate) {
			layer.msg('结束时间不能小于开始时间');
			$('#updeadline').val('');
		}
	}

</script>
</div>
<div id="update" style="display: none;">
	<div style="background-color: #ffffff;height: 50px"></div>
	<div class="layui-form-item center">

		<div class="layui-input-block">
			<input type="text" name="upadvertisementId" id="upadvertisementId" hidden="true" ><br>
<%--		合作商名称：<input type="text" name="upcooperativeName" id="upcooperativeName"><br>--%>
		广告标题:<input type="text" name="upadvertisingHeadlines" id="upadvertisingHeadlines"><br>
<%--		广告类型:<input type="text" name="upadvertisingType" id="upadvertisingType"><br>--%>
<%--		发布时间:<input type="text" name="upreleaseTime" id="upreleaseTime"><br>--%>
<%--		截止时间:<input type="text" name="updeadline" id="updeadline"><br>--%>
		<input hidden="true" type="date" class="layui-input" id="updatereleaseTime" placeholder="yyyy-MM-dd"><br>
		截止时间：<input type="date" class="layui-input" id="updeadline"  onblur="updeadline()" ><br>
			<select id="upadvertisingType" name="upadvertisingType"  style="margin-left: 3%;">

				<option>轮播广告</option>
				<option>车厢广告</option>
				<option>墙面广告</option>
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


<div id="add" style="display: none;">
	<div style="background-color: #ffffff;height: 25px"></div>
	<div class="layui-form-item center">


		<div style="padding: 50px; line-height: 33px; background-color: #393D49; color: #fff; font-weight:800;">
			合作商名称：<input type="text" name="addcooperativeName" id="addcooperativeName"><br>
			广告标题:<input type="text" name="addadvertisingHeadlines" id="addadvertisingHeadlines"><br>
<%--			广告类型:<input type="text" name="addadvertisingType" id="addadvertisingType"><br>--%>
			发布时间:<input readonly type="date" name="addreleaseTime" id="addreleaseTime"><br>
<%--			合作商地区:<input type="text" name="addcooperativeArea" id="addcooperativeArea"><br>--%>
			截止时间：<input  type="date" class="layui-input" id="adddeadline" placeholder="yyyy-MM-dd"  onblur="adddeadline()"><br>
<%--			<label>广告类型</label>--%>
<%--			<div class="layui-input-inline" >--%>
			<div class="layui-upload">
				<button type="button" class="layui-btn" id="test1">上传图片</button>
				<%--	<div class="layui-upload-list">--%>
				<%--		<img class="layui-upload-img" id="demo1">--%>
				<%--		<p id="demoText"></p>--%>
				<%--	</div>--%>
			</div>
			广告类型<select id="addAdvertisingTypeSelect" name="addAdvertisingTypeSelect"  style="margin-left: 3%;">

					<c:if test="${null != requestScope.roleSelect}">
						<option></option>
						<c:forEach items="${requestScope.roleSelect}" begin="0" step="1" var="i">
							<option  value=${i.advertisingType}>${i.advertisingType}</option>
						</c:forEach>
					</c:if>
				</select>
<%--			</div>--%>

		</div>


	<div class="layui-form-item">
		<div class="layui-input-block">
			<button id="add-btn" class="layui-btn" lay-submit lay-filter="save"
			       >提交添加
			</button>
			<button type="reset" class="layui-btn layui-btn-primary"
			        onclick="closeMethod()">取消
			</button>
		</div>
	</div>
  </div>
</div>

<%--<div id="lookss" style="display: none;">--%>
<%--	<div style="background-color: #ffffff;height: 25px"></div>--%>
<%--	<div class="layui-form-item center">--%>

<%--&lt;%&ndash;		<img src="https://i.loli.net/2019/06/01/5cf29027ea78726804.png"  style="width: 200px;height: auto" onerror="javascript:this.src='’http://www.baidu.com'">&ndash;%&gt;--%>

<%--&lt;%&ndash;		<a href="javascript:;">&ndash;%&gt;--%>
<%--&lt;%&ndash;			<a src="http://localhost:8080/bus/backage.jpg" class="layui-nav-img" onerror="javascript:this.src='’http://www.baidu.com'">&ndash;%&gt;--%>
<%--&lt;%&ndash;			</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;	<img  onerror="javascript:this.src='’http://www.baidu.com'">&ndash;%&gt;--%>
<%--&lt;%&ndash;				<a href="http://www.baidu.com">退出</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;		</a>&ndash;%&gt;--%>


<%--&lt;%&ndash;		<img src="<%=path + "/img/backage.jpg"%>" class="layui-nav-img">&ndash;%&gt;--%>
<%--	<img src="<%=path + "/img/backage.jpg"%>" class="layui-nav-img">--%>


<%--	 </div>--%>
<%--</div>--%>
<%--<div class="layui-upload">--%>
<%--	<button type="button" class="layui-btn" id="test1">上传图片</button>--%>
<%--&lt;%&ndash;	<div class="layui-upload-list">&ndash;%&gt;--%>
<%--&lt;%&ndash;		<img class="layui-upload-img" id="demo1">&ndash;%&gt;--%>
<%--&lt;%&ndash;		<p id="demoText"></p>&ndash;%&gt;--%>
<%--&lt;%&ndash;	</div>&ndash;%&gt;--%>
<%--</div>--%>

<%--//上传图片方法--%>
<script>
	layui.use('upload', function(){
		var $ = layui.jquery
			,upload = layui.upload;

		//普通图片上传
		var uploadInst = upload.render({
			elem: '#test1'
			,url: '/bus/addAdvertisementTable'
			,auto: false
			,bindAction: '#add-btn'
			// ,before: function(obj){
			// 	//预读本地文件示例，不支持ie8
			// 	obj.preview(function(index, file, result){
			// 		$('#demo1').attr('src', result); //图片链接（base64）
			//
			// 	});
			// }
			//上传前的回调
			,before: function () {
				var addcooperativeName = $("#addcooperativeName").val();
				var addadvertisingHeadlines = $("#addadvertisingHeadlines").val();
				var addAdvertisingTypeSelect = $("#addAdvertisingTypeSelect").val();
				var adddeadline = $("#adddeadline").val();
				var addreleaseTime = $("#addreleaseTime").val();
				alert(addcooperativeName+addadvertisingHeadlines+addAdvertisingTypeSelect+adddeadline+addreleaseTime)
				if (addadvertisingHeadlines != null && addadvertisingHeadlines.length > 0 && addAdvertisingTypeSelect != null && addAdvertisingTypeSelect.length > 0 && addcooperativeName != null && addcooperativeName.length > 0 && adddeadline != null && adddeadline.length > 0) {

					this.data = {
						cooperativeName: addcooperativeName,
						advertisingHeadlines: addadvertisingHeadlines,
						advertisingType: addAdvertisingTypeSelect,
						deadline: adddeadline,
						releaseTime: addreleaseTime
					};
				}

			}
            //操作成功的回调
			,done: function(res){
				if (res === "添加成功") {
					alert("添加成功");
					location.reload(); //删除成功后再刷新
				} else {
					alert("添加失败");
				}

			}

			// ,error: function(){
			// 	//演示失败状态，并实现重传
			// 	var demoText = $('#demoText');
			// 	demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
			// 	demoText.find('.demo-reload').on('click', function(){
			// 		uploadInst.upload();
			// 	});
			// }
		});
		});

</script>
<c:choose>
	<c:when test="${requestScope.msg!=null}">
		<script>alert("${requestScope.msg}")</script>
	</c:when>
</c:choose>
</body>
</html>
