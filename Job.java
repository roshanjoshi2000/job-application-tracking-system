package com.jats.model;

/**
 * Represents a job posting.
 */
public class Job {

    private int jobId;
    private String title;
    private String description;
    private String location;
    private Employer employer;

    public Job(int jobId, String title, String description, String location, Employer employer) {
        this.jobId = jobId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.employer = employer;
    }

    public Job() {
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
