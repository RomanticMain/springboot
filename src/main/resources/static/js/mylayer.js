var ii = null;
var mylayer = {
    showLoading:function(){
        ii = layer.load(2,{
            shade:0.5
        });
    },
    closeLoading:function () {
        layer.close(ii);
    },
    showMessage:function (msg,time) {
        if(time==undefined||time==null||time==""){
            time=2000;
        }
        layer.msg(msg,{
            icon:1,
            shade:0.5,
            time:time
        });
    },
    showError:function (msg,time) {
        if(time==undefined||time==null||time==""){
            time=2000;
        }
        layer.msg(msg,{
            icon:2,
            shade:0.5,
            time:time
        });
    },
    showCallBackMessage:function (msg,callback) {
        ii = layer.msg(msg,{
            icon:1,
            shade:0.5,
            time:0
        });
        setTimeout(function () {
            layer.close(ii);
            callback();//方法回调的实现
        },2000);
    },
    showErrorCallBackMessage:function (msg,callback) {
        ii = layer.msg(msg,{
            icon:2,
            shade:0.5,
            time:0
        });
        setTimeout(function () {
            layer.close(ii);
            callback();
        },2000);
    },
    getQueryString:function(name){
        var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null){
            return decodeURI(r[2]);
        }else {
            return null;
        }
    }
}