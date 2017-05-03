// JavaScript Document

function showPages(name) { //初始化属性
	this.name = name;      //对象名称
	this.page = 1;         //当前页数
	this.pageCount = 1;    //总页数
	this.argName = 'page'; //参数名
	this.showTimes = 1;    //打印次数
}

showPages.prototype.getPage = function(){ //丛url获得当前页数,如果变量重复只获取最后一个
	var args = location.search;
	var reg = new RegExp('[\?&]?' + this.argName + '=([^&]*)[&$]?', 'gi');
	var chk = args.match(reg);
	this.page = RegExp.$1;
}
showPages.prototype.checkPages = function(){ //进行当前页数和总页数的验证
	if (isNaN(parseInt(this.page))) this.page = 1;
	if (isNaN(parseInt(this.pageCount))) this.pageCount = 1;
	if (this.page < 1) this.page = 1;
	if (this.pageCount < 1) this.pageCount = 1;
	if (this.page > this.pageCount) this.page = this.pageCount;
	this.page = parseInt(this.page);
	this.pageCount = parseInt(this.pageCount);
}
//此方法已经被修改
showPages.prototype.createHtml = function(mode){ //生成html代码
	var strHtml = '', prevPage = this.page - 1, nextPage = this.page + 1;
	if (mode == '' || typeof(mode) == 'undefined') mode = 0;
	switch (mode) {

		case 2 : //模式2 (前后缩略,页数,首页,前页,后页,尾页)
			
			strHtml += '<span class="number">';
			if (prevPage < 1) {
				strHtml += '<span title="First Page">&#171;</span>';
				strHtml += '<span title="Prev Page">&#139;</span>';
			} else {
				strHtml += '<span title="First Page"><a href="javascript:toPage(1);">&#171;</a></span>';
				strHtml += '<span title="Prev Page"><a href="javascript:toPage(' + prevPage + ');">&#139;</a></span>';
			}
			if (this.page != 1) strHtml += '<span title="Page 1"><a href="javascript:toPage(1);">1</a></span>';
			if (this.page >= 5) strHtml += '<span>...</span>';
			if (this.pageCount > this.page + 2) {
				var endPage = this.page + 2;
			} else {
				var endPage = this.pageCount;
			}
			for (var i = this.page - 2; i <= endPage; i++) {
				if (i > 0) {
					if (i == this.page) {
						strHtml += '<span style="color: #fff;margin: 0px 5px;border: 1px solid #4288f7;background: #4288f7;width: 24px;height: 24px;display:inline-block;line-height: 24px;text-align: center;" title="Page ' + i + '">' + i + '</span>';
					} else {
						if (i != 1 && i != this.pageCount) {
							strHtml += '<span title="Page ' + i + '"><a href="javascript:toPage(' + i + ');">' + i + '</a></span>';
						}
					}
				}
			}
			if (this.page + 3 < this.pageCount) strHtml += '<span>...</span>';
			if (this.page != this.pageCount) strHtml += '<span title="Page ' + this.pageCount + '"><a href="javascript:toPage(' + this.pageCount + ');">' + this.pageCount + '</a></span>';
			if (nextPage > this.pageCount) {
				strHtml += '<span title="Next Page">&#155;</span>';
				strHtml += '<span title="Last Page">&#187;</span>';
			} else {
				strHtml += '<span title="Next Page"><a href="javascript:toPage(' + nextPage + ');">&#155;</a></span>';
				strHtml += '<span title="Last Page"><a href="javascript:toPage(' + this.pageCount + ');">&#187;</a></span>';
			}
			strHtml += '</span><br />';
			break;

	}
	return strHtml;
}
showPages.prototype.createUrl = function (page) { //生成页面跳转url
	if (isNaN(parseInt(page))) page = 1;
	if (page < 1) page = 1;
	if (page > this.pageCount) page = this.pageCount;
	var url = location.protocol + '//' + location.host + location.pathname;
	var args = location.search;
	var reg = new RegExp('([\?&]?)' + this.argName + '=[^&]*[&$]?', 'gi');
	args = args.replace(reg,'$1');
	if (args == '' || args == null) {
		args += '?' + this.argName + '=' + page;
	} else if (args.substr(args.length - 1,1) == '?' || args.substr(args.length - 1,1) == '&') {
			args += this.argName + '=' + page;
	} else {
			args += '&' + this.argName + '=' + page;
	}
	return url + args;
}
showPages.prototype.toPage = function(page){ //页面跳转
	var turnTo = 1;
	if (typeof(page) == 'object') {
		turnTo = page.options[page.selectedIndex].value;
	} else {
		turnTo = page;
	}
	self.location.href = this.createUrl(turnTo);
}
showPages.prototype.printHtml = function(mode){ //显示html代码
	this.getPage();
	this.checkPages();
	this.showTimes += 1;
	document.write('<div id="pages_' + this.name + '_' + this.showTimes + '" class="pages"></div>');
	document.getElementById('pages_' + this.name + '_' + this.showTimes).innerHTML = this.createHtml(mode);
	
}
showPages.prototype.formatInputPage = function(e){ //限定输入页数格式
	var ie = navigator.appName=="Microsoft Internet Explorer"?true:false;
	if(!ie) var key = e.which;
	else var key = event.keyCode;
	if (key == 8 || key == 46 || (key >= 48 && key <= 57)) return true;
	return false;
}

//以下是重写的分页方法，主要适用于ajax获取页面数据，
//详情可以参照accountdetail.jsp页面

var turnTo = 1;

function setPage(pg,page){
	pg.page = page;
} 

function checkPages(pg){ //进行当前页数和总页数的验证
	if (isNaN(parseInt(pg.page))) pg.page = 1;
	if (isNaN(parseInt(pg.pageCount))) pg.pageCount = 1;
	if (pg.page < 1) pg.page = 1;
	if (pg.pageCount < 1) pg.pageCount = 1;
	if (pg.page > pg.pageCount) pg.page = pg.pageCount;
	pg.page = parseInt(pg.page);
	pg.pageCount = parseInt(pg.pageCount);
}

function initPage(pageCount,page){  //pageCount总页数，page当前页数
	var pa = $('#pager');  
	var pg = new showPages('pg');
	setPage(pg,page);
	pg.pageCount =	pageCount; 
	checkPages(pg);
	document.getElementById('pager').innerHTML = pg.createHtml(2);
}

function toPage(page){ //页面跳转
	if (typeof(page) == 'object') {
		turnTo = page.options[page.selectedIndex].value;
	} else {
		turnTo = page;
	}
	getData();//需要在自己的页面写getData方法
}
