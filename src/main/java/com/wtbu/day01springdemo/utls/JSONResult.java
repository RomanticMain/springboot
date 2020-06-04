package com.wtbu.day01springdemo.utls;

import com.wtbu.day01springdemo.entity.ResultObj;

public class JSONResult {
    public static ResultObj JSONReturnWithData(String retcode,Object data){
        ResultObj result = new ResultObj();
        result.setRetcode(retcode);
        result.setData(data);
        return result;
    }
}
