package com.thc.realspr.controller;

import com.thc.realspr.dto.GoogleLoginRequest;
import com.thc.realspr.dto.GoogleLoginResponse;
import com.thc.realspr.exception.NoAuthorizationException;
import com.thc.realspr.service.GoogleAuthService;
import com.thc.realspr.service.TbuserService;
import com.thc.realspr.util.JwtTokenUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/tbuser")
@RestController
public class TbuserController {


    private final TbuserService tbuserService;

    public TbuserController(TbuserService tbuserService) {
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

    // Google 로그인 처리
    @PostMapping("/login/google")
    public GoogleLoginResponse loginWithGoogle(@RequestBody GoogleLoginRequest request) {
        return tbuserService.loginWithGoogle(request);
    }


}
