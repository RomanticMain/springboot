package com.wtbu.day01springdemo.service;

import com.wtbu.day01springdemo.dao.ProductMapper;
import com.wtbu.day01springdemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    public ArrayList<Product> getProducts(){

        return productMapper.getProducts();
    }

    public ArrayList<Product> getProductsByCateId(String t_id,String start,String length){
        return productMapper.getProductsByCateId(t_id,start,length);
    }

    public ArrayList<Product> searchProductsByCateId(String search, String start, String length) {
        return productMapper.searchProductsByCateId(search,start,length);
    }

    public Product getProductsByProId(String pro_id) {
        return productMapper.getProductsByProId(pro_id);
    }
}
