// JavaScript Document

function showPages(name) { //初始化属性
	this.name = name;      //对象名称
	this.page = 1;         //当前页数
	this.pageCount = 1;    //总页数
	this.argName = 'page'; //参数名
	this.showTimes = 1;    //打印次数
}

//此方法已经被修改
showPages.prototype.createHtml = function(mode, type){ //生成html代码
	var strHtml = '', prevPage = this.page - 1, nextPage = this.page + 1;
	if (mode == '' || typeof(mode) == 'undefined') mode = 0;
	switch (mode) {

		case 2 : //模式2 (前后缩略,页数,首页,前页,后页,尾页)
			
			strHtml += '<span class="number">';
			if (prevPage < 1) {
				strHtml += '<span title="首页">&#171;</span>';
				strHtml += '<span title="前一页">&#139;</span>';
			} else {
				strHtml += '<span title="首页"><a href="javascript:toPage(1,'+type+');">&#171;</a></span>';
				strHtml += '<span title="前一页"><a href="javascript:toPage(' + prevPage + ','+type+');">&#139;</a></span>';
			}
			if (this.page != 1) strHtml += '<span title="第 1 页"><a href="javascript:toPage(1,'+type+');">1</a></span>';
			if (this.page >= 5) strHtml += '<span>...</span>';
			if (this.pageCount > this.page + 2) {
				var endPage = this.page + 2;
			} else {
				var endPage = this.pageCount;
			}
			for (var i = this.page - 2; i <= endPage; i++) {
				if (i > 0) {
					if (i == this.page) {
						strHtml += '<span style="color: #fff;margin: 0px 5px;border: 1px solid #4288f7;background: #4288f7;width: 24px;height: 24px;display:inline-block;line-height: 24px;text-align: center;" title="第 ' + i + ' 页">' + i + '</span>';
					} else {
						if (i != 1 && i != this.pageCount) {
							strHtml += '<span title="第 ' + i + ' 页"><a href="javascript:toPage(' + i + ','+type+');">' + i + '</a></span>';
						}
					}
				}
			}
			if (this.page + 3 < this.pageCount) strHtml += '<span>...</span>';
			if (this.page != this.pageCount) strHtml += '<span title="第 ' + this.pageCount + ' 页"><a href="javascript:toPage(' + this.pageCount + ','+type+');">' + this.pageCount + '</a></span>';
			if (nextPage > this.pageCount) {
				strHtml += '<span title="下一页">&#155;</span>';
				strHtml += '<span title="末页">&#187;</span>';
			} else {
				strHtml += '<span title="下一页"><a href="javascript:toPage(' + nextPage + ','+type+');">&#155;</a></span>';
				strHtml += '<span title="末页"><a href="javascript:toPage(' + this.pageCount + ','+type+');">&#187;</a></span>';
			}
			strHtml += '</span><br />';
			break;

	}
	return strHtml;
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

function initPage(pageCount,page,pager,type){  //pageCount总页数，page当前页数
	var pa = $('#pager');  
	var pg = new showPages('pg');
	setPage(pg,page);
	pg.pageCount =	pageCount; 
	checkPages(pg);
	document.getElementById(pager).innerHTML = pg.createHtml(2,type);
}

function toPage(page,type){ //页面跳转
	if (typeof(page) == 'object') {
		turnTo = page.options[page.selectedIndex].value;
	} else {
		turnTo = page;
	}
	getData(type);//需要在自己的页面写getData方法
}
