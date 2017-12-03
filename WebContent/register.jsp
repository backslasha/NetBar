<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<link rel="stylesheet" type="text/css"
	href="static/senmantic/semantic.min.css">


<head>
<meta charset="UTF-8">
<title>计科五班实验课设选题</title>
</head>
<body>
	<div class="ui inverted segment"></div>
	<h2 class='ui header' align="center">操作系统课设选题</h2>
	<form class="ui form" id="myForm" method="POST" action="/result/">
		<input type='hidden' name='csrfmiddlewaretoken'
			value='CcLX3jeDO85dy1Qoo1G9CMyxRTW9MpjxsuZoL9ouaFyXNmTa6vKprM2YkDZqGqmm' />
		<div class="ui segment" align='center'>
			<div class="ui divider"></div>
			<div class="inline field" align="center">
				<div class="ui right pointing label">学号</div>
				<input type="text" name="number" class="form-control"
					placeholder="学号">

			</div>

			<div class="inline field" align="center">
				<div class="ui  right pointing label">姓名</div>
				<input type="text" name="name" class="form-control" placeholder="姓名">
			</div>

			<div class="inline field" align="center">
				<div class="ui right pointing label">课题选择</div>
				<div style="width: 20%; display: inline-block">
					<select class="form-control" name="subject">

						<option>两道批处理系统的两级调度-1</option>

						<option>两道批处理系统的两级调度-2</option>

						<option>仿真模拟操作系统中的“哲学家就餐问题”</option>

						<option>多级文件系统-1</option>

						<option>多级文件系统-2</option>

						<option>多道批处理系统的两级调度-1</option>

						<option>多道批处理系统的两级调度-2</option>

						<option>多道批处理系统的两级调度-3</option>

						<option>模拟仿真“生产者-消费者”问题的解决过程及方法</option>

						<option>磁盘空间管理-1</option>

						<option>磁盘空间管理-2</option>

						<option>磁盘空间管理-3</option>

						<option>磁盘空间管理-4</option>

						<option>磁盘调度算法1</option>

						<option>编程模拟多进程共享临界资源</option>

						<option>编程演示三种存储管理方式的地址换算过程</option>

						<option>请求调页存储管理方式的模拟-1</option>

						<option>请求调页存储管理方式的模拟-2</option>

						<option>请求调页存储管理方式的模拟-3</option>

						<option>请求调页存储管理方式的模拟-4</option>

						<option>进程调度</option>

						<option>银行家算法</option>

					</select>
				</div>

			</div>


			<div class="ui divider"></div>
			<input type='submit' value="确定" class="ui  button" align='center'>

		</div>

	</form>


</body>
<script>
	
</script>
</html>
