package app.handong.codegem.controller;

import app.handong.codegem.service.ShortHashService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/kafeed")
@RestController
public class TbKaFeedController {

    private final ShortHashService shortHashService;

    public TbKaFeedController(
            ShortHashService shortHashService) {
        this.shortHashService = shortHashService;
    }

//    @PostMapping("")
//    public Map<String, Object> create(@RequestBody Map<String, Object> param) {
//        System.out.println(param);
//        return tbKaFeedService.create(param);
//    }
//
//    @GetMapping("/last")
//    public Map<String, Object> getLastKaFeed() {
//        return tbKaFeedService.getLastKaFeed();
//    }
//
//    @GetMapping("/all")
//    public List<Map<String, Object>> getAllKaFeed() {
//        return tbKaFeedService.getAll();
//    }


}
