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
					<form id="queryTradeForm" action="<%=basePath%>queryAndStat/txnsStat" method="post">
					<input type="hidden" id="pageIndex" name="page" value="0"/> 
					<input type="hidden" id="pageRows" name="rows" value="10"/> 
					<input type="hidden" id="stime" name="stime" /> 
					<input type="hidden" id="etime" name="etime" /> 
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
	                     <span class="mlr10 fl">统计周期：
		                  	 <select id="selStatCircle" class="selectStatus" onchange="gradeChange()"  name=""> 
		                  	 		<option value="00">自定义时间</option>
			                   		<option value="01">周</option> 
									<option value="02">月</option> 
							 </select>
							 <select id="selYear" class="selectStatus"  name=""> 
							 </select>
							 <select id="selMonth" class="selectStatus"  name=""> 
		                  	 		<option value="1">1月</option>
									<option value="2">2月</option>
									<option value="3">3月</option>
									<option value="4">4月</option>
									<option value="5">5月</option>
									<option value="6">6月</option>
									<option value="7">7月</option>
									<option value="8">8月</option>
									<option value="9">9月</option>
									<option value="10">10月</option>
									<option value="11">11月</option>
									<option value="12">12月</option>
							 </select>
						</span>
	                    <span class="fl mlr10"><input class="inquire" type="button" onclick="queryTrade()" value="查询"/></span>
	                    <span class="fl mlr10"><input class="inquire" type="button" onclick="resize()" value="清除"/></span>
					</div>
					<div class="billtime clearfix divright"> 
						<span class="fl mlr10"><input class="inquirel" type="button" onclick="createExcelForm()" value="生成excel报表"/></span>
	                    <span class="fl mlr10"><input class="inquirel" type="button" onclick="createTxtForm()" value="生成txt报表"/></span>
					</div>
					</form>
					
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
		                            <th >委托机构名称</th>
		                            <th >业务类型</th>
		                            <th >总笔数</th>
		                            <th >成功笔数</th>
		                            <th >失败笔数</th>
		                        	<th >成功金额(元)</th>
		                        	<th >失败金额(元)</th>
		                        	<th >撤销总笔数</th>
		                        	<th >撤销成功笔数</th>
		                            <th >撤销失败笔数</th>
		                            <th >撤销成功金额</th>
		                            <th >撤销失败金额</th>
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
		$('#tradestatms').addClass('active');
		$('#selYear').hide();
		$('#selMonth').hide();
		$('#time').val(new Date().Format('yyyy-MM-dd'));
		$('#time2').val(new Date().Format('yyyy-MM-dd'));
		getFileName();
	});
	
	function search(){
		turnTo = 1;
		getData();
	}
	function prepareYear(){
		var nowyear=(new Date().getFullYear())*1;
		var html = "";
		for(var i=nowyear;i>nowyear-15;i--){
			html += '<option value="' + i + '">' + i + '年</option>';
		}
		$("#selYear").html(html);
	}
	
	function gradeChange(){
		var selStatCircle=$("#selStatCircle option:selected").val();
		if(selStatCircle=="02"){
			$('#selYear').show();
			$('#selMonth').show();
			prepareYear();
		}else{
			$('#selYear').hide();
			$('#selMonth').hide();
		}
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
		var selStatCircle=$("#selStatCircle option:selected").val();
		var beginDate;
		var endDate;
		if(selStatCircle=="00"){
			beginDate = $('#time').val().replace("-","").replace("-","");
			endDate = $('#time2').val().replace("-","").replace("-","");;
		}
		if(selStatCircle=="01"){
			
		}
		if(selStatCircle=="02"){
			var selyear=$("#selYear option:selected").val();
			var selmonth=$("#selMonth option:selected").val();
			var startdate=new Date(selyear,selmonth-1,01);
			var enddate=new Date(selyear,selmonth,01); 
			enddate.setDate(enddate.getDate()-1); 
			
			beginDate = startdate.Format("yyyyMMdd");
			endDate = enddate.Format("yyyyMMdd");
		}
		
		if(beginDate > endDate ){
			$.MessageBox("开始时间不能大于结束时间");
			return false;
		}
		$('#stime').val(beginDate);
		$('#etime').val(endDate);
	}
	
	function createExcelForm(){
		prepareParm();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>queryAndStat/txnsStatExcelForms",
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
			url: "<%=basePath%>queryAndStat/txnsStatTxtForms",
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
			url: "<%=basePath%>queryAndStat/getFileInfo?packageName="+"stat",
			dataType: "json",
			success: function(json) {
				if(json["err"]==null){
					$("#fileallName").val(json['fileAllName']);
					var output="";
					output = output + '<tr height="36" class="bor_bottom" >'; 
					output = output + '<td >'+json['filename']+'</td>';
					output = output + '<td >'+json['filetype']+'</td>';
					output = output + '<td >'+json['date']+'</td>';
					output = output + '<td >'+json['filepatten']+'</td>';
					output = output + '<td >'+'<a href="#" onclick="downloadFile()" class="refund_sq">下载报表</a>'+'</td></tr>';
					$('#tradeFile').html(output);
				}
				
			}
		});
	}
	
	function downloadFile(){
		window.location='<%=basePath%>queryAndStat/downloadFile?fileName='+$("#fileallName").val()+"&"+"packageName=stat";
	}
	function queryTradeController(flag){
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
					output = output + '<td >'+dataStr[i]['MERCHNAME']+'</td>';
					output = output + '<td >'+dataStr[i]['BUSINAME']+'</td>';
					output = output + '<td >'+dataStr[i]['ALLNUM']+'</td>';
					output = output + '<td >'+dataStr[i]['SUCCNUM']+'</td>';
					output = output + '<td >'+dataStr[i]['FAILNUM']+'</td>';
					output = output + '<td >'+dataStr[i]['SUCCAMT']+'</td>';
					output = output + '<td >'+dataStr[i]['FIALAMT']+'</td>';
					output = output + '<td >'+dataStr[i]['CANCELNUM']+'</td>';
					output = output + '<td >'+dataStr[i]['CANCELSUCCNUM']+'</td>';
					output = output + '<td >'+dataStr[i]['CANCELFAILNUM']+'</td>';
					output = output + '<td >'+dataStr[i]['CANCELSUCCAMT']+'</td>';
					output = output + '<td >'+dataStr[i]['CANCELFAILAMT']+'</td>';
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
		$('#selStatCircle,#selYear,#selMonth').val('');
	}
	</script>
</body>
</html>
