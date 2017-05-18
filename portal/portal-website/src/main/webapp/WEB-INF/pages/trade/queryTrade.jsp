<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
	<jsp:include page="../../../common/header.jsp"></jsp:include>
	<!--header_end-->
	<!--会员中心nav_begin-->
	<jsp:include page="../../../common/menu.jsp"></jsp:include>


	<!--content_begin-->
	<div class="w1200 mtb20 clearfix minheight_body">

		<div class="sh_manage_tit">
			<ul>
				<li><a href="javascript:void(0);" class="active">委托机构交易查询</a></li>
				<li><a href="<%=basePath%>trade/showQueryTradeDetail">委托机构交易明细查询</a></li>
			</ul>
			<span class="col6 fr login_time" id="lastLogin"></span>
		</div>

		<!--电子对账单begin-->
		<div class="flow_item">
			<div class="bill_box clearfix">
				<form id="queryTradeForm" action="<%=basePath%>trade/tradeQuery" method="post">
					<input type="hidden" id="pageIndex" name="page" value="0" /> 
					<input type="hidden" id="pageRows" name="rows" value="10" /> 
					<input type="hidden" name="stime" id="stime" value="" /> 
					<input type="hidden" name="etime" id="etime" value="" />

					<div class="billtime clearfix">
						<table width="100%" >
							<tr>
								<td align="left">
									<span class="mlr10 fl">
										交易类型：
										<select id="busicode" class="selectStatus" onchange="busicodeChange()" style="width: 140px" name="busicode">
											<option value="">--请选择交易类型--</option>
											<option value="11000001" <c:if test="${busicode eq 11000001}"> selected="selected"</c:if>>实时代收</option>
											<option value="11000002" <c:if test="${busicode eq 11000002}"> selected="selected"</c:if>>实时代付</option>
											<option value="11000003" <c:if test="${busicode eq 11000003}"> selected="selected"</c:if>>批量代收</option>
											<option value="11000004" <c:if test="${busicode eq 11000004}"> selected="selected"</c:if>>批量代付</option>
										</select>
									</span>
								</td>
								<td align="center">
									<!--日历begin-->
									<span class="mlr10 fl">交易日期：</span>
									<div class="time_start fl">
										<div class="set_time">
											<input type="text" class="input_text show_time" id="time" value="2015-04-13" readonly="readonly" />
											<div class="date_list hide">
												<div class="date_head">
													<div class="left_btn">
														<a href="javascript:;" class="prev_year" title="上一年">上一年</a> <a href="javascript:;" class="prev_month" title="上一月">上一月</a>
													</div>
													<div class="show_date">
														<span class="show_year">2012</span>年 <span class="show_month">4</span>月
													</div>
													<div class="right_btn">
														<a href="javascript:;" class="next_month" title="下一月">下一月</a> <a href="javascript:;" class="next_year" title="下一年">下一年</a>
													</div>
												</div>
												<ul class="data_day">
													<li>日</li>
													<li>一</li>
													<li>二</li>
													<li>三</li>
													<li>四</li>
													<li>五</li>
													<li>六</li>
												</ul>
												<div class="data_time">
													<table class="date_body" width="100%">
			
			
													</table>
												</div>
											</div>
										</div>
									</div>
									<!--日历end-->
									<span class="fl mlr10"> - </span>
									<!--日历begin-->
									<div class="time_start">
										<div class="set_time">
											<input type="text" class="input_text show_time" id="time2" value="2017-04-13" readonly="readonly" />
											<div class="date_list hide">
												<div class="date_head">
													<div class="left_btn">
														<a href="javascript:;" class="prev_year" title="上一年">上一年</a> <a href="javascript:;" class="prev_month" title="上一月">上一月</a>
													</div>
													<div class="show_date">
														<span class="show_year">2012</span>年 <span class="show_month">4</span>月
													</div>
													<div class="right_btn">
														<a href="javascript:;" class="next_month" title="下一月">下一月</a> <a href="javascript:;" class="next_year" title="下一年">下一年</a>
													</div>
												</div>
												<ul class="data_day">
													<li>日</li>
													<li>一</li>
													<li>二</li>
													<li>三</li>
													<li>四</li>
													<li>五</li>
													<li>六</li>
												</ul>
												<div class="data_time">
													<table class="date_body" width="100%">
			
			
													</table>
												</div>
											</div>
										</div>
									</div>
									<!--日历end-->
								</td>
								<td align="right">
									<span class="mlr10 fl">
										处理状态： 
										<select id="status" class="selectStatus" style="width: 140px" name="status">
											<option value="">--请选择处理状态--</option>
											<option value="00">交易成功</option>
											<option value="03">交易失败</option>
											<option value="04">交易超时</option>
										</select>
									</span>
								</td>
							</tr>
							<tr>
								<td align="left">
									<span class="mlr10 fl">
										订单号: <input id="orderid" class="input_text2" name="orderid" value placeholder="" />
									</span>
								</td>
								<td align="center">
									<span class="mlr10 fl">
										原订单号: <input id="orderidog" class="input_text2" name="orderidog" value placeholder="" />
									</span>
								</td>
								<td align="right">
									<span class="fl mlr10">
										<input class="inquire" type="button" id="btnquery" onclick="queryTrade()" value="查询" />
									</span> 
									<span class="fl mlr10">
										<input class="inquire" type="button" id="btnclear" onclick="reSize()" value="清除" />
									</span>
								</td>
							</tr>
						</table>
					</div>
				</form>
				<div class="deal_item clearfix">
					<table width="100%" class="order_detail">
						<thead>
							<tr class="order_field">
								<th width="12%">订单号</th>
								<th width="12%">原订单号</th>
								<th width="12%">提交时间</th>
								<th width="10%">交易类型</th>
								<th width="10%">业务类型</th>
								<th width="9%">金额(元)</th>
								<th width="12%">处理状态</th>
								<th width="12%">渠道处理时间</th>
								<th width="11%">渠道处理状态</th>
<!-- 								<th width="10%">操作</th> -->
							</tr>
						</thead>
						<tbody id="tradeContents">
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
		<!--电子对账单end-->
		<!--我的账户/账户管理/账务明细begin-->
	</div>
	<!--content_end-->
	<!--footer_begin-->
	<jsp:include page="../../../common/foot.jsp"></jsp:include>
	<!--footer_end-->
	<script type="text/javascript">
		$(window).load(function() {
			$("#member,#merch,#trade,#service,#helpe").removeClass();
			$('#merch').addClass('active');
			initTime();
			//getData();
		});

		function search() {
			turnTo = 1;
			getData();
		}

		function initTime() {
			var endDateValue = getNowDate();
			$('#time').val(endDateValue);
			$('#time2').val(endDateValue);
		}
		
		function getData() {
			var beginDate = $('#time').val();
			var endDate = $('#time2').val();
			if (beginDate > endDate) {
				$.MessageBox("开始时间不能大于结束时间");
				return false;
			}
			$('#pageIndex').val(turnTo);
			queryTradeController();
		}
		function queryTrade() {
			$('#pageIndex').val(1);
			$("#month,#month3,#month6,#year").removeClass();
			queryTradeController();
		}
		function queryTradeController() {
			var selBusitype = $("#busiCode option:selected").val();
			if (selBusitype == "") {
				$.MessageBox("请选择交易类型！");
				return false;
			}

			var beginDate = $('#time').val();
			var endDate = $('#time2').val();
			if (beginDate > endDate) {
				$.MessageBox("开始时间不能大于结束时间");
				return false;
			}

			$("#stime").val(beginDate.replace(/-/g, ""));
			$("#etime").val(endDate.replace(/-/g, ""));

			var selBusitype = $("#status option:selected").val();
			if (selBusitype == "") {
				$.MessageBox("请选择处理状态！");
				return false;
			}

			$('#queryTradeForm')
					.ajaxSubmit(
							{
								async : false,
								error : function(XMLHttpRequest, textStatus,
										errorThrown) {
									alert(XMLHttpRequest.status);
								},
								success : function(data) {
									//data= JSON.parse(data);
									if (data.total == "0") {
										if (data.rows.length == 0) {
											$('#tradeContents').html('');
											document.getElementById('pager').innerHTML = '';
											$.MessageBox("您所查询的日期，无交易记录！");
											return;
										} else {
											$.MessageBox("查询错误！");
											return;
										}
									}
									var dataStr = data.rows;
									var output = '';
									for (var i = 0, l = dataStr.length; i < l; i++) {
										output = output
												+ '<tr height="36" class="bor_bottom" >';
										output = output + '<td width="11%">'
												+ (dataStr[i]['ORDERID']==null?"":dataStr[i]['ORDERID'])
												+ '</td>';
										output = output + '<td width="11%">'
												+ (dataStr[i]['ORDERID_OG']==null?"":dataStr[i]['ORDERID_OG'])
												+ '</td>';
										output = output
												+ '<td width="11%">'
												+ (dataStr[i]['TXNTIME']==null?"":changeDateTime(dataStr[i]['TXNTIME']))
												+ '</td>';
										output = output + '<td width="8%">'
												+ (dataStr[i]['PAYNAME']==null?"":dataStr[i]['PAYNAME'])
												+ '</td>';
										output = output + '<td width="8%">'
												+ (dataStr[i]['BUSINAME']==null?"":dataStr[i]['BUSINAME'])
												+ '</td>';
										output = output + '<td width="8%">'
												+ (dataStr[i]['TXNAMT']==null?"":(dataStr[i]['TXNAMT']/100))
												+ '</td>';
										output = output + '<td width="11%">'
												+ (dataStr[i]['STATUS']==null?"":dataStr[i]['STATUS'])
												+ '</td>';
										output = output
												+ '<td width="11%">'
												+ (dataStr[i]['COMMITIME']==null?"":changeDateTime(dataStr[i]['COMMITIME']))
												+ '</td>';
										output = output + '<td width="11%">'
												+ (dataStr[i]['RESPMSG']==null?"":dataStr[i]['RESPMSG'])
												+ '</td>';
										/* output = output
												+ '<td width="10%">'
												+ '<a href="#" onclick="showDetail('
												+ dataStr[i]['TID']
												+ ')" class="refund_sq">明细</a>'
												+ '</td>'; */
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
		
		function busicodeChange(){
			var busicode = $('#busicode').val();
			if (busicode == '11000003' || busicode == '11000004') {
				// 跳到批量页面
				window.location.href="<%=basePath%>trade/showQueryBatchTrade?busicode="+busicode; 
			} 
		};
		
		function reSize(){
			$('#busicode').val('');
			initTime();
			$('#status').val('');
			$('#orderid').val('');
			$('#orderidog').val('');
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
