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
		
		// 使 td 变得可以编辑的函数
		var editable = function(){
			var td = this;
			var oldPadding = this.style.padding;
			var oldNode = this.firstChild;

			td.dblclick=null;
			td.style.padding="unset";
			
			var options = new Array("正在使用","空闲","待维修");
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
				alert("确认修改！");
				// to do
				td.style.color="green";
				td.style.padding=oldPadding;
				td.replaceChild(oldNode,form);
				td.dblclick=editable;
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
				td.dblclick=editable;
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
		
		// 所有 '电脑状态' 的格子（表头行除外），双击时变成可编辑状态
		var index = $("td:contains('电脑状态')").prevAll().length+1;
		$("tr :nth-child("+index+"):not(.row_head)").dblclick(editable);
		
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

function tbodyGenerate(tbody,records){
	addHeaders(tbody,records[0]);
	$.each(records,function(i, record) {
		var tr = document.createElement("tr");
		var td = document.createElement("td");
		td.innerHTML = i+1;
		tr.append(td);
		
		for(var key in record){
			var td = document.createElement("td");
			td.innerHTML = record[key];
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
	var url = action+".ajax?start="+start+"&&end="+count;
	
	var $checkRadio = $($("#filterPanel :checked")[0]);
	var $text =  $($("#filterPanel :text")[0]);
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
	 		td.innerHTML = key;
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
	"</form>";
	container.append(div);
}

function addFilterBar(container,method,options){
	var div = document.createElement("div");
	div.innerHTML="<form id=\"filterPanel\" class=\"ui segment\" style='margin-top: unset !important;'>\n" + 
	"	<input type=\"submit\" value=\"过滤\" style=\"float:right\"/>\n" + 
	"	<input type=\"text\" name=\"filterValue\" style=\"float:right\" />\n" + 
	"	<div class=\"ui right pointing label\" style=\"float:right\">过滤值</div>\n" + 
	"</form>"
	// 新增加表单 div
	container.prepend(div);
	
	// 表单添加需要的 radio 
	var $filterPanel = $("#filterPanel");
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
		
		$filterPanel.append(radio,label);
	}
	
	// 如果之前有选中的 radio，并且此时那个 radio 还在，则恢复它的选中状态
	var $checkRadio = $("#"+window.checkRadioId);
	if($checkRadio){
		$checkRadio.attr("checked",true);

		// 如果之前有输入的字符串，则恢复它
		if(window.preservedText){
			var $text = $($("#filterPanel :text")[0]);
			$text.val(window.preservedText);
		}
	}
	
	// 使用过滤时，记录此时的 radio 的 ID，和 text 中的文本，以便表单重建时恢复它们的状态
	$("#filterPanel").submit(function(event){
		event.preventDefault();
		var $radio = $($("#filterPanel :checked")[0]);
		var $text = $($("#filterPanel :text")[0]);
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


