package com.jats.model;

import java.time.LocalDate;

/**
 * Represents a job application submitted by an applicant.
 */
public class Application {

    private int applicationId;
    private Applicant applicant;
    private Job job;
    private LocalDate applicationDate;
    private ApplicationStatus status;

    public Application(int applicationId, Applicant applicant, Job job,
                       LocalDate applicationDate, ApplicationStatus status) {
        this.applicationId = applicationId;
        this.applicant = applicant;
        this.job = job;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    public Application() {
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
