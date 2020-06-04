package com.wtbu.day01springdemo.service;

import com.wtbu.day01springdemo.dao.CategoryMapper;
import com.wtbu.day01springdemo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    //自动注入
    @Autowired
    CategoryMapper categoryMapper;

    public ArrayList<Category> getAllCategory() {
        return categoryMapper.getAllCategory();
    }
}
