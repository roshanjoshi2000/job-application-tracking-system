package com.jats.dao;

import com.jats.model.Employer;
import com.jats.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    public void createJob(Job job) throws SQLException {
        String sql = "INSERT INTO jobs (title, description, location, employer_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, job.getTitle());
            stmt.setString(2, job.getDescription());
            stmt.setString(3, job.getLocation());
            stmt.setInt(4, job.getEmployer().getId());

            stmt.executeUpdate();
        }
    }

    public List<Job> getAllJobs() throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM jobs";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getInt("job_id"));
                job.setTitle(rs.getString("title"));
                job.setDescription(rs.getString("description"));
                job.setLocation(rs.getString("location"));

                Employer employer = new Employer();
                employer.setId(rs.getInt("employer_id"));
                job.setEmployer(employer);

                jobs.add(job);
            }
        }
        return jobs;
    }
}
