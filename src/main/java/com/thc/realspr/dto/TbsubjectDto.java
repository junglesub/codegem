package com.thc.realspr.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

public class TbsubjectDto {
    @Schema(description = "주제 세부내용 요청 DTO")
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailReqDto {
        private Long subjectId;
        private String userId;
    }


    @Schema(description = "주제 세부내용 응답 DTO")
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailResDto {
        private Long subjectId;
        private boolean like;
        private LastMessageServDto lastMessage;
        private List<MessageHistoryServDto> messageHistory;
    }

    @Schema(description = "주제 세부내용 서비스 DTO")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailServDto {
        private Long subjectId;
        private boolean like;
        private String lastMessageId;
    }



    @Schema(description = "주제 마지막 메시지 서비스 DTO")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LastMessageServDto {
        private String id;
        private long sentAt;
        private String message;
    }

    @Schema(description = "주제 메시지 이력 서비스 DTO")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MessageHistoryServDto {
        private String id;
        private long sentAt;
        private String message;
    }

}
