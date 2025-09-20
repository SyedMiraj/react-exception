# 📘 Business Logic Cases for Course & Exam Demo

## 1. Enroll a Student in a Course
A student can be enrolled in a course **only if**:
- ✅ The **student exists** in the system.
- ✅ The **course exists** in the system.
- ✅ The **student is not already enrolled** in that course.

---

## 2. Schedule an Exam for a Course
An exam can be scheduled **only if**:
- ✅ The **course exists** in the system.
- ✅ The **exam date does not clash** with another exam in the same course.

---

## 3. Record an Exam Result
An exam result can be recorded **only if**:
- ✅ The **student is enrolled** in the course.
- ✅ The **exam belongs** to that course.
- ✅ The **obtained marks** are less than or equal to the **total marks**.

---

## 4. Get Student Report Card
The student’s report card should:
- 📌 Fetch all **courses enrolled** by the student.
- 📌 Fetch all **exams** under each course.
- 📌 Fetch **marks obtained** in each exam.
- 📌 Calculate **percentage/grade** for performance reporting.  
