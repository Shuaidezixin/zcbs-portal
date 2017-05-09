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
	<jsp:include page="../../../common/menu.jsp"></jsp:include>
	<!--header_end-->
	<!--content_begin-->
	<div class="w1200 mtb20 clearfix minheight_body">
	<div class="sh_serve clearfix">
	<jsp:include page="left_menu.jsp"></jsp:include>
		<div class="serve_right fl">
			<br />
			<div class="wrapper">
			    <form id="signupForm" method="post" action="" class="zcform" onSubmit="return modify_submit()">
			        <p class="clearfix m40">
				        <label class="one" for="password">请输入原登录密码</label>
				        <input id="password" name="password" maxlength="20" type="password" class="required input_text3" placeholder="请输入原登录密码" />
				    </p>
			        <p class="clearfix">
				        <label class="one" for="passwordnew">请输入新登录密码</label>
				        <input id="passwordnew" name="passwordnew" maxlength="20" class="input_text3" type="password" class="{required:true,rangelength:[6,20],}" onKeyUp="pwStrength(this.value)" onBlur="pwStrength(this.value)" placeholder="请输入新登录密码" />
				    
				    </p>
				    <p class="pas_intensity">
				    	<span id="strength_L">弱</span>
				        <span id="strength_M">中</span>
				        <span id="strength_H">强</span>
				    </p>
				    <p class="clearfix">
				        <label class="one" for="confirm_passwordnew">重新输入新登录密码</label>
				        <input id="confirm_passwordnew" name="confirm_passwordnew" maxlength="20" class="input_text3" type="password" class="{required:true,equalTo:'#passwordnew'}" value placeholder="重新输入新登录密码" />
				    </p>
			       	<p class="clearfix m40">
						<input id="sureLogoff" class="inquire fl ml150" type="button" value="确定" />
						<input id="cancleLogoff" class="return fl ml20" type="button" value="返回" />
					</p>   
			    </form>
			</div>
		</div>
	</div>
	</div>
	<!--content_end-->
	<!--footer_begin-->
	<jsp:include page="../../../common/foot.jsp"></jsp:include>
	<!--footer_end-->
</body>
<script type="text/javascript"> 
$(document).ready(function(){
	$("#member,#merch,#trade,#service,#user").removeClass();
	$('#user').addClass('active');
	$("#loginPwd").attr("class", "active");
	$("#cancleLogoff").click(function() {
		history.back();
	})
	$("#sureLogoff").click(function() {
		modify_submit();
	})
	initOpenTip();
});
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
        Dfault_color="#f3f3f3";     //默认颜色  
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
    function initOpenTip() {
 	  	 var password = $("#passwordnew");
	  	 var passwordOpentip = new Opentip(password,"密码由6-20位字母，数字和符号至少两种以上组合，区分大小写",{tipJoint:"bottom",background:"#f3f3f3",borderColor:"#cecece"});
	  	 //myInput.focus(function() { inputOpentip.hide(); });
	  	 password.focus(function() {
	  		passwordOpentip.show();
	  	 });
	 }
    function modify_submit(){
    	var passwordValue = $('#password').val();
    	var password = $('#password');
    	var passwordnewValue = $('#passwordnew').val();
    	var passwordnew = $('#passwordnew');
    	var confirm_passwordnewValue = $('#confirm_passwordnew').val();
    	var confirm_passwordnew = $('#confirm_passwordnew');
    	if(passwordValue == "" ||hasEmpty(passwordValue)==false){
    		password.focus();
            return false;
    	}else if(passwordnewValue == ""||passwordnewValue.length<6 ||passwordnewValue.length>20||hasEmpty(passwordnewValue)==false){
        	//passwordnew.focus();
            return false;
        }else if(confirm_passwordnewValue == ""||confirm_passwordnewValue.length<6 ||confirm_passwordnewValue.length>20 ||passwordnewValue!=confirm_passwordnewValue){
        	confirm_passwordnew.focus();
            return false;
        }
    	if (checkStrong(passwordnewValue)<2) {
    		$.MessageBox("密码由6-20位字母，数字和符号至少两种以上组合，区分大小写");
    		return false;
		}
        	$.ajax({
        		type:"post",
        		url:"<%=basePath%>user/modifyPwd",
        		data:$('#signupForm').serialize(),
        		async: false,
        		success:function(data){
        			$.MessageBox(data.INFO);
        			if(data.RET == "succ"){
        				$('#password').val("");
        		    	$('#passwordnew').val("");
        		    	$('#confirm_passwordnew').val("");
        				
        			}
        		}
        	});
        
    }
    function hasEmpty(str) {
		var array=str.split(" ");
        if (array.length!=1) {
        	$.MessageBox("密码中不能包含空格！");
            return false;
        }else{
            return true;
        }
	}
    function isChina(s){ 
    	var patrn=/[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi; 
    	if(!patrn.exec(s)){
    		alert("密码不能包含中文字符")
    		return false; 
    	}
    	else{ 
    		return true; 
    	} 
    }
</script>
</html>