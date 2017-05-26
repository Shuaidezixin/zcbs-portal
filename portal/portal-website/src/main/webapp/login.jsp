<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="./common/head.jsp"/>
<script type="text/javascript" src="<%=basePath%>js/placeholder.js"></script>
<head>
<title>登录</title>
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
					<span><!--  <i class="num_icon"></i>--></span><input id="usernum" type="text" placeholder="请输入委托机构号"  name="info[usernum]" maxlength="15" class="list-input input_login"/>
				</div>
				<div class="login-input">
					 <span><!--<i class="user_icon"></i>--></span><input id="username" type="text" placeholder="请输入登录名"  name="info[username]" maxlength="32" class="list-input input_login"/>
				</div>
				<div class="login-input">
		            <span><!--<i class="pas_icon"></i>--></span><input id="password" type="password" placeholder="请输入密码" name="info[password]" maxlength="20" class="list-input input_login"/>
		        </div>
		        
		        <div class="login"><a class="login_btn" href="javascript:void(0);" id="login-button-submit"></a></div>
			</div>
		</div>
	</div>
	<!--content_end-->
	<!--footer_begin-->
	<jsp:include page="./common/foot.jsp"></jsp:include>
	<!--footer_end-->
</body>
<script> 
$(document).ready(function(){
	H_login = {};
    H_login.openLogin = function(){
        $('.login-header a').click(function(){
            $('.login').show();
            $('.login-bg').show();
        });
    };
    H_login.closeLogin = function(){
        $('.close-login').click(function(){
        	$('.login-msg').html("");
            $('.login').hide();
            $('.login-bg').hide();
        });
    };
    $("body").bind("keydown",function(event){
	        if(event.keyCode == 13){
	        	$("#login-button-submit").click();
	        } 
       });    
    H_login.loginForm = function () {
        $("#login-button-submit").on('click',function(){
            var usernum = $("#usernum");
              var usernumValue = $("#usernum").val();
              var username = $("#username");
              var usernameValue = $("#username").val();
              var password = $("#password");
              var passwordValue = $("#password").val();
              
            if(usernumValue == ""){
            	$('.login-msg').html("商户号不能为空");
                usernum.focus();
                return false;
            }
            if(usernameValue == ""){
            	$('.login-msg').html("用户名不能为空");
                username.focus();
                return false;
            }else if(usernameValue.length > 32){
            	$('.login-msg').html("用户名长度不能大于32字符");
                username.focus();
                return false;
            }else if(passwordValue == ""){
            	$('.login-msg').html("密码不能为空");
                password.focus();
                return false;
            }else if(passwordValue.length < 6 || passwordValue.length > 20){
            	$('.login-msg').html("请重新输入密码");
                password.focus();
                return false;
            }else{
            	 $.ajax({  
                     type: "post",  
                     url: '<%=basePath%>login/login',
                     data: {memberid:usernumValue,loginName:usernameValue,pwd: passwordValue},//发送ajax请求  
                     success: function(result) {   
                         if (result.result == "success") { 
                             parent.document.location.href = '<%=basePath%>order/showOrder';
                         } else {
                        	 $('.login-msg').html(result.info);
                         } 
                     }
                 });  
            }
        });
    };
    H_login.run = function () {
        this.closeLogin();
        this.openLogin();
        this.loginForm();
    };
    H_login.run();
	
  $("button").click(function(){
    $(".login_cont2").fadeIn(500);
  });
 
  $(".login-input").keydown(function () {
	  $('.login-msg').html("");
	});
});
</script> 
</html>
