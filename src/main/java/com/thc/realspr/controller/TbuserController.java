package com.thc.realspr.controller;

import com.thc.realspr.repository.TbuserRepository;
import com.thc.realspr.service.TbuserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/tbuser")
@RestController
public class TbuserController {

    private final TbuserService tbuserService;
    public TbuserController(
            TbuserService tbuserService
    ) {
        this.tbuserService = tbuserService;
    }

    @PostMapping("")
    public Map<String, Object> create(@RequestBody Map<String, Object> param){
        System.out.println(param);
        return tbuserService.create(param);
    }
    @PutMapping("")
    public Map<String, Object> update(@RequestBody Map<String, Object> param){
        System.out.println(param);
        return tbuserService.update(param);
    }
    @GetMapping("/get/{id}")
    public Map<String, Object> detail(@PathVariable("id") String id){
        System.out.println(id);
        return tbuserService.get(id);
    }
}
