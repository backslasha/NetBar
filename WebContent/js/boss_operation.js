
/* 获取并解『析吧内会员信息』,支持过滤*/
function managers(start,count){

	// 重置 container 表格为空，获取到它的 tbody
	var tbody = resetContainer();
	
	// 添加过滤条
	addFilterBar(container,managers,{
		"managerNo":"工号",
		"name":"名字",
		"status":"状态",
		"none":"不过滤"
	});
	
	//根据请求数据类型 拼接 ajax 请求的 url
	var url = paramify("managers",start,count);

	// ajax 成功响应处理函数
	var successHandler = function(data){
		tbodyGenerate(tbody,data.managers);
		var end =parseInt(start)+parseInt(count);
		addButtonBar(container,parseInt(end/7),"managers");
		setCurrentContent("managers");
		
		// 所有 '性别' 的格子（表头行除外），双击时变成可选择调整状态
		var options = new Array("男","女");//1
		var primaryKey = "managerNo";
		var primaryKeyIndex = 2;
		var coloumName = "gender";
		var selectable = getSelectable(options,"managers",primaryKey,primaryKeyIndex,coloumName);
		var index = $("td:contains('性别')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").dblclick(selectable);


		// 所有 '工资' 的格子（表头行除外），双击时变成可编辑状态
		var coloumName = "salary";
		var editable = getEditable("managers",primaryKey,primaryKeyIndex,coloumName);
		var index = $("td:contains('工资')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").dblclick(editable);

		// 所有 '密码' 的格子（表头行除外），双击时变成可编辑状态
		var coloumName = "password";
		var editable = getEditable("managers",primaryKey,primaryKeyIndex,coloumName);
		var index = $("td:contains('密码')").prevAll().length+1;//6
		$("tr :nth-child("+index+"):not(.row_head)").dblclick(editable);
		
	}
	
	// ajax 失败响应处理函数
	var failHandler = function(jqxhr){
			alert(jqxhr.responseText);
			var $radio = $($("#filterBar [name='none']")[0]).attr("checked",true);
			window.checkRadioId=$radio.attr("id");
			managers(0,7);
	}
	
	
	var $checkRadio = $($("#filterBar :checked")[0]);
	var $text =  $($("#filterBar :text")[0]);
	var data = {};
	if($checkRadio.val()!="none"&&$text.val()){
		data["filter"]=$checkRadio.val();
		data["filterValue"]=encodeURI($text.val());
	}	
	data["type"]="query";
	data["start"]=start;
	data["end"]=count;
	// 发送 ajax 请求
	var jqxhr = $.ajax({
		url: "managers.ajax",
		data: data,
		dataType: "json",
		method : "post"
	});
	jqxhr.done(successHandler);
	jqxhr.fail(failHandler);
	
}

function insertManager(){
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
			工号:["managerNo","number"],
			密码:["password","password"],
			工资:["salary","number"]
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
			url : "managers.ajax",
			data : encodeURI(data+"&&type=insert") ,
			method : "post"
		}).done(function(data){
			alert("新增员工："+data.result);
			insertManager();
		}).fail(function(jqxhr){
			alert(jqxhr.responseText);
		});
	});
	
	setCurrentContent('insertManager');
}
