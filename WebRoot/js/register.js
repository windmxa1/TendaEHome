//注册
function register() {
	var username = $('#username').val();
	var password = $('#password').val();
	var passwords = $('#passwords').val();
	//	var md5 = hex_md5(password+'nzj');
	var md5_password = hex_md5(password);
	if(username == '' || password == '') {
		alert('账号或密码不能为空！')
	} else if(password != passwords) {
		alert('密码不一致！')
	} else {
		$.ajax({
			type: "post",
			url: mainUrl + "back/admin/register",
			data: {
				"username": username,
				"password": md5_password
			},
			cache: false,
			success: function(data) {
				if(data.code==101) {
					alert(data.msg);
				} else if(data.code==100) {
					if(confirm("注册成功，是否跳转到登录页？")) {
						window.location.href = "login.html";
					}
				} else {
					alert(data.msg);
				}
			},
			error: function(data) {
				alert("error");
			}
		});
	}
};