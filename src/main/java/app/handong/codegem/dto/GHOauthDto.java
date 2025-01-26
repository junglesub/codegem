package app.handong.codegem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class GHOauthDto {

    @AllArgsConstructor
    @Getter
    public static class Request {
        @JsonProperty("client_id")
        private String clientId;

        @JsonProperty("client_secret")
        private String clientSecret;

        private String code;
    }

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
    public static class User {
        private String login;
        private Long id;

        @JsonProperty("node_id")
        private String nodeId;

        @JsonProperty("avatar_url")
        private String avatarUrl;

        @JsonProperty("gravatar_id")
        private String gravatarId;

        private String url;

        @JsonProperty("html_url")
        private String htmlUrl;

        @JsonProperty("followers_url")
        private String followersUrl;

        @JsonProperty("following_url")
        private String followingUrl;

        @JsonProperty("gists_url")
        private String gistsUrl;

        @JsonProperty("starred_url")
        private String starredUrl;

        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;

        @JsonProperty("organizations_url")
        private String organizationsUrl;

        @JsonProperty("repos_url")
        private String reposUrl;

        @JsonProperty("events_url")
        private String eventsUrl;

        @JsonProperty("received_events_url")
        private String receivedEventsUrl;

        private String type;

        @JsonProperty("user_view_type")
        private String userViewType;

        @JsonProperty("site_admin")
        private boolean siteAdmin;

        private String name;
        private String company;
        private String blog;
        private String location;
        private String email;
        private String hireable;
        private String bio;

        @JsonProperty("twitter_username")
        private String twitterUsername;

        @JsonProperty("notification_email")
        private String notificationEmail;

        @JsonProperty("public_repos")
        private int publicRepos;

        @JsonProperty("public_gists")
        private int publicGists;

        private int followers;
        private int following;

        @JsonProperty("created_at")
        private String createdAt;

        @JsonProperty("updated_at")
        private String updatedAt;
    }
}
