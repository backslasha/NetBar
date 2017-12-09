<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.bean.Manager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Manager manager = (Manager) request.getSession().getAttribute("manager");
	if (manager == null) {
		response.sendRedirect("login.jsp");
		return;
	}
%>
<html>
<link rel="stylesheet" type="text/css"
	href="static/senmantic/semantic.min.css">
	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
<script src="js/manager.js"></script>

<style type="text/css">
body {
	background-image: url(images/background.jpg);
}

form, button {
	margin: 4px !important;
	padding: 10px !important;
}

.main {
	width: 920px;
	margin: 36px auto;
	padding: 0 auto;
	overflow: hidden;
}

.header {
	float: left;
	width: auto;
}

.buttonPanel {
	clear: both;
	margin-right: 36px;
	float: left; width : 16%; height : 60%;
	float: left;
	width: 16%;
	height: 60%;
	padding: 1% !important;
}

.content {
	float: left;
	width: auto;
}
</style>
</head>

	<div class="main">

		<h2 class="ui segment header" style="color: gray; line-height: 0">
			管理员 <span style="color: black; text-decoration: underline;"><%=manager.getName()%></span>
			正在值班
		</h2>

		<div class='ui segment buttonPanel'>
			<button class="ui button menu" 
				style="display: inline" onclick="managerMessage(<%=manager.getManagerNo()%>)">值班人员</button>
				
			<button class="ui button menu" 
				style="display: inline" onclick="computerStatus(0,7)">电脑概况</button>
				
			<button class="ui button menu"
				style="display: inline" onclick="members(0,7)">会员信息</button>

			<button class="ui button menu"
				style="display: inline" onclick="consumptions(0,7)">消费记录</button>
			
			<button class="ui button menu" 
				style="display: inline" onclick="computerUses(0,7)">使用记录</button>
			
			<button class="ui button menu" 
				style="display: inline" onclick="">损坏记录</button>

			<button class="ui button menu"
				style="display: inline" onclick="">管理中心</button>

			<button class="ui button menu" 
				style="display: inline" onclick="">登出</button>

		</div>

		<div style="display: inline; float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div id='container' class='ui segment content' style="float: left;min-width: 640px">
		</div>

	</div>
</body>
<script type="text/javascript">
	managerMessage(<%=manager.getManagerNo()%>);
</script>
</html>