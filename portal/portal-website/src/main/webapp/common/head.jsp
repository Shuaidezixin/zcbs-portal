<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>    
		<meta http-equiv="description" content="宜赋通门户网站"/>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3-min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/resgin.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.pager.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/opentip-jquery.js"></script>
		<script src="<%=basePath%>js/messagebox.js"></script> 
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/Pager.css" />
		<link  rel="stylesheet" href="<%=basePath%>css/messagebox.css" type="text/css"/>
		<link  rel="stylesheet" href="<%=basePath%>css/opentip.css" type="text/css"/>
