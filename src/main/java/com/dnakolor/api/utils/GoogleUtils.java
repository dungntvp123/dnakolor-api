package com.dnakolor.api.utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class GoogleUtils {
    @Value("${spring.security.oauth2.client.registration.google.clientId}")
    private String CLIENT_ID;
    public Map<String, String> getUserInfo(String idTokenString) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {

            GoogleIdToken.Payload payload = idToken.getPayload();
            Map<String, String> data = new HashMap<>();
            data.put("username", payload.getEmail().split("@")[0].trim());
            data.put("email", payload.getEmail());
            data.put("name", (String) payload.get("name"));
            data.put("image", (String) payload.get("picture"));
            return data;
        }
        return null;
    }
}
