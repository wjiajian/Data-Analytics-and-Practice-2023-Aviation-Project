package com.qrsoft.etl.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
    public static String readProperty(String key){
        Properties properties = new Properties();
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("myconfig.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.get(key).toString();
    }
}