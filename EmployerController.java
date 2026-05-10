package com.jats.controller;

import com.jats.model.Employer;
import com.jats.service.EmployerService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class EmployerController implements HttpHandler {

    private final EmployerService employerService = new EmployerService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8));

                String[] data = reader.readLine().split(",");

                Employer employer = new Employer();
                employer.setName(data[0]);
                employer.setEmail(data[1]);
                employer.setCompanyName(data[2]);

                employerService.registerEmployer(employer);
                sendResponse(exchange, 201, "Employer registered");
            } else {
                sendResponse(exchange, 405, "Method Not Allowed");
            }
        } catch (SQLException e) {
            sendResponse(exchange, 500, e.getMessage());
        }
    }

    private void sendResponse(HttpExchange exchange, int status, String message) throws IOException {
        exchange.sendResponseHeaders(status, message.length());
        OutputStream os = exchange.getResponseBody();
        os.write(message.getBytes());
        os.close();
    }
}
