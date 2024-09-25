package com.thc.realspr.service;

import com.thc.realspr.domain.Tbuser;
import com.thc.realspr.dto.DefaultDto;
import com.thc.realspr.dto.GoogleLoginRequest;
import com.thc.realspr.dto.GoogleLoginResponse;
import com.thc.realspr.dto.TbuserDto;
import org.springframework.stereotype.Service;

@Service
public interface TbuserService {
    TbuserDto.CreateResDto create(TbuserDto.CreateReqDto param);

    TbuserDto.DetailResDto detail(DefaultDto.DetailReqDto param);

    TbuserDto.CreateResDto access(String param) throws Exception;

    //    TbuserDto.CreateResDto access(String param) throws Exception;
    GoogleLoginResponse loginWithGoogle(GoogleLoginRequest request);

    Tbuser loginWithGoogle(String credential);
}