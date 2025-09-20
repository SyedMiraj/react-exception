package com.adventure.react_exception.service;

import com.adventure.react_exception.domain.CourseEnrollment;
import com.adventure.react_exception.repository.CourseEnrollmentRepository;
import com.adventure.react_exception.repository.CourseRepository;
import com.adventure.react_exception.repository.StudentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EnrollmentBusinessService {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final CourseEnrollmentRepository enrollmentRepo;

    public EnrollmentBusinessService(StudentRepository studentRepo,
                                     CourseRepository courseRepo,
                                     CourseEnrollmentRepository enrollmentRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    public Mono<CourseEnrollment> enrollStudent(Long studentId, Long courseId) {
        return studentRepo.findById(studentId)
                .switchIfEmpty(Mono.error(new RuntimeException("Student not found")))
                .then(courseRepo.findById(courseId)
                        .switchIfEmpty(Mono.error(new RuntimeException("Course not found"))))
                .then(enrollmentRepo.findByStudentIdAndCourseId(studentId, courseId)
                        .flatMap(existing -> Mono.error(new RuntimeException("Already enrolled"))))
                .then(Mono.defer(() -> {
                    CourseEnrollment enrollment = new CourseEnrollment();
                    enrollment.setStudentId(studentId);
                    enrollment.setCourseId(courseId);
                    return enrollmentRepo.save(enrollment);
                }));
    }
}
