<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2020/1/4
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String laPath = application.getContextPath()+"/layui/";
	String path=request.getContextPath();
	String cssPath=request.getContextPath()+"/css/";
	String jsPath=request.getContextPath()+"/js/";
	String imgPath=request.getContextPath()+"/img/";
%>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
		#l-map{height:80%;width:100%;}
		#r-result{width:100%;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=Cyk0nfKrPb8pL456eO0OHXTGGiptZEbm">
	</script>
	<title>关键字输入提示词条</title>
	<script src=<%=jsPath + "jquery.js"%>></script>
	<link rel="stylesheet" href="<%=laPath%>css/layui.css" media="all">
	<script src="<%=laPath%>layui.js"></script>
</head>
<body>
<div id="l-map"></div>
<div id="r-result">请输入:<input type="text" id="suggestId" size="20" value="百度" style="width:150px;" /></div>
<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
<input id="userLog" type=hidden value="0">
<input id="userLat" type=hidden value="1">
</body>
</html>
<script type="text/javascript">
	var childNum = 5;
	function child(data) {
		childNum = data;
	}
	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}

	var map = new BMap.Map("l-map");
	map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。
	var pp;
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
			,"location" : map
		});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

		setPlace();
	});

	function setPlace(){
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			//将获取到的经纬度进行切割
			var userLng1 = pp.lng.toString();
			var userLat1 = pp.lat.toString();
			var userLng = userLng1.substring(0,8);
			var userLat = userLat1.substring(0,8);
			//将值赋值给吟唱标签，方便后面父类输入框获取
			$("#userLog").val(userLng);
			$("#userLat").val(userLat);
			var userName = $("#suggestId").val();
			if (childNum==='1') {
				parent.layui.$('#userHome').val(userName);
				parent.layui.$('#hid1').val(userLng);
				parent.layui.$('#hid2').val(userLat);

			}
			if (childNum==='2') {
				parent.layui.$('#userCompany').val(userName);
				parent.layui.$('#hid3').val(userLng);
				parent.layui.$('#hid4').val(userLat);

			}

			map.centerAndZoom(pp, 18);
			var marker = new BMap.Marker(pp);
			map.addOverlay(marker);    //添加标注

		}
		var local = new BMap.LocalSearch(map, { //智能搜索
			onSearchComplete: myFun
		});
		local.search(myValue);
	}
</script>

</html>
