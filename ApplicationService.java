package com.jats.service;

import com.jats.dao.ApplicationDAO;
import com.jats.model.Application;
import com.jats.model.ApplicationStatus;

import java.sql.SQLException;
import java.util.List;

public class ApplicationService {

    private final ApplicationDAO applicationDAO;

    // Initialize DAO in constructor to handle SQLException
    public ApplicationService() throws SQLException {
        this.applicationDAO = new ApplicationDAO();
    }

    // Get all applications
    public List<Application> getAllApplications() throws SQLException {
        return applicationDAO.getAllApplications();
    }

    // Apply for a job
    public void applyForJob(Application application) throws SQLException {
        application.setStatus(ApplicationStatus.APPLIED); // default status
        applicationDAO.insertApplication(application);
    }

    // Update application status
    public void updateApplicationStatus(int applicationId, ApplicationStatus newStatus) throws SQLException {
        Application app = applicationDAO.getApplicationById(applicationId);
        if (app != null) {
            app.setStatus(newStatus);
            applicationDAO.updateApplication(app);
        } else {
            throw new SQLException("Application with ID " + applicationId + " not found.");
        }
    }

    // Delete application
    public void deleteApplication(int id) throws SQLException {
        applicationDAO.deleteApplication(id);
    }
}
