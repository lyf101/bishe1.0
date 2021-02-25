package cn.lyf.bishe.controller;

import cn.lyf.bishe.domain.Orders;
import cn.lyf.bishe.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Date:2021/2/25 11:23
 * Author:lyf
 */
@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    //显示配送信息
    @RequestMapping("ordersInfoView")
    public String ordersInfoView(HttpSession session){
        List<Orders> allOrders = ordersService.findAllOrders();
        session.setAttribute("ordersList",allOrders);
        return "admin/view/ordersInfo.html";
    }

    //跳转添加配送页面
    @RequestMapping("ordersAddView")
    public String ordersAddView(){
        return "admin/view/ordersAdd.html";
    }

    //添加配送Controller
    @RequestMapping("ordersAddController")
    public String ordersAddController(@RequestParam("userName")String userName,
                                      @RequestParam("storeName")String storeName,
                                      @RequestParam("staffName")String staffName,
                                      @RequestParam("endAddress")String endAddress,
                                      @RequestParam("currentAddress")String currentAddress,
                                      Model model){
        if (userName==null||storeName==null||staffName==null||endAddress==null||currentAddress==null
                ||userName.equals("")||storeName.equals("")||staffName.equals("")||endAddress.equals("")||currentAddress.equals("")){
            model.addAttribute("msg","信息不能为空");
            return "admin/view/ordersAdd.html";
        }

        boolean b = ordersService.addOrders(userName, storeName, staffName, endAddress, currentAddress);
        if (b){
            return "redirect:/ordersInfoView";
        }else {
            model.addAttribute("msg","添加失败，请重新添加");
            return "admin/view/ordersAdd.html";
        }
    }

    //跳转配送信息维护页面
    @RequestMapping("ordersAlterView")
    public String ordersAlterView(HttpSession session){
        session.setAttribute("ordersList", "");

        List<Orders> allOrders = ordersService.findAllOrders();
        session.setAttribute("ordersList",allOrders);
        return "admin/view/ordersAlter.html";
    }

    //根据id删除配送信息
    @RequestMapping("ordersDeleteController")
    public String ordersDeleteController(@RequestParam("id")String id){
        boolean b = ordersService.deleteOrdersById(id);
        if (b){
            return "redirect:/ordersAlterView";
        }else {
            return "admin/view/ordersAlter.html";
        }
    }

    //跳转修改配送信息页面,带当前数据
    @RequestMapping("ordersModifyView")
    public String ordersModifyView(@RequestParam("id")String id,HttpSession session){
        Orders orderById = ordersService.findOrderById(id);
        session.setAttribute("orders",orderById);
        return "admin/view/ordersModify.html";
    }

    @RequestMapping("ordersModifyController")
    public String ordersModifyController(@RequestParam("userName")String userName,
                                         @RequestParam("storeName")String storeName,
                                         @RequestParam("staffName")String staffName,
                                         @RequestParam("endAddress")String endAddress,
                                         @RequestParam("currentAddress")String currentAddress,
                                         @RequestParam("id")String id,
                                         @RequestParam("time")String time,
                                         Model model
                                         ){
        Orders orders = new Orders();
        orders.setCurrentAddress(currentAddress);
        orders.setEndAddress(endAddress);
        orders.setStaffName(staffName);
        orders.setStoreName(storeName);
        orders.setUserName(userName);
        orders.setTime(time);
        orders.setId(id);

        boolean b = ordersService.updateOrders(orders);
        if (b){
            return "redirect:/ordersAlterView";
        }else {
            model.addAttribute("msg","修改失败，请重新修改");
            return "admin/view/ordersModify.html";
        }
    }
}
