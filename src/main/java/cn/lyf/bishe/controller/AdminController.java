package cn.lyf.bishe.controller;

import cn.lyf.bishe.domain.User;
import cn.lyf.bishe.service.AdminService;
import cn.lyf.bishe.service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Date:2021/1/22 20:02
 * Author:lyf
 */
@Controller
public class AdminController {
    @Autowired
    private LoginRegisterService loginRegisterService;

    @Autowired
    private AdminService adminService;



    //管理员登录模块
    @RequestMapping("/adminLogin")
    public String admin(){
        return "admin/adminLogin.html";
    }

    //管理员页面上
    @RequestMapping("/adminTopController")
    public String adminTopController(){
        return "admin/adminTop.html";
    }
    //管理员页面左
    @RequestMapping("/adminLeftController")
    public String adminLeftController(){
        return "admin/adminLeft.html";
    }




    //管理员登录模块
    @RequestMapping("/loginAdminController")
    public String loginAdminController(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       Model model,
                                       HttpSession session) {
        User user = loginRegisterService.isExistUserByUsernamePassword(username, password);
        if (user == null) {
            model.addAttribute("msg", "管理员用户名或密码错误");
            return "admin/adminLogin.html";
        }else if (user.getUserType().equals("user")){
            model.addAttribute("msg", "普通用户无法进入管理系统");
            return "admin/adminLogin.html";
        }else {
            return "admin/admin.html";
        }

    }

    //查看所有用户信息
    @RequestMapping("/userInfoView")
    public String userInfoView(HttpSession session){

        List<User> userList = adminService.findUser();
        session.setAttribute("userList",userList);

        return "/admin/view/userInfo.html";
    }

    //用户维护
    @RequestMapping("/userAlterView")
    public String userAlterView(HttpSession session){

        List<User> userList = adminService.findUser();
        session.setAttribute("userList",userList);

        return "/admin/view/userAlter.html";
    }


    //删除用户
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("id")String id){
        boolean b = adminService.deleteUserById(id);
        if(b){
            return "redirect:/userInfoView";
        }else return "/admin/view/userInfo.html";
    }

}
