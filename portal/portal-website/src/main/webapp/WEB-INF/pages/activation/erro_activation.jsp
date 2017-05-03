<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="../../../common/head.jsp"></jsp:include>
<title>证联金融商户网站</title>
	</head>
<body>
	<!--header_begin-->
	<jsp:include page="../../../common/header.jsp"></jsp:include>
	<!--header_end-->
	<!--content_begin-->
	<div class="w1200 clearfix minheight_body">
		<div class="mtb90 activate clearfix">
			<i class="activate_loss"></i>
			<h1>激活失败,${errInfo}</h1>
			<p>账户激活失败，将在<span id="myspan">10</span>S后返回登录页面</p>
			<p>如果页面未跳转，请 <a href="../login.jsp">单击跳转</a></p>
		</div>
	</div>
	<!--content_end-->
	<!--footer_begin-->
	<jsp:include page="../../../common/foot.jsp"></jsp:include>
	<!--footer_end-->
</body>
<script type="text/javascript">
	window.onload=function(){
	  var myspan=document.getElementById("myspan");
	  var timer=10;
	  var flag;
	  function daoji(){
	     timer=timer-1;
	     myspan.innerHTML=timer;
	     if(timer==0){
	       location.href="../login.jsp";
	       clearInterval(flag);
	     }
	  }
	  flag=setInterval(daoji,1000);
	}
</script> 
</html>