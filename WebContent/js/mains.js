/*  获取并解析管理员信息  */
function managerMessage(managerNo){
	
	var jsxhr = $.ajax({
		url:"managerMessage.ajax",
		data:{"managerNo":managerNo},
		method:"post",
		dataType:"json"
	});
	jsxhr.done(function(data){
		var tbody = resetContainer();
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
