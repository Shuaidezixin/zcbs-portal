<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
				<li><a href="<%=basePath%>trade/showQueryTrade" >委托机构交易查询</a></li>
				<li><a href="javascript:void(0);" class="active">委托机构交易明细查询</a></li>
			</ul>
			<span class="col6 fr login_time" id="lastLogin"></span>
		</div>

		<div class="flow_item">
			<div class="bill_box clearfix">
				<form id="queryTradeForm" action="<%=basePath%>trade/selTxnsInfo" method="post">
					<input type="hidden" id="pageIndex" name="page" value="0" /> 
					<input type="hidden" id="pageRows" name="rows" value="10" /> 
					<input type="hidden" id="merid" name="merid" value="200000000000610" /> 
					<input type="hidden" name="stime" id="stime" value="" /> 
					<input type="hidden" name="etime" id="etime" value="" />
					<input type="hidden" name="scommitime" id="scommitime" value="" />
					<input type="hidden" name="ecommitime" id="ecommitime" value="" />

					<div class="billtime clearfix">
						<table width="100%" >
							<tr>
								<td align="left">
									<span class="mlr10 fl">
										交易类型： 
										<select id="busicode" class="selectStatus" style="width: 140px" name="busicode">
											<option value="">--请选择交易类型--</option>
											<option value="11000001">实时代收</option>
											<option value="11000002">实时代付</option>
											<option value="11000003">批量代收</option>
											<option value="11000004">批量代付</option>
										</select>
									</span>
								</td>
								<td align="center">
									<span class="mlr10 fl">
										订单号: <input id="orderid" class="input_text2" name="orderid" value placeholder="" />
									</span>
								</td>
								<td align="right">
									<span class="mlr10 fl">
										批次号: <input id="batchno" class="input_text2" name="batchno" value placeholder="" />
									</span>
								</td>
							</tr>
							<tr>
								<td align="left">
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
								<td align="center">
									<!--日历begin-->
									<span class="mlr10 fl">渠道交易日期：</span>
									<div class="time_start fl">
										<div class="set_time">
											<input type="text" class="input_text show_time" id="time3" value="2015-04-13" readonly="readonly" />
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
											<input type="text" class="input_text show_time" id="time4" value="2017-04-13" readonly="readonly" />
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
					<div class="deal_item"> 交易结果 </div>
					<table width="100%" class="order_detail">
						<thead>
							<tr class="order_field">
								<th width="9%">订单号</th>
								<th width="9%">交易流水号</th>
								<th width="9%">批次序列号</th>
								<th width="9%">文件名</th>
								<th width="6%">交易日期</th>
								<th width="6%">交易时间</th>
								<th width="8%">渠道处理时间</th>
								<th width="8%">清算日期</th>
								<th width="4%">开户省</th>
								<th width="4%">市</th>
								<th width="5%">开户银行</th>
								<th width="9%">账号</th>
								<th width="9%">户名</th>
								<th width="5%">处理状态</th>
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

				<div class="deal_item clearfix">
					<div class="deal_item"> 汇总信息 </div>
					<table width="100%" class="order_detail">
						<thead>
							<tr class="order_field">
								<th width="10%">总笔数</th>
								<th width="12%">总金额（元）</th>
								<th width="10%">成功笔数</th>
								<th width="12%">成功金额（元）</th>
								<th width="12%">手续金额（元）</th>
								<th width="10%">失败笔数</th>
								<th width="12%">失败金额（元）</th>
								<th width="10%">其他笔数</th>
								<th width="12%">其他金额（元）</th>
							</tr>
						</thead>
						<tbody id="tradeStat">
						</tbody>
					</table>
				</div>

			</div>
		</div>
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
			$('#time3').val(endDateValue);
			$('#time4').val(endDateValue);
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
			var selBusitype = $("#busicode option:selected").val();
			if (selBusitype == "") {
				$.MessageBox("请选择交易类型！");
				return false;
			}

			var beginDate = $('#time').val();
			var endDate = $('#time2').val();
			if (beginDate > endDate) {
				$.MessageBox("交易日期的开始时间不能大于结束时间");
				return false;
			}
			var beginDateChn = $('#time3').val();
			var endDateChn = $('#time4').val();
			if (beginDateChn > endDateChn) {
				$.MessageBox("渠道交易日期的开始时间不能大于结束时间");
				return false;
			}
			$("#stime").val(beginDate.replace(/-/g, ""));
			$("#etime").val(endDate.replace(/-/g, ""));
			$("#scommitime").val(beginDateChn.replace(/-/g, ""));
			$("#ecommitime").val(endDateChn.replace(/-/g, ""));


			$('#queryTradeForm')
					.ajaxSubmit(
							{
								async : false,
								error : function(XMLHttpRequest, textStatus,
										errorThrown) {                                                                                                                                                                                            
									alert(XMLHttpRequest.status);
								},
								success : function(data) {
									if (data.total == "0") {
										if (data.rows.length == 0) {
											$('#tradeContents').html('');
											document.getElementById('pager').innerHTML = '';
											$.MessageBox("您所查询的日期，无交易结果！");
											return;
										} else {
											$.MessageBox("查询错误！");
											return;
										}
									}
									var dataStr = data.rows;
									var output = '';
									for (var i = 0, l = dataStr.length; i < l; i++) {
										output = output + '<tr height="36" class="bor_bottom" >';
										output = output + '<td width="9%">' + dataStr[i]['ORDERID'] + '</td>';
										output = output + '<td width="9%">' + dataStr[i]['TXNSEQNO'] + '</td>';
										output = output + '<td width="9%">' + dataStr[i]['BATCHNO'] + '</td>';
										output = output + '<td width="9%">' + dataStr[i]['FILENAME'] + '</td>';
										output = output + '<td width="6%">' + changeDate(dataStr[i]['TXNDATE']) + '</td>';
										output = output + '<td width="6%">' + changeTime(dataStr[i]['TXNTIME']) + '</td>';
										output = output + '<td width="8%">' + changeDateTime(dataStr[i]['COMMITIME']) + '</td>';
										output = output + '<td width="8%">' + changeDate(dataStr[i]['SETLDATE']) + '</td>';
										output = output + '<td width="4%">' + dataStr[i]['BANKP'] + '</td>';
										output = output + '<td width="4%">' + dataStr[i]['BANKC'] + '</td>';
										output = output + '<td width="5%">' + dataStr[i]['BANKNODE'] + '</td>';
										output = output + '<td width="9%">' + dataStr[i]['ACCOUNTNO'] + '</td>';
										output = output + '<td width="9%">' + dataStr[i]['ACCOUNTNAME'] + '</td>';
										output = output + '<td width="5%">' + dataStr[i]['STATUS'] + '</td>';
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
									getStat();
									
								}
							});
		}
		
		function getStat(){
			$.ajax({
				url:"<%=basePath%>trade/selTxnsStatPortal",
				type:"post",
				data:{
					merid:$("#merid").val(),
					busicode:$("#busicode").val(),
					orderid:$("#orderid").val(),
					batchno:$("#batchno").val(),
					stime:$("#stime").val(),
					etime:$("#etime").val(),
					scommitime:$("#scommitime").val(),
					ecommitime:$("#ecommitime").val()
				},
				success:function(data){
					if (typeof(data.RET)=="undefined") {
						//总笔数NUMS，总金额AMOUNTS，成功笔数ACCNUMS，成功金额ACCAMTS，手续金额FEES，失败笔数FNUMS，失败金额FAMTS，其他笔数OTHERNUMS，其他金额OTHERAMTS
						var output = '';
						output = output + '<tr height="36" class="bor_bottom" >';
						output = output + '<td width="10%">' + data.NUMS + '</td>';
						output = output + '<td width="12%">' + data.AMOUNTS/100 + '</td>';
						output = output + '<td width="10%">' + data.ACCNUMS + '</td>';
						output = output + '<td width="12%">' + data.ACCAMTS/100 + '</td>';
						output = output + '<td width="12%">' + data.FEES/100 + '</td>';
						output = output + '<td width="10%">' + data.FNUMS + '</td>';
						output = output + '<td width="12%">' + data.FAMTS/100 + '</td>';
						output = output + '<td width="10%">' + data.OTHERNUMS + '</td>';
						output = output + '<td width="12%">' + data.OTHERAMTS/100 + '</td>';
						output = output + '</tr>';
						$('#tradeStat').html(output);
					}else {
						$.MessageBox(data.INFO);
						return;
					}
					console.log(JSON.stringify(data));
				},
				error:function(){
					console.log("汇总信息查询失败！");
				}
			})
		};
		
		function reSize(){
			$('#busicode').val('');
			$('#orderid').val('');
			$('#batchno').val('');
			initTime();
		}
		// 格式化日期时间
		function changeDateTime(value){
			var dateString = value;
			if(dateString==null){
				return "";
			}else{
				year=dateString.substring(0,4);//0123
				month=dateString.substring(4,6);//45
				day=dateString.substring(6,8);//67
				hour=dateString.substring(8,10);//89
				minte=dateString.substring(10,12);//10 11
				s=dateString.substring(12,14);// 11 12
				return year+"-"+month+"-"+day+" " + hour +":"+minte+":"+s;
			}
		}
		// 格式化日期
		function changeDate(value){
			var dateString = value;
			if(dateString==null){
				return "";
			}else{
				year=dateString.substring(0,4);//0123
				month=dateString.substring(4,6);//45
				day=dateString.substring(6,8);//67
				return year+"-"+month+"-"+day;
			}
		}
		// 格式化时间
		function changeTime(value){
			var dateString = value;
			if(dateString==null){
				return "";
			}else{
				hour=dateString.substring(0,2);
				minte=dateString.substring(2,4);
				s=dateString.substring(4,6);
				return hour +":"+minte+":"+s;
			}
		}
	</script>
</body>
</html>
