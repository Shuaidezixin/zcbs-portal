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
		<jsp:include page="menusecond.jsp"></jsp:include>
        <!--电子对账单begin-->
	        <div class="flow_item">
				<div class="bill_box clearfix">
					<input type="hidden" id="fileallName" name="page" value="0"/>
					<form id="queryTradeForm" action="<%=basePath%>queryAndStat/txnsDeta" method="post">
					<input type="hidden" id="pageIndex" name="page" value="0"/> 
					<input type="hidden" id="pageRows" name="rows" value="10"/> 
					<input type="hidden" id="stime" name="stime" /> 
					<input type="hidden" id="etime" name="etime" />
					<input type="hidden" id="scommitime" name="scommitime" /> 
					<input type="hidden" id="ecommitime" name="ecommitime" />  
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
						<span class="mlr10 fl">
							批次号: <input id="batchno" class="input_text2" name="batchno" value placeholder="" />
						</span>
	                    <span class="mlr10 fl">
							订单号: <input id="orderid" class="input_text2" name="orderid" value placeholder="" />
						</span>
						<span class="mlr10 fl">
							户名: <input id="name" class="input_text2" name="name" value placeholder="" />
						</span>
					</div>
					<div class="billtime clearfix"> 
						<!--日历begin-->
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
						<!--日历begin-->
						<span class="mlr10 fl">渠道日期：</span>
						<div class="time_start fl">  
	                        <div class="set_time">
	                            <input type="text" class="input_text show_time" id="time3" name="" value="2015-04-13" readonly="readonly"/>
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
	                          <input type="text" class="input_text show_time" id="time4" name="" value="2017-04-13" readonly="readonly"/>
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
					<span class="mlr10 fl">
							帐号: <input id="account" class="input_text2" name="account" value placeholder="" />
						</span>
					<span class="fl mlr10"><input class="inquire" type="button" onclick="queryTrade()" value="查询"/></span>
	                    <span class="fl mlr10"><input class="inquire" type="button" onclick="resize()" value="清除"/></span>
					<div class="billtime clearfix divright"> 
						<span class="fl mlr10"><input class="inquirel" type="button" onclick="createExcelForm()" value="生成excel报表"/></span>
	                    <span class="fl mlr10"><input class="inquirel" type="button" onclick="createTxtForm()" value="生成txt报表"/></span>
					</div>
					</form>
					</div>
					<div class="deal_item clearfix" id="dataTable">
						<table width="100%" class="order_detail" >
							<thead>
		                        <tr class="order_field">
		                            <th >报表名称</th>
		                            <th >报表类型</th>
		                            <th >报表数据日期</th>
		                            <th >报表格式</th>
		                            <th >操作</th>
		                        </tr>
		                    </thead>
		                    <tbody id="tradeFile">
		                    </tbody>
						</table>
		        	</div>
					<div class="deal_item clearfix" id="dataTable">
						<table width="100%" class="order_detail" >
							<thead>
		                        <tr class="order_field">
		                        	<th>渠道处理时间</th>
		                        	<th>交易日期</th>
		                        	<th>委托机构名称</th>
		                        	<th>订单号</th>
		                        	<th>交易金额</th>
		                        	<th>交易状态</th>
		                        	<th>交易返回码</th>
		                        	<th>订单状态信息</th>
		                        	<th>交易代码</th>
		                        	<th>渠道交易时间</th>
		                        	<th>渠道流水</th>
		                        	<th>业务类型</th>
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
		$("#tradedetailms,#tradestatms,#setlms,#billms").removeClass();
		$('#trade').addClass('active');
		$('#tradedetailms').addClass('active');
		$('#time').val(new Date().Format('yyyy-MM-dd'));
		$('#time2').val(new Date().Format('yyyy-MM-dd'));
		$('#time3').val(new Date().Format('yyyy-MM-dd'));
		$('#time4').val(new Date().Format('yyyy-MM-dd'));
		getFileName();
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
		
		var commitbeginDate;
		var commitendDate;
		
		beginDate = $('#time').val().replace("-","").replace("-","");
		endDate = $('#time2').val().replace("-","").replace("-","");;
		
		commitbeginDate = $('#time3').val().replace("-","").replace("-","");
		commitendDate = $('#time4').val().replace("-","").replace("-","");;
		
		
		
		if(beginDate > endDate ){
			$.MessageBox("开始时间不能大于结束时间");
			return false;
		}
		if(commitbeginDate > commitendDate ){
			$.MessageBox("开始时间不能大于结束时间");
			return false;
		}
		$('#stime').val(beginDate);
		$('#etime').val(endDate);
		
		$('#scommitime').val(commitbeginDate);
		$('#ecommitime').val(commitendDate);
	}
	
	function createExcelForm(){
		prepareParm();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>queryAndStat/txnsDetaExcelForms",
			data :$("#queryTradeForm").serialize(),
			dataType: "json",
			success: function(json) {
				$.MessageBox(json["info"]);
				getFileName();
			}
		});
		
	}
	
	function createTxtForm(){
		prepareParm();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>queryAndStat/txnsDetaTxtForms",
			data :$("#queryTradeForm").serialize(),
			dataType: "json",
			success: function(json) {
				$.MessageBox(json["info"]);
				getFileName();
			}
		});
	}
	
	function getFileName(){
		$.ajax({
			type: "POST",
			url: "<%=basePath%>queryAndStat/getFileInfo?packageName="+"deta",
			dataType: "json",
			success: function(json) {
				$('#tradeFile').html("");
				if(json["err"]==null){
					$("#fileallName").val(json['fileAllName']);
					var output="";
					output = output + '<tr height="36" class="bor_bottom" >'; 
					output = output + '<td >'+json['filename']+'</td>';
					output = output + '<td >'+json['filetype']+'</td>';
					output = output + '<td >'+json['date']+'</td>';
					output = output + '<td >'+json['filepatten']+'</td>';
					output = output + '<td >'+'<a href="#" onclick="downloadFile()" class="refund_sq">下载报表</a>'+'</td>/<tr>';
					$('#tradeFile').html(output);
				}
				
			}
		});
	}
	
	function downloadFile(){
		window.location='<%=basePath%>queryAndStat/downloadFile?fileName='+$("#fileallName").val()+"&"+"packageName=deta";
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
				data= JSON.parse(data);
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
					output = output + '<td >'+dataStr[i]['COMMITIME']+'</td>';
					output = output + '<td >'+dataStr[i]['TXNDATE']+'</td>';
					output = output + '<td >'+dataStr[i]['MERCHNAME']+'</td>';
					output = output + '<td >'+dataStr[i]['ORDERID']+'</td>';
					output = output + '<td >'+dataStr[i]['TXNAMT']+'</td>';//不确定
					output = output + '<td >'+dataStr[i]['RETCODE']+'</td>';
					output = output + '<td >'+dataStr[i]['RETINFO']+'</td>';
					output = output + '<td >'+dataStr[i]['STATUS']+'</td>';
 					output = output + '<td >'+dataStr[i]['BUSICODE']+'</td>';
 					output = output + '<td >'+dataStr[i]['RETTIME']+'</td>';
					output = output + '<td >'+dataStr[i]['TXNSEQNO']+'</td>';
 					output = output + '<td >'+dataStr[i]['BUSINAME']+'</td>';
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
		$('#time3').val(new Date().Format('yyyy-MM-dd'));
		$('#time4').val(new Date().Format('yyyy-MM-dd'));
		$('#busicode').val('');
		$('#batchno,#orderid,#name,#account').val("");
		   
	}
	</script>
</body>
</html>
