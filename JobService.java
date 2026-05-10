package com.jats.service;

import com.jats.dao.JobDAO;
import com.jats.model.Job;

import java.sql.SQLException;
import java.util.List;

public class JobService {

    private final JobDAO jobDAO;
    private final ValidationService validationService;

    public JobService() {
        this.jobDAO = new JobDAO();
        this.validationService = new ValidationService();
    }

    public void postJob(Job job) throws SQLException {
        validationService.validateJob(job);
        jobDAO.createJob(job);
    }

    public List<Job> getAllJobs() throws SQLException {
        return jobDAO.getAllJobs();
    }
}
