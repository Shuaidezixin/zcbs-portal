// JavaScript Document
var CLASS = (function (CLASS) {
	
	//检测是否存在该方法和属性
	if (typeof CLASS.extend != 'function') {
	
		CLASS.extend = function (parent, obj) {
			
			for ( var attr in obj) {
				
				if (! parent.hasOwnProperty(attr)) {
					parent[attr] = obj[attr];
				};
				
			}
		};
			
	};
	
	return CLASS;
}(CLASS || {}))
CLASS.Tab = function (type, menu, cont, callBack){
	this.type = type;
	this.menu = menu;
	this.cont = cont || {};
	this.callBack = callBack;
		
}
CLASS.Tab.prototype = {
	init: function(arg){
		var This = this,
			Than;
	
		this.menu.each(function(index){
			
			$(this)[This.type](function(){
				Than = $(this);
				This.menu.each(function () {
					$(this).removeClass(arg);	
				})
				
				$(this).addClass(arg);
				
				This.cont.each(function () {
					$(this).addClass('hide');	
					
				})
				This.cont.eq(index).removeClass('hide');
				
				if (This.callBack) {
					This.callBack.call(Than, index);	
				}
			})	
		})	
		
	}
};


/*标签切换tab*/
$(function(){
	var tab=new CLASS.Tab('click', $('.flow_tit li'), $('.flow_item'));
	tab.init("active");
	
	var tab=new CLASS.Tab('click', $('.deal_tit li'), $('.deal_item'));
	tab.init("active");
	
	

});
























