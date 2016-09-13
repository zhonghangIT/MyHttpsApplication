package com.education.myhttpsapplication.http;

public class HttpUtils {

    private static volatile HttpUtils singleton;

    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        if (singleton == null) {
            synchronized (HttpUtils.class) {
                if (singleton == null) {
                    singleton = new HttpUtils();
                }
            }
        }
        return singleton;
    }

}