package com.jats.model;

/**
 * Represents an employer posting jobs.
 */
public class Employer extends Person {

    private String companyName;

    public Employer(int id, String name, String email, String companyName) {
        super(id, name, email);
        this.companyName = companyName;
    }

    public Employer() {
        super();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
