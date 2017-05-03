<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="./common/head.jsp"/>
<script type="text/javascript" src="<%=basePath%>js/placeholder.js"></script>
<title>证联金融商户网站</title>
<script> 
$(document).ready(function(){
  $("button").click(function(){
    $(".login_cont2").fadeIn(500);
  });
  
  $(".login-input").keydown(function () {
	  $('.login-msg').html("");
	});
  //$("#loginHide").hide();
});
</script> 
	</head>
<body>
	<!--header_begin-->
	<jsp:include page="./common/header.jsp"/>
	
	<!--header_end-->
	<!--content_begin-->
	<div class="content clearfix">
	<div class="clearfix index_box">
		<div class="login_box">


				<div class="login_tit"></div>
				<div class="login-msg"></div>
				<div class="login-input">
					<span><!--  <i class="num_icon"></i>--></span><input id="usernum" type="text" placeholder="请输入商户号"  name="info[usernum]" maxlength="15" class="list-input input_login"/>
				</div>
				<div class="login-input">
					 <span><!--<i class="user_icon"></i>--></span><input id="username" type="text" placeholder="请输入用户名"  name="info[username]" maxlength="32" class="list-input input_login"/>
				</div>
				<div class="login-input">
		            <span><!--<i class="pas_icon"></i>--></span><input id="password" type="password" placeholder="请输入密码" name="info[password]" maxlength="20" class="list-input input_login"/>
		        </div>
		        
		        <div class="login"><a class="login_btn" href="javascript:void(0);" id="login-button-submit"></a></div>
		        <div class="pas_box clearfix"><a href="<%=basePath%>user/toResetLoginPwd">忘记登录密码？</a></div>
			</div>
		</div>
	</div>
	<!--content_end-->
	<!--footer_begin-->
	<jsp:include page="./common/foot.jsp"></jsp:include>
	<!--footer_end-->
</body>
</html>
