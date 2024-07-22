package com.thc.realspr.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class) // For Audit
public class TbKaFeed {
    @Id
    private String id;

    @Setter
    @Column(nullable = false)
    private String deleted; // 삭제여부

    @Setter
    @Column(nullable = false)
    private long chatId; // 원본 메세지 ID

    // @Setter
    @Column(nullable = false)
    private long roomId; // 원본 메세지 그룹 ID

    // @Setter
    @Column(nullable = false)
    private long userId; // 원본 메세지 작성자 ID

    @Setter
    @Column(nullable = false, length = 2000000)
    @Lob
    private String message; // 원본 메세지 본문

    // @Setter
    @Column(nullable = false)
    private int sentAt; // 원본 메세지 보낸 시간 ID

    // @Setter
    @Column(nullable = false)
    private long clientMessageId; // 이후 이미지와 동기화 하기 위함

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    protected TbKaFeed() {
    }

    private TbKaFeed(long chatId, long roomId, long userId, String message, int sentAt, long clientMessageId) {
        this.chatId = chatId;
        this.roomId = roomId;
        this.userId = userId;
        this.message = message;
        this.sentAt = sentAt;
        this.clientMessageId = clientMessageId;
    }

    public static TbKaFeed of(long chatId, long roomId, long userId, String message, int sentAt, long clientMessageId) {
        return new TbKaFeed(chatId, roomId, userId, message, sentAt, clientMessageId);
    }

    public static TbKaFeed of(String chatId, String roomId, String userId, String message, String sentAt, String clientMessageId) {
        return new TbKaFeed(Long.parseLong(chatId), Long.parseLong(roomId), Long.parseLong(userId), message, Integer.parseInt(sentAt), Long.parseLong(clientMessageId));
    }

    @PrePersist
    public void onPrePersist() {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.deleted = "N";
    }
}
