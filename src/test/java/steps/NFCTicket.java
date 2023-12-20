package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.TestContext;
import dataProvider.ConfigReader;
import fundamentals.*;
import helpers.HttpClientHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.SoftAssertManager;
import org.json.JSONObject;
import pageObjects.RoutePage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NFCTicket {
    TestContext testContext;
    RoutePage routePage;
    AndroidBasics androidBasics;
    InfrastructureEnv env = new InfrastructureEnv();

    public NFCTicket(TestContext context) {
        testContext = context;
        routePage = testContext.getPageObjectManager().getRoutePage();
        androidBasics = testContext.getPageObjectManager().getAndroidBasics();
    }

    private String USERNAME;
    private String PASSWORD;
    private String NOTIFICATION_URL;
    private String SALES_API_URL;
    private String URLENCODEDCONTENTTYPE;
    private String X_CLIENT_ID;
    private String X_CLIENT_SECRET;
    private String X_GRANT_TYPE;
    private String SVCData;
    private HttpClientHelper httpClientHelper;
    private ConfigReader configReader;
    private static String bearerToken;  // Declare the bearerToken here
    private String ticketRequestUID;

    @Before(order = 2, value = "@NFCTicket")
    public void setup() {
        System.out.println("Setting-up the Bearer Token!");
        getConfigReader();
        createHttpService();
        if (bearerToken == null) {
            bearerToken = getBearerToken();
            httpClientHelper.setBearerToken(bearerToken);
        }
    }

    @After(order = 2, value = "@NFCTicket")
    public void end() {
        destroyHttpService();
        System.out.println("Destroying the HTTP Service!");
    }

    private void getConfigReader() {
        if (configReader == null) {
            configReader = new ConfigReader();
            USERNAME = configReader.getPropValue("Username");
            PASSWORD = configReader.getPropValue("Password");
            NOTIFICATION_URL = configReader.getPropValue("notificationURL");
            SALES_API_URL = configReader.getPropValue("baseURL");
            URLENCODEDCONTENTTYPE = configReader.getPropValue("URLEncoded-Content-Type");
            X_CLIENT_ID = configReader.getPropValue("X-client-id");
            X_CLIENT_SECRET = configReader.getPropValue("X-client-secret");
            X_GRANT_TYPE = configReader.getPropValue("X-grant-type");
        } else {
            System.out.println("Config reader already exist");
        }
    }

    private void createHttpService() {
        if (httpClientHelper == null) {
            httpClientHelper = new HttpClientHelper(testContext.getAndroidDriverManager().getDriver());
        } else {
            System.out.println("Http Client Service Already Running");
        }
    }

    private void destroyHttpService() {
        if (httpClientHelper != null) {
            httpClientHelper = null;
        }
    }

    private String getBearerToken() {
        if (bearerToken != null) {
            return bearerToken;  // return the cached token
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("X-client-id", X_CLIENT_ID);
        headers.put("X-client-secret", X_CLIENT_SECRET);
        headers.put("X-grant-type", X_GRANT_TYPE);

        String response = httpClientHelper.sendPost(SALES_API_URL + "accesstoken", headers);
        System.out.println("Access Token API Response: " + response);
        String token = parseBearerToken(response);
        System.out.println("Bearer Token: \n" + token);
        return token;
    }

    private void parseResponse(String response) {
        MyLogger.getInstance().logInfo(response);
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

    private String tapInNFCTicket(String SVCData) {
        String device = env.getDevice();
        String imei = env.getIMEIForDevice(device);

        String requestBody = "{\"IMEI\":\"" + imei + "\",\"data\":{\"service\":\"NFC\",\"command\":\"TAP_SVC_JSON\",\"body\":{\"uuid\":\"" + getTicketRequestUID() + "\",\"svcData\":" + SVCData + ",\"connected\":true,\"deviceID\":4}}}";
        MyLogger.getInstance().logInfo("request:" + requestBody);
        return requestBody;
    }

    private String tapOutNFCTicket(String SVCData) {
        String device = env.getDevice();
        String imei = env.getIMEIForDevice(device);

        String requestBody = "{\"IMEI\":\"" + imei + "\",\"data\":{\"service\":\"NFC\",\"command\":\"REMOVE_TAP_SVC_JSON\",\"body\":{\"uuid\":\"" + getTicketRequestUID() + "\",\"svcData\":" + SVCData + ",\"connected\":true,\"deviceID\":4}}}";
        MyLogger.getInstance().logInfo("request:" + requestBody);
        return requestBody;
    }

    private String getTicketRequestUID() {
        return ticketRequestUID;
    }

    private void setTicketRequestUID(TicketRequest ticketRequest) {
        this.ticketRequestUID = ticketRequest.getUid();
        System.out.println("In setTicketRequestUID Method: " + ticketRequestUID);
    }

    @When("I tap NFC card on the left NFC reader")
    public void iTapNFCCardOnTheLeftNFCReader() {
        androidBasics.checkAppForegroundStatus();
        String requestBody = tapInNFCTicket(SVCData);
        String response = httpClientHelper.sendPOSTWithBasicAuth(NOTIFICATION_URL, USERNAME, PASSWORD, requestBody);
        System.out.println("Tap in is called");
        parseResponse(response);
    }

    @Then("The {string} message should be displayed")
    public void theMessageShouldBeDisplayed(String messageNFC) {
//        SoftAssertManager.getSoftAssert().assertEquals(routePage.getToastMessage(), messageNFC);
//        SoftAssertManager.getSoftAssert().assertEquals(routePage.getNFCBodyText(), messageNFC);
        String requestBody = tapOutNFCTicket(SVCData);
        String response = httpClientHelper.sendPOSTWithBasicAuth(NOTIFICATION_URL, USERNAME, PASSWORD, requestBody);
        parseResponse(response);
    }

    @And("I have an expired ticket on NFC card")
    public void iHaveAnExpiredTicketOnNFCCard() {
        TicketRequest expiredTicketRequest = new TicketRequestBuilder().expiredTicket().build();
        setTicketRequestUID(expiredTicketRequest);
        String expiredRequestBody = expiredTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), expiredRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("I have a valid ticket on NFC card")
    public void iHaveAValidTicketOnNFCCard() {
        TicketRequest validTicketRequest = new TicketRequestBuilder().validTicket().build();
        setTicketRequestUID(validTicketRequest);
        String validRequestBody = validTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), validRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("I have a valid ticket on NFC card with a transfer leg")
    public void iHaveAValidTicketOnNFCCardWithATransferLeg() {
        TicketRequest validTicketRequest = new TicketRequestBuilder().validTicket().build();
        setTicketRequestUID(validTicketRequest);
        String validRequestBody = validTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), validRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("I have multiple valid merchant tickets on the NFC card")
    public void iHaveMultipleValidMerchantTicketsOnTheNFCCard() {
        TicketRequest multipleMerchantValidTicketRequest = new TicketRequestBuilder().multipleMerchantValidTicket().build();
        setTicketRequestUID(multipleMerchantValidTicketRequest);
        String multipleMerchantValidTicketRequestBody = multipleMerchantValidTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), multipleMerchantValidTicketRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("I have a blocked NFC card")
    public void iHaveABlockedNFCCard() {
        TicketRequest blockedNFCCardTicketRequest = new TicketRequestBuilder().blockedNFCCardTicket().build();
        setTicketRequestUID(blockedNFCCardTicketRequest);
        String blockedNFCCardTicketRequestBody = blockedNFCCardTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), blockedNFCCardTicketRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("I have a disabled NFC card")
    public void iHaveADisabledNFCCard() {
        TicketRequest disabledNFCCardTicketRequest = new TicketRequestBuilder().disabledNFCCardTicket().build();
        setTicketRequestUID(disabledNFCCardTicketRequest);
        String disabledNFCCardTicketRequestBody = disabledNFCCardTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), disabledNFCCardTicketRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("I have a valid ticket on NFC card where all the rides for the given day are availed")
    public void iHaveAValidTicketOnNFCCardWhereAllTheRidesForTheGivenDayAreAvailed() {
        TicketRequest ridesAreNotAvailableTicketRequest = new TicketRequestBuilder().ridesAreNotAvailableTicket().build();
        setTicketRequestUID(ridesAreNotAvailableTicketRequest);
        String ridesAreNotAvailableTicketRequestBody = ridesAreNotAvailableTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), ridesAreNotAvailableTicketRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("I have a valid ticket on NFC card with different business unit")
    public void iHaveAValidTicketOnNFCCardWithDifferentBusinessUnit() {
        TicketRequest differentBUTicketRequest = new TicketRequestBuilder().differentBUTicket().build();
        setTicketRequestUID(differentBUTicketRequest);
        String differentBUTicketRequestBody = differentBUTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), differentBUTicketRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("I have a re-issued NFC card with an expired ticket")
    public void iHaveAReIssuedNFCCardWithAnExpiredTicket() {
        TicketRequest reissueNFCCardExpiredTicketRequest = new TicketRequestBuilder().reissueNFCCardExpiredTicket().build();
        setTicketRequestUID(reissueNFCCardExpiredTicketRequest);
        String reissueNFCCardExpiredTicketRequestBody = reissueNFCCardExpiredTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), reissueNFCCardExpiredTicketRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("I have a valid ticket on NFC card with an different NFC card UID")
    public void iHaveAValidTicketOnNFCCardWithAnDifferentNFCCardUID() {
        TicketRequest validTicketRequest = new TicketRequestBuilder().validTicket().build();
        setTicketRequestUID(validTicketRequest);
        String validRequestBody = validTicketRequest.createRequestBody();

        System.out.println("validRequestBody: " + validRequestBody);

        // New UID
        String newUid = "A00A0001000000";

        // Replace the UID in the requestBody
        String updatedRequestBody = validRequestBody.replaceAll("uid=\\w+", "uid=" + newUid);

        System.out.println("updatedRequestBody: " + updatedRequestBody);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), updatedRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("I have a valid ticket on NFC card with no main and transfer leg")
    public void iHaveAValidTicketOnNFCCardWithNoMainAndTransferLeg() {
        TicketRequest noMainTransferLegTicketRequest = new TicketRequestBuilder().noMainTransferLegTicket().build();
        setTicketRequestUID(noMainTransferLegTicketRequest);
        String noMainTransferLegTicketRequestBody = noMainTransferLegTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), noMainTransferLegTicketRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("I have a re-issued to NFC card")
    public void iHaveAReIssuedToNFCCard() {
        TicketRequest reissueNFCCardTicketRequest = new TicketRequestBuilder().reissueNFCCardTicket().build();
        setTicketRequestUID(reissueNFCCardTicketRequest);
        String reissueNFCCardTicketRequestBody = reissueNFCCardTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), reissueNFCCardTicketRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("I have a re-issued from NFC card")
    public void iHaveAReIssuedFromNFCCard() {
        TicketRequest reissueNFCCardTicketRequest = new TicketRequestBuilder().reissueFromNFCCardTicket().build();
        setTicketRequestUID(reissueNFCCardTicketRequest);
        String reissueNFCCardTicketRequestBody = reissueNFCCardTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), reissueNFCCardTicketRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @And("The card ticket counter should be {string}")
    public void theCardTicketCounterShouldBe(String cardTicketCounter) {
        SoftAssertManager.getSoftAssert().assertEquals(routePage.getCardTicketCounterText(), cardTicketCounter);
    }
}
