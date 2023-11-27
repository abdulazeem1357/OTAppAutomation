package utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class TicketPractice {

    public static void main(String[] args) {

        TicketRequest blockedNFCCardTicketRequest = new TicketPractice().blockedNFCCardTicket().build();
        String blockedNFCCardTicketRequestBody = blockedNFCCardTicketRequest.createRequestBody();
        System.out.println("blockedNFCCardTicket: " + blockedNFCCardTicketRequestBody);

        TicketRequest multipleMerchantValidTicketRequest = new TicketPractice().multipleMerchantValidTicket().build();
        String multipleMerchantValidTicketRequestBody = multipleMerchantValidTicketRequest.createRequestBody();
        System.out.println("multipleMerchantValidTicket: " + multipleMerchantValidTicketRequestBody);

        TicketRequest validTicketRequest = new TicketPractice().validTicket().build();
        String validTicketRequestBody = validTicketRequest.createRequestBody();
        System.out.println("validTicket: " + validTicketRequestBody);

        TicketRequest tripsDepletedTicketRequest = new TicketPractice().tripsDepletedTicket().build();
        String tripsDepletedTicketRequestBody = tripsDepletedTicketRequest.createRequestBody();
        System.out.println("tripsDepletedTicket: " + tripsDepletedTicketRequestBody);

        TicketRequest newMainLegNotAllowedWithinTransferTimeTicketRequest = new TicketPractice().newMainLegNotAllowedWithinTransferTimeTicket().build();
        String newMainLegNotAllowedWithinTransferTimeTicketRequestBody = newMainLegNotAllowedWithinTransferTimeTicketRequest.createRequestBody();
        System.out.println("newMainLegNotAllowedWithinTransferTimeTicket: " + newMainLegNotAllowedWithinTransferTimeTicketRequestBody);

        TicketRequest expiredTicketRequest = new TicketPractice().expiredTicket().build();
        System.out.println("Current UID: " + expiredTicketRequest.getUid());
        expiredTicketRequest.setUID("8069F932000000");
        System.out.println("Updated UID: " + expiredTicketRequest.getUid());
        String expiredTicketRequestBody = expiredTicketRequest.createRequestBody();
        System.out.println("expiredTicket: " + expiredTicketRequestBody);
    }

    private String customUid;
    private static final String DEFAULT_UID = "8069F932000000";
    private final JSONArray ticketData;
    public static String uid;

    private TicketPractice() {
        this.ticketData = new JSONArray();
    }

    private String getUID() {
        uid = UIDProvider.getNextUID();
        return uid;
    }

    private TicketPractice addTicket(JSONObject ticket) {
        this.ticketData.put(ticket);
        return this;
    }

    private TicketRequest build() {
        String uidToUse = (customUid != null) ? customUid : getUID();
        return new TicketRequest(uidToUse, ticketData);
    }

    private TicketPractice customUid(String uid) {
        this.customUid = uid;
        return this;
    }

    // Method to update UID after the ticket has been built
    public void updateUid(String newUid) {
        uid = newUid;
    }

    public TicketPractice blockedNFCCardTicket() {
        TicketPractice builder = new TicketPractice();

        builder.customUid("20E06933000000");

        JSONObject ticketData1 = new JSONObject();
        ticketData1.put("change_bus", 0);
        ticketData1.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 10)));
        ticketData1.put("pass_value", 20);
        ticketData1.put("price", 100);
        ticketData1.put("tap_in", 0);
        ticketData1.put("terminal_id", 0);
        ticketData1.put("ticket_type", "trip_pass");
        ticketData1.put("fare_product_id", 1434);
        ticketData1.put("ott_ticket_id", 284);
        ticketData1.put("activation_date", "2023-06-06");
        ticketData1.put("departure_location_id", 0);
        ticketData1.put("destination_location_id", 0);
        ticketData1.put("route_id", 0);
        builder.addTicket(ticketData1);

        return builder;
    }

    public TicketPractice multipleMerchantValidTicket() {
        TicketPractice builder = new TicketPractice();

        JSONObject ticketData1 = new JSONObject();
        ticketData1.put("change_bus", 0);
        ticketData1.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 10)));
        ticketData1.put("pass_value", 20);
        ticketData1.put("price", 100);
        ticketData1.put("tap_in", 0);
        ticketData1.put("terminal_id", 0);
        ticketData1.put("ticket_type", "trip_pass");
        ticketData1.put("fare_product_id", 1434);
        ticketData1.put("ott_ticket_id", 284);
        ticketData1.put("activation_date", "2023-06-06");
        ticketData1.put("departure_location_id", 0);
        ticketData1.put("destination_location_id", 0);
        ticketData1.put("route_id", 0);
        builder.addTicket(ticketData1);

        JSONObject ticketData2 = new JSONObject();
        ticketData2.put("change_bus", 0);
        ticketData2.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 10)));
        ticketData2.put("pass_value", 0);
        ticketData2.put("price", 100);
        ticketData2.put("tap_in", 0);
        ticketData2.put("terminal_id", 0);
        ticketData2.put("ticket_type", "trip_pass");
        ticketData2.put("fare_product_id", 1434);
        ticketData2.put("ott_ticket_id", 284);
        ticketData2.put("activation_date", "2023-06-06");
        ticketData2.put("departure_location_id", 0);
        ticketData2.put("destination_location_id", 0);
        ticketData2.put("route_id", 0);
        ticketData2.put("ride_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), -1)));
        ticketData2.put("ride_time", DateUtil.formatTime(DateUtil.modifyTime(DateUtil.getCurrentTime(), 22, 14, 10)));
        builder.addTicket(ticketData2);

        JSONObject ticketData3 = new JSONObject();
        ticketData3.put("change_bus", 0);
        ticketData3.put("expiry_date", "2022-08-08");
        ticketData3.put("pass_value", 20);
        ticketData3.put("price", 100);
        ticketData3.put("tap_in", 0);
        ticketData3.put("terminal_id", 0);
        ticketData3.put("ticket_type", "trip_pass");
        ticketData3.put("fare_product_id", 1434);
        ticketData3.put("ott_ticket_id", 284);
        ticketData3.put("activation_date", "2022-06-06");
        ticketData3.put("departure_location_id", 0);
        ticketData3.put("destination_location_id", 0);
        ticketData3.put("route_id", 0);
        builder.addTicket(ticketData3);

        JSONObject ticketData4 = new JSONObject();
        ticketData4.put("change_bus", 0);
        ticketData4.put("expiry_date", "2022-08-08");
        ticketData4.put("pass_value", 20);
        ticketData4.put("price", 100);
        ticketData4.put("tap_in", 0);
        ticketData4.put("terminal_id", 0);
        ticketData4.put("ticket_type", "trip_pass");
        ticketData4.put("fare_product_id", 1434);
        ticketData4.put("ott_ticket_id", 284);
        ticketData4.put("activation_date", "2022-06-06");
        ticketData4.put("departure_location_id", 0);
        ticketData4.put("destination_location_id", 0);
        ticketData4.put("route_id", 0);
        builder.addTicket(ticketData4);

        JSONObject ticketData5 = new JSONObject();
        ticketData5.put("change_bus", 0);
        ticketData5.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 10)));
        ticketData5.put("pass_value", 10);
        ticketData5.put("price", 100);
        ticketData5.put("tap_in", 0);
        ticketData5.put("terminal_id", 0);
        ticketData5.put("ticket_type", "trip_pass");
        ticketData5.put("fare_product_id", 1434);
        ticketData5.put("ott_ticket_id", 284);
        ticketData5.put("activation_date", "2022-06-06");
        ticketData5.put("departure_location_id", 0);
        ticketData5.put("destination_location_id", 0);
        ticketData5.put("route_id", 0);
        ticketData5.put("ride_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), -1)));
        ticketData5.put("ride_time", "10:10");

        JSONArray legsConsumedArray = new JSONArray();

        JSONObject mainLeg = new JSONObject();
        mainLeg.put("down_path_consumed", 0);
        mainLeg.put("up_path_consumed", 1);
        legsConsumedArray.put(mainLeg);

        JSONObject transferLegOne = new JSONObject();
        transferLegOne.put("down_path_consumed", 0);
        transferLegOne.put("up_path_consumed", 1);
        legsConsumedArray.put(transferLegOne);

        JSONObject transferLegTwo = new JSONObject();
        transferLegTwo.put("down_path_consumed", 0);
        transferLegTwo.put("up_path_consumed", 1);
        legsConsumedArray.put(transferLegTwo);

        JSONObject transferLegThree = new JSONObject();
        transferLegThree.put("down_path_consumed", 0);
        transferLegThree.put("up_path_consumed", 1);
        legsConsumedArray.put(transferLegThree);

        JSONObject transferLegFour = new JSONObject();
        transferLegFour.put("down_path_consumed", 0);
        transferLegFour.put("up_path_consumed", 0);
        legsConsumedArray.put(transferLegFour);

        ticketData5.put("legs_consumed", legsConsumedArray);
        builder.addTicket(ticketData5);

        return builder;
    }

    public TicketPractice validTicket() {
        TicketPractice builder = new TicketPractice();
        JSONObject ticketData = new JSONObject();
        ticketData.put("change_bus", 0);
        ticketData.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 10)));
        ticketData.put("pass_value", 20);
        ticketData.put("price", 100);
        ticketData.put("tap_in", 0);
        ticketData.put("terminal_id", 0);
        ticketData.put("ticket_type", "trip_pass");
        ticketData.put("fare_product_id", 1434);
        ticketData.put("ott_ticket_id", 284);
        ticketData.put("activation_date", "2023-06-06");
        ticketData.put("departure_location_id", 0);
        ticketData.put("destination_location_id", 0);
        ticketData.put("route_id", 0);
        builder.addTicket(ticketData);
        return builder;
    }

    public TicketPractice expiredTicket() {
        TicketPractice builder = new TicketPractice();
        JSONObject ticketData = new JSONObject();
        ticketData.put("change_bus", 0);
        ticketData.put("expiry_date", "2022-08-08");
        ticketData.put("pass_value", 20);
        ticketData.put("price", 100);
        ticketData.put("tap_in", 0);
        ticketData.put("terminal_id", 0);
        ticketData.put("ticket_type", "trip_pass");
        ticketData.put("fare_product_id", 1434);
        ticketData.put("ott_ticket_id", 284);
        ticketData.put("activation_date", "2022-06-06");
        ticketData.put("departure_location_id", 0);
        ticketData.put("destination_location_id", 0);
        ticketData.put("route_id", 0);
        builder.addTicket(ticketData);
        return builder;
    }

    public TicketPractice tripsDepletedTicket() {
        TicketPractice builder = new TicketPractice();
        JSONObject ticketData = new JSONObject();
        ticketData.put("change_bus", 0);
        ticketData.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 10)));
        ticketData.put("pass_value", 0);
        ticketData.put("price", 100);
        ticketData.put("tap_in", 0);
        ticketData.put("terminal_id", 0);
        ticketData.put("ticket_type", "trip_pass");
        ticketData.put("fare_product_id", 1434);
        ticketData.put("ott_ticket_id", 284);
        ticketData.put("activation_date", "2023-06-06");
        ticketData.put("departure_location_id", 0);
        ticketData.put("destination_location_id", 0);
        ticketData.put("route_id", 0);
        ticketData.put("ride_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), -1)));
        ticketData.put("ride_time", DateUtil.formatTime(DateUtil.modifyTime(DateUtil.getCurrentTime())));
        builder.addTicket(ticketData);
        return builder;
    }

    public TicketPractice newMainLegNotAllowedWithinTransferTimeTicket() {
        TicketPractice builder = new TicketPractice();
        JSONObject ticketData = new JSONObject();
        ticketData.put("change_bus", 0);
        ticketData.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 10)));
        ticketData.put("pass_value", 10);
        ticketData.put("price", 100);
        ticketData.put("tap_in", 0);
        ticketData.put("terminal_id", 0);
        ticketData.put("ticket_type", "trip_pass");
        ticketData.put("fare_product_id", 1434);
        ticketData.put("ott_ticket_id", 284);
        ticketData.put("activation_date", "2022-06-06");
        ticketData.put("departure_location_id", 0);
        ticketData.put("destination_location_id", 0);
        ticketData.put("route_id", 0);
        ticketData.put("ride_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), -1)));
        ticketData.put("ride_time", "10:10");

        JSONArray legsConsumedArray = new JSONArray();

        JSONObject mainLeg = new JSONObject();
        mainLeg.put("down_path_consumed", 0);
        mainLeg.put("up_path_consumed", 0);
        legsConsumedArray.put(mainLeg);

        JSONObject transferLegOne = new JSONObject();
        transferLegOne.put("down_path_consumed", 0);
        transferLegOne.put("up_path_consumed", 0);
        legsConsumedArray.put(transferLegOne);

        JSONObject transferLegTwo = new JSONObject();
        transferLegTwo.put("down_path_consumed", 0);
        transferLegTwo.put("up_path_consumed", 0);
        legsConsumedArray.put(transferLegTwo);

        JSONObject transferLegThree = new JSONObject();
        transferLegThree.put("down_path_consumed", 0);
        transferLegThree.put("up_path_consumed", 0);
        legsConsumedArray.put(transferLegThree);

        JSONObject transferLegFour = new JSONObject();
        transferLegFour.put("down_path_consumed", 0);
        transferLegFour.put("up_path_consumed", 0);
        legsConsumedArray.put(transferLegFour);

        ticketData.put("legs_consumed", legsConsumedArray);
        builder.addTicket(ticketData);
        return builder;
    }
}
