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
	<title>宜赋通门户网站</title>
</head>
<body ng-app="app">
	<jsp:include page="../../../common/head.jsp"></jsp:include> 
	<script type="text/javascript" src="<%=basePath%>js/tab.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/calendar.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/angular.min.js"></script>
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
					<form id="queryTradeForm" action="<%=basePath%>txnsLog/info" method="post">
					<input type="hidden" id="pageIndex" name="page" value="0"/> 
					<input type="hidden" name="pageSize" value="10"/> 
					<input type="hidden" id="busiType" name="busiType" value=""/> 
					<div class="billtime clearfix"> 
						<span class="fl mlr10 detailtime"><a href="javascript:searchByOne('month');" id="month" class="time_active">最近一个月</a></span>
						<span class="fl mlr10 detailtime"><a href="javascript:searchByOne('month3');" id="month3">最近三个月</a></span>
						<span class="fl mlr10 detailtime"><a href="javascript:searchByOne('month6');" id="month6">最近半年</a></span>
 						<span class="fl mlr10 detailtime"><a href="javascript:searchByOne('year');" id="year">最近一年</a></span>
						
						 
						<!--日历begin-->

						 <span class="mlr10 fl">订单提交日期：</span>
						<div class="time_start fl">  
	                        <div class="set_time">
	                            <input type="text" class="input_text show_time" id="time" name="startTime" value="2015-04-13" readonly="readonly"/>
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
	                          <input type="text" class="input_text show_time" id="time2" name="endTime" value="2017-04-13" readonly="readonly"/>
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
	                     <span class="mlr10 fl">业务类型：
		                  	 <select id="selBusiType" class="selectStatus"  name=""> 
			                   		<option value="all">全部</option> 
									<option value="1000">消费</option> 
									<option value="2000">充值</option> 
									<option value="3000">提现</option> 
									<option value="4000">退款</option> 
									<option value="7000">代付</option> 
									<!-- <option value="7000"></option> 
									<option value="8000">text2</option> -->
									<option value="9000">手工充值</option>
							</select>
						</span>
	                    <span class="fl mlr10"><input class="inquire" type="button" onclick="queryTrade()" value="查询"/></span>
					</div>
					</form>
					<div class="deal_item clearfix">
						<table width="100%" class="order_detail" >
							<thead>
		                        <tr class="order_field">
		                            <th width="12%">商户订单号</th>
		                            <th width="12%">交易流水号</th>
		                            <th width="10%">业务类型</th>
		                            <th width="12%">交易时间</th>
		                            <th width="12%">交易金额(元)</th>
		                        	<th width="12%">交易手续费(元)</th>
		                        	<th width="12%">交易状态</th>
		                        	<th width="10%">操作</th>
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
	$(window).load(function(){
		$("#member,#merch,#trade,#service,#helpe").removeClass();
		$('#trade').addClass('active');
		initTime();
		//getData();
	});
	
	function search(){
		turnTo = 1;
		getData();
	}
	
	function searchByOne(time){
		turnTo = 1;
		var beginDateValue = null;
		var endDateValue = getNowDate();
		$('#time2').val(endDateValue);
		if(time == "month"){
			beginDateValue = getNowDaytoAMonth();
			$('#time').val(beginDateValue);
			$("#month,#month3,#month6,#year").removeClass();
			$("#"+time).attr("class","time_active");
		}
		if(time == "month3"){
			beginDateValue = getNowDayto3Month();
			$('#time').val(beginDateValue);
			$("#month,#month3,#month6,#year").removeClass();
			$("#"+time).attr("class","time_active");
		}
		if(time == "month6"){
			beginDateValue = getNowDaytoHalfAyear();
			$('#time').val(beginDateValue);
			$("#month,#month3,#month6,#year").removeClass();
			$("#"+time).attr("class","time_active");
		}
		if(time == 'year'){
			beginDateValue = getNowDaytoAyear();
			$('#time').val(beginDateValue);
			$("#month,#month3,#month6,#year").removeClass();
			$("#"+time).attr("class","time_active");
		}
		getData();
	}
	
	function initTime(){
		var id = $("span a.time_active").attr('id');
		var endDateValue = getNowDate();
		$('#time2').val(endDateValue);
		if(id == 'month'){
			beginDate = getNowDaytoAMonth();
			$('#time').val(beginDate);
		}else if(id == 'month3'){
			beginDate = getNowDayto3Month();
			$('#time').val(beginDate);
		}else if(id == 'month6'){
			beginDate = getNowDaytoHalfAyear();
			$('#time').val(beginDate);
		}else if(id == 'year'){
			beginDate = getNowDaytoAyear();
			$('#time').val(beginDate);
		}
	}
	
	function getData(){
		var beginDate = $('#time').val();
		var endDate = $('#time2').val();
		if(beginDate > endDate ){
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
		function queryTradeController(){
			var selBusitype=$("#selBusiType option:selected").val();
			$("#busiType").val(selBusitype);
			var beginDate = $('#time').val();
			var endDate = $('#time2').val();
			if(beginDate > endDate ){
				$.MessageBox("开始时间不能大于结束时间");
				return false;
			}
			$('#queryTradeForm').ajaxSubmit({
				async:false,
				error: function(XMLHttpRequest, textStatus, errorThrown) {
			    	 alert(XMLHttpRequest.status);
			    	   },
				success : function(data){
					var dataStr;
			    	var output='';
			    	var success,result,count;
			    	$.each(data, function(key, value) {
			    		if (key=='success') {
			    			success=value;
						}

			    	      if (key == 'total') {
			    	    	  count = value;
			    	      }
			    	      if (key == 'cols') {
			    	    	  dataStr = value;
			    	      }
			    	      
			    	});
			    	if (success=="false") {
			    		$.MessageBox("查询错误！");
						return;
					}
			    	if (dataStr.length==0) {
			    		$('#tradeContents').html('');
			    		document.getElementById('pager').innerHTML='';
			    		$.MessageBox("您所查询的日期，无交易记录！");
						return ;
					}
					for(var i=0,l=dataStr.length;i<l;i++){ 
						output = output + '<tr height="36" class="bor_bottom" >'; 
						output = output + '<td width="12%">'+dataStr[i]['accordno']+'</td>';
						output = output + '<td width="12%">'+dataStr[i]['txnseqno']+'</td>';
						output = output + '<td width="10%">'+dataStr[i]['busicode']+'</td>';
						output = output + '<td width="12%">'+dataStr[i].txndate+'</td>';
						output = output + '<td width="12%">'+dataStr[i]['amount']+'</td>';
						output = output + '<td width="12%">'+dataStr[i]['txnfee']+'</td>';
						output = output + '<td width="12%">'+(dataStr[i]['apporderinfo']==null?'':dataStr[i]['apporderinfo'])+'</td>';
						if (dataStr[i]['canRefund']=='0') {
							output = output + '<td width="10%">'+'<a href="#" onclick="refundApply('+dataStr[i]['txnseqno']+')" class="refund_sq">退款</a>'+'</td>';
						}else{
							output = output + '<td width="10%">'+'</td>';
						}
						
						output = output + '</tr>';
					} 
					/* for(var j=dataStr.length,k=10;j<k;j++){
						output = output + '<tr height="36" class="bor_bottom" >';
						output = output + '</tr>';
					} */
					$('#tradeContents').html(output);
					initPage(data.pageCount,data.page,"pager",1);
				}
			});
		}
		function refundApply(texseqno) {
			window.location='<%=basePath%>refund/toRefundApplyReady?txnseqno='+texseqno;
			<%-- $.ajax({
				url: "<%=basePath%>refund/toRefundApplyReady", 
	            type: 'post',
				data: {txnseqno:texseqno},
				async: false,
	            success: function(data){
	        		if (data.success=="true") {
	        			
					}else{
						$.MessageBox(data.result);
					}
	            }  
	         }); --%>
			
		}
	</script>
</body>
</html>
