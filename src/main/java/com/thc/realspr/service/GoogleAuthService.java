package com.thc.realspr.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleAuthService {

    private final HttpTransport transport = new NetHttpTransport();
    private final JsonFactory jsonFactory = new GsonFactory();
    private final GoogleIdTokenVerifier verifier;

    public GoogleAuthService() {
        this.verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList("your_client_id_here"))
                .build();
    }

    public String verifyGoogleToken(String credential) {
        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(credential);
            if (idToken != null) {
                return idToken.getPayload().getEmail(); // 이메일 반환
            } else {
                throw new RuntimeException("Invalid ID token");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify ID token", e);
        }
    }
}