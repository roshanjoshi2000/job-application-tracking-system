CREATE DATABASE IF NOT EXISTS job_application_tracking;
USE job_application_tracking;

CREATE TABLE employers (
    employer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    company_name VARCHAR(100) NOT NULL
);

CREATE TABLE applicants (
    applicant_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    cv TEXT,
    skills VARCHAR(255),
    experience INT
);

CREATE TABLE jobs (
    job_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    location VARCHAR(100),
    employer_id INT,
    FOREIGN KEY (employer_id) REFERENCES employers(employer_id)
);

CREATE TABLE applications (
    application_id INT AUTO_INCREMENT PRIMARY KEY,
    applicant_id INT NOT NULL,
    job_id INT NOT NULL,
    application_date DATE,
    status VARCHAR(20),
    FOREIGN KEY (applicant_id) REFERENCES applicants(applicant_id),
    FOREIGN KEY (job_id) REFERENCES jobs(job_id)
);
