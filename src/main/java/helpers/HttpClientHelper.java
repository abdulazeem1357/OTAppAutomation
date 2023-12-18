package helpers;

import fundamentals.AndroidBasics;
import io.appium.java_client.android.AndroidDriver;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

public class HttpClientHelper {
    AndroidBasics androidBasics;

    public HttpClientHelper(AndroidDriver driver) {
        androidBasics = new AndroidBasics(driver);
        this.httpClient = HttpClients.custom().disableCookieManagement().build();
    }
    private CloseableHttpClient httpClient;
    private String bearerToken;  // To store the token for reuse

    public void setBearerToken(String token) {
        this.bearerToken = token;
    }

    public String sendPOST(String url, String jsonData) {
        try {
            return sendPOST(url, jsonData, Collections.emptyMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String sendPost(String url, Map<String, String> headers) {
        androidBasics.turnOnInternetConnectivity();

        HttpPost post = new HttpPost(url);

        try {
            post.setHeader("Content-Type", "application/json");
            for (Map.Entry<String, String> header : headers.entrySet()) {
                post.setHeader(header.getKey(), header.getValue());
            }

            HttpResponse response = httpClient.execute(post);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String sendPOST(String url, String jsonData, Map<String, String> headers) {
        HttpPost post = new HttpPost(url);

        try {
            post.setEntity(new StringEntity(jsonData));
            post.setHeader("Content-Type", "application/json");

            if (bearerToken != null && !bearerToken.isEmpty()) {
                post.setHeader("Authorization", "Bearer " + bearerToken);
            }

            headers.forEach(post::setHeader);

            HttpResponse response = httpClient.execute(post);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String sendPOSTWithBearerToken(String url, String bearerToken, String jsonData) {
        androidBasics.turnOnInternetConnectivity();

        try {
            return sendPOSTWithBearerToken(url, bearerToken, jsonData, Collections.emptyMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String sendPOSTWithBearerToken(String url, String bearerToken, String jsonData, Map<String, String> headers) {
        HttpPost post = new HttpPost(url);

        try {
            post.setEntity(new StringEntity(jsonData));
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Bearer " + bearerToken);
            for (Map.Entry<String, String> header : headers.entrySet()) {
                post.setHeader(header.getKey(), header.getValue());
            }

            HttpResponse response = httpClient.execute(post);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String sendPOSTWithBasicAuth(String url, String username, String password, String jsonData) {
        try {
            return sendPOSTWithBasicAuth(url, username, password, jsonData, Collections.emptyMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String sendPOSTWithBasicAuth(String url, String username, String password, String jsonData, Map<String, String> headers) {
        HttpPost post = new HttpPost(url);

        try {
            post.setEntity(new StringEntity(jsonData));
            post.setHeader("Content-Type", "application/json");

            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.ISO_8859_1));
            String authHeader = "Basic " + new String(encodedAuth);
            post.setHeader("Authorization", authHeader);

            headers.forEach(post::setHeader);

            HttpResponse response = httpClient.execute(post);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
