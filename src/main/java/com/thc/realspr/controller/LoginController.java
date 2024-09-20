package com.thc.realspr.controller;

import com.thc.realspr.domain.Tbuser;
import com.thc.realspr.dto.GoogleLoginRequest;
import com.thc.realspr.dto.GoogleLoginResponse;
import com.thc.realspr.dto.TbuserDto;
import com.thc.realspr.exception.NoAuthorizationException;
import com.thc.realspr.mapper.TbuserMapper;
import com.thc.realspr.service.GoogleAuthService;
import com.thc.realspr.service.TbuserService;
import com.thc.realspr.util.JwtTokenUtil;
import org.antlr.v4.runtime.TokenFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/login")
public class LoginController {


    // 클라이언트가 구글 ID token을 보내면 서버에서 해당 토큰을 검증한 뒤 리프레시 토큰을 발급.
    private final GoogleAuthService googleAuthService;
    private final JwtTokenUtil jwtTokenUtil;

    public LoginController(GoogleAuthService googleAuthService, JwtTokenUtil jwtTokenUtil) {
        this.googleAuthService = googleAuthService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login/google")
    public ResponseEntity<GoogleLoginResponse> googleLogin(@RequestBody GoogleLoginRequest request) {
        String email = googleAuthService.verifyGoogleToken(request.getCredential());

        if (email == null || !email.endsWith("@handong.ac.kr")) {
            throw new NoAuthorizationException("Unauthorized user");
        }

        String refreshToken = jwtTokenUtil.generateToken(email);  // 리프레시 토큰 발급
        return ResponseEntity.ok(new GoogleLoginResponse(refreshToken));
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<String> refreshAccessToken(@RequestBody String refreshToken) {
        // 리프레시 토큰 검증 후 액세스 토큰 생성
        String email = jwtTokenUtil.extractEmail(refreshToken);
        String accessToken = jwtTokenUtil.generateToken(email);  // 액세스 토큰 발급
        return ResponseEntity.ok(accessToken);
    }
}
