package cn.lyf.bishe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Date:2021/1/19 20:38
 * Author:lyf
 */
@Controller
public class QueryController {

    @RequestMapping("/queryView")
    public String queryView(){
        return "query.html";
    }



}
