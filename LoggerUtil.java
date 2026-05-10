package com.jats.util;

import java.time.LocalDateTime;

public class LoggerUtil {

    private LoggerUtil() {
    }

    public static void info(String message) {
        System.out.println("[INFO] " + LocalDateTime.now() + " - " + message);
    }

    public static void error(String message) {
        System.err.println("[ERROR] " + LocalDateTime.now() + " - " + message);
    }

    public static void error(String message, Exception e) {
        System.err.println("[ERROR] " + LocalDateTime.now() + " - " + message);
        e.printStackTrace();
    }
}
