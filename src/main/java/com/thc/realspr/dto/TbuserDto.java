package com.thc.realspr.dto;


import com.thc.realspr.domain.Tbuser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class TbuserDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccessReqDto{
        @Schema(description = "refreshToken", example="")
        @NotNull
        @NotEmpty
        private String refreshToken;
    }

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto{
//        @Schema(description = "username", example="")
//        @NotNull
//        @NotEmpty
//        @Size(max=400)
//        private String username;
//        @Schema(description = "password", example="")
//        @NotNull
//        @NotEmpty
//        @Size(max=100)
//        private String password;
//
//        public Tbuser toEntity(){
//            return Tbuser.of(username, password);
//        }

        @Schema(description = "사용자 이메일", example = "")
        @NotNull(message = "email cannot be null")
        @NotEmpty(message = "email cannot be empty")
        @Size(max = 36, message = "email must be less than 36 characters")
        private String id;


        @Schema(description = "사용자 아이디(UUID)", example = "")
        @NotNull(message = "UUID cannot be null")
        @NotEmpty(message = "UUID cannot be empty")
        @Size(max = 36, message = "UUID must be less than 36 characters")
        private String uuid;

        @Schema(description = "유저 이름", example = "")
        @NotNull(message = "Name cannot be null")
        @NotEmpty(message = "Name cannot be empty")
        @Size(max = 100, message = "Name must be less than 100 characters")
        private String name;

        @Schema(description = "마지막 로그인 시간", example = "")
        @NotNull(message = "Last login time cannot be null")
        private LocalDateTime lastLoginTime;

        @Schema(description = "생성 시간", example = "")
        @NotNull(message = "Created at time cannot be null")
        private LocalDateTime createdAt;

        // Tbuser 엔티티로 변환하는 메서드
        public Tbuser toEntity() {
            return Tbuser.of(id ,uuid, name, lastLoginTime, createdAt);
        }
    }
    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResDto{
        private String id;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
        @Schema(description = "gender", example="")
        private String gender;
        @Schema(description = "content", example="")
        @Size(max=4000)
        private String content;
        @Schema(description = "img", example="")
        private String img;
    }

    //여기는 빌더 붙이면 에러 나요!! 조심!!
    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{

        @Schema(description = "username", example="")
        private String username;
        /*@Schema(description = "password", example="")
        private String password;*/
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
        @Schema(description = "gender", example="")
        private String gender;
        @Schema(description = "content", example="")
        private String content;
        @Schema(description = "img", example="")
        private String img;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
    }

    @Getter
    @Setter
    public static class GoogleLoginRequest{
        private String credential;

    }

    @Getter
    @Setter
    public class GoogleLoginResponse {
        private String token;

        public GoogleLoginResponse(String token) {
            this.token = token;
        }
    }

}