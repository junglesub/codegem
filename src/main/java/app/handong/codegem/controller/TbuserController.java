package app.handong.codegem.controller;

import app.handong.codegem.dto.GHOauthDto;
import app.handong.codegem.dto.TbuserDto;
import app.handong.codegem.repository.TbuserRepository;
import app.handong.codegem.service.GoogleAuthService;
import app.handong.codegem.service.UserAuthService;
import app.handong.codegem.service.UserService;
import app.handong.codegem.util.TokenFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/tbuser")
@RestController
public class TbuserController {

    private final TbuserRepository tbuserRepository;
    private final UserService userService;
    @Value("${GH_LOGIN_REDIRECT_FRONTEND:${GH_LOGIN_REDIRECT_FRONTEND_DEFAULT:#{null}}}")
    String ghLoginRedirectFrontend;

    private final UserAuthService userAuthService;

    public TbuserController(UserAuthService userAuthService, TbuserRepository tbuserRepository, UserService userService) {
        this.userAuthService = userAuthService;
        this.tbuserRepository = tbuserRepository;
        this.userService = userService;
    }

    // Todo: Temporary debug
    @GetMapping("/me")
    public HashMap<String, Object> getMe(HttpServletRequest request) {
        HashMap<String, Object> response = new HashMap<>();
        GHOauthDto.User GHUser = (GHOauthDto.User) request.getAttribute(GHOauthDto.User.class.getName());
        response.put("gh", GHUser);
        response.put("db", tbuserRepository.findByGithubId(GHUser.getId()));
        return response;
    }

    @GetMapping("/ghRepo/{githubId}")
    public ResponseEntity<TbuserDto.GithubRepo> getGithubRepo(@PathVariable("githubId") String githubId) {
        return ResponseEntity.ok(userService.getRepo(Long.parseLong(githubId)));
    }

    @PostMapping("/ghRepo")
    public String setGhRepo(@RequestBody HashMap<String, String> body, HttpServletRequest request) {
        GHOauthDto.User GHUser = (GHOauthDto.User) request.getAttribute(GHOauthDto.User.class.getName());
        userAuthService.setRepo(GHUser.getId(), body.get("repo"));
        return "{}";
    }


    @GetMapping("/gh")
    public void getGithub(@RequestParam String code, HttpServletResponse response) throws IOException {
        String token = userAuthService.resolveGHAccess(code);
        response.sendRedirect(ghLoginRedirectFrontend + "/gh?code=" + token);
    }
}