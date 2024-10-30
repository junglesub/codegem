package com.thc.realspr.service;


import com.thc.realspr.dto.TbsubjectDto;
import org.springframework.stereotype.Service;


@Service
public interface TbsubjectService {

    TbsubjectDto.DetailResDto getDetail(TbsubjectDto.DetailReqDto param);

}