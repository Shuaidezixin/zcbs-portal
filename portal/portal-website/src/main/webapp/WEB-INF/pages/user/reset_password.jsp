<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		<div class="user_box mtb90 clearfix">
			<h1>重置密码</h1>
			<div class="wrapper">
			    <form id="signupForm" method="post" action="" class="zcform">
			        <p class="clearfix">
			         	<label class="one" for="userMember">登录商户号</label>
			        	<input id="userMember" maxlength="15" name="userMember" type="text" class="required" value placeholder="请输入商户号" />
			        	
			        </p>
			        <p class="clearfix">
			         	<label class="one" for="userName">登录用户名</label>
			        	<input id="userName" name="userName" maxlength="32" type="text" class="required" value placeholder="请输入登录用户名" />
			        </p>
			        <p class="clearfix">
			        	<label class="one" for="telphone">绑定手机号</label>
			        	<input id="telphone" name="telphone" class="required" maxlength="11" value placeholder="请输入绑定手机号码" />
			        </p>
			        <p class="clearfix">
			        	<label class="one" >验证码</label>
			            <input id="identifying_code" class="identifying_code" type="text" name="identifyCode" maxlength="6" value placeholder="验证码是6位" />
			            <input class="get_code" type="button" style="width: 120px" value="获取验证码" />
			            
			        </p>
			        <p class="clearfix">
				        <label class="one"  for="password">请输入新登录密码</label>
				        <input id="password" name="password" maxlength="20" type="password" class="{required:true}" onKeyUp="pwStrength(this.value)" onBlur="pwStrength(this.value)" placeholder="请输入新登录密码" />
				        
				    </p>
				    <p class="pas_intensity">
				    	<span id="strength_L">弱</span>
				        <span id="strength_M">中</span>
				        <span id="strength_H">强</span>
				    </p>
				    <p class="clearfix">
				        <label class="one" for="confirm_password">重新输入新登录密码</label>
				        <input id="confirm_password" name="confirm_password" type="password" maxlength="20" class="{required:true,equalTo:'#password'}" value placeholder="重新输入新登录密码" />
				    	
				    </p>
			        <!--<p class="clearfix">
			        	<label class="one" for="password">请输入新登录密码</label>
			        	<input id="password" name="password" type="password" class="{required:true,rangelength:[6,20],}" value placeholder="请输入新登录密码" />
			        </p>
			        <p class="clearfix">
			        	<label class="one" for="confirm_password">重新输入新登录密码</label>
			        	<input id="confirm_password" name="confirm_password" type="password" class="{required:true,equalTo:'#password'}" value placeholder="重新输入新登录密码" />
			        </p>-->
			        <p class="clearfix">
						<span class="fl ml150"><input class="inquire" type="button" onClick="reset_submit();" value="确定"/></span> 
						<span class="fl mlr10"><input class="inquire" type="button" onClick="goBackIndex()" value="返回"/></span>
					</p>
			     
			    </form>
			</div>
		</div>
	</div>
	<!--content_end-->
	<!--footer_begin-->
	<jsp:include page="../../../common/foot.jsp"></jsp:include>
	<!--footer_end-->
</body>
<script type="text/javascript" src="<%=basePath%>/js/getCode.js"></script>
<script type="text/javascript"> 
    //判断输入密码的类型    
    function CharMode(iN){    
        if (iN>=48 && iN <=57) //数字    
            return 1;    
        if (iN>=65 && iN <=90) //大写    
            return 2;    
        if (iN>=97 && iN <=122) //小写    
            return 4;    
        else    
            return 8;     
    }  
    //bitTotal函数    
    //计算密码模式    
    function bitTotal(num){    
        modes=0;    
        for (i=0;i<4;i++){    
            if (num & 1) modes++;    
            num>>>=1;    
        }  
        return modes;    
    }  
    //返回强度级别    
    function checkStrong(sPW){    
        if (sPW.length<6)    
            return 0; //密码太短，不检测级别  
        Modes=0;    
        for (i=0;i<sPW.length;i++){    
            //密码模式    
            Modes|=CharMode(sPW.charCodeAt(i));    
        }  
        return bitTotal(Modes);    
    }  
    
    //显示颜色    
    function pwStrength(pwd){    
        Dfault_color="#b9b9b9";     //默认颜色  
        L_color="#f75c65";      //低强度的颜色，且只显示在最左边的单元格中  
        M_color="#ec1a26";      //中等强度的颜色，且只显示在左边两个单元格中  
        H_color="#b6000b";      //高强度的颜色，三个单元格都显示  
        if (pwd==null||pwd==''){    
            Lcolor=Mcolor=Hcolor=Dfault_color;  
        }    
        else{    
            S_level=checkStrong(pwd);    
            switch(S_level) {    
            case 0:    
                Lcolor=Mcolor=Hcolor=Dfault_color;  
                break;  
            case 1:    
                Lcolor=L_color;  
                Mcolor=Hcolor=Dfault_color;  
                break;    
            case 2:    
            	Lcolor=L_color;
                Mcolor=M_color;    
                Hcolor=Dfault_color;    
                break;    
            default:   
            	Lcolor=L_color;
                Mcolor=M_color;  
                Hcolor=H_color;    
        	}    
    	}    
    	document.getElementById("strength_L").style.background=Lcolor;    
    	document.getElementById("strength_M").style.background=Mcolor;    
    	document.getElementById("strength_H").style.background=Hcolor;    
    	return;  
	}
    
  //timer处理函数
    function SetRemainTime() {
    	if (curCount == 0) {                
        	window.clearInterval(InterValObj);//停止计时器
        	$(".get_code").removeAttr("disabled");//启用按钮
        	$(".get_code").val("重新发送短信验证码");
        } else {
        	curCount--;
            $(".get_code").val( curCount + "秒后重新获取短信");
        }
     }
    function goBackIndex() {
		history.back();
	}
    function initOpenTip() {
   	 	var userMember = $("#userMember");
   	 	var userMemberOpentip = new Opentip(userMember,"您的证联金融商户号，如遗忘，请联系客服",{tipJoint:"bottom",background:"#f3f3f3",borderColor:"#cecece"});
   	 //myInput.focus(function() { inputOpentip.hide(); });
   		 userMember.focus(function() {
   	 		userMemberOpentip.show();
   	 	});
   		var userName = $("#userName");
   	    var userNameOpentip = new Opentip(userName,"管理员使用邮箱地址/操作员使用用户名称",{tipJoint:"bottom",background:"#f3f3f3",borderColor:"#cecece"});
   	  	 //myInput.focus(function() { inputOpentip.hide(); });
   	  	 userName.focus(function() {
   	  	 	userNameOpentip.show();
   	  	 });
   	 	 var telphone = $("#telphone");
 	  	 var telphoneOpentip = new Opentip(telphone,"证联金融商户系统中绑定的手机号码",{tipJoint:"bottom",background:"#f3f3f3",borderColor:"#cecece"});
 	  	 //myInput.focus(function() { inputOpentip.hide(); });
 	  	 telphone.focus(function() {
 	  		telphoneOpentip.show();
 	  	 });
 	  	 var password = $("#password");
	  	 var passwordOpentip = new Opentip(password,"密码由6-20位字母，数字和符号至少两种以上组合，区分大小写",{tipJoint:"bottom",background:"#f3f3f3",borderColor:"#cecece"});
	  	 //myInput.focus(function() { inputOpentip.hide(); });
	  	 password.focus(function() {
	  		passwordOpentip.show();
	  	 });
	 }
   
    $(function () {
    	initOpenTip();
    	$('.get_code').on('click',function(){
        	var telPhoneValue = $.trim($('#telphone').val());
        	var telPhone = $('#telphone');
        	var userNameValue = $.trim($('#userName').val());
        	var userName = $('#userName');
        	var userMemberValue=$.trim($("#userMember").val());
        	var userMember=$("#userMember");
        	if (userMemberValue.length==0) {
        		$.MessageBox("请输入商户号");
				//userMember.focus();
				return false;
			} else if(userNameValue.length==0){
				$.MessageBox("请输入登录用户名");
        		//userName.focus();
                return false;
        	}else if(telPhoneValue.length==0){
        		$.MessageBox("手机号不能为空");
        		return false;
        	}else if(telPhoneValue.length!=0){
        		 if (telPhoneValue.length<11) {
        			 $.MessageBox("手机号必须为11位");
                     return false;
                 }
                 for (var i = 0; i < telPhoneValue.length; i++) {
                     var char= telPhoneValue.charAt(i);
                     if (isNaN(char)) {
                    	 $.MessageBox("手机号只能是数字");
                         return false;
                     }
                 }
            }
        	
            	$.ajax({
            		type:"post",
            		url:"<%=basePath%>user/reset/sendMsg",
            		data:"userName="+userNameValue+"&telphone="+telPhoneValue+"&userMember="+userMemberValue,
            		success:function(data){
            			if(data == "1"){
            				getCode();
            				$.MessageBox("系统会发送内容为6位验证码的短信到您绑定的手机号码中 ，请注意查收");
            			}else if(data == "2"){
            				$.MessageBox("该商户下的用户不存在");
            				window.clearInterval(InterValObj);//停止计时器
            				$(".get_code").removeAttr("disabled");//启用按钮
            				$(".get_code").val("获取验证码");
            				//userName.focus();
            			}else if(data == "3"){
            				$.MessageBox("请输入正确的绑定手机号");
            				window.clearInterval(InterValObj);//停止计时器
            				$(".get_code").removeAttr("disabled");//启用按钮
            				$(".get_code").val("获取验证码");
            	        	//telPhone.focus();
            			}else{
            				window.clearInterval(InterValObj);//停止计时器
            				$(".get_code").removeAttr("disabled");//启用按钮
            				$(".get_code").val("获取验证码");
            				$.MessageBox("系统错误，请稍后重试");
            			}
            		}
            	});
        });
    });
    
    function reset_submit(){
    	var telPhoneValue = $.trim($('#telphone').val());
    	var telPhone = $('#telphone');
    	var userNameValue = $.trim($('#userName').val());
    	var userName = $('#userName');
    	var identifying_code_value =$.trim($('#identifying_code').val());
    	var identifying_code = $('#identifying_code');
    	var password_value = $.trim($('#password').val());
    	var password = $('#password');
    	var confirm_password_value =$.trim( $('#confirm_password').val());
    	var confirm_password = $('#confirm_password');
    	var userMemberValue=$.trim($("#userMember").val());
    	var userMember=$("#userMember");
    	if (userMemberValue.length==0) {
    		$.MessageBox("请输入商户号");
			//userMember.focus();
			return false;
		}else if(userNameValue.length==0){
			$.MessageBox("请输入用户名");
    		//userName.focus();
            return false;
    	}else if(telPhoneValue.length==0){
    		$.MessageBox("请输入手机号");
        	//telPhone.focus();
            return false;
        }else if(telPhoneValue.length!=0){
	   		 if (telPhoneValue.length<11) {
	   			$.MessageBox("手机号必须为11位");
	             return false;
	         }
	         for (var i = 0; i < telPhoneValue.length; i++) {
	             var char= telPhoneValue.charAt(i);
	             if (isNaN(char)) {
	            	 $.MessageBox("手机号只能是数字");
	                 return false;
	             }
	         }
   	    }
    	if(identifying_code_value.length==0||identifying_code_value.length<6){
    		$.MessageBox("请输入验证码");
        	identifying_code.focus();
            return false;
        }else if(password_value.length==0||password_value.length<6){
        	//password.focus();
        	return false;
        }else if(confirm_password_value.length==0||confirm_password_value.length<6){
        	confirm_password.focus();
        	return false;
        }else if(password_value != confirm_password_value){
        	confirm_password.focus();
        	return false;
        }
    	if (checkStrong(password_value)<2) {
    		$.MessageBox("密码由6-20位字母，数字和符号至少两种以上组合，区分大小写");
    		return false;
		}
        	$.ajax({
                cache:true,
                type:"POST",
                url:"reset/resetLoginPwd",
                data:$('#signupForm').serialize(),// 你的formid
                async: false,
                success: function(data) {
                	if(data.result == "1"){
                		document.forms[0].action = data.newUrl;
        				document.forms[0].submit();
                	}else if(data.result == '2'){
                		$.MessageBox("该商户下用户不存在");
        				//userName.focus();
                	}else if(data.result == '3'){
                		$.MessageBox("请输入绑定的手机号");
        	        	//telPhone.focus();
                	}else if(data.result == '4'){
                		$.MessageBox("验证码不正确");
                    	identifying_code.focus();
                	}
                }
            });
        
    }
</script>  
</html>