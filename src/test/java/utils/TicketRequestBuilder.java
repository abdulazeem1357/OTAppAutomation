package utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class TicketRequestBuilder {
    private String customUid;
    private static final String DEFAULT_UID = "8069F932000000";
    private final JSONArray ticketData;
    public static String uid;

    public TicketRequestBuilder() {
        this.ticketData = new JSONArray();
    }

    private String getUID() {
        uid = UIDProvider.getNextUID();
        return uid;
    }

    private TicketRequestBuilder addTicket(JSONObject ticket) {
        this.ticketData.put(ticket);
        return this;
    }

    public TicketRequest build() {
        String uidToUse = (customUid != null) ? customUid : getUID();
        return new TicketRequest(uidToUse, ticketData);
    }

    private TicketRequestBuilder customUid(String uid) {
        this.customUid = uid;
        return this;
    }

    public TicketRequestBuilder validTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();
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

    public TicketRequestBuilder multipleMerchantValidTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();

        JSONObject soshTicket = new JSONObject();
        soshTicket.put("change_bus", 0);
        soshTicket.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 10)));
        soshTicket.put("pass_value", 20);
        soshTicket.put("price", 100);
        soshTicket.put("tap_in", 0);
        soshTicket.put("terminal_id", 0);
        soshTicket.put("ticket_type", "trip_pass");
        soshTicket.put("fare_product_id", 1434);
        soshTicket.put("ott_ticket_id", 284);
        soshTicket.put("activation_date", "2023-06-06");
        soshTicket.put("departure_location_id", 0);
        soshTicket.put("destination_location_id", 0);
        soshTicket.put("route_id", 0);
        builder.addTicket(soshTicket);

        JSONObject tamTicket = new JSONObject();
        tamTicket.put("change_bus", 0);
        tamTicket.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 10)));
        tamTicket.put("pass_value", 20);
        tamTicket.put("price", 100);
        tamTicket.put("tap_in", 0);
        tamTicket.put("terminal_id", 0);
        tamTicket.put("ticket_type", "trip_pass");
        tamTicket.put("fare_product_id", 1434);
        tamTicket.put("ott_ticket_id", 283);
        tamTicket.put("activation_date", "2023-06-06");
        tamTicket.put("departure_location_id", 0);
        tamTicket.put("destination_location_id", 0);
        tamTicket.put("route_id", 0);
        builder.addTicket(tamTicket);

        JSONObject sowetoTicket = new JSONObject();
        sowetoTicket.put("change_bus", 0);
        sowetoTicket.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 10)));
        sowetoTicket.put("pass_value", 20);
        sowetoTicket.put("price", 100);
        sowetoTicket.put("tap_in", 0);
        sowetoTicket.put("terminal_id", 0);
        sowetoTicket.put("ticket_type", "trip_pass");
        sowetoTicket.put("fare_product_id", 1407);
        sowetoTicket.put("ott_ticket_id", 228);
        sowetoTicket.put("activation_date", "2023-06-06");
        sowetoTicket.put("departure_location_id", 0);
        sowetoTicket.put("destination_location_id", 0);
        sowetoTicket.put("route_id", 0);
        builder.addTicket(sowetoTicket);

        return builder;
    }

    public TicketRequestBuilder expiredTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();
        JSONObject ticketData = new JSONObject();
        ticketData.put("change_bus", 0);
        ticketData.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), -90)));
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

    public TicketRequestBuilder blockedNFCCardTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();

        builder.customUid("20250533000000");

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

    public TicketRequestBuilder reissueNFCCardTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();

        builder.customUid("D0229533000000");

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

    public TicketRequestBuilder reissueNFCCardExpiredTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();

        builder.customUid("D0229533000000");

        JSONObject ticketData1 = new JSONObject();
        ticketData1.put("change_bus", 0);
        ticketData1.put("expiry_date", "2022-08-08");
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

    public TicketRequestBuilder disabledNFCCardTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();

        builder.customUid("90C20233000000");

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

    public TicketRequestBuilder dayNotAllowedTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();
        JSONObject ticketData1 = new JSONObject();
        ticketData1.put("change_bus", 0);
        ticketData1.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 10)));
        ticketData1.put("pass_value", 20);
        ticketData1.put("price", 100);
        ticketData1.put("tap_in", 0);
        ticketData1.put("terminal_id", 0);
        ticketData1.put("ticket_type", "trip_pass");
        ticketData1.put("fare_product_id", 1441);
        ticketData1.put("ott_ticket_id", 311);
        ticketData1.put("activation_date", "2023-06-06");
        ticketData1.put("departure_location_id", 0);
        ticketData1.put("destination_location_id", 0);
        ticketData1.put("route_id", 0);
        builder.addTicket(ticketData1);
        return builder;
    }

    public TicketRequestBuilder differentBUTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();
        JSONObject ticketData = new JSONObject();
        ticketData.put("change_bus", 0);
        ticketData.put("expiry_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 10)));
        ticketData.put("pass_value", 20);
        ticketData.put("price", 100);
        ticketData.put("tap_in", 0);
        ticketData.put("terminal_id", 0);
        ticketData.put("ticket_type", "trip_pass");
        ticketData.put("fare_product_id", 1410);
        ticketData.put("ott_ticket_id", 228);
        ticketData.put("activation_date", "2023-06-06");
        ticketData.put("departure_location_id", 0);
        ticketData.put("destination_location_id", 0);
        ticketData.put("route_id", 0);
        ticketData.put("ride_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), -1)));
        ticketData.put("ride_time", "10:10");
        builder.addTicket(ticketData);
        return builder;
    }

    public TicketRequestBuilder noMainTransferLegTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();
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
        ticketData.put("ride_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), 0)));
        ticketData.put("ride_time", DateUtil.formatTime(DateUtil.modifyTime(DateUtil.getCurrentTime())));

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

    public TicketRequestBuilder ridesAreNotAvailableTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();
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
        ticketData.put("activation_date", "2022-06-06");
        ticketData.put("departure_location_id", 0);
        ticketData.put("destination_location_id", 0);
        ticketData.put("route_id", 0);
        ticketData.put("ride_date", DateUtil.formatDate(DateUtil.modifyDaysToDate(DateUtil.getCurrentDate(), -1)));
        ticketData.put("ride_time", DateUtil.formatTime(DateUtil.modifyTime(DateUtil.getCurrentTime())));

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

    public TicketRequestBuilder reissueFromNFCCardTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();

        builder.customUid("10420233000000");

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
}
