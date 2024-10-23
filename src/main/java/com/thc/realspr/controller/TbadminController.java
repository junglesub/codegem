package com.thc.realspr.controller;

import com.thc.realspr.dto.TbadminDto;
import com.thc.realspr.service.TbadminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/admin")
@RestController
public class TbadminController {
    private final TbadminService tbadminService;

    public TbadminController(TbadminService tbadminService) {
        this.tbadminService = tbadminService;
    }

    @GetMapping("/users")
    public List<TbadminDto.UserDetail> adminGetUser(@RequestParam Map<String, String> param, HttpServletRequest request) {
        return tbadminService.adminGetUser(param);
    }
}
