package com.jats.service;

import com.jats.dao.ApplicantDAO;
import com.jats.model.Applicant;

import java.sql.SQLException;
import java.util.List;

public class ApplicantService {

    private final ApplicantDAO applicantDAO;
    private final ValidationService validationService;

    public ApplicantService() {
        this.applicantDAO = new ApplicantDAO();
        this.validationService = new ValidationService();
    }

    public void registerApplicant(Applicant applicant) throws SQLException {
        validationService.validateApplicant(applicant);
        applicantDAO.createApplicant(applicant);
    }

    public Applicant getApplicantById(int id) throws SQLException {
        return applicantDAO.getApplicantById(id);
    }

    public List<Applicant> getAllApplicants() throws SQLException {
        return applicantDAO.getAllApplicants();
    }

    public void updateApplicant(Applicant applicant) throws SQLException {
        validationService.validateApplicant(applicant);
        applicantDAO.updateApplicant(applicant);
    }

    public void deleteApplicant(int id) throws SQLException {
        applicantDAO.deleteApplicant(id);
    }
}
