package edu.soft1.controller;


import lombok.Data;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;
import java.util.UUID;



@Controller
@RequestMapping(value = "/param1")
public class MyController {
    //多个上传
    @RequestMapping(value = "/upload",method = {RequestMethod.POST})
    public String fileUpload(MultipartFile[] images, HttpServletRequest request) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        int count = 0;
        for (MultipartFile image: images) {
            is = image.getInputStream();//获取文件的输入流对象
            String filename = image.getOriginalFilename();//获取文件的原名
            if(filename.equals("")){
                System.out.println("进入下一轮");
                continue;
            }
            String realPath = request.getServletContext().getRealPath("/images");
            //根据文件的真实路径和名字创建输出流对象
            os = new FileOutputStream(new File(realPath,doFileName(filename)));
            /*IOUtils.copy(is,os);//把输入流写入输出流，完成上传*/
            int size = IOUtils.copy(is,os);
            if(size > 0){count++;}
        }
        os.close();is.close();//工作原则：先开的后关，后开的先关
        System.out.println("上传成功"+count+"张图片");
        return "welcome";
    }
    private String doFileName(String filename){
        String extension = FilenameUtils.getExtension(filename);
        String uuid = UUID.randomUUID().toString();
        System.out.println("上传文件"+uuid);
        return uuid+"."+extension;
    }


    //单个上传
    @RequestMapping(value = "/upload1",method = {RequestMethod.POST})
    public String fileUpload(MultipartFile images,HttpServletRequest request, Map<String,Object>map) throws IOException {
        InputStream is = images.getInputStream();
        String filename = images.getOriginalFilename();
        String realPath = request.getServletContext().getRealPath("/images");
        FileOutputStream os = new FileOutputStream(new File(realPath,doFileName(filename)));
        int size = IOUtils.copy(is,os);
        os.close();is.close();
        if(size > 0){
            map.put("msg","uploadSuccess");
            System.out.println("上传成功"+size);
        }
        return "welcome";
    }

    private String doFileName1(String filename) {
        String extension = FilenameUtils.getExtension(filename);
        String uuid = UUID.randomUUID().toString();
        System.out.println("上传文件" + uuid);
        return uuid + "." + extension;
    }




    @RequestMapping(value = "/load/{filename}")
    public void load(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setHeader("Content-Disposition","attachment;filename="+doFileName1(filename,request);
        String realPath = request.getServletContext().getRealPath("/images");
        System.out.println("下载路径="+realPath);
        InputStream is = new FileInputStream(new File(realPath,filename));
        OutputStream os = response.getOutputStream();
        //把输入流写入输出流
        int size = IOUtils.copy(is,os);
        System.out.println("下载"+size+"字节");
        /*if (size > 0) {//下载成功
            response.setContentType("text/html;charset=uft-8");//响应体设置
            PrintWriter writer = response.getWriter();
            writer.print("<script type=\"text/javascript\">alert(‘上传成功’);</script>");

        }*/
        os.close();is.close();
    }
    /*@RequestMapping("/index2")
    public String hello(HttpServletRequest request){
        System.out.println("---Hello()---");

        return "index2";
    }
    @RequestMapping("/index22")
    public ModelAndView index2() {
        System.out.println("---hello()---");//进入方法的标志
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index2");
        mav.addObject("msg","I'm Peter!");
        return mav;
    }

    *//*@RequestMapping(value = "/param1",method = {RequestMethod.GET})
    public String param1(HttpServletRequest request){
        String name = request.getParameter("name");//接收数据
        System.out.println("name="+name);
        //添加到ModelAndView
        request.setAttribute("name",name);
        return "/index2";
    }*//*
   *//* @RequestMapping("/index11")
    public String index22(String username, Model model) {
        System.out.println("---hello()---");
        //将传入的参数添加到Model对象（存入了request）作用域
        model.addAttribute("username",username);
        return "index2";
    }*//*
    *//*@RequestMapping("/hi")
    public String hi*//*

    @RequestMapping(value = "/param2",method = {RequestMethod.GET,RequestMethod.POST})
    public String  param2(HttpServletRequest request){
        String name = request.getParameter("username");//接收数据
        String age = request.getParameter("age");//控制台打印输出
        System.out.println("姓名="+name+",年龄="+age);//将数据存入session
        request.setAttribute("age",age);//将数据存入request
        request.setAttribute("name",name);
        return "/index2";//跳转至index2
    }
    @RequestMapping(value = "param3",method = {RequestMethod.POST})
    public String param3(String username,int age) {//数据名与方法参数名相同
        System.out.println("---param3()---");
        System.out.println("username="+username);
        System.out.println("age="+age);
        return "/index2";
    }
    @RequestMapping(value = "param4",method = {RequestMethod.POST})//数据名与方法名各不同
    public String param4(
            @RequestParam(value = "username",required = false)String u,
                         @RequestParam(value = "age",defaultValue = "18")int a,HttpSession session) {
        System.out.println("----param4----");
        System.out.println("u="+u);
        System.out.println("a="+a);
        return "/index2";//redirect//forward
    }
    @RequestMapping(value = "param5",method = {RequestMethod.POST})//数据名与方法名各不同
    public String param5(User user,HttpSession session){
        System.out.println("----param5----");
        System.out.println("username="+user.getUsername());
        System.out.println("age="+user.getAge());
        session.setAttribute("name",user.getUsername());
        return "redirect:test";//redirect//forward
    }
    @RequestMapping("test")
    public String test(){
        System.out.println("test-----");
        return "/index2";
    }
    @RequestMapping("/reg")
    public String reg(User user) {
        System.out.println("username="+user.getUsername());
        System.out.println("pwd="+user.getAge());
        System.out.println("birthday="+user.getBirthday());
        System.out.println("city="+user.getAddress().getCity());
        System.out.println("Street="+user.getAddress().getStreet());
        System.out.println("phone="+user.getAddress().getPhone());
        return "redirect:/param1/test";
    }*/



}
