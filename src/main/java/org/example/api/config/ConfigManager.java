package org.example.api.config;

import java.io.InputStream;
import java.util.Properties;

public enum ConfigManager {
    INSTANCE;

    private final Properties properties = new Properties();

    ConfigManager(){
        loadProperties();
    }

    private void loadProperties() {
        Environment env = EnvironmentResolver.resolve();
        String fileName = "config/" + env.name().toLowerCase() + ".properties";

        System.out.println("Loading config file: " + fileName);


        try(InputStream is  = getClass().getClassLoader().getResourceAsStream(fileName)){
            if(is == null){
                throw new RuntimeException("Configuration file not found:" + fileName);
            }

            properties.load(is);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to laod configuration", e);
        }
    }

    public String get(String key){
        return properties.getProperty(key);
    }
}
