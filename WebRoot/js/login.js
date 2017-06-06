//登录
function login() {
	var username = $('#username').val();
	var password = $('#password').val();
	//	var md5 = hex_md5(password+'nzj');
	var md5_pass = hex_md5(password);
	if(username == '' || password == '') {
		alert('账号或密码不能为空！')
	} else {
		$.ajax({
			type: "post",
			url: "back/admin/login",
			data: {
				"username": username,
				"password": md5_pass
			},
			dataType:'json',
			cache: false,
			success: function(data) {
				if(data.code==101) {
					alert(data.msg);
				} else if(data.code==100) {
					SetCookie('token', data.data.token);
					window.location.href = "index.html";
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

//写入到Cookie  
//name:cookie名称  value:cookie值   
function SetCookie(name, value) {  
    var Days = 30;  
    var exp = new Date();  
    exp.setTime(exp.getTime() + 7*24*60*60*1000);//过期时间7天
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();  
}  

