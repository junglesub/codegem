package app.handong.feed.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class AlgorithmDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SolutionReqDto {

        private String id;

        @Schema(description = "", example = "")
        @NotNull
        @NotEmpty
        private String githubRepoId;

        @Schema(description = "", example = "")
        @NotNull
        @NotEmpty
        private String githubFilePath;

        @Schema(description = "", example = "")
        @NotNull
        @NotEmpty
        private String githubIssueId;

        @Schema(description = "", example = "")
        @NotNull
        @NotEmpty
        private String userId;

        private String code;

        @Schema(description = "", example = "")
        private String message;

        @Schema(description = "", example = "")
        private String platform;

        private String problemUrl;

        private String title;
        private String ext;
    }

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SolutionResDto {

        private String id;

        @Schema(description = "", example = "")
        @NotNull
        @NotEmpty
        private String githubRepoId;

        @Schema(description = "", example = "")
        @NotNull
        @NotEmpty
        private String githubFilePath;

        @Schema(description = "", example = "")
        @NotNull
        @NotEmpty
        private int githubIssueId;

        @Schema(description = "", example = "")
        @NotNull
        @NotEmpty
        private String userId;

        @Schema(description = "", example = "")
        @NotNull
        @NotEmpty
        private String message;

        @Schema(description = "", example = "")
        @NotNull
        @NotEmpty
        private LocalDateTime createdDate;

        @Schema(description = "", example = "")
        @NotNull
        @NotEmpty
        private LocalDateTime modifiedDate;
    }


    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProblemResDto {
        private Long id;

        @Schema(description = "삭제 여부", example = "N")
        @NotNull
        @NotEmpty
        private String deleted;

        @Schema(description = "문제 번호(PPS)", example = "A123")
        private String ppsNo;

        @Schema(description = "문제 제목", example = "Sample Problem Title")
        private String title;

        @Schema(description = "문제 출처", example = "LeetCode")
        private String source;

        @Schema(description = "문제 설명", example = "This is the description of the problem.")
        private String description;

        @Schema(description = "문제 난이도", example = "Easy")
        private String difficulty;

        @Schema(description = "문제 링크", example = "https://example.com/problem/123")
        @NotNull
        @NotEmpty
        private String link;

        @Schema(description = "생성일시", example = "2023-10-27T10:00:00")
        @NotNull
        @NotEmpty
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime createdDate;

        @Schema(description = "수정일시", example = "2023-10-27T12:30:00")
        @NotNull
        @NotEmpty
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime modifiedDate;
    }

    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AlgSolutionDashboardDto {
        private String userId;
        private String githubName;
        private LocalDateTime createdDate;

        private String lastPostId;
        private String lastProblemUrl;
        private String lastGithubRepoId;
        private String lastGithubIssueId;
        private LocalDateTime lastCreatedDate;
        private int number; // Count of posts per user per date

        private String lastPPSNo;
        private String lastPPSSource;
        private String lastPPSTitle;
    }

    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AlgSolvedPaths {
        List<String> paths;

    }
}
