package com.example.student_port.Singleton;

public class URLInstance {
    private static String url = "http://192.168.1.7/StudentPortalAPI/api/studentportalapi.php";

    public static String getUrl() {
        return url;
    }
}