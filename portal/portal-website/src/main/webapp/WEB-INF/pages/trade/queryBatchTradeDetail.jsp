<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>宜赋通门户网站</title>
</head>
<body ng-app="app">
	<jsp:include page="../../../common/head.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>js/tab.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/calendar.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.form.js"></script>
	<%-- 	<script type="text/javascript" src="<%=basePath%>js/angular.min.js"></script> --%>
	<script type="text/javascript" src="<%=basePath%>js/pages.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/timeUtil.js"></script>
	<!--header_begin-->
<%-- 	<jsp:include page="../../../common/header.jsp"></jsp:include> --%>
	<!--header_end-->
	<!--会员中心nav_begin-->
<%-- 	<jsp:include page="../../../common/menu.jsp"></jsp:include> --%>


	<!--content_begin-->
	<div class="w1200 mtb20 clearfix minheight_body">

		<%-- <div class="sh_manage_tit">
			<ul>
				<li><a href="javascript:void(0);" class="active">委托机构交易查询</a></li>
				<li><a href="<%=basePath%>trade/showQueryTradeDetail">委托机构交易明细查询</a></li>
			</ul>
			<span class="col6 fr login_time" id="lastLogin"></span>
		</div> --%>

		<div class="flow_item">
			<div class="bill_box clearfix">
				<form id="queryTradeForm" action="<%=basePath%>trade/tradeQueryForBatch" method="post">
					<input type="hidden" id="pageIndex" name="page" value="0" /> 
					<input type="hidden" id="pageRows" name="rows" value="10" /> 
					<input type="hidden" id="batchno" name="batchno" value="${batchno }" /> 
				</form>
				<div class="deal_item clearfix">
					<div class="deal_item"> 
						<table width="100%">
							<tr>
								<td align="left">批次号为【${batchno}】的交易明细：</td>
								<td align="right"><input class="inquire" type="button" id="btnquery" onclick="window.close();" value="关闭" /></td>
							</tr>
						</table>
					</div>
					<table width="100%" class="order_detail">
						<thead>
							<tr class="order_field">
								<th width="13%">订单号</th>
								<th width="11%">提交时间</th>
								<th width="7%">交易金额(元)</th>
								<th width="11%">处理时间</th>
								<th width="12%">交易应答</th>
								<th width="11%">应答时间</th>
								<th width="12%">应答信息</th>
								<th width="13%">渠道流水号</th>
								<th width="10%">备注</th>
							</tr>
						</thead>
						<tbody id="tradeContents">
							<c:forEach items="${batchDetail.rows}" var="row" varStatus="rowStatus">
								<tr height="36" class="bor_bottom" >
									<td width="13%">${row.ORDERID eq "null" ? "" : row.ORDERID}</td>
									<td width="11%">
										<c:choose>
											<c:when test="${row.COMMITIME eq 'null'}"></c:when>
											<c:otherwise>
												${fn:substring(row.COMMITIME,0,4)}-${fn:substring(row.COMMITIME,4,6)}-${fn:substring(row.COMMITIME,6,8)}
												${fn:substring(row.COMMITIME,8,10)}:${fn:substring(row.COMMITIME,10,12)}:${fn:substring(row.COMMITIME,12,14)}
											</c:otherwise>
										</c:choose>
									</td>
									<td width="7%">${row.TXNAMT eq null ? "" : row.TXNAMT}</td>
									<td width="11%">
										<c:choose>
											<c:when test="${row.TXNTIME eq 'null'}"></c:when>
											<c:otherwise>
												${fn:substring(row.TXNTIME,0,4)}-${fn:substring(row.TXNTIME,4,6)}-${fn:substring(row.TXNTIME,6,8)}
												${fn:substring(row.TXNTIME,8,10)}:${fn:substring(row.TXNTIME,10,12)}:${fn:substring(row.TXNTIME,12,14)}
											</c:otherwise>
										</c:choose>
									</td>
									<td width="12%">${row.RESPCODE eq "null" ? "" : row.RESPCODE}</td>
									<td width="11%">
										<c:choose>
											<c:when test="${row.RESPTIME eq 'null'}"></c:when>
											<c:otherwise>
												${fn:substring(row.RESPTIME,0,4)}-${fn:substring(row.RESPTIME,4,6)}-${fn:substring(row.RESPTIME,6,8)}
												${fn:substring(row.RESPTIME,8,10)}:${fn:substring(row.RESPTIME,10,12)}:${fn:substring(row.RESPTIME,12,14)}
											</c:otherwise>
										</c:choose>
									</td>
									<td width="12%">${row.RESPMSG eq "null" ? "" : row.RESPMSG}</td>
									<td width="13%">${row.RELATETRADETXN eq "null" ? "" : row.RELATETRADETXN}</td>
									<td width="10%">${row.NOTES eq "null" ? "" : row.NOTES}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!--分页-->
					<p>
						<div id="pager" class="pages"></div>
					</p>
					<!--分页end-->
				</div>

			</div>
		</div>
	</div>
	<!--content_end-->
	<!--footer_begin-->
<%-- 	<jsp:include page="../../../common/foot.jsp"></jsp:include> --%>
	<!--footer_end-->
	<script type="text/javascript">
		$(window).load(function() {
			$("#member,#merch,#trade,#service,#helpe").removeClass();
			$('#merch').addClass('active');
			console.log('${batchDetail.total}');
			var total = '${batchDetail.total}';
			var pageCount = total/$("#pageRows").val();
			if (total % $("#pageRows").val() !=0) {
				pageCount++;
			}
			initPage(pageCount, $("#pageIndex").val(),
					"pager", 1);
		});
		
		function getData() {
			$('#pageIndex').val(turnTo);
			queryTradeController();
		}
		
		function queryTrade() {
			$('#pageIndex').val(1);
			$("#month,#month3,#month6,#year").removeClass();
			queryTradeController();
		}
		function queryTradeController() {
			$('#queryTradeForm')
					.ajaxSubmit(
							{
								async : false,
								error : function(XMLHttpRequest, textStatus,
										errorThrown) {
									alert(XMLHttpRequest.status);
								},
								success : function(data) {
									var dataStr = data.rows;
									var output = '';
									for (var i = 0, l = dataStr.length; i < l; i++) {
										output = output + '<tr height="36" class="bor_bottom" >';
										output = output + '<td width="13%">' + (dataStr[i]['ORDERID']==null?"":dataStr[i]['ORDERID']) + '</td>';
										output = output + '<td width="11%">' + (dataStr[i]['COMMITIME']==null?"":changeDateTime(dataStr[i]['COMMITIME'])) + '</td>';
										output = output + '<td width="7%">' + (dataStr[i]['TXNAMT']==null?"":dataStr[i]['TXNAMT']) + '</td>';
										output = output + '<td width="11%">' + (dataStr[i]['TXNTIME']==null?"":changeDateTime(dataStr[i]['TXNTIME'])) + '</td>';
										output = output + '<td width="12%">' + (dataStr[i]['RESPCODE']==null?"":dataStr[i]['RESPCODE']) + '</td>';
										output = output + '<td width="11%">' + changeDateTime(dataStr[i]['RESPTIME']) + '</td>';
										output = output + '<td width="12%">' + (dataStr[i]['RESPMSG']==null?"":dataStr[i]['RESPMSG']) + '</td>';
										output = output + '<td width="13%">' + (dataStr[i]['RELATETRADETXN']==null?"":dataStr[i]['RELATETRADETXN']) + '</td>';
										output = output + '<td width="10%">' + (dataStr[i]['NOTES']==null?"":dataStr[i]['NOTES']) + '</td>';
										output = output + '</tr>';
									}
									$('#tradeContents').html(output);
									var pageCount = data.total
											/ $("#pageRows").val();
									if (data.total % $("#pageRows").val() !=0) {
										pageCount++;
									}
									initPage(pageCount, $("#pageIndex").val(),
											"pager", 1);
								}
							});
		}
		
		function goBack(){
			window.history.go(-1);
		}
		// 格式化日期时间
		function changeDateTime(value) {
			var dateString = value;
			if (dateString == null) {
				return "";
			} else {
				year = dateString.substring(0, 4);//0123
				month = dateString.substring(4, 6);//45
				day = dateString.substring(6, 8);//67
				hour = dateString.substring(8, 10);//89
				minte = dateString.substring(10, 12);//10 11
				s = dateString.substring(12, 14);// 11 12
				return year + "-" + month + "-" + day + " " + hour + ":"
						+ minte + ":" + s;
			}
		}
	</script>
</body>
</html>
