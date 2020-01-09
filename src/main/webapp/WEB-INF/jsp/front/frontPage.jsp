<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/20
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cssPath = application.getContextPath()+"/css/" ;
	String jsPath =application.getContextPath()+"/js/" ;
	String path = request.getContextPath();
	String uicssPath = request.getContextPath()+"/layui/" ;
	String uiPath = request.getContextPath()+"/layui/";
%>

<html>

<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<title></title>
	<link rel="icon" href="./img/favicon.ico">

	<link rel="stylesheet" href=<%=cssPath +"frontPage.css"%> >
	<script src=<%=jsPath+"frontPage.js"%>></script>
	<link rel="stylesheet" href=<%=uicssPath +"css/layui.css"%> media="all">
	<script src="<%=jsPath+"jquery-3.4.1.js"%>"></script>
	<script src=<%=uiPath+"layui.js"%>></script>
	<script src=<%=jsPath+"advertisement.js"%>></script>

</head>

<body>
<div class="layui-carousel" id="test1" lay-filter="carofilter" style="font-size:larger">
	<div carousel-item>

		<c:if test="${null != requestScope.tableLists}">
			<c:forEach items="${requestScope.tableLists}" begin="0" step="1" var="i">
				<img src="<%=path%>${i.advertisingImgurl}" style="height:251px;width:234px" >
			</c:forEach>
		</c:if>
<%--		<div style="background-color:red">条目1</div>--%>
<%--		<div style="background-color:green">条目2</div>--%>
<%--		<div style="background-color:blue">条目3</div>--%>
<%--		<div style="background-color:mediumorchid">条目4</div>--%>
<%--		<div style="background-color:orange">条目5</div>--%>
<%--		<a href="https://www.baidu.com/"><img src="static/img/上传.PNG" style="height:251px;width:234px" /></a>--%>
	</div>
</div>

<div class="layui-input-inline" >
	<select id="SelectType" name="SelectType"  style="margin-left: 3%;">

		<c:if test="${null != requestScope.tableLists}">
			<option></option>
			<c:forEach items="${requestScope.tableLists}" begin="0" step="1" var="i">
				<option  value=""><%=path%>${i.advertisingImgurl}</option>
			</c:forEach>
		</c:if>
	</select>
</div>
<!-- 条目中可以是任意内容，如：<img src=""> -->
<script>
	layui.use('carousel', function () {
		var carousel = layui.carousel;

		//***************************建造实例
		var ins=carousel.render({
			elem: '#test1'
			, width: '500px'     //设置容器宽度
			, arrow: 'always'    //始终显示箭头，可选hover,none
			//,anim: 'updown'    //切换动画方式，可选fade,default
			, full: false        //全屏
			, autoplay: true     //自动切换
			, interval: 3000     //自动切换的时间间隔
			, index: 3           //初始化时item索引,默认时0
			, indicator:'inside' //指示器位置，可选outside,none
		});

		//**************************监听轮播切换事件
		carousel.on('change(carofilter)', function (obj) { //test1来源于对应HTML容器的 lay-filter="test1" 属性值
			console.log(obj.index);     //当前条目的索引
			console.log(obj.prevIndex); //上一个条目的索引
			console.log(obj.item);      //当前条目的元素对象
		});

		//****************************重置轮播
		//var ins = carousel.render(options);
		ins.reload({arrow:'hover'});//将arror从alway变成hover
	});
</script>


<%--飘--%>
<div id="mydiv">
<%--	<img src="../img/上传.PNG" alt="">--%>

<%--	<div id="my" style="margin-left: 100%">--%>
<%--		<button type="button" class="close" data-dismiss="modal"--%>
<%--		        aria-hidden="true" onclick="closeModal()">×</button>--%>
<%--	</div>--%>
	<p style="color:red;float:right;font-size:12px;cursor:pointer;" onClick="divNode.style.visibility = 'hidden'">关闭</p>

<c:if test="${null != requestScope.tableListss}">

	<c:forEach items="${requestScope.tableListss}" begin="0" step="1" var="i">
<%--		<option  value=""><%=path%>${i.advertisingImgurl}</option>--%>
		<img src="<%=path%>${i.advertisingImgurl}" alt="">
	</c:forEach>
</c:if>
</div>
</body>

</html>
