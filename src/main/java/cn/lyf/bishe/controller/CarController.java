package cn.lyf.bishe.controller;

import cn.lyf.bishe.domain.Car;
import cn.lyf.bishe.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Date:2021/1/22 21:24
 * Author:lyf
 */
@Controller
public class CarController {

    @Autowired
    private CarService carService;


    //查询所有车辆信息
    @RequestMapping("/carInfoView")
    public String carInfoView(HttpSession session) {
        session.setAttribute("carList", "");


        List<Car> carList = carService.findCar();
        session.setAttribute("carList", carList);

        return "admin/view/carInfo.html";
    }

    //跳转添加车辆页面
    @RequestMapping("/carAddView")
    public String carAddView(){
        return "admin/view/carAdd.html";
    }

    //跳转车辆维护页面
    @RequestMapping("/carAlterView")
    public String carAlterView(HttpSession session){
        session.setAttribute("carList", "");

        List<Car> carList = carService.findCar();
        session.setAttribute("carList", carList);
        return "admin/view/carAlter.html";
    }



    //添加车辆
    @RequestMapping("/carAddController")
    public String carAddController(@RequestParam("carNum")String carNum,
                                   @RequestParam("carBrand")String carBrand,
                                   @RequestParam("carWeight")String carWeight,
                                   @RequestParam("carMile")String carMile,
                                   @RequestParam("carPress")String carPress,
                                   Model model){
        if (carNum==null||carBrand==null||carWeight==null||carMile==null||carPress==null
        ||carNum.equals("")||carBrand.equals("")||carWeight.equals("")||carMile.equals("")||carPress.equals("")){
            model.addAttribute("msg","信息不能为空");
            return "admin/view/carAdd.html";
        }
        boolean b = carService.addCar(carNum, carBrand, carWeight, carMile, carPress);
        if(b){
            return "redirect:/carInfoView";
        }else {
            model.addAttribute("msg","添加失败");
            return "admin/view/carAdd.html";
        }
    }



    //删除车辆信息
    @RequestMapping("/carDeleteController")
    public String carDeleteController(@RequestParam("id")String id){
        boolean b = carService.deleteCarById(id);
        if (b){
            return "redirect:/carAlterView";
        }else return "admin/view/carAlter.html";
    }

    //跳转修改车辆信息页面，带当前数据
    @RequestMapping("/carModifyView")
    public String carModifyView(@RequestParam("id")String id,
                                      HttpSession session){

        session.setAttribute("car","");

        Car car = carService.findCarById(id);
        session.setAttribute("car",car);
        return "admin/view/carModify.html";
    }

    //修改车辆信息功能实现
    @RequestMapping("/carModifyController")
    public String carModifyController(@RequestParam("id")String id,
                                      @RequestParam("carNum")String carNum,
                                      @RequestParam("carBrand")String carBrand,
                                      @RequestParam("carWeight")String carWeight,
                                      @RequestParam("carMile")String carMile,
                                      @RequestParam("carPress")String carPress){

        boolean b = carService.updateCar(id,carNum, carBrand, carWeight, carMile, carPress);
        if (b){
            return "redirect:/carAlterView";
        }else return "admin/view/carModify.html";

    }
}
