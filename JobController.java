package com.jats.controller;

import com.jats.model.Employer;
import com.jats.model.Job;
import com.jats.service.JobService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class JobController implements HttpHandler {

    private final JobService jobService = new JobService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                List<Job> jobs = jobService.getAllJobs();
                StringBuilder response = new StringBuilder();

                for (Job job : jobs) {
                    response.append(job.getJobId())
                            .append(": ")
                            .append(job.getTitle())
                            .append(" - ")
                            .append(job.getLocation())
                            .append("\n");
                }

                sendResponse(exchange, 200, response.toString());
            } else if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8));

                String[] data = reader.readLine().split(",");

                Employer employer = new Employer();
                employer.setId(Integer.parseInt(data[3]));

                Job job = new Job();
                job.setTitle(data[0]);
                job.setDescription(data[1]);
                job.setLocation(data[2]);
                job.setEmployer(employer);

                jobService.postJob(job);
                sendResponse(exchange, 201, "Job posted successfully");
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
