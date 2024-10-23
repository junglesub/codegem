package com.thc.realspr.mapper;

import com.thc.realspr.dto.TbadminDto;

import java.util.List;

public interface TbadminMapper {
    List<TbadminDto.UserDetail> allUsers();
}
