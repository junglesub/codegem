package com.thc.realspr.service.impl;

import com.thc.realspr.domain.Tbuser;
import com.thc.realspr.dto.DefaultDto;
import com.thc.realspr.dto.GoogleLoginRequest;
import com.thc.realspr.dto.GoogleLoginResponse;
import com.thc.realspr.dto.TbuserDto;
import com.thc.realspr.mapper.TbuserMapper;
import com.thc.realspr.repository.TbuserRepository;
import com.thc.realspr.service.TbuserService;
import com.thc.realspr.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TbuserServiceImpl implements TbuserService {

    private final TbuserRepository tbuserRepository;

    private final TbuserMapper tbuserMapper;
    private final TbuserRepository tbuserRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public TbuserServiceImpl(TbuserMapper tbuserMapper, TbuserRepository tbuserRepository, JwtTokenUtil jwtTokenUtil) {
        this.tbuserMapper = tbuserMapper;
        this.tbuserRepository = tbuserRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public TbuserServiceImpl(
            TbuserRepository tbuserRepository
    ) {
        this.tbuserRepository = tbuserRepository;
    }

    public Map<String, Object> create(Map<String, Object> param){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(param);
        Tbuser tbuser = Tbuser.of(param.get("username") + "", param.get("password") + "");
        tbuserRepository.save(tbuser);
        returnMap.put("id", tbuser.getId());
        return returnMap;
    }
    public Map<String, Object> update(Map<String, Object> param){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(param);
        Tbuser tbuser = tbuserRepository.findById(param.get("id") + "").orElseThrow(() -> new RuntimeException(""));
        if(param.get("name") != null) {
            tbuser.setName(param.get("name") + "");
        }
        if(param.get("nick") != null) {
            tbuser.setNick(param.get("nick") + "");
        }
        if(param.get("phone") != null) {
            tbuser.setPhone(param.get("phone") + "");
        }

        tbuserRepository.save(tbuser);
        returnMap.put("id", tbuser.getId());
        return returnMap;
    }
    public Map<String, Object> get(String id){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(id);
        Tbuser tbuser = tbuserRepository.findById(id).orElseThrow(() -> new RuntimeException(""));

        returnMap.put("id", tbuser.getId());
        returnMap.put("username", tbuser.getUsername());
        returnMap.put("name", tbuser.getName());
        returnMap.put("nick", tbuser.getNick());
        returnMap.put("phone", tbuser.getPhone());

        return returnMap;
    }

    @Override
    public TbuserDto.CreateResDto access(String param) throws Exception {
        return null;
    }

    @Override
    public GoogleLoginResponse loginWithGoogle(GoogleLoginRequest request) {
            // Google에서 인증된 사용자 이메일 가져오기
            String email = tbuserMapper.findByEmail(request.getCredential());

            // 이메일이 null이거나 @handong.ac.kr 도메인이 아니면 예외 발생
            if (email == null || !email.endsWith("@handong.ac.kr")) {
                throw new NoAuthorizationException("Unauthorized user");
            }

            // 사용자 엔티티 생성 및 저장
            Tbuser user = new Tbuser();
            user.setUsername(email);
            tbuserRepository.save(user);

            // JWT 토큰 생성
            String token = jwtTokenUtil.generateToken(email);

            // 응답 DTO 생성 및 반환
            return new GoogleLoginResponse(true, "Login successful", token);
    }


}
