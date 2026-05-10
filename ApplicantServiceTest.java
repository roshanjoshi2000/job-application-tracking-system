package com.jats.test;

import com.jats.model.Applicant;
import com.jats.service.ApplicantService;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantServiceTest {

    private ApplicantService applicantService;

    @BeforeEach
    void setUp() {
        applicantService = new ApplicantService();
    }

    @Test
    void testRegisterAndRetrieveApplicant() throws SQLException {
        Applicant applicant = new Applicant();
        applicant.setName("Test User");
        applicant.setEmail("testuser@email.com");
        applicant.setCv("Sample CV");
        applicant.setSkills("Java, SQL");
        applicant.setYearsOfExperience(2);

        applicantService.registerApplicant(applicant);

        List<Applicant> applicants = applicantService.getAllApplicants();
        assertFalse(applicants.isEmpty());
    }

    @Test
    void testInvalidApplicantThrowsException() {
        Applicant applicant = new Applicant();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            applicantService.registerApplicant(applicant);
        });

        assertNotNull(exception.getMessage());
    }
}
