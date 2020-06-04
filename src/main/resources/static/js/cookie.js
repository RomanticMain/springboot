var cookie = {
    set:function (key,val,time) {//设置cookie方法
        var date=new Date();//获取当前时间
        var expiresDays=time;//将date设置为n天以后的时间
        date.setTime(date.getTime()+expiresDays*24*3600*1000);
        document.cookie=key+"="+val+";expires="+date.toGMTString();
    },
    get:function (key) {
        var getCookie = document.cookie.replace(/[ ]/g,"");
        var arrCookie = getCookie.split(";");
        var tips;
        for(var i = 0; i < arrCookie.length; i++){
            var arr = arrCookie[i].split("=");
            if(key==arr[0]){
                tips=arr[1];
                break;
            }
        }
        return tips;
    },
    deleteCookie:function (key) {
        var date = new Date();
        date.setTime(date.getTime()-10000);
        document.cookie = key + "=v; expires =" + date.toGMTString();
    }
}