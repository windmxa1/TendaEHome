<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		var token = "";
		var user = {
			username : 1,
			password : 12
		};
		$.ajax({
			type : "post",
			url : "back/admin/login",
			data : user,
			dataType : "json",
			//contentType : 'application/json;charset=utf-8',
			async : false,
			cache : false,
			success : function(data) {
				token = data.data.token;
				console.log(token);
			},
			error : function(data) {
				alert("error");
			}
		});
		$("#btn").click(function() {
			var json1 = {};
			$.ajax({
				type : "post",
				url : "back/user/getUserList",
				data : json1,
				dataType : "json",
				headers : {
					"token" : token
				},
				async : false,
				cache : false,

				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});
		$("#btn1").click(function() {
			var json1 = {
				state : 2
			};
			$.ajax({
				type : "post",
				url : "back/orders/getOrdersList",
				data : json1,
				dataType : "json",
				headers : {
					"token" : token
				},
				async : false,
				cache : false,

				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});
		$("#btn2").click(function() {
			var json1 = {
				state : 3,
				id : 2
			};
			$.ajax({
				type : "post",
				url : "back/orders/updateOrder",
				data : json1,
				dataType : "json",
				headers : {
					"token" : token
				},
				async : false,
				cache : false,

				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});
		$("#btn3").click(function() {
			var json1 = {
				limit : 1
			};
			$.ajax({
				type : "post",
				url : "back/garousel/getGarousels",
				data : json1,
				dataType : "json",
				headers : {
					"token" : token
				},
				async : false,
				cache : false,
				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});
		$("#btn4").click(function() {
			var json1 = {
				limit : 1
			};
			$.ajax({
				type : "post",
				url : "back/garousel/getCatalog",
				data : json1,
				dataType : "json",
				headers : {
					"token" : token
				},
				async : false,
				cache : false,
				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});
		$("#btn5").click(function() {
			var json1 = {
				id : 12
			};
			$.ajax({
				type : "post",
				url : "back/garousel/deleteGarousel",
				data : json1,
				dataType : "json",
				headers : {
					"token" : token
				},
				async : false,
				cache : false,
				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});
		$("#btn7").click(function() {
			var json1 = {
				id : 13,
				title : "123哈",
				url : "",
				catalogId : "1",
				hyperlink : ""
			};
			$.ajax({
				type : "post",
				url : "back/garousel/updateGarousel",
				data : json1,
				dataType : "json",
				headers : {
					"token" : token
				},
				async : false,
				cache : false,
				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});
		$("#btn8").click(function() {
			var json1 = {
				catalog : "啊实打实"
			};
			$.ajax({
				type : "post",
				url : "back/garousel/addCatalog",
				data : json1,
				dataType : "json",
				headers : {
					"token" : token
				},
				async : false,
				cache : false,
				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});
		$("#btn9").click(function() {
			var json1 = {
				id : 3,
				catalog : "啊实打asdasdf"
			};
			$.ajax({
				type : "post",
				url : "back/garousel/updateCatalog",
				data : json1,
				dataType : "json",
				headers : {
					"token" : token
				},
				async : false,
				cache : false,
				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});
		$("#btn10").click(function() {
			var json1 = {
				id : 4
			};
			$.ajax({
				type : "post",
				url : "back/garousel/deleteCatalog",
				data : json1,
				dataType : "json",
				headers : {
					"token" : token
				},
				async : false,
				cache : false,
				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});
		$("#btn11").click(function() {
			var form = new FormData(document.getElementById("garousel1"));
			$.ajax({
				type : "post",
				url : "back/garousel/updateGarousel",
				headers : {
					"token" : token
				},
				data : form,
				processData : false,
				contentType : false,
				dataType : "json",
				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});
		$("#btn12").click(function() {
			var form = new FormData(document.getElementById("garousel2"));
			form.append("","");
			$.ajax({
				type : "post",
				url : "back/garousel/addGarousel",
				headers : {
					"token" : token
				},
				data : form,
				processData : false,
				contentType : false,
				dataType : "json",
				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");

				}
			});
		});
		$("#btn13").click(function() {
			$.ajax({
				type : "post",
				url : "app/user/getUserInfo",
				data : {},
				dataType : "json",
				headers : {
					"token" : token
				},
				async : false,
				cache : false,
				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});
		$("#btn14").click(function() {
			var json = {orderNum:1465};
			$.ajax({
				type : "post",
				url : "back/orders/getOrderByOrderNum",
				data : json,
				dataType : "json",
				headers : {
					"token" : token
				},
				async : false,
				cache : false,
				success : function(data) {
					console.log(JSON.stringify(data));
				},
				error : function(data) {
					alert("error");
				}
			});
		});

	});
</script>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>

<body>
	<input type="button" id="btn" value="getUserList">
	<br>
	<input type="button" id="btn1" value="getOrdersList">
	<br>
	<input type="button" id="btn2" value="updateOrder">
	<br>
	<input type="button" id="btn3" value="getGarousels">
	<br>
	<input type="button" id="btn4" value="getCatalog">
	<br />
	<input type="button" id="btn5" value="deleteGarousel">
	<br>
	<input type="button" id="btn6" value="insertGarousel">
	<br>
	<input type="button" id="btn7" value="updateGarousel">
	<br>
	<input type="button" id="btn8" value="insertCatalog">
	<br>
	<input type="button" id="btn9" value="updateCatalog">
	<br>
	<input type="button" id="btn10" value="deleteCatalog">
	<input type="button" id="btn13" value="getUserInfo">
	<input type="button" id="btn14" value="getOrderByOrderNum">
	<br>
	<div class="container">
		<form action="" id="garousel1">
			<div class="form-group">
				<label for="exampleInputFile">图片</label> <input type="file"
					id="exampleInputFile" name="file">
				<p class="help-block">请上传轮播图图片</p>
			</div>
			id<br /> <input class="form-control" type="text" name="id" /> <br />
			标题<br /> <input class="form-control" type="text" name="title" /> <br />
			目录<br /> <select class="form-control" name="catalogId">
				<option value="1">首页轮播图</option>
				<option value="2">一米菜园轮播图</option>
			</select> <br />
			<div class="form-group">
				<label>外链</label><br /> <input type="text" name="hyperlink"
					id="hyperlink">
			</div>
			<input type="button" id="btn11" value="提交">
		</form>
	</div>
	<div class="container">
		<form action="" id="garousel2">
			<div class="form-group">
				<label for="exampleInputFile">图片</label> <input type="file"
					id="exampleInputFile" name="file">
				<p class="help-block">请上传轮播图图片</p>
			</div>
			标题<br /> <input class="form-control" type="text" name="title" /> <br />
			目录<br /> <select class="form-control" name="catalogId">
				<option value="1">首页轮播图</option>
				<option value="2">一米菜园轮播图</option>
			</select> <br />
			<div class="form-group">
				<label>外链</label><br /> <input type="text" name="hyperlink"
					id="hyperlink">
			</div>
			<input type="button" id="btn12" value="提交">
		</form>
	</div>
</body>
</html>
