<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path=request.getContextPath();
	String cssPath=request.getContextPath()+"/css/";
	String jsPath=request.getContextPath()+"/js/";
	String imgPath=request.getContextPath()+"/img/";
	String laPath = application.getContextPath()+"/layui/";
%>

<html>

    <head>
	    <title>缴费成功后的购买详情界面</title>
	    <script src=<%=jsPath + "jquery.js"%>></script>

    </head>
    
    <body>
        <input type="hidden" id="hidUrl" value="<%=request.getContextPath()%>/successApy">
        <h1 style="color: green;">购买成功</h1>
        <table>
        	<tr>
        		<td>
        			订单编号: ${out_trade_no }
        		</td>
        	</tr>
        		<td>
        			支付宝交易号: ${trade_no }
        		</td>
        	<tr>
        	</tr>
        		<td>
        			实付金额: ${total_amount }
        		</td>
        	<tr>
        	</tr>
        		<td>
        			购买产品：余额充值
        		</td>
        	</tr>
        </table>


        <script type="text/javascript">
	        function webpageClose(){

		        window.location.href =  $("#hidUrl").val();
	        }
	        setTimeout( webpageClose,4000)//10s钟后关闭
        </script>


    </body>
    
</html>


