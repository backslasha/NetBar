<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.bean.Boss"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Boss manager = (Boss) request.getSession().getAttribute("boss");
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
<title>老板先生！</title>
<script src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		window.containerContent="managerMessage";
	     $(".menu").mouseenter(function() {
	        $(this).animate({	
	            height: '+=10px',
	            width: '+=10px'
	        },
	        100);
	    }).mouseout(function() {
	        $(this).animate({
	            height: '-=10px',
	            width: '-=10px'
	        },
	        100);
	    }).click(function(){
	    	$(".pin").removeClass("pin").addClass("menu");
			$(this).removeClass("menu").addClass("pin");
	     });
	});
</script>
<script charset="utf-8" src="js/manager_operation.js"></script>
<script charset="utf-8" src="js/core.js"></script>
<script charset="utf-8" src="js/boss_operation.js"></script>

<style type="text/css">
body {
	    background-color: khaki;
}

td{
    width: 72px;
    max-width: 20em;  
    white-space: nowrap;
    overflow: hidden;
}

.menu{
	margin: 4px !important;
	width:auto  !important;
	padding: 10px !important;
}
.main {
	width: 1120px;
	margin: 64px auto;
	padding: 0 auto;
	overflow: hidden;
}

.header {
	float: left;
	width: auto;
}

.buttonPanel {
	clear: both;
	float: left;
	width: 16%;
	height: 60%;
	float: left;
	width: 9em;
	height: 60%;
}

.content {
	float: left;
	width: auto;
}

#text1{
    transform: rotate(333deg);
    position: fixed;
    right: 0px;
    font-size: -webkit-xxx-large;
    bottom: 190px;
}

#text2{
    transform: rotate(333deg);
    position: fixed;
    right: 0px;
    font-size: -webkit-xxx-large;
    bottom: 113px;
}
}
</style>
</head>

<div class="main">

	<h2 class="ui segment header" style="color: gray; line-height: 0">
		企业老板
	</h2>

	<div class='ui segment buttonPanel'>
		<button class="ui button menu" style="display: inline"
			onclick="managers(0,7)">值班人员</button>

		<button class="ui button menu" style="display: inline"
			onclick="computerStatus(0,7)">电脑概况</button>

		<button class="ui button menu" style="display: inline"
			onclick="members(0,7)">会员信息</button>

		<button class="ui button menu" style="display: inline"
			onclick="consumptions(0,7)">消费记录</button>

		<button class="ui button menu" style="display: inline"
			onclick="computerUses(0,7)">使用记录</button>

		<button class="ui button menu" style="display: inline"
			 onclick="brokenRecords(0,7)">损坏记录</button>

		<button class="ui button menu" style="display: inline" 
			onclick="insertManager()">新员工</button>

		<button class="ui button menu" style="display: inline"
			 onclick="insertComputer()">新电脑</button>

		<button class="ui button menu" style="display: inline" onclick="">登出</button>

	</div>

	<div style="display: inline; float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	<div id='container' class='ui segment content'
		style="float: left; min-width: 640px"></div>
	<text id="text1">童叟无欺</text>
	<text id="text2">良心网吧</text>
</div>
</body>
<script type="text/javascript">
managers(0,7);
</script>
</html>