USE job_application_tracking;

INSERT INTO employers (name, email, company_name)
VALUES ('John Employer', 'employer@email.com', 'Tech Solutions Ltd');

INSERT INTO applicants (name, email, cv, skills, experience)
VALUES ('Alice Applicant', 'alice@email.com', 'Experienced developer', 'Java, SQL', 3);

INSERT INTO jobs (title, description, location, employer_id)
VALUES ('Junior Java Developer', 'Entry level Java role', 'Kathmandu', 1);

INSERT INTO applications (applicant_id, job_id, application_date, status)
VALUES (1, 1, CURDATE(), 'APPLIED');
