$(function () {
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
                     url: "login/login",
                     data: {memberid:usernumValue,loginName:usernameValue,pwd: passwordValue},//发送ajax请求  
                     success: function(result) {   
                         if (result.result == "success") { 
                             parent.document.location.href = '/trade/showQueryTrade1';
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
});