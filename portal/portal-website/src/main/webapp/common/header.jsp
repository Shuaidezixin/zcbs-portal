<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

	<div id="loginHide" class="top">
		<div class="w1200 clearfix top_box">
			<div class="fl top_l">宜赋通门户网站</div>
				<div id="logOutDiv" class="fr top_r"><a id="logOutConfirm" href="javascript:void(0);">退出</a></div>
				<div class="fr top_r" id="loginusername"></div>
				<div class="fr top_r" id="lasttime"></div>
			</div>
	</div>
	
	<div class="header">
		<div class="w1200 clearfix header_box">
			<div class="fl logo"><img src="<%=basePath%>images/logo.png"></div>
			<div class="fr nav">
				<ul>
					<li><a href="<%=basePath%>login.jsp"  id="index">首页</a></li>
					<!-- <li><a href="" id="servicHall">服务大厅</a></li> -->
				</ul>
			</div>
		</div>
	</div>
<script type="text/javascript">
	function getCookie(name)
	{
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
		else
		return null;
	}
	
	function delCookie(name) 
	{ 
	    var exp = new Date(); 
	    exp.setTime(exp.getTime() - 1); 
	    var cval=getCookie(name); 
	    if(cval!=null) 
	        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
	} 
	$(function () {
		var loginname=getCookie("login_user_name");
		var lasttime =getCookie("login_last_time");
		var l=loginname==null?"":loginname;
		var t=lasttime==null?"":lasttime;
		if(loginname!=null){
			$("#loginusername").html("用户名:"+l+"&nbsp;&nbsp;&nbsp;&nbsp;");
		}else{
			$("#logOutDiv").remove();
		}
		
		//$("#lasttime").html("上次登录时间:"+t+"&nbsp;&nbsp;&nbsp;&nbsp;");
		
		$("#logOutConfirm").click(function() {
	        $.MessageBox({
	            buttonDone: "确定",
	            buttonFail: "取消",
	            message: "确定要退出吗?"
	        }).done(function() {
	        	$.ajax({
	    			type: "POST",
	    			url: "<%=basePath%>login/logout",
	    			dataType: "json",
	    			success: function(json) {
	    				if(json.code==00){
	    					delCookie("login_user_name");
	    					delCookie("login_last_time");
	    					window.location='<%=basePath%>login.jsp';
	    				}
	    			}
	    		});
	        }).fail(function() {
	            
	        });
	    });
		
	});
</script>