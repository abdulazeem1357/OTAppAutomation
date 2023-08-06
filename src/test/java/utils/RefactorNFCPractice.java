package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RefactorNFCPractice {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the JSON input:");
        String inputJson = scanner.nextLine();

        String parsedSVCData = parseSVCData(inputJson);
        String outputJson = formatInputOutput(parsedSVCData);
        System.out.println("Output:");
        System.out.println(outputJson);
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

    public static String formatInputOutput(String inputJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, String>> beforeList = mapper.readValue(inputJson, new TypeReference<List<Map<String, String>>>() {
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
}
