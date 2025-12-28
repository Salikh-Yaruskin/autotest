package helpers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertyProvider {
    private static PropertyProvider instance;

    @Getter
    private final Properties properties = new Properties();

    public static synchronized PropertyProvider getInstance() {
        if (instance == null)
            instance = new PropertyProvider();
        return instance;
    }

    public PropertyProvider() {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties.load(is);
            resolverPlaceholder();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void resolverPlaceholder(){
        for (String key : properties.stringPropertyNames()){
            String value = properties.getProperty(key);
            for (String propertyKey : properties.stringPropertyNames()){
                value = value.replace("${" + propertyKey + "}", properties.getProperty(propertyKey));
            }
            properties.setProperty(key, value);
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
