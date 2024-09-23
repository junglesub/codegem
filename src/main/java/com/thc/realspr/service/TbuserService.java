package com.thc.realspr.service;

import com.thc.realspr.dto.DefaultDto;
import com.thc.realspr.dto.GoogleLoginRequest;
import com.thc.realspr.dto.GoogleLoginResponse;
import com.thc.realspr.dto.TbuserDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TbuserService {
    public Map<String, Object> create(Map<String, Object> param);
    public Map<String, Object> update(Map<String, Object> param);
    public Map<String, Object> get(String id);

    TbuserDto.CreateResDto access(String param) throws Exception;

    //    TbuserDto.CreateResDto access(String param) throws Exception;
    GoogleLoginResponse loginWithGoogle(GoogleLoginRequest request);
}
