package com.thc.realspr.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class TbadminDto {
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDetail {
        private String name;
        private LocalDateTime createdAt;
        private LocalDateTime lastLoginTime;
        private LocalDateTime lastReadTime;

    }
}
