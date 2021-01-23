package cn.lyf.bishe.controller;

import cn.lyf.bishe.domain.Store;
import cn.lyf.bishe.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Date:2021/1/23 21:02
 * Author:lyf
 */
@Controller
public class StoreController {


    @Autowired
    private StoreService storeService;


    //显示所有库存
    @RequestMapping("/storeInfoView")
    public String storeInfoView(HttpSession session) {
        List<Store> storeList = storeService.findStore();
        session.setAttribute("storeList", storeList);
        return "admin/view/storeInfo.html";
    }


    //跳转添加库存页面
    @RequestMapping("/storeAddView")
    public String storeAddView(HttpSession session) {
        return "admin/view/storeAdd.html";
    }

    //添加库存controller
    @RequestMapping("/storeAddController")
    public String storeAddController(@RequestParam("storeName") String storeName,
                                     @RequestParam("goodsName") String goodsName,
                                     @RequestParam("goodsNum") String goodsNum,
                                     @RequestParam("goodsStatus") String goodsStatus,
                                     Model model) {
        boolean b = storeService.addStore(storeName, goodsName, goodsNum, goodsStatus);
        if (b) {
            return "redirect:/storeInfoView";
        } else {
            model.addAttribute("msg", "新增失败");
            return "admin/view/storeAdd.html";
        }

    }

    //跳转库存维护页面
    @RequestMapping("/storeAlterView")
    public String storeAlterView(HttpSession session){

        List<Store> storeList = storeService.findStore();
        session.setAttribute("storeList", storeList);
        return "admin/view/storeAlter.html";
    }

    //删除库存信息
    @RequestMapping("/storeDeleteController")
    public String storeDeleteController(@RequestParam("id")String id){
        boolean b = storeService.deleteStoreById(id);
        if (b){
            return "redirect:/storeAlterView";
        }else return "admin/view/storeAlter.html";
    }

    //跳转修改库存信息页面，带当前数据
    @RequestMapping("/storeModifyView")
    public String storeModifyView(@RequestParam("id")String id,
                                HttpSession session){


        Store store = storeService.findStoreById(id);
        session.setAttribute("store",store);
        return "admin/view/storeModify.html";
    }

    //修改库存信息功能实现
    @RequestMapping("/storeModifyController")
    public String carModifyController(@RequestParam("id")String id,
                                      @RequestParam("storeName")String storeName,
                                      @RequestParam("goodsName")String goodsName,
                                      @RequestParam("goodsNum")String goodsNum,
                                      @RequestParam("goodsStatus")String goodsStatus){

        boolean b = storeService.updateStore(id,storeName, goodsName, goodsNum, goodsStatus);
        if (b){
            return "redirect:/storeAlterView";
        }else return "admin/view/storeModify.html";

    }

}
