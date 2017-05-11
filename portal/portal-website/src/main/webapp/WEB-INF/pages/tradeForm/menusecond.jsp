<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="sh_manage_tit">
	<ul >
		<li><a href="<%=basePath%>queryAndStat/showTradeDetail" class="" id="tradedetailms" >交易明细报表</a></li>
		<li><a href="<%=basePath%>queryAndStat/showTradeStat" id="tradestatms" >交易汇总报表</a></li>
		<li><a href="<%=basePath%>queryAndStat/showSetl" id="setlms" >结算单报表</a></li>
		<li><a href="<%=basePath%>queryAndStat/showBill" id="billms" >对账单</a></li>
	</ul>
</div>
