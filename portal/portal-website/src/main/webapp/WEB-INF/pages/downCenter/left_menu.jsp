<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="fl leftnav">
	<ul>
		<li><a href="<%=basePath%>downFile/downloadCommon" id="operation" class="active">公共资源下载</a></li></br></br></br>
		<li><a href="<%=basePath%>downFile/downloadMould" id="operation" class="active">模板下载</a></li>
	</ul>
</div>