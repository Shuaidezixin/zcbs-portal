var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数

//timer处理函数
function SetRemainTime() {
	if (curCount == 0) {                
		window.clearInterval(InterValObj);//停止计时器
		$(".get_code").removeAttr("disabled");//启用按钮
		$(".get_code").val("重发短信");
	} else {
		curCount--;
		$(".get_code").val( curCount + "秒后重发短信");
	}
}

function getCode(){
    curCount = count;
	//设置button效果，开始计时
	$(".get_code").attr("disabled", "true");
    $(".get_code").val(curCount + "秒后重发短信");
	InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
};