package com.jats.dao;

import com.jats.model.Employer;

import java.sql.*;

public class EmployerDAO {

    public void createEmployer(Employer employer) throws SQLException {
        String sql = "INSERT INTO employers (name, email, company_name) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employer.getName());
            stmt.setString(2, employer.getEmail());
            stmt.setString(3, employer.getCompanyName());

            stmt.executeUpdate();
        }
    }

    public Employer getEmployerById(int id) throws SQLException {
        String sql = "SELECT * FROM employers WHERE employer_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Employer employer = new Employer();
                employer.setId(rs.getInt("employer_id"));
                employer.setName(rs.getString("name"));
                employer.setEmail(rs.getString("email"));
                employer.setCompanyName(rs.getString("company_name"));
                return employer;
            }
        }
        return null;
    }
}
