package com.thc.realspr.service;

import com.thc.realspr.dto.TbadminDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TbadminService {
    public List<TbadminDto.UserDetail> adminGetUser(String userId, Map<String, String> param);
}
