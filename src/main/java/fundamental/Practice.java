package fundamental;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class Practice {
    static String bearerToken;

    public static void main(String[] args) {
        getAccessToken();
    }

    public static void getAccessToken() {
        // Create client
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .disableCookieManagement()
                .build()) {

            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties().setFileName("config.properties"));
            Configuration config = builder.getConfiguration();

            // Create POST request
            HttpPost httpPost = new HttpPost("http://putco.opentransit.co/backend/web/salesapis/v2_4/accesstoken");

            // Set headers
            httpPost.setHeader("X-client-id", config.getString("X-client-id"));
            httpPost.setHeader("X-client-secret", config.getString("X-client-secret"));
            httpPost.setHeader("X-grant-type", config.getString("X-grant-type"));
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, config.getString("Content-Type"));

            // Execute request and get response
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // Get and store the bearer token
                String responseJson = EntityUtils.toString(response.getEntity());
                System.out.println("Resoponse JSON: \n" + responseJson);

                bearerToken = parseBearerToken(responseJson);  // Implement this method to extract the token from the response
                System.out.println("Bearer Token: \n" + bearerToken);


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (IOException | ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static String parseBearerToken(String responseJson) {
        JSONObject jsonObject = new JSONObject(responseJson);
        JSONObject tokenObject = jsonObject.getJSONObject("token");
        return tokenObject.getString("access_token");
    }
}
