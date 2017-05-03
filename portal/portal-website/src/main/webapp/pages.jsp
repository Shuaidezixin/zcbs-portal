<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
	  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>    
		<meta http-equiv="description" content="证联金融商户网站"/>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3-min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/login.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/resgin.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.pager.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/Pager.css" />
</head>
<body>

	<div id="pager" style="text-align: right;"></div>
	
	<script type="text/javascript">
	$(function() {
		  init(1);
	});

	//默认加载
	function init(pagenumber){
		
		//向服务器发送请求，查询满足条件的记录
		//$.getJSON('',{},function(data){
	        //data 为返回json 对象 并包括(pagecount、totalcount)的key-value值;
			var data = {'pagecount':15,'totalcount':150};
			$("#pager").pager({ pagenumber: pagenumber, pagecount:data.pagecount,totalcount:data.totalcount, buttonClickCallback: PageClick});
		//});
	}
	//回调函数
	PageClick = function(pageclickednumber) {
		init(pageclickednumber);
	}
	</script>
</body>
</html>