/**
* 获取本周、本季度、本月、上月的开端日期、停止日期
*/
var now = new Date(); //当前日期
var nowDayOfWeek = now.getDay()-1; //今天本周的第几天
var nowDay = now.getDate(); //当前日
var nowMonth = now.getMonth(); //当前月
var nowYear = now.getYear(); //当前年
nowYear += (nowYear < 2000) ? 1900 : 0; //

var lastMonthDate = new Date(); //上月日期
lastMonthDate.setDate(1);
lastMonthDate.setMonth(lastMonthDate.getMonth()-1);
var lastYear = lastMonthDate.getYear();
var lastMonth = lastMonthDate.getMonth();






function getNowFormatDate()
{
var day = new Date();
var Year = 0;
var Month = 0;
var Day = 0;
var CurrentDate = "";
//初始化时间
//Year= day.getYear();//有火狐下2008年显示108的bug
Year= day.getFullYear();//ie火狐下都可以
Month= day.getMonth()+1;
Day = day.getDate();
//Hour = day.getHours();
// Minute = day.getMinutes();
// Second = day.getSeconds();
CurrentDate += Year + "";
if (Month >= 10 )
{
CurrentDate += Month + "";
}
else
{
CurrentDate += "0" + Month + "";
}
if (Day >= 10 )
{
CurrentDate += Day ;
}
else
{
CurrentDate += "0" + Day ;
}
return CurrentDate;
} 

		
		

//格局化日期：yyyy-MM-dd
function formatDate(date) {
var myyear = date.getFullYear();
var mymonth = date.getMonth()+1;
var myweekday = date.getDate();

if(mymonth < 10){
mymonth = "0" + mymonth;
}
if(myweekday < 10){
myweekday = "0" + myweekday;
}
return (myyear+"-"+mymonth + "-" + myweekday);
}

//获得某月的天数
function getMonthDays(myMonth){
var monthStartDate = new Date(nowYear, myMonth, 1);
var monthEndDate = new Date(nowYear, myMonth + 1, 1);
var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24);
return days;
}

//获得本季度的开端月份
function getQuarterStartMonth(){
var quarterStartMonth = 0;
if(nowMonth<3){
quarterStartMonth = 0;
}
if(2<nowMonth && nowMonth<6){
quarterStartMonth = 3;
}
if(5<nowMonth && nowMonth<9){
quarterStartMonth = 6;
}
if(nowMonth>8){
quarterStartMonth = 9;
}
return quarterStartMonth;
}

//获得当日的
function getNowDate() {
var lastWeekEndDate = new Date(nowYear, nowMonth,nowDay );
return formatDate(lastWeekEndDate);
}

//获得本周的开端日期
function getWeekStartDate() {
var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek);
return formatDate(weekStartDate);
}

//获得本周的停止日期
function getWeekEndDate() {
var    weekEndDate = new Date(nowYear, nowMonth, nowDay + (6 - nowDayOfWeek));
return formatDate(weekEndDate);
}

//获得上周的开端日期
function getLastWeekStartDate() {
var lastWeekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek -7);
return formatDate(lastWeekStartDate);
}

//获得上周的停止日期
function getLastWeekEndDate() {
var lastWeekEndDate = new Date(nowYear, nowMonth,nowDay + (6 - nowDayOfWeek - 7));
return formatDate(lastWeekEndDate);
}
//获取前一日
function getUpDayDate() {
	var  upDayDate = new Date(nowYear, nowMonth,nowDay-1);
	return formatDate(upDayDate);
}

//获得本月的开端日期
function getMonthStartDate(){
var monthStartDate = new Date(nowYear, nowMonth, 1);
return formatDate(monthStartDate);
}

//获得本月的停止日期
function getMonthEndDate(){
var monthEndDate = new Date(nowYear, nowMonth, getMonthDays(nowMonth));
return formatDate(monthEndDate);
}

//获得上月开端时候
function getLastMonthStartDate(){
var lastMonthStartDate = new Date(nowYear, lastMonth, 1);
return formatDate(lastMonthStartDate);
}

//获得上月停止时候
function getLastMonthEndDate(){
var lastMonthEndDate = new Date(nowYear, lastMonth, getMonthDays(lastMonth));
return formatDate(lastMonthEndDate);
}

//获得本季度的开端日期
function getQuarterStartDate(){

var quarterStartDate = new Date(nowYear, getQuarterStartMonth(), 1);
return formatDate(quarterStartDate);
}

//或的本季度的停止日期
function getQuarterEndDate(){
var quarterEndMonth = getQuarterStartMonth() + 2;
var quarterStartDate = new Date(nowYear, quarterEndMonth, getMonthDays(quarterEndMonth));
return formatDate(quarterStartDate);
} 

//获得当前日前一周
function getNowDaytoWeek(){
	var weekDate = new Date(nowYear, nowMonth,nowDay-7);
	return formatDate(weekDate);
}

//获得当前日前一个月
function getNowDaytoAMonth(){
	var monthDate = new Date(nowYear, nowMonth,nowDay-30);
	return formatDate(monthDate);
}

//获得当前日前一年
function getNowDaytoAyear(){
	var yearDate = new Date(nowYear, nowMonth,nowDay-365);
	return formatDate(yearDate);
}
//获得当前日前3个月
function getNowDayto3Month(){
	var yearDate = new Date(nowYear, nowMonth-3,nowDay);
	return formatDate(yearDate);
}
//获得当前日前6个月
function getNowDaytoHalfAyear(){
	var yearDate = new Date(nowYear, nowMonth-6,nowDay);
	return formatDate(yearDate);
}






