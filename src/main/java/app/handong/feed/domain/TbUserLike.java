package app.handong.feed.domain;

import app.handong.feed.id.UserSubjectId;
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
public class TbUserLike {
    @EmbeddedId UserSubjectId id;
    @Setter @Column(nullable = false) private String deleted; // 삭제여부

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    protected TbUserLike() {
    }

    private TbUserLike(String userId, int subjectId) {
        this.id = new UserSubjectId(userId, subjectId);

    }

    public static TbUserLike of(String userId, int subjectId) {
        return new TbUserLike(userId, subjectId);
    }

    @PrePersist
    public void onPrePersist() {
        this.deleted = "N";
    }
}
