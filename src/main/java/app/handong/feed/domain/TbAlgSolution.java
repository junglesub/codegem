package app.handong.feed.domain;

import app.handong.feed.dto.AlgorithmDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class) // For Audit
public class TbAlgSolution {
    @Id
    private String id;

    @Setter
    @Column(nullable = false)
    private String deleted; // 삭제여부

    @Setter
    @Column(nullable = false)
    private String githubRepoId;

    @Column(nullable = false)
    private String githubFilePath;

    @Column(nullable = false)
    private int githubIssueId;

    @Column(nullable = false)
    private String problemUrl;

    // @Setter
    @Column(nullable = false)
    private String userId; // 작성자 ID


    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;


    @PrePersist
    public void onPrePersist() {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.deleted = "N";
    }

    public TbAlgSolution(String githubRepoId, String githubFilePath, int githubIssueId, String problemUrl, String userId) {
        this.githubRepoId = githubRepoId;
        this.githubFilePath = githubFilePath;
        this.githubIssueId = githubIssueId;
        this.problemUrl = problemUrl;
        this.userId = userId;
    }

    public AlgorithmDto.SolutionResDto convertToDto() {
        return AlgorithmDto.SolutionResDto.builder()
                .id(this.getId())
                .githubRepoId(this.getGithubRepoId())
                .githubFilePath(this.getGithubFilePath())
                .githubIssueId(this.getGithubIssueId())
                .userId(this.getUserId())
                .createdDate(this.getCreatedDate())
                .modifiedDate(this.getModifiedDate())
                .build();
    }


}
