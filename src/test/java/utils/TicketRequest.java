package utils;

import org.json.JSONArray;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TicketRequest {
    private String uid;
    private final JSONArray ticketData;

    // Constructor should be package-private to prevent classes in other packages from directly instantiating this class
    TicketRequest(String uid, JSONArray ticketData) {
        this.uid = uid;
        this.ticketData = ticketData;
    }

    public String getUid() {
        return uid;
    }

    public String setUID(String newUID) {
        this.uid = newUID;
        return uid;
    }

    public JSONArray getTicketData() {
        return ticketData;
    }

    public String createRequestBody() {
        try {
            return "uid=" + URLEncoder.encode(uid, String.valueOf(StandardCharsets.UTF_8))
                    + "&ticket_data=" + URLEncoder.encode(ticketData.toString(), String.valueOf(StandardCharsets.UTF_8));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
