<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="../../../common/head.jsp"></jsp:include>
<title>证联金融商户网站</title>
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
			<jsp:include page="left_menu.jsp"></jsp:include>
			<!--右边内容部分begin-->
			<div class="serve_right fl">
				<div class="serve_box1 clearfix">
					<p class="clearfix">
						<span class="fl mlr10"><a href="javascript:void(0);" onclick="interfaceDoc()">境内付款模版下载</a></span>
					</p>
					<p class="clearfix">
						<span class="fl mlr10"><a href="javascript:void(0);" onclick="backInfor()">境外付汇模版下载</a></span>
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
	<script type="text/Javascript">
	$(document).ready(function(){
		
	});
	function interfaceDoc(){
		downLoadDoc("b.txt","银行信息.txt");
	}
	function insteadPayDoc() {
		downLoadDoc("InsteadPayTemp.xls","代付模板文件.xls");
	}
	function backInfor(){
		downLoadDoc("ZLBC_PBC.rar","ZLBC_PBC.rar");
	}
	function keyTools(){
		downLoadDoc("RSAKeyTools.zip","openssl-0.9.8k_WIN32(RSAKeyTools).zip");
	}
	function downLoadDoc(ftpFileName,showFileName){
		$.ajax({
    		type:"post",
    		url:"downCommonFile",
    		data:{ftpFileName:ftpFileName,showFileName:showFileName},
    		success:function(data){
    			if(data.success == "false"){
    				$.MessageBox("文件更新维护中,暂无法下载,请稍后再试。");
    			}else{
    				window.location=data.url+"?ftpFileName="+ftpFileName+"&showFileName="+showFileName;
    			}
    		}
    	});
	}
	</script>
</body>
</html>