package app.handong.feed.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTFactory {
    @Value("${GH_KEY_PATH:${GH_KEY_PATH_DEFAULT:#{null}}}")
    String ghPrivateKeyPath;

    @Value("${GH_CLIENT_ID:${GH_CLIENT_ID_DEFAULT:#{null}}}")
    String ghClientId;

    private static String staticGHPrivateKeyPath;
    private static String staticGHClientId;


    @PostConstruct
    public void init() {
        staticGHPrivateKeyPath = ghPrivateKeyPath;
        staticGHClientId = ghClientId;
    }

    public static String generateJwt() throws Exception {
        return generateJwt(staticGHClientId);
    }

    public static String generateJwt(String iss) throws Exception {
        Instant now = Instant.now();
        RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey();
        // Generate JWT
        return JWT.create().withIssuer(iss).withIssuedAt(Date.from(now.minusSeconds(60))).withExpiresAt(Date.from(now.plusSeconds(600))).sign(Algorithm.RSA256(null, privateKey));
    }

    private static PrivateKey getPrivateKey() throws Exception {
        try (InputStream is = loadPrivateKeyFile();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            StringBuilder keyBuilder = new StringBuilder();
            String line;

            // Read and process the PEM file
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("-----BEGIN") || line.startsWith("-----END")) {
                    continue; // Skip the header and footer
                }
                keyBuilder.append(line.trim());
            }
            // Decode Base64 content
            byte[] keyBytes = Base64.getDecoder().decode(keyBuilder.toString());

            // Generate PrivateKey from PKCS8 spec
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        }
    }


    private static InputStream loadPrivateKeyFile() throws Exception {
        // Check if running in development or production
        boolean isProduction = System.getenv("ENV") != null && System.getenv("ENV").equalsIgnoreCase("PRODUCTION");

        if (isProduction) {
            // Use absolute path in production
            return new FileInputStream(staticGHPrivateKeyPath);
        } else {
            // Use ClassPathResource in development
            return new ClassPathResource(staticGHPrivateKeyPath).getInputStream();
        }
    }
}
