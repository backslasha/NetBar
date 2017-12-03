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
<title>A.I.Channel</title>

</head>
<body>
	<div class="ui inverted segment"></div>
	<h1 class='ui header'  align="center">HUMBERT NET BAR</h1>
	<p  align="center">Welcome to Humbert Net Bar Manager System!</p>
	<form class="ui form" name="loginForm" action="login.do" method="post">
		<div class="ui segment" align='center'>
			<div class="inline field" align="center">
				<div class="ui right pointing label">userNo</div>
				<input type="text" name="userNo" class="form-control"
					placeholder="userNo">
			</div>
			<div class="inline field" align="center">
				<div class="ui right pointing label">password</div>
				<input type="text" name="password" class="form-control"
					placeholder="password">
			</div>
			<div class="inline field" align="center">
				<div class="ui right pointing label">userType</div>
				<div style="width: 20%; display: inline-block">
					<select class="form-control" name="userType">
						<option>member</option>
						<option>manager</option>
						<option>boss</option>

					</select>
				</div>

				<div class="ui divider"></div>
				<div class="inline field" align='center'>
					<input type="button" value="register" class="ui  button"
						onclick="javascrtpt:window.location.href='register.jsp'"/>
					<input type='submit' value="okay" class="ui  button">
				</div>
			</div>

			<div align="center"></div>
	</form>

</body>
</html>