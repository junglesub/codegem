package com.thc.realspr.domain;

import com.thc.realspr.dto.TbuserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
public class Tbuser {
    /*
    * - email
- uuid
- 이름
- last login time
- modified_at
- created_at*/
    @Id private String id; // 이메일
    @Setter @Column(nullable = false) private String uuid; // 사용자아이디
    @Setter @Column(nullable = false) private String name;
    @Setter @Column(nullable = false) private LocalDateTime last_login_time;
    @Setter @Column(nullable = false) private LocalDateTime modified_at;
    @Setter @Column(nullable = false) private LocalDateTime created_at; // 프로필 사진

  


    protected Tbuser(){}
    private Tbuser(String id, String uuid, String name, LocalDateTime last_login_time, LocalDateTime modified_at, LocalDateTime created_at) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.last_login_time = last_login_time;
        this.modified_at = modified_at;
        this.created_at = created_at;
    }

    public Tbuser(String uuid, String name, LocalDateTime last_login_time, LocalDateTime created_at) {
        this.uuid = uuid;
        this.name = name;
        this.last_login_time = last_login_time;
        this.created_at = created_at;
    }

    public Tbuser(String id) {
        this.id = id;
    }



    // TODO: role 추가 해야 함 .
    public static Tbuser of(String id, String uuid, String name, LocalDateTime last_login_time, LocalDateTime created_at){
        return new Tbuser(id, uuid, name, last_login_time, null, created_at);
    }

    @PrePersist
    public void onPrePersist() {
        this.uuid = UUID.randomUUID().toString().replace("-", "");
    }



//    public String getRoleKey(){
//        return this.role.getKey();
//    }



}
