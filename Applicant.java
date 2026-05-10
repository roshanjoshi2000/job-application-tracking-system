package com.jats.model;

/**
 * Represents a job applicant.
 */
public class Applicant extends Person {

    private String cv;
    private String skills;
    private int yearsOfExperience;

    public Applicant(int id, String name, String email, String cv, String skills, int yearsOfExperience) {
        super(id, name, email);
        this.cv = cv;
        this.skills = skills;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Applicant() {
        super();
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
