package com.qrsoft.etl.common.db;

import com.qrsoft.etl.util.ConfigManager;

import java.util.ArrayList;
import java.util.List;

public class DBInitInfo {
    // 设置注册属性
    public static String DRIVER = ConfigManager.getInstance().getValue("jdbc.driver");
    public static String URL = ConfigManager.getInstance().getValue("jdbc.url");
    public static String USERNAME = ConfigManager.getInstance().getValue("jdbc.username");
    public static String PASSWORD = ConfigManager.getInstance().getValue("jdbc.password");
    public static String MinConnections = ConfigManager.getInstance().getValue("jdbc.min");
    public static String MaxConnections = ConfigManager.getInstance().getValue("jdbc.max");

    public static List<DBbean> beans = null;

    static {
        beans = new ArrayList<DBbean>();
        // 这里数据 可以从xml 等配置文件进行获取，为了测试，这里直接写死了
        DBbean beanMysql = new DBbean();
        beanMysql.setDriverName(DRIVER);
        beanMysql.setUrl(URL);
        beanMysql.setUserName(USERNAME);
        beanMysql.setPassword(PASSWORD);

        beanMysql.setMinConnections(Integer.parseInt(MinConnections));
        beanMysql.setMaxConnections(Integer.parseInt(MaxConnections));

        beanMysql.setPoolName("pool");
        beans.add(beanMysql);
    }
}