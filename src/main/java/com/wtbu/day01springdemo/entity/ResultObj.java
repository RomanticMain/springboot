package com.wtbu.day01springdemo.entity;
//这个类用来封装各种自定义的返回信息 方便前端页面进行判断
public class ResultObj {
    private String retcode;
    private Object data;//因为返回的对象不确定 都使用父类 表现是一种多态

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
