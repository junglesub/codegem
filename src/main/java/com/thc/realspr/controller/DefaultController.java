package com.thc.realspr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DefaultController {
    @GetMapping("/")
    public String create(){

        return "Hello World";
    }
}
