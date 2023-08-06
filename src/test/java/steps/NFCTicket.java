package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fundamental.ConfigReader;
import fundamental.MyLogger;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import tests.TestBase;
import utils.HttpClientHelper;
import utils.TicketRequest;
import utils.TicketRequestBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NFCTicket extends TestBase {

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

    @Before
    public void setup() {
        getConfigReader();
        createHttpService();
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
            httpClientHelper = new HttpClientHelper();
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
        Map<String, String> headers = new HashMap<>();
        headers.put("X-client-id", X_CLIENT_ID);
        headers.put("X-client-secret", X_CLIENT_SECRET);
        headers.put("X-grant-type", X_GRANT_TYPE);
        String response = httpClientHelper.sendPost(SALES_API_URL + "accesstoken", headers);
        System.out.println("Access Token API Response: " + response);
        String bearerToken = parseBearerToken(response);
        System.out.println("Bearer Token: \n" + bearerToken);
        return bearerToken;
    }

    @And("I have an expired ticket on NFC card")
    public void iHaveAnExpiredTicketOnNFCCard() {
        TicketRequest expiredTicketRequest = TicketRequestBuilder.expiredTicket().build();
        String expiredRequestBody = expiredTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), expiredRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @When("I tap NFC card with expired ticket on the left NFC reader")
    public void iTapNFCCardWithExpiredTicketOnTheLeftNFCReader() {
        String requestBody = tapInNFCTicket(SVCData);
        String response = httpClientHelper.sendPOSTWithBasicAuth(NOTIFICATION_URL, USERNAME, PASSWORD, requestBody);
        parseResponse(response);
    }

    @Then("The ticket is expired message should be displayed")
    public void theTicketIsExpiredMessageShouldBeDisplayed() {
        String requestBody = tapOutNFCTicket(SVCData);
        String response = httpClientHelper.sendPOSTWithBasicAuth(NOTIFICATION_URL, USERNAME, PASSWORD, requestBody);
        parseResponse(response);
        destroyHttpService();
    }
    @And("I have a valid ticket on NFC card")
    public void iHaveAValidTicketOnNFCCard() {
        TicketRequest validTicketRequest = TicketRequestBuilder.validTicket().build();
        String validRequestBody = validTicketRequest.createRequestBody();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", URLENCODEDCONTENTTYPE);

        String response = httpClientHelper.sendPOSTWithBearerToken(SALES_API_URL + "svc_tickets_encode", getBearerToken(), validRequestBody, headers);
        System.out.println("NFC ticket response: " + response);

        SVCData = parseSVCData(response);
        SVCData = formatSVCJSONForNotification(SVCData);
        System.out.println("\nSVC DATA: \n" + SVCData);
    }

    @When("I tap NFC card with active ticket on the left NFC reader")
    public void iTapNFCCardWithActiveTicketOnTheLeftNFCReader() {
        String requestBody = tapInNFCTicket(SVCData);
        String response = httpClientHelper.sendPOSTWithBasicAuth(NOTIFICATION_URL, USERNAME, PASSWORD, requestBody);
        parseResponse(response);
    }

    @Then("The ticket is valid message should be displayed")
    public void theTicketIsValidMessageShouldBeDisplayed() {
        String requestBody = tapOutNFCTicket(SVCData);
        String response = httpClientHelper.sendPOSTWithBasicAuth(NOTIFICATION_URL, USERNAME, PASSWORD, requestBody);
        parseResponse(response);
        destroyHttpService();
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
        String requestBody = "{\"IMEI\":\"351625111274519\",\"data\":{\"service\":\"NFC\",\"command\":\"TAP_SVC_JSON\",\"body\":{\"uuid\":\"8069F932000000\",\"svcData\":" + SVCData + ",\"connected\":true,\"deviceID\":4}}}";
        MyLogger.getInstance().logInfo("request:" + requestBody);
        return requestBody;
    }

    private String tapOutNFCTicket(String SVCData) {
        String requestBody = "{\"IMEI\":\"351625111274519\",\"data\":{\"service\":\"NFC\",\"command\":\"REMOVE_TAP_SVC_JSON\",\"body\":{\"uuid\":\"8069F932000000\",\"svcData\":" + SVCData + ",\"connected\":true,\"deviceID\":4}}}";
        MyLogger.getInstance().logInfo("request:" + requestBody);
        return requestBody;
    }

    @And("I have a valid ticket on NFC card with a transfer leg")
    public void iHaveAValidTicketOnNFCCardWithATransferLeg() {
    }

    @And("I navigate to the transfer station")
    public void iNavigateToTheTransferStation() {
    }

    @And("I have multiple valid merchant tickets on the NFC card")
    public void iHaveMultipleValidMerchantTicketsOnTheNFCCard() {
    }

    @And("I have a blocked NFC card")
    public void iHaveABlockedNFCCard() {
    }

    @When("I tap the blocked NFC card on the left NFC reader")
    public void iTapTheBlockedNFCCardOnTheLeftNFCReader() {
    }

    @Then("The card is blocked message should be displayed")
    public void theCardIsBlockedMessageShouldBeDisplayed() {
    }

    @And("I have a re-issued NFC card")
    public void iHaveAReIssuedNFCCard() {
    }

    @When("I tap the re-issued NFC card with a valid ticket on the left NFC reader")
    public void iTapTheReIssuedNFCCardWithAValidTicketOnTheLeftNFCReader() {
    }

    @When("I tap the re-issued NFC card with a expired ticket on the left NFC reader")
    public void iTapTheReIssuedNFCCardWithAExpiredTicketOnTheLeftNFCReader() {
    }

    @And("I have a disabled NFC card")
    public void iHaveADisabledNFCCard() {
    }

    @When("I tap the disabled NFC card on the left NFC reader")
    public void iTapTheDisabledNFCCardOnTheLeftNFCReader() {
    }

    @Then("The card is disabled message should be displayed")
    public void theCardIsDisabledMessageShouldBeDisplayed() {
    }

    @And("I have a valid ticket on NFC card that does not work on the given day")
    public void iHaveAValidTicketOnNFCCardThatDoesNotWorkOnTheGivenDay() {
    }

    @Then("The day is not allowed message should be displayed")
    public void theDayIsNotAllowedMessageShouldBeDisplayed() {
    }

    @When("I perform a main leg transaction")
    public void iPerformAMainLegTransaction() {
    }

    @And("I try to perform another main leg transaction within transfer leg")
    public void iTryToPerformAnotherMainLegTransactionWithinTransferLeg() {
    }

    @Then("The new main leg not allowed within transfer leg message should be displayed")
    public void theNewMainLegNotAllowedWithinTransferLegMessageShouldBeDisplayed() {
    }

    @And("I have a valid ticket on NFC card where all the rides for the given day are availed")
    public void iHaveAValidTicketOnNFCCardWhereAllTheRidesForTheGivenDayAreAvailed() {
    }

    @Then("The no rides available for the day message should be displayed")
    public void theNoRidesAvailableForTheDayMessageShouldBeDisplayed() {
    }

    @And("I have a valid ticket on NFC card with zero rides available")
    public void iHaveAValidTicketOnNFCCardWithZeroRidesAvailable() {
    }

    @Then("The trips depleted message should be displayed")
    public void theTripsDepletedMessageShouldBeDisplayed() {
    }

    @And("I have a valid ticket on NFC card with pass limit exceeded")
    public void iHaveAValidTicketOnNFCCardWithPassLimitExceeded() {
    }

    @Then("The pass limit message should be displayed")
    public void thePassLimitMessageShouldBeDisplayed() {
    }

    @And("I have a valid ticket on NFC card with invalid ride date")
    public void iHaveAValidTicketOnNFCCardWithInvalidRideDate() {
    }

    @Then("The ride date is invalid message should be displayed")
    public void theRideDateIsInvalidMessageShouldBeDisplayed() {
    }

    @And("I have a valid ticket on NFC card with route not allowed")
    public void iHaveAValidTicketOnNFCCardWithRouteNotAllowed() {
    }

    @Then("The route not allowed message should be displayed")
    public void theRouteNotAllowedMessageShouldBeDisplayed() {
    }

    @And("I have a valid ticket on NFC card with station not allowed")
    public void iHaveAValidTicketOnNFCCardWithStationNotAllowed() {
    }

    @Then("The station is not allowed message should be displayed")
    public void theStationIsNotAllowedMessageShouldBeDisplayed() {
    }

    @And("I have a valid ticket on NFC card with no transfer")
    public void iHaveAValidTicketOnNFCCardWithNoTransfer() {
    }

    @Then("The transfer not allowed message should be displayed")
    public void theTransferNotAllowedMessageShouldBeDisplayed() {
    }

    @And("I have a valid ticket on NFC card with different business unit")
    public void iHaveAValidTicketOnNFCCardWithDifferentBusinessUnit() {
    }

    @Then("The incorrect contract message should be displayed")
    public void theIncorrectContractMessageShouldBeDisplayed() {
    }

    @And("I have a valid ticket on NFC card of a different NFC card")
    public void iHaveAValidTicketOnNFCCardOfADifferentNFCCard() {
    }

    @Then("The error in uid matching message should be displayed")
    public void theErrorInUidMatchingMessageShouldBeDisplayed() {
    }
}
