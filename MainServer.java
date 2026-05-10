package com.jats.app;

import com.jats.controller.*;
import com.jats.util.LoggerUtil;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class MainServer {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

            server.createContext("/applicants", new ApplicantController());
            server.createContext("/employers", new EmployerController());
            server.createContext("/jobs", new JobController());
            server.createContext("/applications", new ApplicationController());

            server.setExecutor(null);
            server.start();

            LoggerUtil.info("Server started at http://localhost:8000");
            LoggerUtil.info("Available endpoints:");
            LoggerUtil.info("GET/POST  /applicants");
            LoggerUtil.info("POST      /employers");
            LoggerUtil.info("GET/POST  /jobs");
            LoggerUtil.info("GET/POST  /applications");

        } catch (Exception e) {
            LoggerUtil.error("Failed to start server", e);
        }
    }
}
