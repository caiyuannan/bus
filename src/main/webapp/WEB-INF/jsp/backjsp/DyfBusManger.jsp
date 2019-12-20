<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2019/12/16
  Time: 15:29
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
	<title>车辆信息模块</title>
	<link rel="stylesheet" href="<%=laPath%>css/layui.css" media="all">
	<script src="<%=laPath%>layui.js"></script>
	<script src=<%=jspath + "jquery-3.4.1.js"%>></script>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<style type="text/css">
		.layui-table-page {
			text-align: center;
		}

	</style>
</head>
<body style="">


<h1 style="float: left;margin-top: -80px;margin-left: 40px;background-color: #8D8D8D">欢迎来到公交车车辆信息配置</h1>
<table align="center" style="margin-left: 300px;margin-top:100px">
	<tr align="center">
		<td>
			<div class="layui-input-inline">
				<input type="text" id="driverName" name="driverName" required lay-verify="required" placeholder="维护人"
				       autocomplete="off" class="layui-input">
			</div>
		</td>
		<td>
			<div class="layui-form">
				<select name="driverState" id="driverState" lay-filter="myselect">
					<option value="">请选择车辆状态</option>
					<option value="在用">在用</option>
					<option value="故障待救援">故障待救援</option>
					<option value="故障救援">故障救援</option>
					<option value="维修">维修</option>
					<option value="报废停用">报废停用</option>
				</select>
			</div>
		</td>
		<td>
			<%--			使用年数input--%>
			<div class="layui-input-inline">
				<input type="text" id="sendYear" name="sendYear" required lay-verify="required" placeholder="使用年数"
				       autocomplete="off" class="layui-input">
			</div>
		</td>
		<td>
		</td>
	</tr>
	<tr>
		<td>
			<%--			车牌号码input--%>
			<div class="layui-input-inline">
				<input type="text" id="carCard" name="carCard" required lay-verify="required" placeholder="车牌号码"
				       autocomplete="off" class="layui-input">
			</div>
		</td>
		<td>
			<%--			使用年限input--%>
			<div class="layui-input-inline">
				<input type="text" id="driverAge" name="driverAge" required lay-verify="required" placeholder="使用年限"
				       autocomplete="off" class="layui-input">
			</div>
		</td>
		<td style="margin-left: 50px;padding-left: 0px">
			<button type="button" class="layui-btn" onclick="onAddBtn()">
				<i class="layui-icon">&#xe608;</i> 添加
			</button>
			<input class="layui-btn  layui-btn-primary layui-btn-lg" type="button" value="搜索" onclick="gotoDb()">
		</td>
	</tr>
</table>

<table id="demo" lay-filter="test" style="margin-top: 10px"></table>
<div class="layui-footer my-footer">
	<script id="barDemo" type="text/html">
		<a class="layui-btn layui-btn-xs" lay-event="edit">排班</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="detail">修改</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="check1">报废停用</a>
	</script>

	<script>
		layui.use('table', function () {
			var table = layui.table;
			//第一个实例
			table.render({
				elem: '#demo'
				, height: 312
				, url: 'http://localhost:8080/bus/DyfBusMangerServlet' //数据接口
				, page: true
				, cols: [[ //表头
					{field: 'busId', title: '序号', align: "center"}
					, {field: 'busLicense', title: '车牌', align: "center"}
					, {field: 'busDutyDriver', title: '维护人', align: "center"}
					, {field: 'busMin', title: '使用时间(年)', align: "center", minWidth: "40px"}
					, {field: 'busAge', title: '使用年限(年)', align: "center"}
					, {field: 'stateName', title: '公交车状态', align: "center"}
					, {fixed: 'right', title: '操作', minWidth: "120px", toolbar: '#barDemo', align: "center"}
				]]
				, skin: 'row' //表格风格
				, even: true
				, limits: [1, 5, 10]
				, limit: 1 //每页默认显示的数量

			});
//监听工具条
			table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				if (layEvent === 'edit') { //查看
					layer.msg("jinlai")
				} else if (layEvent === 'del') { //删除
					var stateName = data['stateName'];
					var busId = data['busId'];
					var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
					if (stateName === "已删除") {
						layer.msg("该车已删除，无法操作")
					} else {
						layer.confirm('真的删除行么', function (index) {
							$.ajax({
								type: "post",
								url: "http://localhost:8080/bus/DyfBusUpdateState",
								data: "msg=" + busId + "=" + "已删除",
								datatype: "text",
								async: true,
								success: function (res) {
									if (res === "success") {
										layer.msg("删除成功");
										layer.close(index);
										obj.update({
											stateName: '已删除'
										});
									}
								},
								error: function () {
									layer.msg("删除失败")
								}
							})
						});
					}

				} else if (layEvent === 'detail') { //编辑
					var busState = data['stateName'];
					if (busState === '已删除' || busState === '报废停用') {
						layer.msg("该车已报废或删除，无法更改操作")
					} else {
						layer.confirm('是否修改', function () {
							$("#afterCardProvince").val(data['busLicense']);
							$("#afterBusDiver").val(data['busDutyDriver']);
							$("#afterBusAge").val(data['busMin']);
							$("#afterBusYear").val(data['busAge']);
							$("#busId").val(data['busId']);
							$("#afterBusState").val(data['stateName']);
							//调用修改弹窗界面
							updateBus();
						})
					}


					//同步更新缓存对应的值
					/*obj.update({
						username: '123'
						,title: 'xxx'
					});*/
				} else if (layEvent === "check1") {
					var stateName = data['stateName'];
					var busId = data['busId'];
					if (stateName === "报废停用" || stateName === "已删除") {
						layer.msg("该车已报废停用或删除，无法操作")
					} else {
						layer.confirm('是否报废停用', function (index) {
							$.ajax({
								type: "post",
								url: "http://localhost:8080/bus/DyfBusUpdateState",
								data: "msg=" + busId + "=" + "报废停用",
								datatype: "text",
								async: true,
								success: function (res) {
									if (res === "success") {
										layer.msg("报废成功");
										layer.close(index);
										obj.update({
											stateName: '报废停用'
										});
									}
								},
								error: function () {
									layer.msg("操作失败")
								}
							})
						});
					}

				}
			});

		});

		//修改窗内确认修改事件
		function continueUpdate() {
			var cardNum = $("#beforeCardProvince").val();
			var busDiver = $("#beforeBusDiver").val();
			var afterBusState = $("#afterBusState").val();
			var beforeBusState = $("#beforeBusState").val();
			if (busDiver === '0') {
				busDiver = null;
			}
			var busAge = parseInt($("#beforeBusAge").val().trim());
			var busYear = parseInt($("#beforeBusYear").val().trim());
			var afBusAge = parseInt($("#afterBusAge").val().trim());
			var afBusYear = parseInt($("#afterBusYear").val().trim());
			var busId = $("#busId").val();
			var addNum = 1;
			if (cardNum != null && cardNum.length > 0) {
				addNum++;
			}
			if (busDiver != null && busDiver.length > 0) {
				addNum++;
			}
			if (!isNaN(busAge)) {
				addNum++;
			}
			if (!isNaN(busYear)) {
				addNum++;
			}
			if (beforeBusState != null && beforeBusState.length > 0) {
				addNum++;
			}

			// 5,7 console.log(busAge, busYear);
			var num = 1;
			if (cardNum === $("#afterCardProvince").val()
				|| busDiver === $("#afterBusDiver").val()
				|| busAge === afBusAge
				|| busYear === afBusYear
				|| beforeBusState === afterBusState) {
				layer.msg("与之前数据重复修改后重试");
				num++;
			} else if (cardNum != null && cardNum.length > 0) {
				var str = cardNum.split(".");
				var provinceAfter = $("#sessionProvince").val();
				if (str[0] != provinceAfter) {
					layer.msg("请选择与该城市相匹配的车辆牌照");
					num++;
				} else if (str[1].length != 5) {
					layer.msg("请输入正确长度牌照");
					num++;
				}

			} else if (busAge > 10 || busYear > 10) {
				layer.msg("车辆使用时间限和报废年限为十年");
				num++;
			} else if (busAge > 0 && busYear > 0) {
				if (busAge >= busYear || busAge >= afBusYear) {
					layer.msg("使用年限不可超过或等于报废年限");
					num++;
				}
			} else if (busAge > 0 && isNaN(busYear)) {

				if (busAge >= afBusYear) {
					layer.msg("使用年限不可超过或等于报废年限");
					num++;
				}
			}
			if (addNum === 1) {
				layer.msg("更改信息不能为空")
			}
			if (num === 1 && addNum > 1) {
				var DyfBusBean = new Object();
				DyfBusBean.busId = $("#busId").val();
				DyfBusBean.busLicense = cardNum;
				DyfBusBean.busDutyDriver = busDiver;
				DyfBusBean.busMin = busAge;
				DyfBusBean.busAge = busYear;
				$.ajax({
					type: "post",
					url: "http://localhost:8080/bus/DyfBusUpdateInFor",
					data: {DyfBusBean: JSON.stringify(DyfBusBean), beforeBusState: beforeBusState},
					datatype: "text",
					async: true,
					success: function (res) {
						if (res === "success") {
							alert("修改成功");
							$("#beforeCardProvince").val('0');
							$("#beforeBusDiver").val('0');
							layer.close(layer.index);
							window.location.reload();
						} else {
							layer.msg("添加失败")
						}
					},
					error: function () {
						layer.msg("数据添加异常，请稍后重试")
					}
				})
			}


		}

		//条件查询ajax刷新
		function gotoDb() {
			var driverState = $("#driverState").val();
			var sendYear = $("#sendYear").val();
			var carCard = $("#carCard").val();
			var driverAge = $("#driverAge").val();
			var driverName = $("#driverName").val();
			var table = layui.table;
			table.reload('demo', {
				url: 'http://localhost:8080/bus/DyfBusMangerServlet'
				, where: {
					"driverSta": driverState,
					"sendYear": sendYear,
					"carCard": carCard,
					"driverAge": driverAge,
					"driverName": driverName
				}
				, page: {
					curr: 1
				}//设定异步数据接口的额外参数
				//,height: 300
			});

		}

		//add-main 添加弹窗显示
		function onAddBtn() {
			//页面层-自定义
			if ($("#driverNull").val() != null && $("#driverNull").val().length > 0) {
				layer.open({
					type: 1,
					title: "新建配置",
					closeBtn: 1,
					shift: 2,
					area: ['500px', '600px'],
					shadeClose: true,
					// btn: ['新增', '取消'],
					// btnAlign: 'c',
					content: $("#add1-main"),
					success: function (layero, index) {
					},
					yes: function () {
					}

				});
			} else {
				alert("请先添加该地区司机")
			}

		}

		//updateBus 修改弹窗显示
		function updateBus() {
			//页面层-自定义
			if ($("#driverNull").val() != null && $("#driverNull").val().length > 0) {

				layer.open({
					type: 1,
					title: "新建配置",
					closeBtn: 1,
					shift: 2,
					area: ['500px', '750px'],
					shadeClose: true,
					// btnAlign: 'c',
					content: $("#updateBus"),
					success: function (layero, index) {
					},
					yes: function () {
					}

				});
			} else {
				alert("请先添加该地区司机")
			}

		}

		function AddBusCarAjaxFrom() {
			var cardNum = $("#cardProvince").val();
			var busDiver = $("#busDiver").val();
			var busAge = $("#busAge").val();
			var busYear = $("#busYear").val();
			var carType = $("#busState").val();
			var num = 1;
			//判断车牌号是否正确，省市是否匹配
			if (cardNum != null && cardNum.length > 0) {
				var str = cardNum.split(".");
				var provinceAfter = $("#sessionProvince").val();
				if (str[0] != provinceAfter) {
					alert("请选择与该城市相匹配的车辆牌照");
					return;
				} else if (str[1].length != 5) {
					alert("请输入正确长度牌照");
					return;
				} else {
					num++;
				}

			} else {
				alert("请先输入车牌号码");
				return;
			}
			//判断是否有分配司机
			if (busDiver == null || busDiver.length < 1 || busDiver === "0") {
				alert("请先选择司机");
				return;
			} else {
				num++;
			}
			//	判断是否填入使用时间
			if (busAge == null || busAge.length < 1) {
				alert("请先输入车辆使用时间！最高年限为十年");
				return;
			} else {
				if (busAge > 10) {
					alert("车辆使用时间限为十年");
					return;
				} else {
					num++;
				}

			}


			//	判断车辆使用年限是否为整数或空

			if (busYear == null || busYear.length < 1) {
				alert("请先输入车辆使用年限");
				return;
			} else {
				if (busYear > 10) {
					alert("车辆使用年限强制报废期最高为十年");
					return;
				} else {
					num++;
				}

			}


			if (busAge >= busYear) {
				alert("使用年限不可超过或等于报废年限");
				return;
			}

			if (carType == null || carType.length < 1 || carType === "0") {
				alert("请先选择车辆类型");
				return;
			} else {
				num++;
			}
			//如果num等于6时代表前面的所有项已填写
			if (num >= "6") {
				$.ajax({
					type: "post",
					url: "http://localhost:8080/bus/DyfBusAddBus",
					data: "msg=" + cardNum + "=" + busDiver + "=" + busAge + "=" + busYear + "=" + carType,
					datatype: "text",
					async: true,
					success: function (res) {
						console.log(res);
						if (res === "success") {
							alert("添加成功");
							closeDialog();
						} else {
							alert("添加失败")
						}
					},
					error: function () {
						alert("数据添加异常，请稍后重试")
					}
				})
			}

		}

		function closeDialog() {
			$("#cardProvince").val("");
			$("#busAge").val("");
			$("#busYear").val("");
			$("#busDiver option[value='0']").prop("selected", true);
			$("#busState option[value='0']").prop("selected", true);
			$("#beforeCardProvince").val('0');
			$("#beforeBusDiver").val('0');
			layer.close(layer.index);
			window.location.reload();
		}
	</script>

	<%--添加按钮弹窗--%>
	<div id="add1-main" style="display: none;" align="center">
		<h2 style="color: #2D93CA;margin-top: 50px">添加公交车数据</h2>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="text" name="value" id="cardProvince" required
				       style="width: 240px;margin-top: 30px;margin-left: -60px" lay-verify="required"
				       placeholder="公交车车牌"
				       autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form">
			<div class="layui-input-block">
				<select name="driverState" id="busDiver" lay-filter="required" style="width: 100px;margin-left: -60px">
					<option value="0">请选择车辆保养人员</option>
					<c:if test="${requestScope.selectAllDriver !=null}">
						<c:forEach items="${requestScope.selectAllDriver}" begin="0" step="1" var="item">
							<option value="${item.driverName}">${item.driverName}</option>
						</c:forEach>
					</c:if>

				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="text" name="busAge" id="busAge" required style="width: 240px;margin-left: -60px"
				       lay-verify="required" placeholder="使用时间(年)" autocomplete="off" class="layui-input"
				       onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="text" name="busYear" id="busYear" required style="width: 240px;margin-left: -60px"
				       lay-verify="required" placeholder="使用年限(年)" autocomplete="off" class="layui-input"
				       onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">
			</div>
		</div>
		<div class="layui-form">
			<div class="layui-input-block">
				<select name="driverState" id="busState" lay-filter="required" style="width: 100px;margin-left: -60px">
					<option value="0">请选择车辆类型</option>
					<option value="新能源">新能源</option>
					<option value="燃油">燃油</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block" style="margin-left: -40px;margin-top: 70px">
				<button class="layui-btn" lay-submit lay-filter="save" onclick="AddBusCarAjaxFrom()">立即提交</button>
				<button type="button" class="layui-btn layui-btn-primary" id="closeSome" onclick="closeDialog()">取消
				</button>
			</div>
		</div>
		<label style="margin-top: 30px;background-color: #00F7DE">如想关闭该弹窗，也可点击弹窗外部任意地方</label>
	</div>
	<%--添加按钮弹窗--%>
	<div id="updateBus" style="display: none;" align="center">
		<h2 style="color: #2D93CA;margin-top: 100px" align="center">修改公交车数据</h2>
		<div class="layui-form-item" style="margin-top: 20px">
			<label class="layui-form-label" style="width: 140px"><i class="asterisk">*</i>原公交车牌:</label>
			<div class="layui-inline">
				<input type="text" name="value" id="afterCardProvince" required
				       lay-verify="required" autocomplete="off"
				       class="layui-input" disabled="disabled">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 140px"><i class="asterisk">*</i>新公交车牌:</label>
			<div class="layui-inline">
				<input type="text" name="value" id="beforeCardProvince" required lay-verify="required"
				       placeholder="公交车车牌"
				       autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 140px"><i class="asterisk">*</i>原车辆保养人员:</label>
			<div class="layui-inline">
				<input type="text" name="value" id="afterBusDiver" required lay-verify="required" placeholder="公交车车牌"
				       autocomplete="off" class="layui-input" disabled="disabled">
			</div>
		</div>
		<div class="layui-form">
			<label class="layui-form-label" style="width: 140px"><i class="asterisk">*</i>请选择车辆保养人员:</label>
			<div class="layui-inline" style="width: 160px">
				<select name="driverState" id="beforeBusDiver" lay-filter="required">
					<option value="0"></option>
					<c:if test="${requestScope.selectAllDriver !=null}">
						<c:forEach items="${requestScope.selectAllDriver}" begin="0" step="1" var="item">
							<option value="${item.driverName}">${item.driverName}</option>
						</c:forEach>
					</c:if>

				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 140px"><i class="asterisk">*</i>原使用时间(年):</label>
			<div class="layui-inline">
				<input type="text" name="busAge" id="afterBusAge" required
				       lay-verify="required" placeholder="使用时间(年)" autocomplete="off" class="layui-input"
				       disabled="disabled">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 140px"><i class="asterisk">*</i>新使用时间(年):</label>
			<div class="layui-inline">
				<input type="text" name="busAge" id="beforeBusAge" required
				       lay-verify="required" placeholder="使用时间(年)" autocomplete="off" class="layui-input"
				       onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 140px"><i class="asterisk">*</i>原使用年限(年):</label>
			<div class="layui-inline">
				<input type="text" name="busYear" id="afterBusYear" required
				       lay-verify="required" placeholder="使用年限(年)" autocomplete="off" class="layui-input"
				       disabled="disabled">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 140px"><i class="asterisk">*</i>新使用年限(年):</label>
			<div class="layui-inline" style="width: 160px">
				<input type="text" name="busYear" id="beforeBusYear" required
				       lay-verify="required" placeholder="使用年限(年)" autocomplete="off" class="layui-input"
				       onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 140px"><i class="asterisk">*</i>原车辆状态:</label>
			<div class="layui-inline" style="width: 160px">
				<input type="text" name="busYear" id="afterBusState" required
				       lay-verify="required" placeholder="原车辆状态" autocomplete="off" class="layui-input"
				       disabled="disabled">
			</div>
		</div>
		<div class="layui-form">
			<label class="layui-form-label" style="width: 140px"><i class="asterisk">*</i>车辆新状态:</label>
			<div class="layui-inline" style="width: 160px">
				<select name="driverState" id="beforeBusState" lay-filter="required">
					<option value="0"></option>
					<c:if test="${requestScope.selectAllstate !=null}">
						<c:forEach items="${requestScope.selectAllstate}" begin="0" step="1" var="item">
							<c:if test="${item.stateName !='已删除' &&item.stateName!='报废停用'}">
								<option value="${item.stateName}" name="">${item.stateName}</option>
							</c:if>
						</c:forEach>
					</c:if>

				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block" style="margin-left: -40px;margin-top: 40px">
				<button class="layui-btn" lay-submit lay-filter="save" onclick="continueUpdate()">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary" id="closeSome1" onclick="closeDialog()">取消
				</button>
			</div>
		</div>

	</div>

	<%--	用来获取会话域中的地名值--%>
	<input type="hidden" id="sessionProvince" value="${sessionScope.province}">
	<%--	用来判断司机数据是否有数据--%>
	<input type="hidden" id="driverNull" value="${requestScope.selectAllDriver}">
	<input type="hidden" id="busId">

</body>
</html>

