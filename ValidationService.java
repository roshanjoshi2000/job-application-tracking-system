package com.jats.service;

import com.jats.model.Applicant;
import com.jats.model.Job;

public class ValidationService {

    public void validateApplicant(Applicant applicant) {
        if (applicant == null) {
            throw new IllegalArgumentException("Applicant cannot be null.");
        }
        if (applicant.getName() == null || applicant.getName().isEmpty()) {
            throw new IllegalArgumentException("Applicant name is required.");
        }
        if (applicant.getEmail() == null || applicant.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Applicant email is required.");
        }
    }

    public void validateJob(Job job) {
        if (job == null) {
            throw new IllegalArgumentException("Job cannot be null.");
        }
        if (job.getTitle() == null || job.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Job title is required.");
        }
        if (job.getEmployer() == null) {
            throw new IllegalArgumentException("Job must be linked to an employer.");
        }
    }
}
