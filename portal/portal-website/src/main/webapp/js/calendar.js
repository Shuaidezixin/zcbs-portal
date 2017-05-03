// JavaScript Document

var VIEW_DATA = {

	// slect控件
	selStyle : {
		"selHover" : "active",
		"selTips" : "sel_down"
	},
	checkStyle : {
		"init" : "init",
		"selection" : "selection",
		"disabled" : "disabled"
	},
	radioStyle : {
		"init" : "init",
		"selection" : "selection",
		"disabled" : "disabled"
	},
	inputStyle : {
		"init" : "input_text",
		"hover" : "input_hover",
		"focus" : "input_focus",
		"error" : "input_error",
		"disabled" : "input_disabled"
	}
};

var common = (function(common) {

	// 检测是否存在该方法和属性
	if (typeof common.extend != 'function') {

		common.extend = function(parent, obj) {

			for ( var attr in obj) {

				if (!parent.hasOwnProperty(attr)) {
					parent[attr] = obj[attr];
				}
				;

			}
		};

	}
	;
	/**
	 * 仿select的控件
	 */
	common.extend(common, {
		ImitationSelect : function(obj, callBack) {
			var parent = obj.parents('.imitation');
			this.selText = obj;
			this.callBack = callBack;
			this.selData = parent.find('.data_list');
			this.selTips = parent.find('.select_tips')
			this.selVal = parent.find('.select_val');
			this.init();

		}
	});

	common.ImitationSelect.prototype = {
		init : function() {
			var This = this;

			this.selText.click(function(ev) {

				if (This.selData.is(':visible')) {
					$('.data_list').hide();
					This.selTips.removeClass(VIEW_DATA.selStyle.selTips);
				} else {
					This.selData.show();
					This.selTips.addClass(VIEW_DATA.selStyle.selTips);
				}
				;
				if (This.callBack) {
					This.callBack.call(This.selText);
				}
				ev.stopPropagation()
			});
			this.selTips.click(function(ev) {

				if (This.selData.is(':visible')) {
					$('.data_list').hide();
					This.selTips.removeClass(VIEW_DATA.selStyle.selTips);
				} else {
					This.selData.show();
					This.selTips.addClass(VIEW_DATA.selStyle.selTips);
				}
				ev.stopPropagation();
			});

			this.toHide(this.selData);
		},
		toHide : function(obj) {
			var This = this;
			$(document).click(function(ev) {
				obj.hide();
				This.selTips.removeClass(VIEW_DATA.selStyle.selTips);
			})

		},
		getVal : function(arr, fn) {
			var This = this;

			arr.each(function(index) {

				$(this).hover(

						function() {
							$(this).addClass(VIEW_DATA.selStyle.selHover)
									.siblings().removeClass(
											VIEW_DATA.selStyle.selHover);
						});

				$(this).mousedown(function() {
					getText($(this));

				});

				function getText(obj) {
					This.selText.text(obj.text());
					This.selVal.val(obj.attr('val'));

					obj.addClass(VIEW_DATA.selStyle.selHover);

					if (fn) {
						fn.call(obj);
					}

				}
				;
			});

		}

	};
	return common
}(common || {}));
$(function() {

	var $1 = new common.ImitationSelect($("#status"))
	$1.getVal($('#status').next().find('li'));

	var $2 = new common.ImitationSelect($("#status2"))
	$2.getVal($('#status2').next().find('li'));

	new common.Radio($('.radioBox'))
	if (typeof SelDate == 'function') {
		new SelDate($('#time'))
		new SelDate($('#time2'))
		new SelDate($('#time3'))
	}

})

/* 性别选择 */

common.extend(common, {
	Radio : function(obj) {
		this.obj = obj;
		this.init();
	}
});

common.Radio.prototype = {
	init : function() {
		var This = this, aRadio = this.obj.find('.radio_wrap'), oInput, val, oRadio;

		aRadio.click(function() {
			oRadio = $(this).find('.init');
			val = oRadio.attr('data_text');
			oInput = $(this).parents('.radioBox').find('[type=hidden]');

			// 如果checkbox是禁用状态
			if (oRadio.hasClass(VIEW_DATA.radioStyle.disabled)) {
				return;
			} else {

				if (!oRadio.hasClass(VIEW_DATA.radioStyle.selection)) {
					$(this).parents('.radioBox').find('.init').removeClass(
							VIEW_DATA.radioStyle.selection);
					oRadio.addClass(VIEW_DATA.radioStyle.selection);
					oInput.val(val);
					oInput.attr('sVal', val)
				}
			}

		});

	}
};

/**
 * 日历
 */

function SelDate(obj) {

	if (!obj.length)
		return;
	this.oText = obj;
	this.oBox = this.oText.next('.date_list');
	this.oPrevYear = this.oBox.find('.prev_year')
	this.oPrevMonth = this.oBox.find('.prev_month')
	this.oNextYear = this.oBox.find('.next_year')
	this.oNextMonth = this.oBox.find('.next_month')
	this.oTable = this.oBox.find('.date_body')
	this.bBtn = true;
	this.data_date = null;

	this.showDate();

}
SelDate.prototype = {
	constructor : SelDate,
	showDate : function() {
		var This = this;
		this.oText.focus(function() {

			//var oDate = new Date();
			var oDate =new Date(this.value);
			This.data_date = {
				year : oDate.getFullYear(),
				month : oDate.getMonth() + 1,
				//day:oDate.getDate()
			};
			This.oBox.show();
			This.createDate(This.data_date);

		});
		this.oText.click(function(ev) {
			ev.stopPropagation();
		});
		// 上一年
		this.oPrevYear.click(function(ev) {
			This.data_date.year = This.data_date.year - 1;
			This.createDate(This.data_date);
			ev.stopPropagation();
		});

		// 上一个月
		this.oPrevMonth.click(function(ev) {

			This.data_date.month = This.data_date.month - 1;

			if (This.data_date.month == 0) {
				This.data_date.month = 12;

				if (This.data_date.month == 12) {

					// 条件满足时年也应该相应的减少一年
					This.data_date.year--;

				}
				;

			}
			;

			This.createDate(This.data_date);
			ev.stopPropagation();
		});

		// 下一年
		this.oNextYear.click(function(ev) {

			This.data_date.year = This.data_date.year + 1;
			This.createDate(This.data_date);
			ev.stopPropagation();
		});

		// 下一个月
		this.oNextMonth.click(function(ev) {

			This.data_date.month = This.data_date.month + 1;

			if (This.data_date.month == 13) {
				This.data_date.month = 1;

				if (This.data_date.month == 1) {

					// 条件满足时年也应该相应的增加一年
					This.data_date.year++;

				}
			}
			;

			This.createDate(This.data_date);
			ev.stopPropagation();
		});

		$(document).click(function() {

			This.oBox.hide();

		});

	},
	createDate : function(data) {
		var days = this.getDays(data.year, data.month), This = this, oDate = new Date(), prevDays, oTboby, oTr, oTd, weeks, index, aTd;

		// 生成年和月
		this.showHeadDate(data);
		if (this.bBtn) {

			oTboby = document.createElement('tbody');

			for (var i = 0; i < 6; i++) {
				oTr = document.createElement('tr');

				for (var j = 0; j < 7; j++) {
					oTd = document.createElement('td');
					oTr.appendChild(oTd);
				}
				oTboby.appendChild(oTr);
			}
			this.oTable[0].appendChild(oTboby);

			this.bBtn = false;
		}
		;
		oDate.setFullYear(data.year);
		oDate.setMonth(data.month - 1);
		oDate.setDate(1);
		weeks = oDate.getDay();
		console.log(data)
		switch (weeks) {
		case 0:
			index = 0;
			break;
		case 1:
			index = 1;
			break;
		case 2:
			index = 2;
			break;
		case 3:
			index = 3;
			break;
		case 4:
			index = 4;
			break;
		case 5:
			index = 5;
			break;
		case 6:
			index = 6;
			break;
		// default
		}
		;

		// 显示日期
		aTd = this.oTable.find('td');

		if (data.month == 1) {
			prevDays = 12;
		} else {
			prevDays = data.month - 1;
		}
		;

		aTd.each(function(i) {
			if (i < index) {
				$(this).html(
						'<b style="color:#cfcfcf">'
								+ (This.getDays(data.year, prevDays) + 1
										- index + i) + '</b>');
			} else if (i >= days + index) {
				$(this).html(
						'<b style="color:#cfcfcf">' + -(days + index - 1 - i)
								+ '</b>');
			} else {
				if (data.year == new Date().getFullYear()
						&& data.month == new Date().getMonth() + 1) {

					if (new Date().getDate() + index - 1 == i) {
						$(this).addClass('active');
					}
					// aTd[new Date().getDate()+index-1].addClass('active');
				} else {
					$(this).removeClass('active');
				}
				;
				$(this).html(i - index + 1);
			}
			;
			$(this).mousedown(function() {
				This.addDate(this, data);
			});
		})

	},

	showHeadDate : function(data) {
		var oMonth = $('.show_month'), oYear = $('.show_year');

		oMonth.html(data.month);
		oYear.html(data.year);
	},

	addDate : function(obj, data) {

		var dateHtml = obj.innerHTML, re = /^\d+$/;
		var s = data.month;
		if (data.month.toString().length < 2 & data.month < 10) {
			data.month = "0" + data.month;
		}
		if (obj.innerHTML.length < 2 & obj.innerHTML < 10) {
			obj.innerHTML = "0" + obj.innerHTML;
		}
		if (re.test(dateHtml)) {

			this.oText.val(data.year + '-' + data.month + '-' + obj.innerHTML);

		} else {
			return '';
		}
		;

	},

	getDays : function(year, month) {

		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else if (month == 2) {

			if ((year % 4 == 0 && year % 100 != 0)
					|| (year % 100 == 0 && year % 400 == 0)) {
				return 29;
			} else {

				return 28;

			}
		}
	}
};
