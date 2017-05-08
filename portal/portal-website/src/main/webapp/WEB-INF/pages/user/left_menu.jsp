<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="fl leftnav">
	<ul>
		<li><a href="../user/showUserManager" id="userInfo">用户信息</a></li>
		<li><a href="../user/toModifyUserPwd" id="loginPwd">修改登录密码</a></li>
	</ul>
</div>