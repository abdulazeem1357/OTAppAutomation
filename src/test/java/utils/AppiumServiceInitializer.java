package utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

// This class initializes the Appium server
public class AppiumServiceInitializer {
    AppiumDriverLocalService localService;

    //    sets up the Appium service with IP address and port number
    public void startAppiumServer() {
        localService = new AppiumServiceBuilder()
                .withIPAddress("0.0.0.0")
                .usingPort(4723)
                .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                .withAppiumJS(new File("/Users/IT/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .build();
        localService.start();
    }

    //    stops the Appium service if it is running.
    //    If not, it displays a message that the service is not running.
    public void stopAppiumServer() {
        if (localService.isRunning()) {
            localService.stop();
            System.out.println("The appium Service is stopped!");
        } else {
            System.out.println("The appium Service is not running!");
        }
    }

    public boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }
}
