package com.thc.realspr.service;

import com.thc.realspr.dto.UserInteractionDto;
import org.springframework.stereotype.Service;

@Service
public interface UserInteractionService {


    String seen(UserInteractionDto.SeenSubjectReqDto param, String userId);

}