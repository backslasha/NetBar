
function translate(text){
	var reg = ".*[a-zA-Z].*";
	if(!text.match(reg)){
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
		default:
			return text;
		}
	}else{
		switch(text){
		case "busy":
			return "正在使用";
		case "idle": 
			return "空闲";
		case "broken":
			return "待维修";
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
	var url = action+".ajax?start="+start+"&&end="+count+"&&type=query";
	
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


