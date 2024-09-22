package com.thc.realspr.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    @Value("${FB_CONFIG_PATH:${FB_CONFIG_PATH_DEFAULT:#{null}}}")
    private String firebaseConfigPath;

    @Value("${FB_BUCKET:${FB_BUCKET_DEFAULT:#{null}}}")
    private String storageBucketUrl;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream()))
                    .setStorageBucket(storageBucketUrl)
                    .build();

            return FirebaseApp.initializeApp(options);
        } else {
            return FirebaseApp.getInstance();
        }
    }
}
