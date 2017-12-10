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
		
		// 所有 '电脑状态' 的格子（表头行除外），双击时变成可编辑状态
		var index = $("td:contains('电脑状态')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").bind('dblclick',selectable);
		
		
		var coloumName = "comment";
		var editable = getEditable("computerStatus",primaryKey,primaryKeyIndex,coloumName);
		
		// 所有 '电脑状态' 的格子（表头行除外），双击时变成可编辑状态
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

// 返回一个可以使 td 变得可编辑的函数
function getSelectable(options,actionName,primaryKey,primaryKeyIndex,coloumName){
	var selectable = function(){
		var td = this;
		var oldPadding = this.style.padding;
		var oldNode = this.firstChild;

		$(td).unbind('dblclick',selectable);
		td.style.padding="unset";
		
		var form = document.createElement("form");
		form.setAttribute("class","ui form");
		form.style.padding="0px";
		
		var sure = document.createElement("input");
		sure.style.padding="5px";
		sure.value="提交";
		sure.setAttribute("type","button");
		sure.style.display="inline";
		sure.style.margin="0 16px";
		sure.setAttribute("class","ui button");
		sure.onclick=function(){
			var primaryValue = td.parentNode.childNodes[primaryKeyIndex-1].innerText;
			var data = {};
			data["type"]="update";
			data[primaryKey]=primaryValue;
			data["columnName"]=coloumName;
			data[coloumName]=encodeURI(translate(select.value));
			
			alert(JSON.stringify(data));
			var jsxhr = $.ajax({
				url: actionName+".ajax",
				data: data,
				dataType : "json",
				method : "post"
			});
			jsxhr.done(function(data){
				oldNode.textContent=translate(data.result);
				td.style.color="green";
				td.style.padding=oldPadding;
				td.replaceChild(oldNode,form);
				$(td).bind('dblclick',selectable);
			});
			jsxhr.fail(function(jsxhr){
				alert(jsxhr.responseText);
			});
		}

		var cancel = document.createElement("input");
		cancel.style.padding="5px";
		cancel.value="取消";
		cancel.setAttribute("type","button");
		cancel.style.display="inline";
		cancel.style.margin="0 0 0 16px";
		cancel.setAttribute("class","ui button");
		cancel.onclick=function(){
			td.style.padding=oldPadding;
			td.replaceChild(oldNode,form);
			$(td).bind('dblclick',selectable);
		}

		
		var fillModel = function(options,selected){
			var i = 0;
			for(;i<options.length;i++){
				if(options[i]==selected){
					break;
				}
			}
			for(var j=0;j<options.length;j++){
				var option = document.createElement("option");
				option.innerHTML=options[j];
				this.add(option,null);
			}
			this.selectedIndex=i;
		};
		var onSelectChange = function(){
			if(select.initialOption!=select.value){
				select.style.color="red";
				form.appendChild(sure);
			}else{
				select.style.color="black";
				form.removeChild(sure);
			}
		}
		var select = document.createElement("select");
		select.name=coloumName;//5
		select.style.display="inline";
		select.style.width="auto";
		select.fillModel = fillModel;
		select.fillModel(options,oldNode.textContent);
		select.initialOption=oldNode.textContent;
		select.setAttribute("class","ui form-control");
		select.onchange=onSelectChange;
		
		form.appendChild(select);
		form.appendChild(cancel);
		
		td.replaceChild(form,oldNode);
	};
	return selectable;
}

function getEditable(actionName,primaryKey,primaryKeyIndex,coloumName){
	var editable = function(){
		var td = this;
		var oldPadding = this.style.padding;
		var oldNode = this.firstChild;
		if(!oldNode){
			oldNode = document.createElement("text");
			td.appendChild(oldNode);			
		}
		var oldText = oldNode.textContent;

		$(td).unbind('dblclick',editable);
		td.style.padding="unset";
		
		var form = document.createElement("form");
		form.setAttribute("class","ui form");
		form.style.padding="0px";
		
		var sure = document.createElement("input");
		sure.style.padding="5px";
		sure.value="提交";
		sure.setAttribute("type","button");
		sure.style.display="inline";
		sure.style.margin="0 16px";
		sure.setAttribute("class","ui button");
		sure.onclick=function(){
			var primaryValue = td.parentNode.childNodes[primaryKeyIndex-1].innerText;
			var data = {};
			data["type"]="update";
			data[primaryKey]=primaryValue;
			data["columnName"]=coloumName;
			data[coloumName]=encodeURI(translate(textarea.value));
			
			var jsxhr = $.ajax({
				url: actionName+".ajax",
				data: data,
				dataType : "json",
				method : "post"
			});
			jsxhr.done(function(data){
				oldNode.textContent=translate(data.result);
				td.style.color="green";
				td.style.padding=oldPadding;
				td.replaceChild(oldNode,form);
				$(td).bind('dblclick',editable);
			});
			jsxhr.fail(function(jsxhr){
				alert(jsxhr.responseText);
			});
		}

		var cancel = document.createElement("input");
		cancel.style.padding="5px";
		cancel.value="取消";
		cancel.setAttribute("type","button");
		cancel.style.display="inline";
		cancel.style.margin="0 0 0 16px";
		cancel.setAttribute("class","ui button");
		cancel.onclick=function(){
			td.style.padding=oldPadding;
			td.replaceChild(oldNode,form);
			$(td).bind('dblclick',editable);
		}

		var ontextChange = function(){
			if(oldText!=this.value){
				form.appendChild(sure);
				textarea.style.color="red";
			}else{
				if(form.contains(sure))
					form.removeChild(sure);
				textarea.style.color="black";
			}
		}
		var textarea = document.createElement("textarea");
		textarea.name=coloumName;//5
		textarea.style.display="inline";
		textarea.style.width="auto";
		textarea.style.maxHeight="4em";
		textarea.style.minHeight="2em";
		textarea.setAttribute("class","ui form-control");
		textarea.value=oldNode.textContent;
		textarea.onchange=ontextChange;
		
		form.appendChild(textarea);
		form.appendChild(cancel);
		
		td.replaceChild(form,oldNode);
	};
	return editable;
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
		
		
		var options = new Array("男","女");//1
		var primaryKey = "memberNo";
		var primaryKeyIndex = 2;
		var coloumName = "gender";
		var selectable = getSelectable(options,"members",primaryKey,primaryKeyIndex,coloumName);
		
		// 所有 '性别' 的格子（表头行除外），双击时变成可编辑状态
		var index = $("td:contains('性别')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").dblclick(selectable);
		
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
