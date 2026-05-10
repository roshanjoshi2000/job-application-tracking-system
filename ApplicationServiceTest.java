package com.jats.test;

import com.jats.model.*;
import com.jats.service.ApplicationService;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationServiceTest {

    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        applicationService = new ApplicationService();
    }

    @Test
    void testApplyForJob() throws SQLException {
        Applicant applicant = new Applicant();
        applicant.setId(1);

        Job job = new Job();
        job.setJobId(1);

        Application application = new Application();
        application.setApplicant(applicant);
        application.setJob(job);
        application.setApplicationDate(LocalDate.now());

        applicationService.applyForJob(application);

        List<Application> applications = applicationService.getAllApplications();
        assertFalse(applications.isEmpty());
        assertEquals(ApplicationStatus.APPLIED, applications.get(0).getStatus());
    }
}
