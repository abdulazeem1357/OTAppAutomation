package utils;

import java.util.Arrays;
import java.util.List;

public class UIDProvider {

    private static final List<String> UIDS = Arrays.asList(
            "8069F932000000",
            "F0D7EF19000000",
            "166360F6000000",
            "90EB0E1A000000",
            "70AC5733000000",
            "703BF832000000",
            "46BB65F6000000",
            "40D2FF32000000",
            "6007091A000000",
            "D039FF19000000",
            "F04A8633000000",
            "80B37433000000",
            "20E06933000000",
            "A67F65F6000000",
            "40390633000000",
            "80D7FE32000000",
            "C6CEFCF5000000",
            "A0B6F719000000",
            "90C20233000000",
            "20250533000000",
            "10420233000000",
            "E62760F6000000"
    );

    // This will track which UID to provide next.
    private static int currentUIDIndex = 0;

    public static String getNextUID() {
        if (currentUIDIndex >= UIDS.size()) {
            throw new RuntimeException("No more UIDs available. Increase your UID list or reset the index.");
        }
        return UIDS.get(currentUIDIndex++);
    }

    // Call this method when you restart your feature.
    public static void resetUIDIndex() {
        currentUIDIndex = 0;
    }

}
