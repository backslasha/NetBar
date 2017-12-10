
/*
 * 返回一个可以使 td 变得可调整的函数
 * @param options 可编辑框中的选项，array，选项名如果和服务器不一致，需要修改 translate() 设置转换一下
 * @param actionName 提交的时服务器接口名，如 "members"
 * @param primaryKey 指定正在操作的表格的主键名，如 "memberNo"
 * @param primaryKeyIndex 指定正在操作的表格的主键在网页的 <table> 中 列的 index，常常为 2 
 * @param coloumName 指定正在操作的表格的列，在服务器上数据库中的名字，如 "gender"
 */
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

/*
 * 返回一个可以使 td 变得可编辑的函数
 * @param actionName 提交的时服务器接口名，如 "computerStatus"
 * @param primaryKey 指定正在操作的表格的主键名，如 "computerNo"
 * @param primaryKeyIndex 指定正在操作的表格的主键在网页的 <table> 中 列的 index，常常为 2 
 * @param coloumName 指定正在操作的表格的列，在服务器上数据库中的名字，如 "comment"
 */
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
		textarea.style.width="10em";
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

function translate(text){
	var reg = /.*[a-zA-Z].*/;
	if(!reg.exec(text)){
		switch(text){
		case "正在使用":
			return "busy";
		case "空闲": 
			return "idle";
		case "待维修":
			return "broken";
		case "男":
			return "男";
		case "女":
			return "女";
		case "离线": 
			return "offline";
		case "在线":
			return "online";
		default:
			return text;
		}
	}else{
		switch(text){
		case "computerNo":
			return "电脑编号"
		case "status":
			return "状态	"
		case "comment":
			return "备注	"
		case "busy":
			return "正在使用";
		case "idle": 
			return "空闲";
		case "broken":
			return "待维修";
		case "offline": 
			return "离线";
		case "online":
			return "在线";
		default:
			return text;
		}
	}
	
}

function tbodyGenerate(tbody,records){
	addHeaders(tbody,records[0]);
	$.each(records,function(i, record) {
		var tr = document.createElement("tr");
		var td = document.createElement("td");
		td.innerHTML = i+1;
		tr.append(td);
		
		for(var key in record){
			var td = document.createElement("td");
			td.innerHTML = translate(record[key]);
			tr.append(td);
		}
		
		tbody.append(tr);
	});
}

function resetContainer(){
	var container = $("#container");
	container.html("<table class='ui table' border='1' style='margin: 0;'><tbody></tbody></table>");
	container.css("margin","0");
	return container.find("tbody")[0];
}

function paramify(action,start,count) {
	var url = action+".ajax?start="+start+"&&end="+count+"&&type=query";
	
	var $checkRadio = $($("#filterBar :checked")[0]);
	var $text =  $($("#filterBar :text")[0]);
	if(!$checkRadio){
		return url;
	}
	// 是否需要添加过滤参数
	if($checkRadio.val()!="none"&&$text.val()){
		url = url+"&&filter="+$checkRadio.val()+"&&filterValue="+$text.val();
	}
	return url;
}

function setCurrentContent(content){
	  if(window.containerContent!=content){
			$("#container").hide().show("fast");
			window.containerContent=content;					
	 }
}

function addHeaders(tbody, record){	
	 	var tr = document.createElement("tr");
	 	var td = document.createElement("td");
	   td.innerHTML = '序号';
	   tr.append(td);
	   
	 	for(var key in record){
	 		var td = document.createElement("td");
	 		td.setAttribute("class","row_head");
	 		td.innerHTML = translate(key);
	 	   tr.append(td);
	 	}
      tbody.append(tr);
}

function addButtonBar(container,pageNo,methodName){
	var div = document.createElement("div");
	$(div).css("margin","0");
	div.innerHTML="<form class=\"ui segment\" style='margin-bottom: unset !important;'>"+
	"第<input class='inline field' id='text_pageno' name='pageNo' type='text' value='"+pageNo+"' style='width:24px'/>页"+
	"<input class='inline field' name='next' type='button' value='下一页' style='float:right' onclick='pages(true,"+methodName+")'/>"+
	"<input class='inline field' name='previous' type='button' value='上一页' style='float:right' onclick='pages(false,"+methodName+")'/>"+
	"<input class='inline field' id='button_insert' type='button' value='新增' style='float:right;margin-right:15px'/>"+
	"</form>";
	container.append(div);
}

function addFilterBar(container,method,options){
	var div = document.createElement("div");
	div.innerHTML="<form id=\"filterBar\" class=\"ui segment\" style='margin-top: unset !important;'>\n" + 
	"	<input type=\"submit\" value=\"过滤\" style=\"float:right\"/>\n" + 
	"	<input type=\"text\" name=\"filterValue\" style=\"float:right\" />\n" + 
	"	<div class=\"ui right pointing label\" style=\"float:right\">过滤值</div>\n" + 
	"</form>"
	// 新增加表单 div
	container.prepend(div);
	
	// 表单添加需要的 radio 
	var $filterBar = $("#filterBar");
	for(var key in options){
		var value = key;
		var name = "filter";
		var text = options[key];
		
		var radio = document.createElement("input");
		radio.setAttribute('type',"radio");
		radio.setAttribute('name',name);
		radio.setAttribute('value',value);
		radio.setAttribute('id',"radio_"+value);
		if(key=="none"){
			radio.setAttribute('checked',true);
		}
		
		var label = document.createElement("label");
		label.setAttribute('for',"radio_"+value);
		label.innerText=text;
		
		$filterBar.append(radio,label);
	}
	
	// 如果之前有选中的 radio，并且此时那个 radio 还在，则恢复它的选中状态
	var $checkRadio = $("#"+window.checkRadioId);
	if($checkRadio){
		$checkRadio.attr("checked",true);

		// 如果之前有输入的字符串，则恢复它
		if(window.preservedText){
			var $text = $($("#filterBar :text")[0]);
			$text.val(window.preservedText);
		}
	}
	
	// 使用过滤时，记录此时的 radio 的 ID，和 text 中的文本，以便表单重建时恢复它们的状态
	$("#filterBar").submit(function(event){
		event.preventDefault();
		var $radio = $($("#filterBar :checked")[0]);
		var $text = $($("#filterBar :text")[0]);
		window.checkRadioId=$radio.attr("id");
		window.preservedText=$text.val();
		method(0,7);
	});
}

function pages(toNextPage,method){
	var pageNo = $("#text_pageno").val();
	var lastItemNo = parseInt(pageNo)*7-1;
	if(toNextPage){
		method(lastItemNo+1,7);
	}else{
		method(lastItemNo-7-7+1,7);
	}
}


