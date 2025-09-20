package com.adventure.react_exception.service;

import com.adventure.react_exception.repository.CourseEnrollmentRepository;
import com.adventure.react_exception.repository.CourseRepository;
import com.adventure.react_exception.repository.ExamRepository;
import com.adventure.react_exception.repository.ExamResultRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReportCardService {

    private final CourseEnrollmentRepository enrollmentRepo;
    private final CourseRepository courseRepo;
    private final ExamRepository examRepo;
    private final ExamResultRepository resultRepo;

    public ReportCardService(CourseEnrollmentRepository enrollmentRepo,
                             CourseRepository courseRepo,
                             ExamRepository examRepo,
                             ExamResultRepository resultRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.courseRepo = courseRepo;
        this.examRepo = examRepo;
        this.resultRepo = resultRepo;
    }

    public Flux<String> getReportCard(Long studentId) {
        return enrollmentRepo.findByStudentId(studentId)
                .flatMap(enrollment -> courseRepo.findById(enrollment.getCourseId()))
                .flatMap(course -> examRepo.findByCourseId(course.getId())
                        .flatMap(exam -> resultRepo.findByExamIdAndStudentId(exam.getId(), studentId)
                                .map(result -> String.format(
                                        "Course: %s | Exam: %s | Marks: %d/%d",
                                        course.getName(),
                                        exam.getTitle(),
                                        result.getObtainedMarks(),
                                        exam.getTotalMarks()
                                ))
                        ));
    }
}

