package edu.soft1.controller;

import edu.soft1.controller.pojo.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")//被拦截器排除在外
public class UserController {
    @RequestMapping(value = "/login")
    public String login(User user, HttpSession session,HttpServletRequest request){
        System.out.println("---login()---");
        //获取username的值
//        request.getParameter("");//对get以及post
        System.out.println("username="+user.getUsername());
        //调用业务层（业务层调用dao层），获取flag的值
        int flag = 1;
        if (flag==1){
            session.setAttribute("user",user);//登录对象放入session
            return "welcome";//登录成功
        }
        System.out.println("登陆失败，返回首页index");
        request.setAttribute("error","登录失败，请重新尝试");
        return "forward:/index.jsp";
        //return "forward:/index.jsp";//登录失败，转回首页
       // return  "redirect:/index.jsp";
    }
    @RequestMapping("/logout")//被拦截器拦截的方法
    public String logout(HttpSession session) {
        //清空session
        System.out.println("logout");
        session.invalidate();
        return "redirect:/index.jsp";//重定向跳转至首页
    }
    @RequestMapping("/delete")//被拦截器拦截的方法
    public String delete(HttpServletRequest request) {
        System.out.println("---执行delete()成功---");
        request.setAttribute("CRUDmsg","删除功能");
        return "hello";
    }
    @RequestMapping("/welcome")//被拦截器拦截的方法
    public String welcome() {
        System.out.println("---welcome()---");
        return "welcome";

    }


    /*private  String doFilename2(String filename, HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.toUpperCase().indexOf("FIREFOX")>0);{
            filename = "?UTF-8?B?"+new String();//
        }else{
            filename = URLEncoder.encode(filename,"UTF-8");
        }
    }*/



    @RequestMapping("/hello")
    public String hello() {
        System.out.println("");
        return "/hello";
    }



    /*@RequestMapping("/reg")
    public String reg(User user) {
        System.out.println("username="+user.getUsername());
        System.out.println("pwd="+user.getPwd());
        System.out.println("age="+user.getAge());
        *//*System.out.println("birthday="+user.getBirthday());*//*
        System.out.println("city="+user.getAddress().getCity());
        System.out.println("Street="+user.getAddress().getStreet());
        System.out.println("phone="+user.getAddress().getPhone());
        return "redirect:/param1/test";

}*/
}

