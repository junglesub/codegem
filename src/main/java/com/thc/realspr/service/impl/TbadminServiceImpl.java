package com.thc.realspr.service.impl;

import com.thc.realspr.dto.TbadminDto;
import com.thc.realspr.exception.NoAuthorizationException;
import com.thc.realspr.id.UserPermId;
import com.thc.realspr.mapper.TbadminMapper;
import com.thc.realspr.repository.TbUserPermRepository;
import com.thc.realspr.service.TbadminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TbadminServiceImpl implements TbadminService {
    private final TbadminMapper tbadminMapper;
    private final TbUserPermRepository tbUserPermRepository;

    public TbadminServiceImpl(TbadminMapper tbadminMapper, TbUserPermRepository tbUserPermRepository) {
        this.tbadminMapper = tbadminMapper;
        this.tbUserPermRepository = tbUserPermRepository;
    }

    public List<TbadminDto.UserDetail> adminGetUser(String userId, Map<String, String> param) {

        if (tbUserPermRepository.findById(new UserPermId(userId, "adminGetUser")).isEmpty())
            throw new NoAuthorizationException("No Admin Permission");
        return tbadminMapper.allUsers();
    }

}
