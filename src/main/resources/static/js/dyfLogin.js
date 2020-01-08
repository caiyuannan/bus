$(function () {

})
var userName ;
var passWord1;
/*用户登入 */
function login() {
	var imgUrl = $("#imgPathUrl").val();
	var aHref = $("#aHrefValue").val();
	var userName = $("#login_number").val();
	var userPs = $("#login_password").val();
	if (userName.length<1||userPs.length<1) {
		alert("输入账号或密码为空，请输入")
	}else {
		$.ajax({
			url :'http://localhost:8080/bus/htmlLogin',
			type: "post",
			data:{userName:userName,userPs:userPs},
			datatype: "json",
			async: true,
			success:function (res) {
				if (res!=null&&res!="")  {
					alert("登入成功");
					$("#user").show();
					$("#welecome").text('欢迎你，'+res.userName);
					$("#welecome").show();
					$("#outUser").show();
					$("#userSecssion").val(res);
					$("#imgUser").attr('src',imgUrl+res.userHeadPortrait);
					$("#user").attr('href',aHref+res.userPhoneNumber);
					$("#liUser").show();
					$("#Login_start_").hide();
					$("#Regist_start_").hide();
					someClost();
				}else {
					alert("登入失败请重试")
				}
			},
			error:function () {
				alert()("数据异常，请查看网络")
			}
		})
	}

}
function registUser() {
	 userName = $("#regist_account").val();  //用户名
	 passWord1 = $("#regist_password1").val(); //密码1
	var passWord2 = $("#regist_password2").val();//密码2
	var phoneNum = $("#regist_phone").val();    //电话号码
	var vcode = $("#regist_vcode").val();       //验证码
	console.log(dyfMessageBean);
	var numberTi = 6;
	if (userName.length<2){
		alert("用户名不能为空");
		numberTi--;
		return;
	}
	if (passWord1.length<2||passWord1.length<2) {
		alert("两次密码必须填写");
		numberTi--;
		return;
	}
	if (passWord1 != passWord2){
		alert("两次密码必须相同");
		numberTi--;
		return;
	}
	if (phoneNum.length!=11){
		alert("请输入完整的手机号码，否则注册验证码无法发送到手机上");
		numberTi--;
		return;
	}
	/*if (dyfMessageBean.num.isNaN(NaN)) {
		alert("请先确认信息有验证信息");
		numberTi--;
		return;
	}*/
	if (vcode!=dyfMessageBean.num){
		alert("请输入正确的短信验证码");
		numberTi--;
	}
	if (numberTi===6) {
		$.ajax({
			url :'http://localhost:8080/bus/userRegisetUser',
			type: "post",
			data:{userName:userName,passWord1:passWord1,phoneNum:phoneNum},
			datatype: "json",
			async: true,
			success:function (res) {
				//判断登入成功
				if (res==="success") {
					alert("注册成功，请返回登入界面登入");
					$("#toLogin").click(function(){
						$("#regist_container").hide(500);
						$("#login_container").show(500);
					});
					$("#login_number").val(userName);
					$("#login_password").val(passWord1);
				}else if (res==="fail"){
					alert("注册失败，请稍后重试")
				}
			},
			error:function () {
				alert("数据异常，请联系管理员")
			}
		})
	}

}

//短信窗口立即登入按钮跳转到登入窗口方法
function reg(){
	$("#messageLogin").hide(500);
	$("#login_container").show(500);
}

var clock = '';
	var nums = 30;
	var btn;
	var loginMsgUser=1;
	var phoneNum;
	function sendCode1(thisBtn) {
		 phoneNum = $("#loginPhoneNumber").val();    //电话号码
		if (phoneNum.length != 11) {
			alert("请先输入正确的电话号码后再获取验证码登入")
		} else {
			btn = thisBtn;
			btn.disabled = true; //将按钮置为不可点击
			btn.value = '重新获取（' + nums + '）';
			clock = setInterval(doLoop, 1000); //一秒执行一次
			$.ajax({
				url: 'http://localhost:8080/bus/userMsgLogin',
				type: "post",
				data: {phoneNum: phoneNum},
				datatype: "json",
				async: true,
				success: function (res) {
				 if (res.status==="success") {
				 	console.log(res);
						alert("发送成功");
					 loginMsgUser = res;
					} else {
						alert("该账号还未注册，请先注册");
					    nums = 0;
					}

				},
				error: function () {
					alert("网络数据异常，请联系管理员")
				}
			})
		}

	}

	function loginMsg() {
		var userLoginView = $("#loginPhoneNumber").val();
		var regist_vcode1 = $("#regist_vcode1").val();
		var imgUrl = $("#imgPathUrl").val(); //工程路径
		if (null!=userLoginView&&userLoginView!='') {

			if (loginMsgUser!=1&&userLoginView === phoneNum && loginMsgUser.num === parseInt(regist_vcode1.trim())) {
				$.ajax({
					url: 'http://localhost:8080/bus/userLoginMsg',
					type: "post",
					data: {userLoginView: userLoginView},
					datatype: "json",
					async: true,
					success: function (res) {
						if (null != res && "" != res) {
							var aHref = $("#aHrefValue").val();
							alert("登入成功");
							$("#userSecssion").val(res);
							$("#user").show();
							$("#welecome").text('欢迎你，' + res.userName);
							$("#welecome").show();
							$("#outUser").show();
							$("#imgUser").attr('src', imgUrl + res.userHeadPortrait);
							$("#user").attr('href',aHref+res.userPhoneNumber);
							$("#liUser").show();
							$("#Login_start_").hide();
							$("#Regist_start_").hide();
							someClost();
						}

					}
				})
			} else {
				alert("请确认电话号码和验证码正确")
			}
		}else {
			alert('请先填写手机号并发送信息')

		}

	}

	function someClost() {
		$("#_close").animate({
			height: '0px',
			width: '0px'
		}, 500, function() {
			$("#_close").hide(500);
			$("#login_container").hide(500);
			$("#regist_container").hide(500);
			$("#messageLogin").hide(500);
			$("#_start").animate({
				left: '0px',
				height: '0px',
				width: '0px'
			}, 500);
		});
	}

