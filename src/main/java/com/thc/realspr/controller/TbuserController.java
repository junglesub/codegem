package com.thc.realspr.controller;

import com.thc.realspr.dto.GoogleLoginRequest;
import com.thc.realspr.dto.GoogleLoginResponse;
import com.thc.realspr.exception.NoAuthorizationException;
import com.thc.realspr.service.GoogleAuthService;
import com.thc.realspr.service.TbuserService;
import com.thc.realspr.util.TokenFactory;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/tbuser")
@RestController
public class TbuserController {

    private final TbuserService tbuserService;

    private final GoogleAuthService googleAuthService;

    public TbuserController(TbuserService tbuserService, GoogleAuthService googleAuthService) {
        this.tbuserService = tbuserService;
        this.googleAuthService = googleAuthService;
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


    //  Google 로그인 처리
    @PostMapping("/login/google")
    public ResponseEntity<GoogleLoginResponse> loginWithGoogle(@RequestBody GoogleLoginRequest request) {
        String email = googleAuthService.verifyGoogleToken(request.getCredential());

        System.out.println("동작중입니다. ");

        System.out.println(email);

        if (email == null || !email.endsWith("@handong.ac.kr")) {
            throw new NoAuthorizationException("Unauthorized user");
        }


        String refreshToken = TokenFactory.issueRefreshToken(email);  // 리프레시 토큰 발급

        System.out.println(refreshToken);
        return ResponseEntity.ok(new GoogleLoginResponse(refreshToken));

    }

//    @ResponseBody
//    @GetMapping(value = "/auth/login/google/callback")
//    public ResponseEntity<GoogleLoginResponse> callback (
//            @PathVariable(name = "socialLoginType") String socialLoginPath,
//            @RequestParam(name = "code") String code)throws IOException{
//        System.out.println(">> 소셜 로그인 API 서버로부터 받은 code :"+ code);
//        ResponseEntity socialLoginType= ResponseEntity.valueOf(socialLoginPath.toUpperCase());
//        GetSocialOAuthRes getSocialOAuthRes=oAuthService.oAuthLogin(socialLoginType,code);
//        return new BaseResponse<>(getSocialOAuthRes);
//    }





//    @PostMapping("/token/refresh")
//    public ResponseEntity<String> refreshAccessToken(@RequestBody String refreshToken) {
//        // 리프레시 토큰 검증 후 액세스 토큰 생성
//        String email = TokenFactory.extractEmail(refreshToken);
//        String accessToken = TokenFactory.generateToken(email);  // 액세스 토큰 발급
//        return ResponseEntity.ok(accessToken);
//    }





}
