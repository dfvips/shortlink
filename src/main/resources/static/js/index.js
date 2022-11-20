var inter=null;
window.onload=function(){
    loadcss();
};
int();
function int(){
    inter = window.setInterval(function(){
        var val = document.getElementById("dfs").attributes.placeholder.value;
        var arr=[" ","请","输","入","要","生","成","短","链","的","网","址"];
        var index = val.length;
        if(index<arr.length){
            document.getElementById("dfs").attributes.placeholder.value=val+arr[index];
        }else{
            document.getElementById("dfs").attributes.placeholder.value="";
        }
    },210);
}
$("#dfs").focus(function(){
    window.clearInterval(inter);
    $(this).attr("placeholder"," 请输入要生成短链的网址");
});
$("#dfs").blur(function(){
    int();
});
function loadcss(){
    var flag = IsPC();
    if(flag==true){
        $("#mobilecss").remove();
        // document.getElementsByTagName("link")[0].href="css/pc.css";
    }else{
        // document.getElementsByTagName("link")[0].href="css/mobile.css";
        $("#pccss").remove();
    }
};
document.getElementsByClassName("btn")[0].onclick=function(){
    checkform();
}
document.getElementById("dfs").onkeydown=function(e){
    if(e.keyCode==13){
        checkform();
    }
}
function checkform(){
    $(".btn").removeAttr('onclick');
    var val=document.getElementById("dfs").value;
    if(checkurl(val)==true){
        handleresult(val);
    }else{
        document.getElementById("url_box").style.cssText="display:block";
        document.getElementById("url_title").innerHTML="温馨提示";
        document.getElementById("url_input").value="请输入正确的网址！";
        $("#url_input").addClass("active");
    }
}
function handleresult(val){
    document.getElementById("url_box").style.cssText="display:block";
    $.ajax({
        url: "https://api.2cn.dev/" + encodeURIComponent(encodeURIComponent(val)),
        type: "GET",
        dataType: "json",
        success: function(data) {
            var result=data.url;
            if(result==undefined||result=="undefined"){
                document.getElementById("url_input").value="暂停服务";
            }else{
                document.getElementById("url_input").value=result;
            }
        }
    });
}
function closeframe(){
    document.getElementById("url_box").style.cssText="display:none";
    document.getElementById("url_input").value="";
    document.getElementById("url_title").innerHTML="短网址";
    // document.getElementById("dfs").value="";
    $("#url_input").removeClass("active");
    $(".btn").attr("onclick");
}
function IsPC(){
    var sUserAgent = navigator.userAgent.toLowerCase();
    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";//判断是否为iPad
    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";//判断是否为iPhone用户
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
    var bIsAndroid = sUserAgent.match(/android/i) == "android";
    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";

    if (!(bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM)) {
        return true;
    }else{
        return false;
    }
}
function checkurl(domain) {
    var name = /[a-zA-z]+:\/\/[^\s]*/;
    if( !(name.test(domain))){
        return false;
    }else{
        return true;
    }
}