package cn.lyf.bishe.controller;

import cn.lyf.bishe.domain.Orders;
import cn.lyf.bishe.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Date:2021/1/19 20:38
 * Author:lyf
 */
@Controller
public class QueryController {

    @Autowired
    private OrdersService ordersService;

    //跳转主页，并清空用户数据
    @RequestMapping("/queryView")
    public String queryView(HttpSession session){
        session.setAttribute("user",null);
        return "query.html";
    }
    //跳转页面，不清空数据
    @RequestMapping("/toQueryView")
    public String toQueryView(){
        return "query.html";
    }



    @RequestMapping("/queryController")
    public String queryController(@RequestParam("text")String text,
                                  HttpSession session){
        List<Orders> ordersList = ordersService.findOrdersByText(text);
        session.setAttribute("ordersList",ordersList);
        return "/query.html";

    }



}
