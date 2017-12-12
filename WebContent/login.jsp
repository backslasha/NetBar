<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String message = (String) request.getSession().getAttribute("message");
	if (message != null) {
		out.println("<Script language='javascript'>alert('" + message + "')</Script>");
		request.getSession().invalidate();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css"
	href="static/senmantic/semantic.min.css">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请登陆</title>
<style type="text/css">


</style>
</head>
<body>
	<div class="ui inverted segment"></div>
	<h1 class='ui header'  align="center">欢迎网吧会员登陆</h1>
	<p  align="center">数据库系统概论期末作业</p>
	<form class="ui form" name="loginForm" action="login.do" method="post">
		<div class="ui segment" align='center'>
			<div class="inline field" align="center">
				<div class="ui right pointing label">帐号</div>
				<input type="text" name="userNo" class="form-control"
					placeholder="帐号">
			</div>
			<div class="inline field" align="center">
				<div class="ui right pointing label">密码</div>
				<input type="text" name="password" class="form-control"
					placeholder="密码">
			</div>
			<div class="inline field" align="center">
				<div class="ui right pointing label">用户类型</div>
				<div style="width: 20%; display: inline-block">
					<select class="form-control" name="userType">
						<option value="member">会员</option>
						<option value="manager">网管</option>
						<option value="boss">老板</option>
					</select>
				</div>

				<div class="ui divider"></div>
				<div class="inline field" align='center'>
					<input type="button" value="注册" class="ui  button"
						onclick="javascrtpt:window.location.href='register.jsp'"/>
					<input type='submit' value="登陆" class="ui  button">
				</div>
			</div>

			<div align="center"></div>
	</form>

</body>
</html>