package com.thc.realspr.controller;

import com.thc.realspr.dto.UserInteractionDto;
import com.thc.realspr.service.UserInteractionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/feeduser")
@RestController
public class UserInteractionController {

    private final UserInteractionService userInteractionService;

    public UserInteractionController(
            UserInteractionService userInteractionService
    ) {
        this.userInteractionService = userInteractionService;
    }

    @PostMapping("/seen")
    public String postSeen(@Valid @RequestBody UserInteractionDto.SeenSubjectReqDto param, HttpServletRequest request) {
        return userInteractionService.seen(param, request.getAttribute("reqUserId").toString());
    }


}
