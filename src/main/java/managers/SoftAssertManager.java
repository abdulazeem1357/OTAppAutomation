package managers;

import org.testng.asserts.SoftAssert;

public class SoftAssertManager {
    private static SoftAssert softAssert;

    public static void createSoftAssert() {
        softAssert = new SoftAssert();
    }

    public static SoftAssert getSoftAssert() {
        return softAssert;
    }

    public static void assertAllAndClear() {
        getSoftAssert().assertAll();
        softAssert = null;
    }
}
