<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="common/head.jsp"></jsp:include>
		<title>证联金融商户网站</title>
	</head>
<body>
	<jsp:include page="common/head.jsp"></jsp:include>
	<!--header_begin-->
	<jsp:include page="common/header.jsp"></jsp:include>
	<!--header_end-->
	<!--会员中心nav_begin-->
	<jsp:include page="common/menu.jsp"></jsp:include>
	<div class="w1200 mtb20 clearfix">
		<!--我的账户/账户管理/账务明细begin-->
		<div class="mtb90 activate clearfix">
			
			<h1>您无权限访问该页面,如有需要请联系管理员</h1>
		</div>
    </div>
    <jsp:include page="common/foot.jsp"></jsp:include>
</body>
<script type="text/javascript">
 $(document).ready(function () {
	 $("#member,#merch,#trade,#service,#helpe").removeClass();
	 $('#merch').addClass('active');
})

</script>
</html>