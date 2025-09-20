-- ==============================
-- Course & Exam Management Schema
-- ==============================

-- Drop tables if exist (for demo purposes)
DROP TABLE IF EXISTS exam_results;
DROP TABLE IF EXISTS course_enrollments;
DROP TABLE IF EXISTS exams;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS courses;

-- ==============================
-- Courses Table
-- ==============================
CREATE TABLE courses
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    description  TEXT,
    teacher_name VARCHAR(100) NOT NULL
);

-- ==============================
-- Students Table
-- ==============================
CREATE TABLE students
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(100)        NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- ==============================
-- Exams Table
-- ==============================
CREATE TABLE exams
(
    id          SERIAL PRIMARY KEY,
    course_id   INT          NOT NULL,
    title       VARCHAR(100) NOT NULL,
    exam_date   TIMESTAMP    NOT NULL,
    total_marks INT          NOT NULL,
    CONSTRAINT fk_exam_course FOREIGN KEY (course_id)
        REFERENCES courses (id) ON DELETE CASCADE
);

-- ==============================
-- Course Enrollments Table
-- ==============================
CREATE TABLE course_enrollments
(
    id         SERIAL PRIMARY KEY,
    student_id INT NOT NULL,
    course_id  INT NOT NULL,
    CONSTRAINT fk_enrollment_student FOREIGN KEY (student_id)
        REFERENCES students (id) ON DELETE CASCADE,
    CONSTRAINT fk_enrollment_course FOREIGN KEY (course_id)
        REFERENCES courses (id) ON DELETE CASCADE,
    CONSTRAINT uq_enrollment UNIQUE (student_id, course_id)
);

-- ==============================
-- Exam Results Table
-- ==============================
CREATE TABLE exam_results
(
    id             SERIAL PRIMARY KEY,
    exam_id        INT NOT NULL,
    student_id     INT NOT NULL,
    obtained_marks INT NOT NULL,
    CONSTRAINT fk_result_exam FOREIGN KEY (exam_id)
        REFERENCES exams (id) ON DELETE CASCADE,
    CONSTRAINT fk_result_student FOREIGN KEY (student_id)
        REFERENCES students (id) ON DELETE CASCADE,
    CONSTRAINT uq_result UNIQUE (exam_id, student_id)
);
