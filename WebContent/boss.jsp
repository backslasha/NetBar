<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.bean.Boss"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager</title>
<style type="text/css">

body{
	text-align: center;	
}
.wrapper {
	width: 920px;
	margin: 0 auto;
	text-align: left;	
}

.header{
	background-color: orange;
}
.footer{
	background-color: purple;
}

.content .primary {
	width: 650px;
	padding-right: 20px;
	float: right;
	display: inline;
	background-color:white;
	
}

.content .secondary {
	background-color:gray;
	width: 230px;
	padding-right: 20px;
	float: left;
	display: inline;
}

.content{
	overflow: hidden;
}
</style>
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<h1>HEADER</h1>
		</div>
		<div class="content">
			<div class="primary">函数 描述 .add() 将元素添加到匹配元素的集合中。 .andSelf()
				把堆栈中之前的元素集添加到当前集合中。 .children() 获得匹配元素集合中每个元素的所有子元素。 .closest()
				从元素本身开始，逐级向上级元素匹配，并返回最先匹配的祖先元素。 .contents()
				获得匹配元素集合中每个元素的子元素，包括文本和注释节点。 .each() 对 jQuery 对象进行迭代，为每个匹配元素执行函数。
				.end() 结束当前链中最近的一次筛选操作，并将匹配元素集合返回到前一次的状态。 .eq()
				将匹配元素集合缩减为位于指定索引的新元素。 .filter() 将匹配元素集合缩减为匹配选择器或匹配函数返回值的新元素。 .find()
				获得当前匹配元素集合中每个元素的后代，由选择器进行筛选。 .first() 将匹配元素集合缩减为集合中的第一个元素。 .has()
				将匹配元素集合缩减为包含特定元素的后代的集合。 .is() 根据选择器检查当前匹配元素集合，如果存在至少一个匹配元素，则返回 true。
				.last() 将匹配元素集合缩减为集合中的最后一个元素。 .map() 把当前匹配集合中的每个元素传递给函数，产生包含返回值的新
				jQuery 对象。 .next() 获得匹配元素集合中每个元素紧邻的同辈元素。 .nextAll()
				获得匹配元素集合中每个元素之后的所有同辈元素，由选择器进行筛选（可选）。 .nextUntil()
				获得每个元素之后所有的同辈元素，直到遇到匹配选择器的元素为止。 .not() 从匹配元素集合中删除元素。 .offsetParent()
				获得用于定位的第一个父元素。 .parent() 获得当前匹配元素集合中每个元素的父元素，由选择器筛选（可选）。 .parents()
				获得当前匹配元素集合中每个元素的祖先元素，由选择器筛选（可选）。 .parentsUntil()
				获得当前匹配元素集合中每个元素的祖先元素，直到遇到匹配选择器的元素为止。 .prev()
				获得匹配元素集合中每个元素紧邻的前一个同辈元素，由选择器筛选（可选）。 .prevAll()
				获得匹配元素集合中每个元素之前的所有同辈元素，由选择器进行筛选（可选）。 .prevUntil()
				获得每个元素之前所有的同辈元素，直到遇到匹配选择器的元素为止。 .siblings()
				获得匹配元素集合中所有元素的同辈元素，由选择器筛选（可选）。 .slice() 将匹配元素集合缩减为指定范围的子集。</div>
			<div class="secondary">函数 描述 .add() 将元素添加到匹配元素的集合中。 .andSelf()
				把堆栈中之前的元素集添加到当前集合中。 .children() 获得匹配元素集合中每个元素的所有子元素。 .closest()
				从元素本身开始，逐级向上级元素匹配，并返回最先匹配的祖先元素。 .contents()
				获得匹配元素集合中每个元素的子元素，包括文本和注释节点。 .each() 对 jQuery 对象进行迭代，为每个匹配元素执行函数。
				.end() 结束当前链中最近的一次筛选操作，并将匹配元素集合返回到前一次的状态。 .eq()
				将匹配元素集合缩减为位于指定索引的新元素。 .filter() 将匹配元素集合缩减为匹配选择器或匹配函数返回值的新元素。 .find()
				获得当前匹配元素集合中每个元素的后代，由选择器进行筛选。 .first() 将匹配元素集合缩减为集合中的第一个元素。 .has()
				将匹配元素集合缩减为包含特定元素的后代的集合。 .is() 根据选择器检查当前匹配元素集合，如果存在至少一个匹配元素，则返回 true。
				.last() 将匹配元素集合缩减为集合中的最后一个元素。 .map() 把当前匹配集合中的每个元素传递给函数，产生包含返回值的新</div>
		</div>
		<div class="footer"style="overflow: hidden;">
			<h1 style="float: right;">FOOTER</h1>
		</div>
	</div>
	<center style="display: none">
		<h1 >
			Welcome
			<%
			Boss boss = (Boss) request.getSession().getAttribute("boss");
			if (boss == null)
				return;
		%>
			<%=boss.toString()%>
		</h1>
	</center>
</body>
</html>