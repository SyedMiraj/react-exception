# Course & Exam Management Demo

## Overview
The **Course & Exam Management Demo** is a sample project built with **Spring Boot (Reactive with R2DBC)** and **React**.  
It demonstrates how to manage **courses, exams, students, enrollments, and results** in a modern, reactive web application.  
The backend uses **PostgreSQL running on Docker** as the database.

---

## Business Description
Educational institutions, training centers, and online learning platforms need a streamlined way to **manage courses and exams**.  
This demo system provides the following capabilities:

1. **Course Management**
    - Admin/Teacher can create, update, and view courses.
    - Each course contains details such as course name, description, and assigned teacher.

2. **Exam Management**
    - Exams are linked to courses.
    - Admin/Teacher can create exams with titles, dates, and total marks.

3. **Student Management**
    - Students can register in the system with personal details.
    - Each student can enroll in one or multiple courses.

4. **Course Enrollment**
    - Students enroll in specific courses.
    - Enrollment data helps track which student belongs to which course.

5. **Exam Results**
    - Results are stored for each exam taken by students.
    - Admin/Teacher can view and manage student performance.

---

## Entities & Relationships
- **Course**
    - Represents a course offered by the institution.
- **Exam**
    - Belongs to a course and defines assessments.
- **Student**
    - Represents a learner in the system.
- **Course Enrollment**
    - Connects students with courses.
- **Exam Result**
    - Tracks marks obtained by students in specific exams.

### Relationships
- One **Course** → Many **Exams**
- One **Student** → Many **Courses** (via Enrollment)
- One **Student** → Many **Exams** (via Results)

---

## Key Benefits
- Centralized management of **courses, exams, and results**.
- Transparent tracking of **student performance**.
- Scalable foundation for **e-learning or academic platforms**.
- Built on modern stack (**Spring Reactive + R2DBC + React**).

---
