package fundamental;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;
    public ConfigReader() {
        try {
            String filePath = "src/main/resources/config.properties";
            FileInputStream fis = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPropValue(String key) {
        return prop.getProperty(key);
    }
}
