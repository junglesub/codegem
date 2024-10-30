package app.handong.feed.mapper;

import app.handong.feed.dto.TbmessageDto;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TbmessageMapper {
    /**/
    List<TbmessageDto.Detail> scrollList(String fetchType, int afterSentAt, String userId);

    int countAll(int afterSentAt, String userId);

    List<TbmessageDto.FileDetail> fileDetails(String messageId);

    @Async
    default CompletableFuture<List<TbmessageDto.FileDetail>> fileDetailsAsync(String messageId) {
        // Call the original fileDetails method asynchronously
        return CompletableFuture.completedFuture(fileDetails(messageId));
    }
}
