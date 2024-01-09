package enums;

public enum DeviceType {
    HONOR("7BQDU17111007827"),
    MOTOROLA("ZY322PLGM8"),
    HUAWEI("6PQ7N17605002492"),
    VIRTUAL_DEVICE("emulator-5554");

    private final String deviceCode;

    DeviceType(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }
}
