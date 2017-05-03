// JavaScript Document

/*-------注册验证-----------*/
$().ready(function() {
	$("#signupForm").validate({
		rules : {
			telphone : {
				required : true,
				rangelength : [ 11, 11 ],
				digits : "只能输入整数"
			},
			emile : {
				required : true
			},
			username : {
				required : true
			},
			identity_card : {
				required : true
			},
			password : {
				required : true,
				rangelength : [ 6, 20 ]
			},
			confirm_password : {
				required : true,
				equalTo : "#password",
				rangelength : [ 6, 20 ]
			},
			passwordnew : {
				required : true,
				rangelength : [ 6, 20 ]
			},
			confirm_passwordnew : {
				required : true,
				equalTo : "#passwordnew",
				rangelength : [ 6, 20 ]
			},
			usernum1 : {
				required : true
			},
			paynum : {
				required : true
			},
			banknum : {
				required : true,
				rangelength : [ 16, 19 ]
			},
			payolde : {
				required : true
			},
			paynew : {
				required : true,
				rangelength : [ 6, 20 ]
			},
			confirm_paynew : {
				required : true,
				equalTo : "#paynew",
				rangelength : [ 6, 20 ]
			},
			oldPassWd : {
				required : true,
				rangelength : [ 6, 20 ]
			},
			newPassWd : {
				required : true,
				rangelength : [ 6, 20 ]
			},
			newconfirm_pay : {
				required : true,
				equalTo : "#newPassWd",
				rangelength : [ 6, 20 ]
			}
		},

		messages : {
			telphone : {
				required : "请输入手机号",
				rangelength : jQuery.format("请输入正确的手机号"),
			},
			emile : {
				required : "请输入邮箱或者账号",
			},
			username : {
				required : "请输入法人姓名",
			},
			identity_card : {
				required : "请输入法人身份证号码",
			},
			password : {
				required : "请输入新登录密码",
				rangelength : jQuery.format("密码6~20位字符组成"),
			},
			confirm_password : {
				required : "重新输入新登录密码",
				rangelength : jQuery.format("密码6~20位字符组成"),
				equalTo : "请再次输入上面的密码"
			},
			passwordnew : {
				required : "请输入新登录密码",
				rangelength : jQuery.format("密码6~20位字符组成"),
			},
			confirm_passwordnew : {
				required : "重新输入新登录密码",
				rangelength : jQuery.format("密码6~20位字符组成"),
				equalTo : "两次输入密码不一致"
			},
			usernum1 : {
				required : "请输入登录账号",
			},
			paynum : {
				required : "请输入结算账号",
			},
			banknum : {
				required : "请输入银行卡号",
				rangelength : jQuery.format("请正确输入位银行卡号"),
			},
			payolde : {
				required : "请输入原支付密码",
			},
			paynew : {
				required : "请输入新支付密码",
				rangelength : jQuery.format("密码6~20位字符组成"),
			},
			confirm_paynew : {
				required : "重新输入新支付密码",
				rangelength : jQuery.format("密码6~20位字符组成"),
				equalTo : "两次输入密码不一致"
			},
			oldPassWd : {
				required : "请输入原支付密码",
				rangelength : jQuery.format("密码6~20位字符组成")
			},
			newPassWd : {
				required : "请输入新支付密码",
				rangelength : jQuery.format("密码6~20位字符组成"),
			},
			newconfirm_pay : {
				required : "重新输入新支付密码",
				rangelength : jQuery.format("密码6~20位字符组成"),
				equalTo : "两次输入密码不一致"
			}
		}
	});

	$("#createUserForm").validate({
		rules : {
			userName : {
				required : true,
				rangelength : [ 11, 11 ],
				digits : "只能输入整数"
			},
			userEmail : {
				required : true
			}
		},
		messages : {
			userName : {
				required : "请输入手机号",
				rangelength : jQuery.format("请输入正确的手机号"),
			},
			userEmail : {
				required : "请输入邮箱或者账号",
			}
		}
	});
});
