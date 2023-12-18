package fundamentals;

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

    public String getDevice() {

        String device = System.getProperty("device", "VirtualDevice");

        Map<String, String> devices = new HashMap<>();
        devices.put("Honor", "7BQDU17111007827");
        devices.put("Motorola", "ZY322PLGM8");
        devices.put("Huawei", "6PQ7N17605002492");
        devices.put("VirtualDevice", "emulator-5554");

        return devices.get(device);
    }

    public String getIMEIForDevice(String device) {
        Map<String, String> imeiMap = new HashMap<>();
        imeiMap.put("7BQDU17111007827", "862510033859914"); //Honor
        imeiMap.put("ZY322PLGM8", "351625111274519"); //Motorola
        imeiMap.put("6PQ7N17605002492", "862789037359960"); //Huawei
        imeiMap.put("emulator-5554", "358240051111110"); //VirtualDevice

        return imeiMap.get(device);
    }

    public String getDriverType() {

        if (getDevice().equals("VirtualDevice")) {
            return "emulatorDeviceFullReset";
        }

        return "physicalDeviceFullReset";

        //PhysicalDeviceNoReset
        //physicalDeviceFullReset
        //emulatorDeviceNoReset
        //emulatorDeviceFullReset
    }
}
