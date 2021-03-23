window.setInterval(function(){
	var urodz= new Date("2/5/2021");
	var now = new Date();
	var old = urodz.getTime();
	var ile = now.getTime();
	var time = ile-old;
	
	$("#time").html("本站已稳定运行"+secondsFormat(time/1000));
	if(IsPC()==true){
		var html = $("#time").html();
		html += "，如需协助请联系 admin@dfvips.com.";
		$("#time").html(html);
	}
},1000);
function secondsFormat(s) { 
    var day = tran(Math.floor( s/ (24*3600))); 
    var hour = tran(Math.floor( (s - day*24*3600) / 3600)); 
    var minute = tran(Math.floor( (s - day*24*3600 - hour*3600) /60 )); 
    var second = tran(Math.floor(s - day*24*3600 - hour*3600 - minute*60)); 
    return day + "天"  + hour + "小时" + minute + "分钟" + second + "秒"; 
}
function tran(i){
	if(i<10){
		i="0"+i;
	}
	return i;
}