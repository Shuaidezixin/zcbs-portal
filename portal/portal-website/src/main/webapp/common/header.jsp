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
				<div class="fr top_r"><a id="logOutConfirm" href="javascript:void(0);">退出</a></div>
		</div>
	</div>
	
	<div class="header">
		<div class="w1200 clearfix header_box">
			<div class="fl logo"><a href="<%=basePath%>user/index.html"><img src="<%=basePath%>images/logo.png"></a></div>
			<div class="fr nav">
				<ul>
					<%-- <li><a href="<%=basePath%>user/index.html" class="active" id="index">首页</a></li>
					<li><a href="<%=basePath%>merchant/serviceHall.html" id="servicHall">服务大厅</a></li> --%>
				</ul>
			</div>
		</div>
	</div>