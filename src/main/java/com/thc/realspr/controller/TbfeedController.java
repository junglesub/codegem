package com.thc.realspr.controller;

import com.thc.realspr.service.TbfeedService;
import com.thc.realspr.service.TbuserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/feed")
@RestController
public class TbfeedController {

    private final TbfeedService tbfeedService;
    public TbfeedController(
            TbfeedService tbfeedService
    ) {
        this.tbfeedService = tbfeedService;
    }

    @PostMapping("")
    public Map<String, Object> create(@RequestBody Map<String, Object> param){
        System.out.println(param);
        return tbfeedService.create(param);
    }
    @PutMapping("")
    public Map<String, Object> update(@RequestBody Map<String, Object> param){
        System.out.println(param);
        return tbfeedService.update(param);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id){
        tbfeedService.delete(id);
        return "";
    }
    @GetMapping("/get")
    public List<Map<String, Object>> getAll() {
        return tbfeedService.getAll();
    }
    @GetMapping("/get/{id}")
    public Map<String, Object> detail(@PathVariable("id") String id){
        System.out.println(id);
        return tbfeedService.get(id);
    }
}
