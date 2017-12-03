<%@page import="com.bean.Computer"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.bean.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css"
	href="static/senmantic/semantic.min.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member</title>
</head>
<%
	Member member = (Member) request.getSession().getAttribute("member");
	if (member == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	Computer computer = (Computer) request.getSession().getAttribute("computer");
	if (computer == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	long loginTime = member.getLastLoginDate().getTime();
%>
<body style="background-image: url(images/background.jpg)">
	<center>

		<%=request.getSession().getId()%>

		<div class='ui segment' style="margin-top: 7%; width: 40%;">
			<h2 class="ui header">
				Welcome
				<%=member.getName()%>，假装你正在电脑（编号：<%=computer.getComputerNo()%>)上网
			</h2>
			<table border='1' class='ui table'>
				<tr>
					<td>Account</td>
					<td><%=member.getMemberNo()%></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><%=member.getName()%></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td><%=member.getGender()%></td>
				</tr>
				<tr>
					<td>Age</td>
					<td><%=member.getAge()%></td>
				</tr>
				<tr>
					<td>Funds</td>
					<td><%=member.getFunds()%></td>
				</tr>
				<tr>
					<td>LoginTime</td>
					<td><%=new SimpleDateFormat().format(member.getLastLoginDate())%></td>
				</tr>
				<tr>
					<td>Duration</td>
					<td id="duration_td"></td>
				</tr>
			</table>
			<form action="logout.do">
				<input type="hidden" name='userType' value="member" /> 
				<input type="hidden" name='memberNo' value="<%=member.getMemberNo()%>"/>
				<input type="hidden" name='computerNo' value="<%=computer.getComputerNo()%>"/>
				<input type="submit" value="结帐下机" class="ui button" style="float: right;" />
			</form>
			<form action="logout.do">
				<input type="hidden" name='userType' value="member" /> <input
					type="submit" name="updatePassword" value="修改密码" class="ui button"
					style="float: right;" />
			</form>
		</div>
	</center>
</body>

<script type="text/javascript">
	var now = new Date().getTime();
	var logintime =<%=member.getLastLoginDate().getTime() + ""%>;
	var duration = now - logintime;
	var durationTd = document.getElementById('duration_td');
	function flushDuration(){
		var temp = duration;
		var hours = parseInt(temp / 1000 / 60 / 60);
		temp = temp%(1000*60*60);
		var minutes = parseInt(temp / 1000 / 60);
		temp = temp%(1000*60);
		var seconds = parseInt(temp / 1000);
		
		if(hours<=9){  
			hours = "0".concat(hours);
		}
		if(minutes<=9){
			minutes = "0".concat(minutes);
		}
		if(seconds<=9){
			seconds = "0".concat(seconds);
		}
		durationTd.innerHTML = hours+":"+minutes+":"+seconds;
		duration = duration + 1000;
	}
	setInterval(flushDuration,1000);
</script>
</html>