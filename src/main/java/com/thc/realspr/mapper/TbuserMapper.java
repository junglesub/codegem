package com.thc.realspr.mapper;

import com.thc.realspr.dto.TbuserDto$DetailResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TbuserMapper {

    // 이메일로 사용자 찾기
    TbuserDto.DetailResDto findByEmail(String email);
}
