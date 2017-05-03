<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta charset="UTF-8">
	<script type="text/javascript"
		src="<%=basePath%>js/jquery-1.8.3-min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/tab.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/calendar.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css.css">
		<title>证联金融商户网站</title>
</head>
<body>
	<!--header_begin-->
	<jsp:include page="../../../common/header.jsp"></jsp:include>
	<!--header_end-->
	<!--会员中心nav_begin-->
	<jsp:include page="../../../common/menu.jsp"></jsp:include>
	<!--会员中心nav_end-->
	<!--content_begin-->
	<div class="w1200 mtb20 clearfix minheight_body">
		<div class="sh_serve clearfix">
			<!--侧导航begin-->
			<!--侧导航end-->
			<!--右边内容部分begin-->
			<div class="serve_right fl">
				<div class="mtb90 activate clearfix">
					<!-- <i class="reset"></i> -->
					<h1>${type}</h1>
					<p><a href="${returnPath}">${returnMsg}</a></p>
				</div>

			</div>
			<!--右边内容部分end-->
		</div>

	</div>
	<!--content_end-->
	<!--footer_begin-->
	<jsp:include page="../../../common/foot.jsp"></jsp:include>
	<!--footer_end-->
</body>
</html>