<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head> 
	<title>订单查询</title>
	<style> 
		.divright{ float:right} 
	</style> 
</head>
<body ng-app="app">
	<jsp:include page="../../../common/head.jsp"></jsp:include> 
	<script type="text/javascript" src="<%=basePath%>js/tab.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/calendar.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/pages.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/timeUtil.js"></script>
	<!--header_begin-->
	<jsp:include page="../../../common/header.jsp"></jsp:include>
	<!--header_end-->
	<!--会员中心nav_begin-->
	<jsp:include page="../../../common/menu.jsp"></jsp:include>
	
	<!--content_begin-->
	<div class="w1200 mtb20 clearfix minheight_body">
        <!--电子对账单begin-->
	        <div class="flow_item">
				<div class="bill_box clearfix">
					<input type="hidden" id="fileallName" name="page" value="0"/>
					<form id="queryTradeForm" action="/order/orderForBatchAndSingle" method="post">
					<input type="hidden" id="pageIndex" name="page" value="0"/> 
					<input type="hidden" id="pageRows" name="rows" value="10"/> 
					<input type="hidden" id="stime" name="stime" /> 
					<input type="hidden" id="etime" name="etime" />
					<div class="billtime clearfix"> 
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
						<span class="mlr10 fl">交易日期：</span>
						<div class="time_start fl">  
	                        <div class="set_time">
	                            <input type="text" class="input_text show_time" id="time" name="" value="2015-04-13" readonly="readonly"/>
	                            <div class="date_list hide" >
	                                <div class="date_head">
	                                    <div class="left_btn">
	                                        <a href="javascript:;" class="prev_year" title="上一年">上一年</a>
	                                        <a href="javascript:;" class="prev_month" title="上一月">上一月</a>
	                                    </div>
	                                    <div class="show_date">
	                                         <span class="show_year">2012</span>年 <span class="show_month">4</span>月
	                                    </div>
	                                    <div class="right_btn">
	                                        <a href="javascript:;" class="next_month" title="下一月">下一月</a>
	                                        <a href="javascript:;" class="next_year" title="下一年">下一年</a>
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
	                          <input type="text" class="input_text show_time" id="time2" name="" value="2017-04-13" readonly="readonly"/>
	                            <div class="date_list hide" >
									<div class="date_head">
									  <div class="left_btn">
									      <a href="javascript:;" class="prev_year" title="上一年">上一年</a>
									      <a href="javascript:;" class="prev_month" title="上一月">上一月</a>
									  </div>
									  <div class="show_date">
									       <span class="show_year">2012</span>年 <span class="show_month">4</span>月
									  </div>
									  <div class="right_btn">
									      <a href="javascript:;" class="next_month" title="下一月">下一月</a>
									      <a href="javascript:;" class="next_year" title="下一年">下一年</a>
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
					</div>
					<div class="billtime clearfix"> 
						<span class="mlr10 fl">
							订单号: <input id="orderid" class="input_text2" name="orderid" value placeholder="" />
						</span>
						<span class="mlr10 fl">
							户名: <input id="name" class="input_text2" name="name" value placeholder="" />
						</span>
						<span class="mlr10 fl">
							帐号: <input id="account" class="input_text2" name="account" value placeholder="" />
						</span>
						<span class="fl mlr10"><input class="inquire" type="button" onclick="queryTrade()" value="查询"/></span>
	                    <span class="fl mlr10"><input class="inquire" type="button" onclick="resize()" value="清除"/></span>
					</div>
					</form>
					
					<div class="deal_item clearfix" id="dataTable">
		        	</div>
					<div class="deal_item clearfix" id="dataTable">
						<table width="100%" class="order_detail" >
							<thead>
		                        <tr class="order_field">
		                        	<th>订单请求日期</th>
		                        	<th>处理时间</th>
		                        	<th>订单号</th>
		                        	<th>交易金额</th>
		                        	<th>交易应答</th>
		                        	<th>应答信息</th>
		                        	<th>渠道应答时间</th>
		                        	<th>渠道流水号</th>
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
	<jsp:include page="/common/foot.jsp"></jsp:include>
	<!--footer_end-->
	<script type="text/javascript">
	$(window).load(function(){
		$("#member,#merch,#trade,#service,#user").removeClass();
		$('#member').addClass('active');
		$('#time').val(new Date().Format('yyyy-MM-dd'));
		$('#time2').val(new Date().Format('yyyy-MM-dd'));
	});
	
	function search(){
		turnTo = 1;
		getData();
	}
	
	
	function getData(){
		$('#pageIndex').val(turnTo);
		queryTradeController();
	}
	function queryTrade() {
		$('#pageIndex').val(1);
		queryTradeController();
	}
	
	function prepareParm(){
		var beginDate;
		var endDate;
		beginDate = $('#time').val().replace("-","").replace("-","");
		endDate = $('#time2').val().replace("-","").replace("-","");;
		if(beginDate > endDate ){
			$.MessageBox("开始时间不能大于结束时间");
			return false;
		}
		$('#stime').val(beginDate);
		$('#etime').val(endDate);
		
	}
	function queryTradeController(){
		var selBusitype = $("#busicode option:selected").val();
		if (selBusitype == "") {
			$.MessageBox("请选择交易类型！");
			return false;
		}
		prepareParm();
		$('#queryTradeForm').ajaxSubmit({
			async:false,
			error: function(XMLHttpRequest, textStatus, errorThrown) {
		    	 alert(XMLHttpRequest.status);
		    	   },
			success : function(data){
				if(data.total==0 && data.rows.length==1){
					$.MessageBox(data.rows[0].INFO);
					return;
				}

		    	if (data.total==0 && data.rows.length==0) {
		    		$('#tradeContents').html('');
		    		document.getElementById('pager').innerHTML='';
		    		$.MessageBox("您所查询的日期，无交易记录！");
					return ;
				}
		    	var dataStr=data.rows;
		    	var output="";
				for(var i=0,l=dataStr.length;i<l;i++){ 
					output = output + '<tr height="36" class="bor_bottom" >'; 
					output = output + '<td >'+(dataStr[i]['COMMITIME']==null?"":dataStr[i]['COMMITIME'])+'</td>';
					output = output + '<td >'+(dataStr[i]['TXNTIME']==null?"":dataStr[i]['TXNTIME'])+'</td>';
					output = output + '<td >'+(dataStr[i]['ORDERID']==null?"":dataStr[i]['ORDERID'])+'</td>';
					output = output + '<td >'+(dataStr[i]['TXNAMT']==null?"":dataStr[i]['TXNAMT'])+'</td>';
					output = output + '<td >'+(dataStr[i]['RESPCODE']==null?"":dataStr[i]['RESPCODE'])+'</td>';
					output = output + '<td >'+(dataStr[i]['RESPMSG']==null?"":dataStr[i]['RESPMSG'])+'</td>';
					output = output + '<td >'+(dataStr[i]['RESPTIME']==null?"":dataStr[i]['RESPTIME'])+'</td>';
					output = output + '<td >'+(dataStr[i]['RELATETRADETXN']==null?"":dataStr[i]['RELATETRADETXN'])+'</td>';
					output = output + '</tr>';
				} 
				$('#tradeContents').html(output);
				var pagecontent;
				if(data.total%$("#pageRows").val()>0){
					pagecontent=data.total/$("#pageRows").val()+1;
				}else{
					pagecontent=data.total/$("#pageRows").val()+1;
				}
				initPage(pagecontent,$("#pageIndex").val(),"pager",1);
			}
		});
	}
	Date.prototype.Format = function (fmt) {
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt))
	fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o){
	    if (new RegExp("(" + k + ")").test(fmt)) {
	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	}
	    }
	    return fmt;
	}
	function resize(){
		$('#time').val(new Date().Format('yyyy-MM-dd'));
		$('#time2').val(new Date().Format('yyyy-MM-dd'));
		$('#busicode').val('');
		$("#orderid,#name,#account").val("");
		  
	}
	</script>
</body>
</html>
