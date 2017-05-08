<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="../../../common/head.jsp"></jsp:include>
		<title>宜赋通门户网站</title>
	</head>
<body>
	<!--header_begin-->
	<jsp:include page="../../../common/header.jsp"></jsp:include>
	<!--header_end-->
	<!--content_begin-->
	<div class="w1200 clearfix minheight_body">
		<div class="mtb90 activate clearfix">
			<i class="reset"></i>
			<h1>修改成功</h1>
			<p><a href="<%=basePath%>login.jsp">返回</a></p>
		</div>
	</div>
	<!--content_end-->
	<!--footer_begin-->
	<jsp:include page="../../../common/foot.jsp"></jsp:include>
	<!--footer_end-->
</body>
</html>