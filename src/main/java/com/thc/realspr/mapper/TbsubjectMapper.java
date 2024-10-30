package com.thc.realspr.mapper;

import com.thc.realspr.dto.TbsubjectDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TbsubjectMapper {
    TbsubjectDto.DetailServDto getDetailById(TbsubjectDto.DetailReqDto reqDto);

    TbsubjectDto.LastMessageServDto getLastMessageByMessageId(String lastMessageId);

    List<TbsubjectDto.MessageHistoryServDto> getMessageHistoryById(Long subjectId);
}
