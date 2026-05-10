package com.jats.controller;

import com.jats.model.Applicant;
import com.jats.service.ApplicantService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class ApplicantController implements HttpHandler {

    private final ApplicantService applicantService = new ApplicantService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                handleGet(exchange);
            } else if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                handlePost(exchange);
            } else {
                sendResponse(exchange, 405, "Method Not Allowed");
            }
        } catch (Exception e) {
            sendResponse(exchange, 500, e.getMessage());
        }
    }

    private void handleGet(HttpExchange exchange) throws SQLException, IOException {
        List<Applicant> applicants = applicantService.getAllApplicants();
        StringBuilder response = new StringBuilder();

        for (Applicant a : applicants) {
            response.append(a.getId())
                    .append(" - ")
                    .append(a.getName())
                    .append(" (")
                    .append(a.getEmail())
                    .append(")\n");
        }

        sendResponse(exchange, 200, response.toString());
    }

    private void handlePost(HttpExchange exchange) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8));

        String[] data = reader.readLine().split(",");

        Applicant applicant = new Applicant();
        applicant.setName(data[0]);
        applicant.setEmail(data[1]);
        applicant.setCv(data[2]);
        applicant.setSkills(data[3]);
        applicant.setYearsOfExperience(Integer.parseInt(data[4]));

        applicantService.registerApplicant(applicant);
        sendResponse(exchange, 201, "Applicant created successfully");
    }

    private void sendResponse(HttpExchange exchange, int status, String message) throws IOException {
        exchange.sendResponseHeaders(status, message.length());
        OutputStream os = exchange.getResponseBody();
        os.write(message.getBytes());
        os.close();
    }
}
