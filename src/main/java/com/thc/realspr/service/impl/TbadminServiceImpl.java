package com.thc.realspr.service.impl;

import com.thc.realspr.dto.TbadminDto;
import com.thc.realspr.mapper.TbadminMapper;
import com.thc.realspr.service.TbadminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TbadminServiceImpl implements TbadminService {
    private final TbadminMapper tbadminMapper;

    public TbadminServiceImpl(TbadminMapper tbadminMapper) {
        this.tbadminMapper = tbadminMapper;
    }

    public List<TbadminDto.UserDetail> adminGetUser(Map<String, String> param) {
        return tbadminMapper.allUsers();
    }

}
