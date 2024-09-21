package com.thc.realspr.mapper;

import com.thc.realspr.dto.TbmessageDto;

import java.util.List;

public interface TbmessageMapper {
    /**/
    List<TbmessageDto.Detail> scrollList();

    List<TbmessageDto.FileDetail> fileDetails(String messageId);
}
