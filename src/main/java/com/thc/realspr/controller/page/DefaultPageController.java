package com.thc.realspr.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DefaultPageController {
    @GetMapping({"","/","/index"})
    public String index(){
        return "index";
    }
/*

    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return "tbbanner/" + page;
    }
*/

}
