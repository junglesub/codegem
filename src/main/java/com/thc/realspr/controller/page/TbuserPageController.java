package com.thc.realspr.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbuser")
@Controller
public class TbuserPageController {
    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return "tbuser/" + page;
    }
}
