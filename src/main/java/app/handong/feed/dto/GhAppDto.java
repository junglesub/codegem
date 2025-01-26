package app.handong.feed.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class GhAppDto {
    @Getter
    public static class Response {
        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("token_type")
        private String tokenType;

        //        @JsonProperty
        private String scope;
    }

    @Getter
    @AllArgsConstructor
    public static class LabelBody {
        String name;
        String color;
    }

    @Getter
    @AllArgsConstructor
    public static class IssueBody {
        String title;
        String body;
        List<String> labels;
    }


}
