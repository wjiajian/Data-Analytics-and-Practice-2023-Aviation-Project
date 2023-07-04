package com.qrsoft.common;

import java.util.HashMap;
import java.util.Map;

public class R {
    private final boolean success;
    private final String msg;
    private final Map<String, Object> payloadMap;

    private R(boolean success, String msg, Map<String, Object> payloadMap) {
        this.success = success;
        this.msg = msg;
        this.payloadMap = payloadMap;
    }
    public static R ok(Map<String, Object> payloadMap) {
        return new R(true, "", payloadMap);
    }
    public static R error(String msg) {
        return new R(false, msg, new HashMap<>());
    }
    public boolean isSuccess() {
        return success;
    }
    public Map<String, Object> getPayloadMap() {
        return payloadMap;
    }
    @Override
    public String toString() {
        return "R{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", payloadMap=" + payloadMap +
                '}';
    }
}
