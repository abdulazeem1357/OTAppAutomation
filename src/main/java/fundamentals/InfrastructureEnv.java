package fundamentals;

import enums.DeviceType;
import enums.DriverType;

import java.util.HashMap;
import java.util.Map;

// mvn test -Denv=QA
public class InfrastructureEnv {
    public String getAppURL() {

        String env = System.getProperty("env", "Local");

        Map<String, String> urls = new HashMap<>();
        urls.put("Local", System.getProperty("user.dir") + "/" + "build/app.apk");
        urls.put("QA", "http://qa.opentransit.co/backend/web/builds/latest.apk");
        urls.put("Staging", "http://putco.opentransit.co/backend/web/builds/latest.apk");

        return urls.get(env);
    }

    public DeviceType getDevice() {
        String device = System.getProperty("device", "HONOR");
        return DeviceType.valueOf(device.toUpperCase());
    }

    public String getIMEIForDevice(DeviceType device) {
        Map<DeviceType, String> imeiMap = new HashMap<>();
        imeiMap.put(DeviceType.HONOR, "862510033859914");
        imeiMap.put(DeviceType.MOTOROLA, "351625111274519");
        imeiMap.put(DeviceType.HUAWEI, "862789037359960");
        imeiMap.put(DeviceType.VIRTUAL_DEVICE, "358240051111110");

        return imeiMap.get(device);
    }

    public DriverType getDriverType() {
        return getDevice() == DeviceType.VIRTUAL_DEVICE ? DriverType.EMULATOR_DEVICE_FULL_RESET : DriverType.PHYSICAL_DEVICE_FULL_RESET;
    }
}
