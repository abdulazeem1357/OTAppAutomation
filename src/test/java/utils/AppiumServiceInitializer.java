package utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

// This class initializes the Appium server
public class AppiumServiceInitializer {

    AppiumDriverLocalService localService;

    //    sets up the Appium service with IP address and port number
    public void startAppiumServer() {
        localService = new AppiumServiceBuilder()
                .withIPAddress("0.0.0.0")
                .usingPort(4723)
                .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                .build();
        localService.start();
    }

    //    stops the Appium service if it is running.
    //    If not, it displays a message that the service is not running.
    public void stopAppiumServer() {
        if (localService.isRunning()) {
            localService.stop();
        } else {
            System.out.println("The appium Service is not running!");
        }
    }
}
