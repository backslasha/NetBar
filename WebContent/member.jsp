<%@page import="java.math.BigDecimal"%>
<%@page import="com.utils.Counter"%>
<%@page import="com.bean.Computer"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.bean.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	long millseconds = Calendar.getInstance().getTimeInMillis() - member.getLastLoginDate().getTime();
	int minutes =(int) Math.ceil(millseconds/1000f/60f);
	BigDecimal expense = Counter.expense(minutes);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="static/senmantic/semantic.min.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="1800">
<script type="text/javascript" src="js/member_operation.js"></script>
<title>会员<%=member.getName() %></title>
</head>
<body style="background-color: antiquewhite;">
	<center>
		<div class='ui segment' style="margin-top: 7%; width: 40%;">
			<h2 class="ui header" style="color: gray;">
				<span style="color: black;text-decoration: underline;"><%=member.getName()%></span> 正在电脑（编号：<span style="color: black;text-decoration: underline;"><%=computer.getComputerNo()%></span>)上网
			</h2>
			<table border='1' class='ui table'>
				<tr>
					<td>会员帐号</td>
					<td><%=member.getMemberNo()%></td>
				</tr>
				<tr>
					<td>姓名</td>
					<td><%=member.getName()%></td>
				</tr>
				<tr>
					<td>性别</td>
					<td><%=member.getGender()%></td>
				</tr>
				<tr>
					<td>年龄</td>
					<td><%=member.getAge()%></td>
				</tr>
				<tr>
					<td>账户余额</td>
					<td  id='funds_td'><%=member.getFunds().subtract(expense)%></td>
				</tr>
				<tr>
					<td>开机时间</td>
					<td><%=new SimpleDateFormat().format(member.getLastLoginDate())%></td>
				</tr>
				<tr>
					<td>使用时长</td>
					<td id="duration_td">00:00:00</td>
				</tr>
				<tr>
					<td>已产生费用</td>
					<td id="expense_td"><%=expense %> 元</td>
				</tr>
			</table>
			<form action="logout.do">
				<input type="hidden" name='userType' value="member" /> 
				<input type="hidden" name='memberNo' value="<%=member.getMemberNo()%>"/>
				<input type="hidden" name='computerNo' value="<%=computer.getComputerNo()%>"/>
				<input type="submit" value="结帐下机" onclick="logout(<%=member.getLastLoginDate().getTime()%>)" class="ui button" style="float: right;" />
			</form>
			<form action="logout.do">
				<input type="hidden" readonly="readonly" name='userType' value="member" /> 
				<input type="submit" name="updatePassword" value="修改密码" class="ui button" disabled="disabled"
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

		if(minutes%30==0&&seconds==0){
			location.reload(); 
		}
		duration = duration + 1000;
	}
	setInterval(flushDuration,1000);
</script>
</html>