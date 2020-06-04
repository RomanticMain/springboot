package com.wtbu.day01springdemo.controller;

import com.wtbu.day01springdemo.entity.ResultObj;
import com.wtbu.day01springdemo.entity.User;
import com.wtbu.day01springdemo.geetcode.GeetestConfig;
import com.wtbu.day01springdemo.geetcode.GeetestLib;
import com.wtbu.day01springdemo.geetcode.GeetestLibResult;
import com.wtbu.day01springdemo.service.UserService;
import com.wtbu.day01springdemo.utls.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/queryUserByName")
    @ResponseBody
    public ResultObj queryUserByName(String name, String password,
                                     String geetest_challenge, String geetest_validate, String geetest_seccode, HttpServletRequest request){
        System.out.println("接收到name:"+name+"和password:"+password);
        GeetestLib gtLib = new GeetestLib(GeetestConfig.GEETEST_ID, GeetestConfig.GEETEST_KEY);
        String challenge = geetest_challenge;
        String validate = geetest_validate;
        String seccode = geetest_seccode;
        int status = 0;
        String userId = "";
        try {
            // 从session中获取一次验证状态码和userId
            status = (Integer) request.getSession().getAttribute(GeetestLib.GEETEST_SERVER_STATUS_SESSION_KEY);
            userId = (String) request.getSession().getAttribute("userId");

            Map<String, String> paramMap = new HashMap<String, String>();
            // 自定义参数,可选择添加
            paramMap.put("user_id", userId); // 网站用户id
            GeetestLibResult result = null;
            if (status == 1) {
                result = gtLib.successValidate(challenge, validate, seccode, paramMap);
            } else {
                result = gtLib.failValidate(challenge, validate, seccode);
                return JSONResult.JSONReturnWithData("6","服务器状态不正常");
            }
            if (result.getStatus() == 1) {
                try {
                    User u = userService.queryUserByName(name);
                    if(u!=null){
                        if(u.getU_password().equals(password)){
                            u.setU_password("");
                            return JSONResult.JSONReturnWithData("0",u);
                        }else{
                            return JSONResult.JSONReturnWithData("1","用户密码错误");
                        }
                    }else {
                        return JSONResult.JSONReturnWithData("3","用户名不存在，请先注册");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return JSONResult.JSONReturnWithData("2","服务器繁忙");
                }

                //    out.println(String.format("{\"result\":\"success\",\"version\":\"%s\"}", GeetestLib.VERSION));
            } else {
                return JSONResult.JSONReturnWithData("5","服务器二次验证失败");
                //    out.println(String.format("{\"result\":\"fail\",\"version\":\"%s\",\"msg\":\"%s\"}", GeetestLib.VERSION, result.getMsg()));
            }
            //out.println(result.getData());

        } catch (Exception e) {
            return JSONResult.JSONReturnWithData("4","session取key发生异常");
        }

    }
    @RequestMapping("/registUser")
    @ResponseBody
    public ResultObj registUser(String name, String password,
                                String geetest_challenge, String geetest_validate, String geetest_seccode, HttpServletRequest request){
        System.out.println("接收到注册信息name:"+name+"和password:"+password);
        GeetestLib gtLib = new GeetestLib(GeetestConfig.GEETEST_ID, GeetestConfig.GEETEST_KEY);
        String challenge = geetest_challenge;
        String validate = geetest_validate;
        String seccode = geetest_seccode;
        int status = 0;
        String userId = "";
        try {
            // 从session中获取一次验证状态码和userId
            status = (Integer) request.getSession().getAttribute(GeetestLib.GEETEST_SERVER_STATUS_SESSION_KEY);
            userId = (String) request.getSession().getAttribute("userId");

            Map<String, String> paramMap = new HashMap<String, String>();
            // 自定义参数,可选择添加
            paramMap.put("user_id", userId); // 网站用户id
            GeetestLibResult result = null;
            if (status == 1) {
                result = gtLib.successValidate(challenge, validate, seccode, paramMap);
            } else {
                result = gtLib.failValidate(challenge, validate, seccode);
                return JSONResult.JSONReturnWithData("6","服务器状态不正常");
            }
            if (result.getStatus() == 1) {
                try {
                    User u = userService.queryUserByName(name);
                    if(u!=null){
                            return JSONResult.JSONReturnWithData("1","用户名已存在");
                    }else {
                        User tempUser = new User();
                        tempUser.setU_name(name);
                        tempUser.setU_password(password);
                        boolean isRegistSuccess = userService.registUser(tempUser);
                        if(isRegistSuccess){
                            return JSONResult.JSONReturnWithData("2","注册成功");
                        }else{
                            return JSONResult.JSONReturnWithData("3","注册失败，请检查服务器");
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return JSONResult.JSONReturnWithData("2","服务器繁忙");
                }

                //    out.println(String.format("{\"result\":\"success\",\"version\":\"%s\"}", GeetestLib.VERSION));
            } else {
                return JSONResult.JSONReturnWithData("5","服务器二次验证失败");
                //    out.println(String.format("{\"result\":\"fail\",\"version\":\"%s\",\"msg\":\"%s\"}", GeetestLib.VERSION, result.getMsg()));
            }
            //out.println(result.getData());

        } catch (Exception e) {
            return JSONResult.JSONReturnWithData("4","session取key发生异常");
        }
    }
    //检验用户名是否存在
    @RequestMapping("/checkIsExistUserName")
    @ResponseBody
    public ResultObj checkIsExistUserName(String name) {
        User user = userService.queryUserByName(name);
        if(user!=null){
            return JSONResult.JSONReturnWithData("1","用户名已经注册了");
        }else{
            return JSONResult.JSONReturnWithData("0","用户名可以注册");
        }
    }
}
