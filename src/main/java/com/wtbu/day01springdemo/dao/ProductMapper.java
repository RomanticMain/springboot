package com.wtbu.day01springdemo.dao;

import com.wtbu.day01springdemo.entity.Product;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

//mybatis采用接口的方式进行编程
public interface ProductMapper {
    @Select("SELECT * FROM t_product")
    ArrayList<Product> getProducts();

    //实际开发不能将数据一次性查出来 可以引入分页功能解决这个问题 SQL的LIMIT语句
    @Select("SELECT * FROM t_product where pro_state=#{param1} LIMIT ${param2},${param3}")
    ArrayList<Product> getProductsByCateId(String t_id,String start,String length);

    @Select("SELECT * FROM t_product WHERE pro_name LIKE '${param1}%' LIMIT ${param2},${param3}")
    ArrayList<Product> searchProductsByCateId(String search, String start, String length);

    @Select("SELECT * FROM t_product WHERE pro_id = ${param1}")
    Product getProductsByProId(String pro_id);
}
