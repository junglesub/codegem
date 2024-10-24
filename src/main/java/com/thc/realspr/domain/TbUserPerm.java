package com.thc.realspr.domain;

import com.thc.realspr.id.UserPermId;
import com.thc.realspr.id.UserSubjectId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class) // For Audit
public class TbUserPerm {
    @EmbeddedId UserPermId id;
    @Setter @Column(nullable = false) private String deleted; // 삭제여부

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    protected TbUserPerm() {
    }

    private TbUserPerm(String userId, String permission) {
        this.id = new UserPermId(userId, permission);
    }

    public static TbUserPerm of(String userId, String permission) {
        return new TbUserPerm(userId, permission);
    }

    @PrePersist
    public void onPrePersist() {
        this.deleted = "N";
    }
}
