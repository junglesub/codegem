package app.handong.codegem.util;

import app.handong.codegem.dto.GHOauthDto;
import app.handong.codegem.exception.NoAuthenticatedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

public class GHOauthHandler {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static GHOauthDto.Response getGithubOauth(String clientId, String clientSecret, String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<GHOauthDto.Request> request = new HttpEntity<>(new GHOauthDto.Request(clientId, clientSecret, code), headers);
        try {
            return restTemplate.postForObject("https://github.com/login/oauth/access_token", request, GHOauthDto.Response.class);
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new NoAuthenticatedException("Github Returned 401");
        }
    }


    public static GHOauthDto.User getGhUser(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<GHOauthDto.User> request = new HttpEntity<>(headers);
        try {
            return restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, request, GHOauthDto.User.class).getBody();
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new NoAuthenticatedException("Github Returned 401");
        }
    }
}
