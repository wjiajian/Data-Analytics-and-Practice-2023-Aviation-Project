package com.qrsoft.etl.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static ConfigManager configManager; // 声明工具类的一个私有的对象
    private static Properties properties; //声明对象

    private ConfigManager() { //私有无参构造方法
        String configFile = "config.properties";
        properties = new Properties();
        InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
        try {
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigManager getInstance() {
        if (configManager == null) {
            configManager = new ConfigManager();
        }
        return configManager;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}