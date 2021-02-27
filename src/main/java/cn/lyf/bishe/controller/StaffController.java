package cn.lyf.bishe.controller;

import cn.lyf.bishe.domain.Staff;
import cn.lyf.bishe.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Date:2021/1/22 21:24
 * Author:lyf
 */
@Controller
public class StaffController {

    @Autowired
    private StaffService staffService;


    //查询所有员工信息
    @RequestMapping("/staffInfoView")
    public String carInfoView(HttpSession session) {
        session.setAttribute("staffList", "");


        List<Staff> staffList = staffService.findStaff();
        session.setAttribute("staffList", staffList);

        return "admin/view/staffInfo.html";
    }

    //跳转添加员工页面
    @RequestMapping("/staffAddView")
    public String staffAddView(){
        return "admin/view/staffAdd.html";
    }

    //跳转员工维护页面
    @RequestMapping("/staffAlterView")
    public String staffAlterView(HttpSession session){
        session.setAttribute("staffList", "");

        List<Staff> staffList = staffService.findStaff();
        session.setAttribute("staffList", staffList);
        return "admin/view/staffAlter.html";
    }



    //添加员工
    @RequestMapping("/staffAddController")
    public String staffAddController(@RequestParam("staffId")String staffId,
                                   @RequestParam("staffName")String staffName,
                                   @RequestParam("staffSex")String staffSex,
                                   @RequestParam("staffState")String staffStateStr,
                                   @RequestParam("staffTel")String staffTel,
                                   @RequestParam("staffAddress")String staffAddress,
                                   Model model){
        if (staffId==null||staffName==null||staffSex==null||staffStateStr==null||staffTel==null||staffAddress==null
        ||staffId.equals("")||staffName.equals("")||staffSex.equals("")||staffStateStr.equals("")||staffTel.equals("")||staffAddress.equals("")){
            model.addAttribute("msg","信息不能为空");
            return "admin/view/staffAdd.html";
        }
        int staffState = Integer.valueOf(staffStateStr);
        boolean b = staffService.addStaff(staffId, staffName, staffSex, staffState, staffTel,staffAddress);
        if(b){
            return "redirect:/staffInfoView";
        }else {
            model.addAttribute("msg","添加失败");
            return "admin/view/staffAdd.html";
        }
    }



    //删除员工信息
    @RequestMapping("/staffDeleteController")
    public String staffDeleteController(@RequestParam("id")String id){
        boolean b = staffService.deleteStaffById(id);
        if (b){
            return "redirect:/staffAlterView";
        }else return "admin/view/staffAlter.html";
    }

    //跳转修改员工信息页面，带当前数据
    @RequestMapping("/staffModifyView")
    public String staffModifyView(@RequestParam("id")String id,
                                      HttpSession session){

        session.setAttribute("staff","");

        Staff staff = staffService.findStaffById(id);
        session.setAttribute("staff",staff);
        return "admin/view/staffModify.html";
    }

    //修改员工信息功能实现
    @RequestMapping("/staffModifyController")
    public String staffModifyController(@RequestParam("id")String id,
                                        @RequestParam("staffId")String staffId,
                                        @RequestParam("staffName")String staffName,
                                        @RequestParam("staffSex")String staffSex,
                                        @RequestParam("staffState")String staffStateStr,
                                        @RequestParam("staffTel")String staffTel,
                                        @RequestParam("staffAddress")String staffAddress){
        int staffState = Integer.valueOf(staffStateStr);
        boolean b = staffService.updateStaff(id,staffId, staffName, staffSex, staffState, staffTel,staffAddress);
        if (b){
            return "redirect:/staffAlterView";
        }else return "admin/view/staffModify.html";

    }
}
