package app.handong.codegem.util;

import app.handong.codegem.dto.GHOauthDto;
import app.handong.codegem.dto.GhAppDto;
import app.handong.codegem.exception.NoAuthenticatedException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GHAppHandler {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static String getAccessToken(String repo) throws Exception {
        String jwt = JWTFactory.generateJwt();
        String appId = getAppId(repo, jwt);
        System.out.println(jwt);
        System.out.println(appId);

        // Run Access Token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization", "Bearer " + jwt);
        HttpEntity<GHOauthDto.Request> request = new HttpEntity<>(null, headers);
        try {
            HashMap<String, Object> response = restTemplate.exchange(
                    "https://api.github.com/app/installations/" + appId + "/access_tokens",
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<HashMap<String, Object>>() {
                    }
            ).getBody();

            if (response == null) return null;
            return response.get("token").toString();

        } catch (HttpClientErrorException.Unauthorized e) {
            throw new NoAuthenticatedException("Github Returned 401");
        }
    }

    public static String getAppId(String repo, String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization", "Bearer " + jwt);
        HttpEntity<GHOauthDto.Request> request = new HttpEntity<>(null, headers);
        try {
            HashMap<String, Object> response = restTemplate.exchange(
                    "https://api.github.com/repos/" + repo + "/installation",
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<HashMap<String, Object>>() {
                    }
            ).getBody();

            if (response == null) return null;
            return response.get("id").toString();

        } catch (HttpClientErrorException.Unauthorized e) {
            throw new NoAuthenticatedException("Github Returned 401");
        }
    }


    // Todo: Need to handle github errors
    public static Void addLabel(String accessToken, String repo, String label) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<GhAppDto.LabelBody> request = new HttpEntity<>(new GhAppDto.LabelBody(label, HashToHexColor.hashToHexColor(label)), headers);
        try {
            return restTemplate.postForObject("https://api.github.com/repos/" + repo + "/labels", request, Void.class);
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new NoAuthenticatedException("Github Returned 401");
        } catch (HttpClientErrorException.UnprocessableEntity ignored) {
            return null;
        }
    }

    // Todo: Need to handle github errors
    public static int addIssue(String accessToken, String repo, String label, String title, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<GhAppDto.IssueBody> request = new HttpEntity<>(new GhAppDto.IssueBody(title, body, List.of(label)), headers);
        try {
            Object number = Objects.requireNonNull(restTemplate.postForObject("https://api.github.com/repos/" + repo + "/issues", request, HashMap.class)).get("number");
            return Integer.parseInt(number.toString());
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new NoAuthenticatedException("Github Returned 401");
        }
    }


}
