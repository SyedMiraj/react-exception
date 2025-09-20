package com.adventure.react_exception.controller;

import com.adventure.react_exception.domain.Exam;
import com.adventure.react_exception.domain.Student;
import com.adventure.react_exception.dto.ExamRequestDTO;
import com.adventure.react_exception.dto.ExamResultRequestDTO;
import com.adventure.react_exception.dto.StudentRequestDTO;
import com.adventure.react_exception.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Validated
public class StudentController {

    private final EnrollmentBusinessService enrollmentService;
    private final ExamBusinessService examService;
    private final ExamResultBusinessService resultService;
    private final ReportCardService reportCardService;
    private final StudentService studentService;

    @PostMapping
    public Mono<ResponseEntity<Student>> createStudent(@Valid @RequestBody StudentRequestDTO request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        return studentService.createStudent(student)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

    // ✅ Enroll student in course
    @PostMapping("/{studentId}/enroll/{courseId}")
    public Mono<ResponseEntity<String>> enrollStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        return enrollmentService.enrollStudent(studentId, courseId)
                .map(enrollment -> ResponseEntity.ok("Student enrolled successfully!"));
    }

    // ✅ Schedule exam for a course
    @PostMapping("/courses/{courseId}/exams")
    public Mono<ResponseEntity<String>> scheduleExam(
            @PathVariable Long courseId,
            @Valid @RequestBody ExamRequestDTO request) {
        Exam exam = new Exam();
        exam.setCourseId(courseId);
        exam.setTitle(request.getExamName());
        exam.setExamDate(request.getExamDate());
        exam.setTotalMarks(request.getTotalMarks());

        return examService.scheduleExam(exam)
                .map(e -> ResponseEntity.ok("Exam scheduled successfully!"));
    }

    // ✅ Record exam result
    @PostMapping("/{studentId}/courses/{courseId}/exams/{examId}/result")
    public Mono<ResponseEntity<String>> recordExamResult(
            @PathVariable Long studentId,
            @PathVariable Long courseId,
            @PathVariable Long examId,
            @Valid @RequestBody ExamResultRequestDTO request) {

        return resultService.recordResult(examId, studentId, request.getObtainedMarks())
                .map(r -> ResponseEntity.ok("Exam result recorded successfully!"));
    }

    // ✅ Get student report card
    @GetMapping("/{studentId}/report")
    public Flux<String> getReportCard(@PathVariable Long studentId) {
        return reportCardService.getReportCard(studentId);
    }
}
