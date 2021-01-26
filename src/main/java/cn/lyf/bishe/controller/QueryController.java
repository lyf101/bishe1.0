package cn.lyf.bishe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Date:2021/1/19 20:38
 * Author:lyf
 */
@Controller
public class QueryController {

    //跳转主页，并清空用户数据
    @RequestMapping("/queryView")
    public String queryView(HttpSession session){
        session.setAttribute("user",null);
        return "query.html";
    }



 /*   @RequestMapping("/queryController")
    public String queryController(){

    }*/



}
