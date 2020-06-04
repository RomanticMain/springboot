package com.wtbu.day01springdemo.controller;

import com.wtbu.day01springdemo.entity.Category;
import com.wtbu.day01springdemo.entity.ResultObj;
import com.wtbu.day01springdemo.service.CategoryService;
import com.wtbu.day01springdemo.utls.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/getCategoryJSON")
    @ResponseBody
    public ResultObj getCategory(){
        try {
            ArrayList<Category> cates = categoryService.getAllCategory();
            if(cates.size()>0){
                return JSONResult.JSONReturnWithData("0",cates);
            }else {
                return JSONResult.JSONReturnWithData("1","暂无分类信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.JSONReturnWithData("2","后台繁忙，请稍后再试");
        }
    }
}
