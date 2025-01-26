package app.handong.codegem.domain;

import app.handong.codegem.dto.TbuserDto;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tbuser {
    @Id @Setter @Column(nullable = false) private String id; // 사용자아이디
    @Unique @Setter @Column(nullable = false) private Long githubId;
    @Setter @Column(nullable = true) private String email; // 이메일
    @Setter @Column(nullable = false) private String githubName;
    @Setter @Column(nullable = true) private String githubRepo;
    @Setter @Column(nullable = false) private LocalDateTime last_login_time; // 최근 로그인 시간
    @Setter @Column(nullable = false) private LocalDateTime modified_at; // 수정날짜
    @Setter @Column(nullable = false) private LocalDateTime created_at; // 생성 날짜


    private Tbuser(String email, String uuid, String githubName, LocalDateTime last_login_time, LocalDateTime modified_at, LocalDateTime created_at) {
        this.email = email;
        this.id = uuid;
        this.githubName = githubName;
        this.last_login_time = last_login_time;
        this.modified_at = modified_at;
        this.created_at = created_at;
    }

    public Tbuser(String email, String uuid, String githubName, LocalDateTime last_login_time, LocalDateTime created_at) {
        this.email = email;
        this.id = uuid;
        this.githubName = githubName;
        this.last_login_time = last_login_time;
        this.created_at = created_at;
    }

    public Tbuser(String email) {
        this.email = email;
    }


    // TODO: role 추가 해야 함 .
    public static Tbuser of(String email, String uuid, String githubName, LocalDateTime last_login_time, LocalDateTime modified_at, LocalDateTime created_at) {
        return new Tbuser(email, uuid, githubName, last_login_time, modified_at, created_at);
    }


    @PrePersist
    public void onPrePersist() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = UUID.randomUUID().toString().replace("-", "");
        }
    }

    public TbuserDto.CreateResDto toCreateResDto() {
        return TbuserDto.CreateResDto.builder().email(this.getEmail()).build();
    }


//    public String getRoleKey(){
//        return this.role.getKey();
//    }


}