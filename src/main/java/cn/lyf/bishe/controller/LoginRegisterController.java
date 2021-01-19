package cn.lyf.bishe.controller;

import cn.lyf.bishe.domain.User;
import cn.lyf.bishe.service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Date:2021/1/19 21:00
 * Author:lyf
 */
@Controller
public class LoginRegisterController {

    @Autowired
    private LoginRegisterService loginRegisterService;



    //跳转登录页面
    @RequestMapping("/loginView")
    public String loginView(){
        return "login.html";
    }

    //跳转注册页面
    @RequestMapping("registerView")
    public String registerView(){
        return "register.html";
    }


    //用户登录模块
    @RequestMapping("/loginController")
    public String loginController(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  Model model,
                                  HttpSession session) {

        model.addAttribute("msg", "");

        User user = loginRegisterService.isExistUserByUsernamePassword(username, password);
        if (user==null) {
            model.addAttribute("msg", "用户名或密码错误");
            return "login.html";
        } else {
            session.setAttribute("user", user);
            return "query.html";
        }
    }


    //用户注册模块
    @RequestMapping("/registerController")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("rePassword") String rePassword,
                           @RequestParam("realName") String realName,
                           Model model) {

        model.addAttribute("msg", "");
        if (!password.equals(rePassword)) {
            model.addAttribute("msg", "两次密码不一致");
            return "register.html";
        }


        boolean existStaffByUsername = loginRegisterService.isExistUserByUsername(username);
        if (existStaffByUsername) {
            model.addAttribute("msg", "用户名已存在");
            return "register.html";
        }

        int i = loginRegisterService.addUser(username, password, realName);
        if (i != 1) {
            model.addAttribute("msg", "注册失败，请重新注册");
            return "register.html";
        }

        model.addAttribute("msg", "注册成功");
        return "register.html";
    }
}
