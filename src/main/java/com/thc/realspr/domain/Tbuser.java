package com.thc.realspr.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

@Getter
@Entity
public class Tbuser {
    @Id private String id;
    @Setter @Column(nullable = false) private String deleted; // 삭제여부

    @Setter @Column(nullable = false) private String username; // 사용자아이디
    @Setter @Column(nullable = false) private String password; // 비번
    @Setter @Column(nullable = false) private String name;
    @Setter @Column(nullable = false) private String nick;
    @Setter @Column(nullable = false) private String phone;
    @Setter @Column(nullable = false) private String mpic; // 프로필 사진
    @Setter @Column(nullable = false, length=2000000) @Lob private String content; // 본문

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;


    protected Tbuser(){}
    private Tbuser(String username, String password, String name, String nick, String phone, String mpic, String content, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.nick = nick;
        this.phone = phone;
        this.mpic = mpic;
        this.content = content;
        this.role = role;
    }
    // TODO: role 추가 해야 함 .
    public static Tbuser of(String username, String password){
        return new Tbuser(username, password, "", "", "", "", "", null);
    }

    @PrePersist
    public void onPrePersist() {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.deleted = "N";
    }

    public String getRoleKey(){
        return this.role.getKey();
    }



}
