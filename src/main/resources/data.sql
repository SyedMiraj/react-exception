-- ==============================
-- Clear existing data
-- ==============================
TRUNCATE TABLE exam_results CASCADE;
TRUNCATE TABLE course_enrollments CASCADE;
TRUNCATE TABLE exams CASCADE;
TRUNCATE TABLE students CASCADE;
TRUNCATE TABLE courses CASCADE;

-- ==============================
-- Insert sample courses
-- ==============================
INSERT INTO courses (id, name, description, teacher_name)
VALUES (1, 'Java Basics', 'Introduction to Java', 'Mr. Hasan'),
       (2, 'Spring Boot', 'Spring Boot development', 'Ms. Ayesha'),
       (3, 'Database Design', 'Relational DB concepts', 'Mr. Karim');

-- ==============================
-- Insert sample students
-- ==============================
INSERT INTO students (id, name, email)
VALUES (1, 'Zia Ahmed', 'zia@example.com'),
       (2, 'Mina Akter', 'mina@example.com'),
       (3, 'Rahim Uddin', 'rahim@example.com');

-- ==============================
-- Insert sample exams (course_id must exist)
-- ==============================
INSERT INTO exams (id, course_id, title, exam_date, total_marks)
VALUES (1, 1, 'Java Basics Midterm', '2025-10-01 10:00:00', 100),
       (2, 1, 'Java Basics Final', '2025-11-01 10:00:00', 100),
       (3, 2, 'Spring Boot Midterm', '2025-10-05 10:00:00', 100),
       (4, 3, 'Database Design Final', '2025-11-10 10:00:00', 100);

-- ==============================
-- Insert enrollments (student_id and course_id must exist)
-- ==============================
INSERT INTO course_enrollments (id, student_id, course_id)
VALUES (1, 1, 1), -- Zia -> Java
       (2, 1, 2), -- Zia -> Spring
       (3, 2, 1), -- Mina -> Java
       (4, 3, 3);
-- Rahim -> DB

-- ==============================
-- Insert exam results (exam_id and student_id must exist)
-- ==============================
INSERT INTO exam_results (id, exam_id, student_id, obtained_marks)
VALUES (1, 1, 1, 85), -- Zia Java Midterm
       (2, 2, 1, 90), -- Zia Java Final
       (3, 3, 1, 88), -- Zia Spring Midterm
       (4, 1, 2, 78), -- Mina Java Midterm
       (5, 4, 3, 95); -- Rahim DB Final
