package com.thc.realspr.service.impl;

import com.thc.realspr.domain.Tbuser;
import com.thc.realspr.dto.DefaultDto;
import com.thc.realspr.dto.GoogleLoginRequest;
import com.thc.realspr.dto.GoogleLoginResponse;
import com.thc.realspr.dto.TbuserDto;
import com.thc.realspr.exception.NoAuthorizationException;
import com.thc.realspr.repository.TbuserRepository;
import com.thc.realspr.service.GoogleAuthService;
import com.thc.realspr.service.TbuserService;
import com.thc.realspr.util.TokenFactory;
import org.springframework.stereotype.Service;
//import com.thc.realspr.util.TokenFactory;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TbuserServiceImpl implements TbuserService {

    private final TbuserRepository tbuserRepository;
    private final GoogleAuthService googleAuthService;



    public TbuserServiceImpl(TbuserRepository tbuserRepository, GoogleAuthService googleAuthService) {
        this.tbuserRepository = tbuserRepository;
        this.googleAuthService = googleAuthService;
    }



    @Override
    public Tbuser create(TbuserDto.CreateReqDto param) {
        //사용자 등록 완료!
        Tbuser tbuser = tbuserRepository.save(param.toEntity());
        //String uuid, String name, LocalDateTime last_login_time, LocalDateTime created_at
        return tbuser.of(param.getId(), param.getUuid(), param.getName(), param.getLastLoginTime(), param.getCreatedAt());
    }

    @Override
    public TbuserDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return null;
    }

    @Override
    public TbuserDto.CreateResDto access(String param) throws Exception {
        TokenFactory tokenFactory = new TokenFactory();
        String tbuserId = tokenFactory.issueAccessToken(param);

        return null;
    }

    @Override
    public GoogleLoginResponse loginWithGoogle(GoogleLoginRequest request) {
        return null;
    }

    @Override
    public Tbuser loginWithGoogle(String credential) {
        String email = googleAuthService.verifyGoogleToken(credential);
        String name = googleAuthService.verifyGoogleToken(credential);
        LocalDateTime now = LocalDateTime.now();
        String uuid = UUID.randomUUID().toString().replace("-", "");

        // 유저 객체 생성
        Tbuser tbuser = Tbuser.of(uuid, name, name, now, now);

        // 유저가 @handong.ac.kr 이메일이 아니면 예외 처리
        if (email == null || !email.endsWith("@handong.ac.kr")) {
            throw new NoAuthorizationException("Unauthorized user");
        }

        return tbuser;
    }


}
