package com.wtbu.day01springdemo.dao;

import com.wtbu.day01springdemo.entity.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CategoryMapper {
    @Select("SELECT * FROM t_category")
    public ArrayList<Category> getAllCategory();
}
