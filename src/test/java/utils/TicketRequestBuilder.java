package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TicketRequestBuilder {
    private static final String DEFAULT_UID = "8069F932000000";
    private final JSONArray ticketData;

    public TicketRequestBuilder() {
        this.ticketData = new JSONArray();
    }

    public TicketRequestBuilder addTicket(JSONObject ticket) {
        this.ticketData.put(ticket);
        return this;
    }

    public TicketRequest build() {
        return new TicketRequest(DEFAULT_UID, ticketData);
    }

    public static TicketRequestBuilder validTicket() {
        return validTicket(getTenDaysFromGivenDate());
    }

    public static TicketRequestBuilder validTicket(String expiry) {
        TicketRequestBuilder builder = new TicketRequestBuilder();
        JSONObject ticketData = new JSONObject();
        ticketData.put("change_bus", 0);
        ticketData.put("expiry_date", expiry);
        ticketData.put("pass_value", 20);
        ticketData.put("price", 100);
        ticketData.put("tap_in", 0);
        ticketData.put("terminal_id", 0);
        ticketData.put("ticket_type", "trip_pass");
        ticketData.put("fare_product_id", 1379);
        ticketData.put("ott_ticket_id", 9);
        ticketData.put("activation_date", "2023-06-06");
        ticketData.put("departure_location_id", 0);
        ticketData.put("destination_location_id", 0);
        ticketData.put("route_id", 0);
        builder.addTicket(ticketData);
        return builder;
    }

    public static TicketRequestBuilder expiredTicket() {
        TicketRequestBuilder builder = new TicketRequestBuilder();
        JSONObject ticketData = new JSONObject();
        ticketData.put("change_bus", 0);
        ticketData.put("expiry_date", "2022-08-08");
        ticketData.put("pass_value", 20);
        ticketData.put("price", 100);
        ticketData.put("tap_in", 0);
        ticketData.put("terminal_id", 0);
        ticketData.put("ticket_type", "trip_pass");
        ticketData.put("fare_product_id", 1379);
        ticketData.put("ott_ticket_id", 9);
        ticketData.put("activation_date", "2022-06-06");
        ticketData.put("departure_location_id", 0);
        ticketData.put("destination_location_id", 0);
        ticketData.put("route_id", 0);
        builder.addTicket(ticketData);
        return builder;
    }

    private static String getTenDaysFromGivenDate() {
        // Get the current date and add 10 days to it
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        Date expiryDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(expiryDate);
    }
}
