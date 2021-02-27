package cn.lyf.bishe.controller;

import cn.lyf.bishe.domain.Orders;
import cn.lyf.bishe.domain.Staff;
import cn.lyf.bishe.service.OrdersService;
import cn.lyf.bishe.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Date:2021/2/27 20:31
 * Author:lyf
 */
@Controller
public class MailSendController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/toMailSendView")
    public String toMailSendView(HttpSession session){
        if (session.getAttribute("user")==null||session.getAttribute("user").equals("")){
            return "redirect:/loginView";
        }else return "mailSend.html";
    }


    @RequestMapping("/mailSendController")
    public String mailSendController(@RequestParam("userName")String username,
                                     @RequestParam("storeName")String storeName,
                                     @RequestParam("endAddress")String endAddress){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(date);

        String staffName = "";
        String staffAddress = "";

        Staff staff = staffService.findStaffByState();
        if (staff!=null){
            staffName = staff.getStaffName();
            staffAddress = staff.getStaffAddress();
        }

        Orders orders = new Orders();
        orders.setId(UUID.randomUUID().toString());
        orders.setTime(time);
        orders.setUserName(username);
        orders.setStoreName(storeName);
        orders.setStaffName(staffName);
        orders.setCurrentAddress(staffAddress);
        orders.setEndAddress(endAddress);

        boolean b = ordersService.addOrdersForUser(orders);
        if (b){
            return "redirect:/toQueryView";
        }else {
            return "mailSend.html";
        }

    }
}
