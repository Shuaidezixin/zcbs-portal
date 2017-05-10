<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css.css"/>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3-min.js"/>
		<script type="text/javascript" src="<%=basePath%>js/tab.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/pages.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
		<script src="<%=basePath%>js/messagebox.js"></script> 
		<link rel="stylesheet" href="<%=basePath%>css/messagebox.css" type="text/css"/>
		<title>用户信息</title>
</head>
<body>
	<!--header_begin-->
	<jsp:include page="../../../common/header.jsp"></jsp:include>
	<!--header_end-->
	<!--会员中心nav_begin-->
	<jsp:include page="../../../common/menu.jsp"></jsp:include>
	<!--会员中心nav_end-->
	<!--content_begin-->
	<div class="w1200 mtb20 clearfix minheight_body">
		<div class="sh_serve clearfix">
			<!--侧导航begin-->
			<jsp:include page="left_menu.jsp"></jsp:include>
			<!--侧导航end-->
			<!--右边内容部分begin-->
			<div class="serve_right fl">
				<div class="m10 clearfix">
					<div class="serve_box1 clearfix">
						<input type="hidden" id="pageIndex" name="page" value="0" /> 
						<input type="hidden" id="pageRows" name="rows" value="10" /> 
						<span class="mlr10 fl">登录号:
						<input id="loginName" class="input_text2"value placeholder="" maxlength="11" /></span> 
						<span class="mlr10 fl">用户名:
						<input id="userName" class="input_text2" value placeholder="" maxlength="32" /></span> 
						<!-- <span class="mlr10 fl">
							状态： 
							<select id="status" class="selectStatus" style="width:112px" name="status">
								<option value="00">正常</option>
								<option value="01">注销</option>
							</select>
						</span> -->
						<span class="fl mlr10"><input id="queryUser" class="inquire" type="submit" value="查询" /></span> 
						<span class="fl mlr10"><input id="reSize" class="inquire" type="button" value="清除" /></span>
					</div>
					<div class="serve_box clearfix">
						<table width="100%" class="order_detail">
							<thead>
								<tr class="order_field">
									<th >登录名</th>
									<th >用户名称</th>
									<th >所属委托机构</th>
									<th >状态</th>
<!-- 									<th >操作</th> -->
								</tr>
							</thead>
							<tbody id="takeAddress" >
							</tbody>
						</table>
						<!--分页-->
						<p>
							<div id="pager" class="pages"></div>
						</p>
						<!--分页end-->
					</div>
				</div>

			</div>

			<!--右边内容部分end-->
		</div>

	</div>
	<!--content_end-->
	<!--footer_begin-->
	<jsp:include page="../../../common/foot.jsp"></jsp:include>
	<!--footer_end-->
</body>
<script type="text/javascript">

$(document).ready(function(){
	getData();
	$("#queryUser").click(function(){
		turnTo=1;
		getData();
	});
});

function  onlyNum(phone) {
	  	var cha=phone;
		if (cha>=48 && cha <=57){ //数字    
	        return true; 
    	}else{
    		return false;
    	}
	
}

function getData(){
	$.ajax({
		url:"<%=basePath%>user/queryUsers",
		type:"post",
		dataType:"json",
		data:{
			userName:$("#userName").val(),
			loginName:$("#loginName").val(),
			rows:$("#pageRows").val(),
			page:turnTo
		},
		success:function(data){
			if (data.total=="0" && data.rows.length=="1") {
				$.MessageBox(data.rows[0].INFO);
			}else{
				var tBody = $('#takeAddress');
            	var items = data.rows;
            	if (items.length==0) {
            		tBody.html('');
            		document.getElementById("pager").innerHTML = '';
            		return ;
				}
            	output(tBody, items);
            	initPage(data.pageCount,data.page,"pager",1);
				 
			}
		}
	});
}

function output(tBody, items){
	tBody.html('');
	for(i in items){
		var statusName="";
		var activeOrLogOff="";
		if(items[i].STATUS==00){
			statusName="正常";
			activeOrLogOff='<a href="<%=basePath%>user/tologoff?id=' +items[i].USERID+ '" class="refund_sq fl mlr3">注销</a>'
		}else if(items[i].STATUS==01){
			statusName="已注销";
			activeOrLogOff='<a href="<%=basePath%>user/toActiveUser?id=' +items[i].USERID+ '" class="refund_sq fl mlr3">启用</a>'
		}
		var operation='<a href="<%=basePath%>user/toUpdateUser?id=' +items[i].USERID+ '" class="refund_sq fl mlr3 ml10">修改</a>';
		tBody.append( '<tr height="36" class="bor_bottom">'+
		'<td >'+items[i].LOGIN_NAME+'</td>'+
        '<td >'+items[i].USER_NAME+'</td>'+
        '<td >'+items[i].MEMBER_ID+'</td>'+
        '<td >'+statusName+'</td>'+
		'</td>'+
    	'</tr>');
	}
}
$(window).load(function(){
	$("#member,#merch,#trade,#service,#user").removeClass();
	$('#user').addClass('active');
	$("#userInfo").attr("class", "active");
});
</script>
</html>
