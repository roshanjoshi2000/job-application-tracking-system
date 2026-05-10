package com.jats.test;

import com.jats.model.Employer;
import com.jats.model.Job;
import com.jats.service.JobService;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobServiceTest {

    private JobService jobService;

    @BeforeEach
    void setUp() {
        jobService = new JobService();
    }

    @Test
    void testPostAndRetrieveJob() throws SQLException {
        Employer employer = new Employer();
        employer.setId(1);

        Job job = new Job();
        job.setTitle("Software Engineer");
        job.setDescription("Java developer role");
        job.setLocation("Kathmandu");
        job.setEmployer(employer);

        jobService.postJob(job);

        List<Job> jobs = jobService.getAllJobs();
        assertFalse(jobs.isEmpty());
    }
}
