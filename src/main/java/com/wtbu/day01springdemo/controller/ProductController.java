package com.wtbu.day01springdemo.controller;

import com.wtbu.day01springdemo.entity.Product;
import com.wtbu.day01springdemo.entity.ResultObj;
import com.wtbu.day01springdemo.service.ProductService;
import com.wtbu.day01springdemo.utls.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class ProductController {
    @Value("${person.name}")
    private String name;
    @Value("${person.age}")
    private Integer age;
    private ArrayList<Product> products;

    @RequestMapping("/test")
    public String test(){
        return "myTest";
    }

    @RequestMapping("/quick")
    //告诉服务器返回的是字符串，不是页面。可以自动将对象转换为json数据
    @ResponseBody
    public String quick(){
        return "springboot访问成功 name="+name+",age="+age;
    }

    @Autowired
    ProductService productService;

    @RequestMapping("/showProducts")
    //@ResponseBody
    public String showProducts(Model model){
        model.addAttribute("products",productService.getProducts());
        model.addAttribute("name","张三");
        return "products";
    }

    //下面接口写法不严谨
    @RequestMapping("/showProductsJSON")
    @ResponseBody
    public ArrayList<Product> showProductsJSON(){
        return productService.getProducts();
    }

    @RequestMapping("/getProductsByCateIdJSON")
    @ResponseBody
    public ResultObj getProductsByCateIdJSON(String t_id,String start,String length){
        System.out.println("接收到t_id:"+t_id);
        try {
            ArrayList<Product> products = productService.getProductsByCateId(t_id,start,length);
            if(products.size()>0){
                return JSONResult.JSONReturnWithData("0",products);
            }else{
                return JSONResult.JSONReturnWithData("1","该分类下没有商品");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONResult.JSONReturnWithData("0",t_id);
    }

    @RequestMapping("/searchContent")
    @ResponseBody
    public ResultObj searchContent(String search,String start,String length){
        try {
            ArrayList<Product> products = productService.searchProductsByCateId(search,start,length);
            if(products.size()>0){
                return JSONResult.JSONReturnWithData("0",products);
            }else{
                return JSONResult.JSONReturnWithData("1","没有搜索到相关商品");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.JSONReturnWithData("2","服务器繁忙");
        }
    }

    @RequestMapping("/getProductDetail")
    @ResponseBody
    public ResultObj getProductDetail(String pro_id){
        try {
            Product product = productService.getProductsByProId(pro_id);
            if(product!=null){
                return JSONResult.JSONReturnWithData("0",product);
            }else{
                return JSONResult.JSONReturnWithData("1","没有搜索到相关商品");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.JSONReturnWithData("2","服务器繁忙");
        }
    }
}
