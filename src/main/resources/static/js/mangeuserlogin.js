$(function () {
	// 页面初始化生成验证码
	window.onload = createCode('#loginCode');
	// 验证码切换
	$('#loginCode').click(function () {
		createCode('#loginCode');
	});
});

function subMethod() {
	var name=$("#loginUsername").val();
	var pass=$("#loginPassword").val();
	var inputCode=$("#loginCard").val().toUpperCase();
	var cardCode=$("#loginCode").val();

	if(name.length>0&&pass.length>0){
		// if(inputCode===cardCode){
			$("#form-date").attr("action", $("#form-date").attr("action"));
			$("#form-date").submit();
		// }else {
		// 	createCode('#loginCode');
		// 	alert("验证码输入有误！");
		// }
	}else {
		createCode('#loginCode');
		alert("账号或密码不能为空");

	}
	return false;
}

// 生成验证码
function createCode(codeID) {
	var code = "";
	// 验证码长度
	var codeLength = 4;
	// 验证码dom元素
	var checkCode = $(codeID);
	// 验证码随机数
	var random = [0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
		'S','T','U','V','W','X','Y','Z'];
	for (var i = 0;i < codeLength; i++){
		// 随机数索引
		var index = Math.floor(Math.random()*36);
		code += random[index];
	}
	// 将生成的随机验证码赋值
	checkCode.val(code);
}