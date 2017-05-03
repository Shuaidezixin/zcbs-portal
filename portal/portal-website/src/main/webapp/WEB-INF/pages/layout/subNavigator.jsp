<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
	<div class="membernav clearfix">
		<ul class="w1200 minheight_body">
			<li><a href=""  >会员中心</a></li>
			<li><a href="<%=basePath%>insteadPay/apply.html" class="<%=request.getAttribute("item").toString().startsWith("10")?"active":""%>">商户中心</a></li>
			<li><a href="<%=basePath%>trade/query.html" class="<%=request.getAttribute("item").toString().startsWith("20")?"active":""%>">交易查询</a></li>
			<li><a href="">商户服务</a></li>
			<li><a href="">帮助</a></li>
		</ul>
	</div>