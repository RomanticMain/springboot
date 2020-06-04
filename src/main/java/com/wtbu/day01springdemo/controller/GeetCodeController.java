package com.wtbu.day01springdemo.controller;

import com.wtbu.day01springdemo.geetcode.GeetestConfig;
import com.wtbu.day01springdemo.geetcode.GeetestLib;
import com.wtbu.day01springdemo.geetcode.GeetestLibResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//处理验证码的控制器
@Controller
public class GeetCodeController {
    @RequestMapping("/register")
    public void getFirstCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GeetestLib gtLib = new GeetestLib(GeetestConfig.GEETEST_ID, GeetestConfig.GEETEST_KEY);
        String userId = "test";
        String digestmod = "md5";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("digestmod", digestmod); // 必传参数，此版本sdk可支持md5、sha256、hmac-sha256，md5之外的算法需特殊配置的账号，联系极验客服
        // 自定义参数,可选择添加
        paramMap.put("user_id", userId); // 网站用户id
        paramMap.put("client_type", "web"); // web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        paramMap.put("ip_address", "127.0.0.1"); // 传输用户请求验证时所携带的IP
        GeetestLibResult result = gtLib.register(paramMap, digestmod);
        // 将结果状态设置到session中
        // 注意，此处api1接口存入session，api2会取出使用，格外注意session的存取和分布式环境下的应用场景
        request.getSession().setAttribute(GeetestLib.GEETEST_SERVER_STATUS_SESSION_KEY, result.getStatus());
        request.getSession().setAttribute("userId", userId);
        // 注意，不要更改返回的结构和值类型
        PrintWriter out = response.getWriter();
        out.println(result.getData());
    }

}
