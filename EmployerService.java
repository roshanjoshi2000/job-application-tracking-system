package com.jats.service;

import com.jats.dao.EmployerDAO;
import com.jats.model.Employer;

import java.sql.SQLException;

public class EmployerService {

    private final EmployerDAO employerDAO;

    public EmployerService() {
        this.employerDAO = new EmployerDAO();
    }

    public void registerEmployer(Employer employer) throws SQLException {
        if (employer == null) {
            throw new IllegalArgumentException("Employer cannot be null.");
        }
        employerDAO.createEmployer(employer);
    }

    public Employer getEmployerById(int id) throws SQLException {
        return employerDAO.getEmployerById(id);
    }
}
