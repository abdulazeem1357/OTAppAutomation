package utils;

import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.time.Duration;

//This code sets up Appium capabilities for an Android emulator or physical device.
//The code uses Apache Commons Configuration library to read values from a config.properties file.
public class ConfigHelper {

    static InfrastructureEnv infrastructureEnv = new InfrastructureEnv();

    //    sets up the capabilities for an Android emulator with no reset,
    //    meaning the app state will be preserved between test runs.
    public static UiAutomator2Options configWithNoResetTrue() {
        UiAutomator2Options capabilities;

        try {
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties().setFileName("config.properties"));
            Configuration config = builder.getConfiguration();

            capabilities = new UiAutomator2Options()
                    .setApp(config.getString("APILevel25"))
                    .setDeviceName(config.getString("androidEmulator"))
                    .setIsHeadless(true)
//                    .skipServerInstallation()
//                    .noSign()
                    .setUiautomator2ServerInstallTimeout(Duration.ofSeconds(30))
                    .setApp(infrastructureEnv.getAppURL())
//                .setApp(System.getProperty("user.dir") + "/" + configReader.getPropValue("targetAppOT"))
                    .setAutoGrantPermissions(Boolean.parseBoolean(config.getString("autoGrantPermissionsTrue")))
                    .setNoReset(Boolean.parseBoolean(config.getString("noResetTrue")))
                    .setFullReset(Boolean.parseBoolean(config.getString("fullResetFalse")))
                    .setUdid(infrastructureEnv.getDevice());

        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }

        return capabilities;
    }

    //    sets up the capabilities for an Android emulator with reset,
    //    meaning the app state will be reset between test runs.
    public static UiAutomator2Options configWithResetFalse() {
        UiAutomator2Options capabilities;

        try {
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties().setFileName("config.properties"));
            Configuration config = builder.getConfiguration();

            capabilities = new UiAutomator2Options()
                    .setApp(config.getString("APILevel25"))
                    .setDeviceName(config.getString("androidEmulator"))
                    .setIsHeadless(true)
//                    .skipServerInstallation()
                    .setUiautomator2ServerInstallTimeout(Duration.ofSeconds(60))
                    .setApp(infrastructureEnv.getAppURL())
//                .setApp(System.getProperty("user.dir") + "/" + configReader.getPropValue("targetAppOT"))
                    .setAutoGrantPermissions(Boolean.parseBoolean(config.getString("autoGrantPermissionsTrue")))
                    .setNoReset(Boolean.parseBoolean(config.getString("noResetFalse")))
                    .setFullReset(Boolean.parseBoolean(config.getString("fullResetFalse")))
                    .setUdid(infrastructureEnv.getDevice());

        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }

        return capabilities;
    }

    //    sets up the capabilities for a physical device with no reset,
    //    meaning the app state will be preserved between test runs.
    public static UiAutomator2Options configWithNoResetTruePhysicalDevice() {
        UiAutomator2Options capabilities;

        try {
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties().setFileName("config.properties"));
            Configuration config = builder.getConfiguration();

            capabilities = new UiAutomator2Options()
                    .setApp(infrastructureEnv.getAppURL())
//                .setApp(System.getProperty("user.dir") + "/" + config.getString("targetAppOT"))
                    .setAutoGrantPermissions(Boolean.parseBoolean(config.getString("autoGrantPermissionsTrue")))
                    .setNoReset(Boolean.parseBoolean(config.getString("noResetTrue")))
                    .setFullReset(Boolean.parseBoolean(config.getString("fullResetFalse")))
                    .setUdid(infrastructureEnv.getDevice());

        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }

        return capabilities;
    }

    //    sets up the capabilities for a physical device with reset
    //    meaning the app state will be reset between test runs.
    public static UiAutomator2Options configWithResetFalsePhysicalDevice() {
        UiAutomator2Options capabilities;

        try {
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties().setFileName("config.properties"));
            Configuration config = builder.getConfiguration();

            capabilities = new UiAutomator2Options()
                    .setApp(infrastructureEnv.getAppURL())
//                    .setApp(System.getProperty("user.dir") + "/" + config.getString("targetAppOT"))
                    .setAutoGrantPermissions(Boolean.parseBoolean(config.getString("autoGrantPermissionsTrue")))
                    .setNoReset(Boolean.parseBoolean(config.getString("noResetFalse")))
                    .setFullReset(Boolean.parseBoolean(config.getString("fullResetFalse")))
                    .setUdid(infrastructureEnv.getDevice());

        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }

        return capabilities;
    }
}
