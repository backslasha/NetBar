/*  获取并解析管理员信息  */
function managerMessage(managerNo){
	
	var tbody = resetContainer();
	var jsxhr = $.ajax({
		url:"managerMessage.ajax",
		data:{"managerNo":managerNo},
		method:"post",
		dataType:"json"
	});
	jsxhr.done(function(data){
		for(var key in data){
			var tr = document.createElement("tr");
 			var td = document.createElement("td");
 			td.innerHTML = key;
 			tr.append(td);
 			var td = document.createElement("td");
 			td.innerHTML = data[key];
 			tr.append(td);
 			tbody.append(tr);
         }
		setCurrentContent('managerMessage');
	})
}		

/* 获取并解析『电脑概况 』 */
function computerStatus(start,count){
	var tbody = resetContainer();
	
	addFilterBar(container,computerStatus,{
		"status":"电脑状态",
		"computerNo":"电脑编号",
		"none":"不过滤"
	});
	
	var url = paramify("computerStatus",start,count);

	// ajax 成功响应处理函数
	var successHandler = function(data){
		tbodyGenerate(tbody,data.computerStatus);
		var end =parseInt(start)+parseInt(count);
		addButtonBar(container,parseInt(end/7),"computerStatus");
		setCurrentContent("computerStatus");
		
		var options = new Array("正在使用","空闲","待维修");//1
		var primaryKey = "computerNo";
		var primaryKeyIndex = 2;
		var coloumName = "status";
		var selectable = getSelectable(options,"computerStatus",primaryKey,primaryKeyIndex,coloumName);
		
		// 所有 '状态' 的格子（表头行除外），双击时变成可编辑状态
		var index = $("td:contains('状态')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").bind('dblclick',selectable);
		
		
		var coloumName = "comment";
		var editable = getEditable("computerStatus",primaryKey,primaryKeyIndex,coloumName);
		
		// 所有 '备注' 的格子（表头行除外），双击时变成可编辑状态
		var index = $("td:contains('备注')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").css({
			"overflow":"hide",
			"width":"9em !important",
			"text-overflow": "ellipsis"
		}).bind('dblclick',editable);
		
	}
	
	// ajax 失败响应处理函数
	var failHandler = function(jqxhr){
			alert(jqxhr.responseText);
			var $radio = $($("#filterBar [name='none']")[0]).attr("checked",true);
			window.checkRadioId=$radio.attr("id");
			computerStatus(0,7);
	}
	
	// 发送 ajax 请求
	var jqxhr = $.ajax({
		url:url,
		dataType: "json"
	});
	
	jqxhr.done(successHandler);
	jqxhr.fail(failHandler);
}

/* 获取并解析『电脑使用记录』,支持过滤*/
function computerUses(start,count){

	// 重置 container 表格为空，获取到它的 tbody
	var tbody = resetContainer();
	
	//根据请求数据类型 拼接 ajax 请求的 url
	var url = paramify("uses",start,count);
	
	// ajax 成功响应处理函数
	var successHandler = function(data){
		tbodyGenerate(tbody,data.computerUses);
		var end =parseInt(start)+parseInt(count);
		addButtonBar(container,parseInt(end/7),"computerUses");
		setCurrentContent("computerUses");
	}
	
	// ajax 失败响应处理函数
	var failHandler = function(jqxhr){
			alert(jqxhr.responseText);
			computerUses(0,7);
	}
	
	// 发送 ajax 请求
	var jqxhr = $.ajax({
		url:url,
		dataType: "json"
	});
	
	jqxhr.done(successHandler);
	jqxhr.fail(failHandler);
}

/* 获取并解『析吧内会员信息』,支持过滤*/
function members(start,count){

	// 重置 container 表格为空，获取到它的 tbody
	var tbody = resetContainer();
	
	// 添加过滤条
	addFilterBar(container,members,{
		"memberNo":"会员帐号",
		"lastLoginDate":"最近登陆",
		"none":"不过滤"
	});
	
	//根据请求数据类型 拼接 ajax 请求的 url
	var url = paramify("members",start,count);

	// ajax 成功响应处理函数
	var successHandler = function(data){
		tbodyGenerate(tbody,data.members);
		var end =parseInt(start)+parseInt(count);
		addButtonBar(container,parseInt(end/7),"members");
		setCurrentContent("members");
		
		// 所有 '性别' 的格子（表头行除外），双击时变成可选择调整状态
		var options = new Array("男","女");//1
		var primaryKey = "memberNo";
		var primaryKeyIndex = 2;
		var coloumName = "gender";
		var selectable = getSelectable(options,"members",primaryKey,primaryKeyIndex,coloumName);
		var index = $("td:contains('性别')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").dblclick(selectable);

		// 所有 '状态' 的格子（表头行除外），双击时变成可选择调整状态
		var options = new Array("离线","在线");//1
		var coloumName = "status";
		var selectable = getSelectable(options,"members",primaryKey,primaryKeyIndex,coloumName);
		var index = $("td:contains('状态')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").dblclick(selectable);

		// 所有 '余额' 的格子（表头行除外），双击时变成可编辑状态
		var coloumName = "funds";
		var editable = getEditable("members",primaryKey,primaryKeyIndex,coloumName);
		var index = $("td:contains('余额')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").dblclick(editable);

		// 所有 '密码' 的格子（表头行除外），双击时变成可编辑状态
		var coloumName = "password";
		var editable = getEditable("members",primaryKey,primaryKeyIndex,coloumName);
		var index = $("td:contains('密码')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").dblclick(editable);
		
	}
	
	// ajax 失败响应处理函数
	var failHandler = function(jqxhr){
			alert(jqxhr.responseText);
			var $radio = $($("#filterBar [name='none']")[0]).attr("checked",true);
			window.checkRadioId=$radio.attr("id");
			members(0,7);
	}
	
	// 发送 ajax 请求
	var jqxhr = $.ajax({
		url:url,
		dataType: "json"
	});
	
	jqxhr.done(successHandler);
	jqxhr.fail(failHandler);
	
}

/* 获取并解析『消费记录信息』,支持过滤*/
function consumptions(start,count){
	
	var tbody = resetContainer();
	
	addFilterBar(container,consumptions,{
			"memberNo":"会员帐号",
			"computerNo":"电脑编号",
			"timeLogin":"最近登陆",
			"none":"不过滤"
	});
	
	var url = paramify("consumptions",start,count);
	

	// ajax 成功响应处理函数
	var successHandler = function(data){
		tbodyGenerate(tbody,data.consumptions);
		var end =parseInt(start)+parseInt(count);
		addButtonBar(container,parseInt(end/7),"consumptions");
		setCurrentContent("consumptions");
	}
	
	// ajax 失败响应处理函数
	var failHandler = function(jqxhr){
			alert(jqxhr.responseText);
			var $radio = $($("#filterBar [name='none']")[0]).attr("checked",true);
			window.checkRadioId=$radio.attr("id");
			consumptions(0,7);
	}
	
	// 发送 ajax 请求
	var jqxhr = $.ajax({
		url:url,
		dataType: "json"
	});
	
	jqxhr.done(successHandler);
	jqxhr.fail(failHandler);
	
}

/* 获取并解『析机器损坏记录』,支持过滤*/
function brokenRecords(start,count){

	// 重置 container 表格为空，获取到它的 tbody
	var tbody = resetContainer();
	
	// 添加过滤条{name:text}
	addFilterBar(container,brokenRecords,{
		"brokenRecordNo":"损坏编号",
		"computerNo":"电脑编号",
		"timeBroken":"损坏日期",
		"none":"不过滤"
	});
	
	//根据请求数据类型 拼接 ajax 请求的 url
	var url = paramify("brokenRecords",start,count);

	// ajax 成功响应处理函数
	var successHandler = function(data){
		tbodyGenerate(tbody,data.brokenRecords);
		var end =parseInt(start)+parseInt(count);
		addButtonBar(container,parseInt(end/7),"brokenRecords");
		setCurrentContent("brokenRecords");
		

		// 所有 '修理时间' 的格子（表头行除外），双击时变成可编辑状态
		var primaryKey = "brokenRecordNo";
		var primaryKeyIndex = 2;
		var coloumName = "timeRepaired";
		var editable = getEditable("brokenRecords",primaryKey,primaryKeyIndex,coloumName);
		var index = $("td:contains('修理时间')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").dblclick(editable);

		// 所有 '备注' 的格子（表头行除外），双击时变成可编辑状态
		var coloumName = "brokenComment";
		var editable = getEditable("brokenRecords",primaryKey,primaryKeyIndex,coloumName);
		var index = $("td:contains('备注')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").dblclick(editable);
		
	}
	
	// ajax 失败响应处理函数
	var failHandler = function(jqxhr){
			alert(jqxhr.responseText);
			var $radio = $($("#filterBar [name='none']")[0]).attr("checked",true);
			window.checkRadioId=$radio.attr("id");
			brokenRecords(0,7);
	}
	
	// 发送 ajax 请求
	var jqxhr = $.ajax({
		url:url,
		dataType: "json"
	});
	
	jqxhr.done(successHandler);
	jqxhr.fail(failHandler);
	
}

function insertMember(){
	var $container = $("#container");
	$container.html(
			"<form id='form_insert' action='' class='ui form'>" +
			"<table class='ui table' border='1' style='margin: 0;'>" +
			"<tbody>" +
			"</tbody>" +
			"</table>"+
			"</form>" 
	);
	$container.css("margin","0");
	var tbody =  $container.find("tbody")[0];
	var form =  $container.find("form")[0];

	var data = {
			姓名:["name","text"],
			性别:["gender","text"],	
			年龄:["age","number"]	,
			会员帐号:["memberNo","number"],
			密码:["password","password"],
			帐号余额:["funds","number"]
	}
	
	for(var key in data){
		var tr = document.createElement("tr");
 		var td = document.createElement("td");
 		td.innerHTML = key;
 		tr.append(td);
 		
 		var td = document.createElement("td");
 		td.style.padding="0px";

 		var input = document.createElement("input");
 		input.setAttribute("name",data[key][0]);
 		input.setAttribute("type",data[key][1]);
 		input.style.width="100%";
 		input.style.height="3.0em";
 		td.appendChild(input);
 		
 		tr.appendChild(td);
 		tbody.appendChild(tr);
    }
	
	var div = document.createElement("div");
	$(div).css("margin","0");
	div.innerHTML="<div class=\"ui segment\" style='padding: 0.5em!important; overflow:hidden;'>"+
	"<input class='inline field' type='submit' method='post' value='确认新增' style='float:right'/>"+
	"</div>";
	form.append(div);
	
	$(form).submit(function(e){
		e.preventDefault();
		var data =$(this).serialize();
		if(data.indexOf("=&")>0){
			alert("请填写完整！");
			return;
		}
		$.ajax({
			url : "members.ajax",
			data : encodeURI(data+"&&type=insert") ,
			method : "post"
		}).done(function(data){
			alert("新增会员："+data.result);
			insertMember();
		}).fail(function(jqxhr){
			alert(jqxhr.responseText);
		});
	});
	
	setCurrentContent('insertMember');
}

function insertComputer(){
	var $container = $("#container");
	$container.html(
			"<form id='form_insert' action='' class='ui form'>" +
			"<table class='ui table' border='1' style='margin: 0;'>" +
			"<tbody>" +
			"</tbody>" +
			"</table>"+
			"</form>" 
	);
	$container.css("margin","0");
	var tbody =  $container.find("tbody")[0];
	var form =  $container.find("form")[0];
	
	var data = {
			电脑编号:["computerNo","text"],
			备注:["comment","text"],	
			状态:["status","text"]	,
	}
	
	for(var key in data){
		var tr = document.createElement("tr");
		var td = document.createElement("td");
		td.innerHTML = key;
		tr.append(td);
		
		var td = document.createElement("td");
		td.style.padding="0px";
		
		var input = document.createElement("input");
		input.setAttribute("name",data[key][0]);
		input.setAttribute("type",data[key][1]);
		input.style.width="100%";
		input.style.height="3.0em";
		td.appendChild(input);
		
		tr.appendChild(td);
		tbody.appendChild(tr);
	}
	
	var div = document.createElement("div");
	$(div).css("margin","0");
	div.innerHTML="<div class=\"ui segment\" style='padding: 0.5em!important; overflow:hidden;'>"+
	"<input class='inline field' type='submit' method='post' value='确认新增' style='float:right'/>"+
	"</div>";
	form.append(div);
	
	$(form).submit(function(e){
		e.preventDefault();
		var data =$(this).serialize();
		if(data.indexOf("=&")>0){
			alert("请填写完整！");
			return;
		}
		$.ajax({
			url : "computerStatus.ajax",
			data : encodeURI(data+"&&type=insert") ,
			method : "post"
		}).done(function(data){
			alert("新增电脑："+data.result);
			insertComputer();
		}).fail(function(jqxhr){
			alert(jqxhr.responseText);
		});
	});
	
	setCurrentContent('insertComputer');
}

function logout(userType,userNo){
	window.alert("结帐成功！本次上机时间为软妹币！");
	if(userType=="manager"){
		window.location.href="logout.do?userType=manager&managerNo="+userNo;
	}else	if(userType=="boss"){
		window.location.href="logout.do?userType=boss";
	}
}