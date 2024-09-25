package com.thc.realspr.service.impl;

import com.thc.realspr.domain.TbKaFeed;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.thc.realspr.util.TokenFactory;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TbuserServiceImpl implements TbuserService {

    @Autowired
    private final TbuserRepository tbuserRepository;
    private final GoogleAuthService googleAuthService;


    public TbuserServiceImpl(TbuserRepository tbuserRepository, GoogleAuthService googleAuthService) {
        this.tbuserRepository = tbuserRepository;
        this.googleAuthService = googleAuthService;
    }


    @Override
    public TbuserDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        // TODO: implement detail
        return null;
    }

    @Override
    public Map<String, Object> create(Map<String, Object> params) {
        System.out.println("create");
        Map<String, Object> result = new HashMap<String, Object>();

        String email = (String) params.get("email");

        Tbuser user = tbuserRepository.findByUsername(email);
        if (user == null) {
//            TODO: Fix below
//            user = new Tbuser();
//            user.setName((String) params.get("name"));
//            user.setCreated_at(LocalDateTime.now());
//            user.setLast_login_time(LocalDateTime.now());
//            user.setUuid((String) params.get("uuid"));
//            user = TbuserRepository.save(user);

            Tbuser tbuser = Tbuser.of(params.get("email") + "", params.get("uuid") + "", params.get("name") + "", null, null);
            result.put("email", user.getEmail());
        } else {
            result.put("id duplicated", user.getName());
        }
        return result;
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
        TbuserDto.CreateReqDto param = TbuserDto.CreateReqDto.builder().email(email).build();
        tbuser = tbuserRepository.save(param.toEntity());

        return tbuser;
    }


}