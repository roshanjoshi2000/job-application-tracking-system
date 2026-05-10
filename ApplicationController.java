package com.jats.controller;

import com.jats.model.*;
import com.jats.service.ApplicationService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ApplicationController implements HttpHandler {

    private final ApplicationService applicationService;

    // Initialize in constructor
    public ApplicationController() {
        ApplicationService service = null;
        try {
            service = new ApplicationService();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
        this.applicationService = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (applicationService == null) {
                sendResponse(exchange, 500, "Service initialization failed");
                return;
            }

            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                List<Application> applications = applicationService.getAllApplications();
                StringBuilder response = new StringBuilder();

                for (Application app : applications) {
                    response.append("Application ")
                            .append(app.getApplicationId())
                            .append(" - Status: ")
                            .append(app.getStatus())
                            .append("\n");
                }

                sendResponse(exchange, 200, response.toString());

            } else if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8));

                String[] data = reader.readLine().split(",");

                Applicant applicant = new Applicant();
                applicant.setId(Integer.parseInt(data[0]));

                Job job = new Job();
                job.setJobId(Integer.parseInt(data[1]));

                Application application = new Application();
                application.setApplicant(applicant);
                application.setJob(job);
                application.setApplicationDate(LocalDate.now());

                applicationService.applyForJob(application);
                sendResponse(exchange, 201, "Application submitted");

            } else if ("PUT".equalsIgnoreCase(exchange.getRequestMethod())) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8));

                String[] data = reader.readLine().split(",");
                int applicationId = Integer.parseInt(data[0]);
                ApplicationStatus newStatus = ApplicationStatus.valueOf(data[1].toUpperCase());

                applicationService.updateApplicationStatus(applicationId, newStatus);
                sendResponse(exchange, 200, "Application status updated");

            } else if ("DELETE".equalsIgnoreCase(exchange.getRequestMethod())) {
                String query = exchange.getRequestURI().getQuery(); // e.g., ?id=1
                int id = Integer.parseInt(query.split("=")[1]);
                applicationService.deleteApplication(id);
                sendResponse(exchange, 200, "Application deleted");

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
