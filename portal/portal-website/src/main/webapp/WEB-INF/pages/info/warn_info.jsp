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
	<!--会员中心nav_begin-->
	<div class="w1200 mtb20 clearfix minheight_body">
		<div class="sh_serve clearfix">
			
			<!--右边内容部分begin-->
			<div class="serve_right fl">
				<div class="mtb90 serve_box2 clearfix">
					<p class="clearfix">
						<span>${warnInfo}</span>
					</p>
					<p>
						<input id="userName" class="inquire" type="hidden"value="${userName}" />
						<input id="requestUrl" class="inquire"type="hidden" value="${requestUrl}" />
						<span class="fl"><input id="sureLogoff" class="inquire" type="submit" value="确定" /></span> 
						<span class="fl mlr10"><input id="cancleLogoff" class="inquire" type="submit" value="取消" /></span>
					</p>
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
	$("#member,#merch,#trade,#service,#helpe").removeClass();
	$('#service').addClass('active');
	$("#open,#download,#business,#contract,#operation").removeClass();
	$("#operation").attr("class", "active");
	//确定注销
	$("#sureLogoff").click(function(){
		$.ajax({
			url:'<%=basePath%>'+$("#requestUrl").val(),
			type:"post",
			dataType:"json",
			data:{userName:$("#userName").val()},
			success:function(data){
				if (data.success=="true") {
					window.location="<%=basePath%>" + data.result;
					} else {
						alert(data.result);
					}
				}
			})
		})
		//取消
		$("#cancleLogoff").click(function() {
			history.back();
		})
	});
</script>
</html>
