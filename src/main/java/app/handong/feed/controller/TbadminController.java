package app.handong.feed.controller;

import app.handong.feed.dto.TbadminDto;
import app.handong.feed.service.TbadminService;
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
        String reqUserId = request.getAttribute("reqUserId").toString();
        return tbadminService.adminGetUser(reqUserId, param);
    }

    @GetMapping("/firebasefilelist")
    public List<String> adminGetFirebaseStorageList(@RequestParam Map<String, String> param, HttpServletRequest request) {
        String reqUserId = request.getAttribute("reqUserId").toString();
        return tbadminService.adminGetFirebaseStorageList(reqUserId);
    }
}
