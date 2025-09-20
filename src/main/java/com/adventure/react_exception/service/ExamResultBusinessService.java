package com.adventure.react_exception.service;

import com.adventure.react_exception.domain.ExamResult;
import com.adventure.react_exception.repository.CourseEnrollmentRepository;
import com.adventure.react_exception.repository.ExamRepository;
import com.adventure.react_exception.repository.ExamResultRepository;
import com.adventure.react_exception.repository.StudentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExamResultBusinessService {

    private final ExamRepository examRepo;
    private final StudentRepository studentRepo;
    private final CourseEnrollmentRepository enrollmentRepo;
    private final ExamResultRepository resultRepo;

    public ExamResultBusinessService(ExamRepository examRepo,
                                     StudentRepository studentRepo,
                                     CourseEnrollmentRepository enrollmentRepo,
                                     ExamResultRepository resultRepo) {
        this.examRepo = examRepo;
        this.studentRepo = studentRepo;
        this.enrollmentRepo = enrollmentRepo;
        this.resultRepo = resultRepo;
    }

    public Mono<ExamResult> recordResult(Long examId, Long studentId, int obtainedMarks) {
        return examRepo.findById(examId)
                .switchIfEmpty(Mono.error(new RuntimeException("Exam not found")))
                .flatMap(exam -> {
                    if (obtainedMarks > exam.getTotalMarks()) {
                        return Mono.error(new RuntimeException("Obtained marks cannot exceed total marks"));
                    }
                    return enrollmentRepo.findByStudentIdAndCourseId(studentId, exam.getCourseId())
                            .switchIfEmpty(Mono.error(new RuntimeException("Student not enrolled in this course")))
                            .then(Mono.defer(() -> {
                                ExamResult result = new ExamResult();
                                result.setExamId(examId);
                                result.setStudentId(studentId);
                                result.setObtainedMarks(obtainedMarks);
                                return resultRepo.save(result);
                            }));
                });
    }
}

