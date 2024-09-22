package com.thc.realspr.mapper;

import com.thc.realspr.dto.TbmessageDto;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TbmessageMapper {
    /**/
    List<TbmessageDto.Detail> scrollList(int afterSentAt);

    List<TbmessageDto.FileDetail> fileDetails(String messageId);

    @Async
    default CompletableFuture<List<TbmessageDto.FileDetail>> fileDetailsAsync(String messageId) {
        // Call the original fileDetails method asynchronously
        return CompletableFuture.completedFuture(fileDetails(messageId));
    }
}
