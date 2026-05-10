package com.jats.dao;

import com.jats.model.Applicant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicantDAO {

    public void createApplicant(Applicant applicant) throws SQLException {
        String sql = "INSERT INTO applicants (name, email, cv, skills, experience) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, applicant.getName());
            stmt.setString(2, applicant.getEmail());
            stmt.setString(3, applicant.getCv());
            stmt.setString(4, applicant.getSkills());
            stmt.setInt(5, applicant.getYearsOfExperience());

            stmt.executeUpdate();
        }
    }

    public Applicant getApplicantById(int id) throws SQLException {
        String sql = "SELECT * FROM applicants WHERE applicant_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Applicant applicant = new Applicant();
                applicant.setId(rs.getInt("applicant_id"));
                applicant.setName(rs.getString("name"));
                applicant.setEmail(rs.getString("email"));
                applicant.setCv(rs.getString("cv"));
                applicant.setSkills(rs.getString("skills"));
                applicant.setYearsOfExperience(rs.getInt("experience"));
                return applicant;
            }
        }
        return null;
    }

    public List<Applicant> getAllApplicants() throws SQLException {
        List<Applicant> applicants = new ArrayList<>();
        String sql = "SELECT * FROM applicants";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Applicant applicant = new Applicant();
                applicant.setId(rs.getInt("applicant_id"));
                applicant.setName(rs.getString("name"));
                applicant.setEmail(rs.getString("email"));
                applicant.setCv(rs.getString("cv"));
                applicant.setSkills(rs.getString("skills"));
                applicant.setYearsOfExperience(rs.getInt("experience"));
                applicants.add(applicant);
            }
        }
        return applicants;
    }

    public void updateApplicant(Applicant applicant) throws SQLException {
        String sql = "UPDATE applicants SET name=?, email=?, cv=?, skills=?, experience=? WHERE applicant_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, applicant.getName());
            stmt.setString(2, applicant.getEmail());
            stmt.setString(3, applicant.getCv());
            stmt.setString(4, applicant.getSkills());
            stmt.setInt(5, applicant.getYearsOfExperience());
            stmt.setInt(6, applicant.getId());

            stmt.executeUpdate();
        }
    }

    public void deleteApplicant(int id) throws SQLException {
        String sql = "DELETE FROM applicants WHERE applicant_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
