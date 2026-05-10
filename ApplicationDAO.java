package com.jats.dao;

import com.jats.model.Application;
import com.jats.model.ApplicationStatus;
import com.jats.model.Applicant;
import com.jats.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    private final Connection conn;

    // Initialize connection in constructor to handle SQLException
    public ApplicationDAO() throws SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    // Insert a new application
    public void insertApplication(Application application) throws SQLException {
        String sql = "INSERT INTO applications (applicant_id, job_id, application_date, status) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, application.getApplicant().getId());
        stmt.setInt(2, application.getJob().getJobId());
        stmt.setDate(3, Date.valueOf(application.getApplicationDate()));
        stmt.setString(4, application.getStatus().name());
        stmt.executeUpdate();
        stmt.close();
    }

    // Get all applications
    public List<Application> getAllApplications() throws SQLException {
        String sql = "SELECT * FROM applications";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Application> applications = new ArrayList<>();
        while (rs.next()) {
            Application app = new Application();

            app.setApplicationId(rs.getInt("application_id"));

            Applicant applicant = new Applicant();
            applicant.setId(rs.getInt("applicant_id"));
            app.setApplicant(applicant);

            Job job = new Job();
            job.setJobId(rs.getInt("job_id"));
            app.setJob(job);

            app.setApplicationDate(rs.getDate("application_date").toLocalDate());
            app.setStatus(ApplicationStatus.valueOf(rs.getString("status")));

            applications.add(app);
        }
        rs.close();
        stmt.close();
        return applications;
    }

    // Get application by ID
    public Application getApplicationById(int id) throws SQLException {
        String sql = "SELECT * FROM applications WHERE application_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Application app = new Application();
            app.setApplicationId(rs.getInt("application_id"));

            Applicant applicant = new Applicant();
            applicant.setId(rs.getInt("applicant_id"));
            app.setApplicant(applicant);

            Job job = new Job();
            job.setJobId(rs.getInt("job_id"));
            app.setJob(job);

            app.setApplicationDate(rs.getDate("application_date").toLocalDate());
            app.setStatus(ApplicationStatus.valueOf(rs.getString("status")));

            rs.close();
            stmt.close();
            return app;
        }
        rs.close();
        stmt.close();
        return null;
    }

    // Update application
    public void updateApplication(Application app) throws SQLException {
        String sql = "UPDATE applications SET status=? WHERE application_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, app.getStatus().name());
        stmt.setInt(2, app.getApplicationId());
        stmt.executeUpdate();
        stmt.close();
    }

    // Delete application
    public void deleteApplication(int id) throws SQLException {
        String sql = "DELETE FROM applications WHERE application_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}
