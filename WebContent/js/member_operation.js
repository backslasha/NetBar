function logout(logintime){
		var now = new Date().getTime();
		var duration = now - logintime;
		var temp = duration;
		var hours = parseInt(temp / 1000 / 60 / 60);
		temp = temp%(1000*60*60);
		var minutes = parseInt(temp / 1000 / 60);
		temp = temp%(1000*60);
		var seconds = parseInt(temp / 1000);
		
		if(hours<=9){  
			hours = "0".concat(hours);
		}
		if(minutes<=9){
			minutes = "0".concat(minutes);
		}
		if(seconds<=9){
			seconds = "0".concat(seconds);
		}
		var expense = hours*3;
		if(minutes>30){
			expense=expense+3;
		}else if(minutes>0){
			expense=expense+1.5;
		}
		if(expense==0){
			expense=1.5;
		}
		window.alert("结帐成功！本次上机时间为 "+hours+":"+minutes+":"+seconds+",共花费 "+expense+"软妹币！");
}