package app.handong.codegem.domain;

import app.handong.codegem.dto.AlgorithmDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class) // For Audit
public class TbAlgProblem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Setter @ColumnDefault("'N'") private String deleted; // 삭제여부

    @Unique @Column(nullable = true) private String ppsNo;
    @Column(nullable = true) private String title;
    @Column(nullable = true) private String source;
    @Column(nullable = true, length = 2000000) @Lob private String description;
    @Column(nullable = true) private String difficulty;
    @Unique @Column(nullable = false) private String link;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;


    @PrePersist
    public void onPrePersist() {
    }

    public TbAlgProblem(String ppsNo, String title, String source, String description, String difficulty, String link) {
        this.ppsNo = ppsNo;
        this.title = title;
        this.source = source;
        this.description = description;
        this.difficulty = difficulty;
        this.link = link;
    }

    public AlgorithmDto.ProblemResDto convertToDto() {
        return AlgorithmDto.ProblemResDto.builder()
                .id(this.getId())
                .deleted(this.getDeleted())
                .ppsNo(this.getPpsNo())
                .title(this.getTitle())
                .source(this.getSource())
                .description(this.getDescription())
                .difficulty(this.getDifficulty())
                .link(this.getLink())
                .createdDate(this.getCreatedDate())
                .modifiedDate(this.getModifiedDate())
                .build();
    }


}
