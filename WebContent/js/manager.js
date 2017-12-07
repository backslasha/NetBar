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

/*  consumptionPanel */
function consumptionPanel(){
	//ConsumptionNo | computerNo | memberNo   | timeLogin  | timeLogout | cost
	
}


/*  获取并解析管理员信息  */
function managerMessage(managerNo){
	$.getJSON("managerMessage.ajax?managerNo="+managerNo,
			function(data) {
				var container = $("#container");
				container.html("<table class='ui table' border='1'><tbody></tbody></table>");
				var tbody = container.find("tbody")[0];
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
				if(window.containerContent!="managerMessage"){
					$("#container").hide().show("fast");
					window.containerContent="managerMessage";					
				}
			});
}		

/* 获取并解析『电脑概况 』 */
function computerStatus(start,count){
	var end =parseInt(start)+parseInt(count);
	var url = "computerStatus.ajax?start="+start+"&&end="+count;
	
//	var formMap = $("#filterPanel").serializeArray();
//	// 是否需要添加过滤参数
//	if(formMap&&formMap.length!=0&&formMap[0].value!="none"){
//		url = url+"&&filter="+formMap[0].value+"&&filterValue="+formMap[1].value;
//	}
	$.getJSON(url,function(data) {
		 			var container = $("#container");
		 			container.html("<table class='ui table' border='1' style='margin: 0;'><tbody></tbody></table>");
		 			container.css("margin","0");
	            var tbody = container.find("tbody")[0];
	            var records = data.computers;
	            addHeaders(tbody,records[0]);
	            $.each(records,
	            function(i, record) {
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
	            // addFilterBar(container,computerStatus);
	            addButtonBar(container,parseInt(end/7),"computerStatus");
	            if(window.containerContent!="computerStatus"){
						$("#container").hide().show("fast");
						window.containerContent="computerStatus";					
					}
	  });
}

/* 获取并解析『电脑使用记录』，支持过滤*/
function computerUses(start,count){
	var end =parseInt(start)+parseInt(count);
	var url = "uses.ajax?start="+start+"&&end="+count;
	
//	var formMap = $("#filterPanel").serializeArray();
//	// 是否需要添加过滤参数
//	if(formMap&&formMap.length!=0&&formMap[0].value!="none"){
//		url = url+"&&filter="+formMap[0].value+"&&filterValue="+formMap[1].value;
//	}
	
	$.getJSON(url,function(data) {
		 			var container = $("#container");
		 			container.html("<table class='ui table' border='1' style='margin: 0;'><tbody></tbody></table>");
	        		container.css("margin","0");
	            var tbody = container.find("tbody")[0];
	            var records = data.records;
	            addHeaders(tbody,records[0]);
	            $.each(records,
	            function(i, record) {
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
//	            addFilterBar(container,computerUses);
	            addButtonBar(container,parseInt(end/7),"computerUses");
	            if(window.containerContent!="computerUses"){
						$("#container").hide().show("fast");
						window.containerContent="computerUses";					
					}
	  });
}

/* 获取并解『析吧内会员信息』，支持过滤*/
function members(start,count){
	var end =parseInt(start)+parseInt(count);
	var url = "members.ajax?start="+start+"&&end="+count;
	
	var formMap = $("#filterPanel").serializeArray();
	// 是否需要添加过滤参数
	if(formMap&&formMap.length!=0&&formMap[0].value!="none"){
		url = url+"&&filter="+formMap[0].value+"&&filterValue="+formMap[1].value;
	}
	
	$.getJSON(url,function(data) {
		var container = $("#container");
		container.html("<table class='ui table' border='1' style='margin: 0;min'><tbody></tbody></table>");
		container.css("margin","0");
		var tbody = container.find("tbody")[0];
		var records = data.records;
		addHeaders(tbody,records[0]);
		$.each(records,
				function(i, record) {
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
		addFilterBar(container,members);
		addButtonBar(container,parseInt(end/7),"members");
		if(window.containerContent!="members"){
			$("#container").hide().show("fast");
			window.containerContent="members";					
		}
	});
}

/* 获取并解析『消费记录信息』，支持过滤*/
function consumptions(start,count){
	var end =parseInt(start)+parseInt(count);
	var url = "consumptions.ajax?start="+start+"&&end="+count;
	
	var formMap = $("#filterPanel").serializeArray();
	// 是否需要添加过滤参数
	if(formMap&&formMap.length!=0&&formMap[0].value!="none"){
		url = url+"&&filter="+formMap[0].value+"&&filterValue="+formMap[1].value;
	}
	
	$.getJSON(url,function(data) {
		var container = $("#container");
		container.html("<table class='ui table' border='1' style='margin: 0;min'><tbody></tbody></table>");
		container.css("margin","0");
		var tbody = container.find("tbody")[0];
		var records = data.consumptions;
		addHeaders(tbody,records[0]);
		$.each(records,
				function(i, record) {
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
		addFilterBar(container,consumptions);
		addButtonBar(container,parseInt(end/7),"consumptions");
		if(window.containerContent!="consumptions"){
			$("#container").hide().show("fast");
			window.containerContent="consumptions";					
		}
	});
}

function addHeaders(tbody, record){	
	 	var tr = document.createElement("tr");
	 	var td = document.createElement("td");
	   td.innerHTML = '序号';
	   tr.append(td);
	   
	 	for(var key in record){
	 		var td = document.createElement("td");
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

function addFilterBar(container,method){
	var div = document.createElement("div");
	div.innerHTML="<form id=\"filterPanel\" class=\"ui segment\" style='margin-top: unset !important;'>\n" + 
	"	<input type=\"radio\" name=\"filter\" value=\"memberNo\" >会员帐号</input>\n" + 
	"	<input type=\"radio\" name=\"filter\" value=\"computerNo\" >电脑编号</input>\n" + 
	"	<input type=\"radio\" name=\"filter\" value=\"date\" >日期</input>\n" + 
	"	<input type=\"radio\" name=\"filter\" value=\"none\" checked=\"checked\">无</input>\n" + 
	"	<input type=\"submit\" value=\"过滤\" style=\"float:right\"/>\n" + 
	"	<input type=\"text\" name=\"filterValue\" style=\"float:right\" />\n" + 
	"	<div class=\"ui right pointing label\" style=\"float:right\">过滤值</div>\n" + 
	"</form>"
	container.prepend(div);
	
	if(window.filter){
		var text;
		var radio = $("#filterPanel input")
									.filter(function(index){
										if($(this).attr("name")=="filterValue"){
											text = $(this);
										}
										return $(this).val()==window.filter;
									})[0];
		$(radio).attr("checked",true);
		
		if(window.filterValue){
			text.val(window.filterValue);
		}
	}
	
	$("#filterPanel").submit(function(event){
		event.preventDefault();
		method(0,7);
		var formMap = $("#filterPanel").serializeArray();
		if(formMap&&formMap.length!=0){
			window.filter=formMap[0].value;
			window.filterValue=formMap[1].value
		}
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


