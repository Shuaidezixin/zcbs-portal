<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div id="loginHide" class="top">
		<div class="w1200 clearfix top_box">
			<div class="fl top_l">证联金融商户网站</div>
			<shiro:authenticated>	<div class="fr top_r"><a id="logOutConfirm" href="javascript:void(0);">退出</a></div></shiro:authenticated>
		</div>
	</div>
	
	<div class="header">
		<div class="w1200 clearfix header_box">
			<div class="fl logo"><a href="user/index.html"><img src="/images/logo.png"></a></div>
			<div class="fr nav">
				<ul>
					<li><a href="user/index.html" class="active" id="index">首页</a></li>
					<li><a href="merchant/serviceHall.html" id="servicHall">服务大厅</a></li>
					<!-- <li><a href="javascript:void(0);">建议中心</a></li> -->
				</ul>
			</div>
		</div>
	</div>