package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fundamental.ConfigReader;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NFCPractice {
    static String bearerToken;
    static String SVCData;
    private static HttpClientHelper httpClientHelper;
    private static ConfigReader configReader;

    public static void createHttpService() {
        httpClientHelper = new HttpClientHelper();
    }

    public void destroyHttpService() {
        if (httpClientHelper != null) {
            httpClientHelper = null;
        }
    }

    public static void main(String[] args) throws Exception {
        configReader = new ConfigReader();
        getAccessTokenNew();
        makeTicketAPICall();
        tapInExpiredNFCTicket();
        tapOutExpiredNFCTicket();
    }

    public static void getAccessTokenNew() {
        createHttpService();
        String URL = configReader.getPropValue("baseURL") + "accesstoken";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-client-id", configReader.getPropValue("X-client-id"));
        headers.put("X-client-secret", configReader.getPropValue("X-client-secret"));
        headers.put("X-grant-type", configReader.getPropValue("X-grant-type"));
        String response = httpClientHelper.sendPost(URL, headers);
        bearerToken = parseBearerToken(response);
        System.out.println("Bearer Token: \n" + bearerToken);
    }

    public static void makeTicketAPICall() {
        TicketRequest expiredTicketRequest = new TicketRequestBuilder().expiredTicket().build();
        String expiredRequestBody = expiredTicketRequest.createRequestBody();

        createHttpService();
        String URL = configReader.getPropValue("baseURL") + "svc_tickets_encode";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        String response = httpClientHelper.sendPOSTWithBearerToken(URL, bearerToken, expiredRequestBody, headers);
        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    public static void tapInExpiredNFCTicket() {
        String URL = configReader.getPropValue("notificationURL");
        String USERNAME = configReader.getPropValue("Username");
        String PASSWORD = configReader.getPropValue("Password");
        String requestBody = tapInNFCTicket(SVCData);
        System.out.println("\nTap In Request Body: \n" + requestBody);

        String response = httpClientHelper.sendPOSTWithBasicAuth(URL, USERNAME, PASSWORD, requestBody);
        System.out.println("\nTAP IN Expired NFC Ticket Response: \n" + response);
    }

    public static void tapOutExpiredNFCTicket() {
        String URL = configReader.getPropValue("notificationURL");
        String USERNAME = configReader.getPropValue("Username");
        String PASSWORD = configReader.getPropValue("Password");
        String requestBody = tapOutNFCTicket(SVCData);
        System.out.println("Tap Out Request Body: \n" + requestBody);

        String response = httpClientHelper.sendPOSTWithBasicAuth(URL, USERNAME, PASSWORD, requestBody);
        System.out.println("\nTAP OUT Expired NFC Ticket Response: \n" + response);
    }

    private static String parseBearerToken(String responseJson) {
        JSONObject jsonObject = new JSONObject(responseJson);
        JSONObject tokenObject = jsonObject.getJSONObject("token");
        return tokenObject.getString("access_token");
    }

    private static String parseSVCData(String responseJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseJson);
            JsonNode svcDataNode = rootNode.get("svc_data");
            return svcDataNode.toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatSVCJSONForNotification(String jsonBefore) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, String>> beforeList = mapper.readValue(jsonBefore, new TypeReference<List<Map<String, String>>>() {
            });
            Map<String, Map<String, String>> afterMap = new LinkedHashMap<>(); // use LinkedHashMap to maintain the order

            for (Map<String, String> item : beforeList) {
                String sectorNo = item.remove("sector_no");
                afterMap.put("sector" + sectorNo, item);
            }

            return mapper.writeValueAsString(afterMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String tapInNFCTicket(String SVCData) {
        String requestBody = "{\"IMEI\":\"358240051111110\",\"data\":{\"service\":\"NFC\",\"command\":\"TAP_SVC_JSON\",\"body\":{\"uuid\":\"8069F932000000\",\"svcData\":"+SVCData+",\"connected\":true,\"deviceID\":4}}}";
        return requestBody;
    }

    public static String tapOutNFCTicket(String SVCData) {
        String requestBody = "{\"IMEI\":\"358240051111110\",\"data\":{\"service\":\"NFC\",\"command\":\"REMOVE_TAP_SVC_JSON\",\"body\":{\"uuid\":\"8069F932000000\",\"svcData\":"+SVCData+",\"connected\":true,\"deviceID\":4}}}";
        return requestBody;
    }
}
