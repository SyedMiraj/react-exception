package com.adventure.react_exception.service;

import com.adventure.react_exception.domain.CourseEnrollment;
import com.adventure.react_exception.repository.CourseEnrollmentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CourseEnrollmentService {

    private final CourseEnrollmentRepository enrollmentRepository;

    public CourseEnrollmentService(CourseEnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Flux<CourseEnrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public Flux<CourseEnrollment> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public Mono<CourseEnrollment> enrollStudent(Long studentId, Long courseId) {
        CourseEnrollment enrollment = new CourseEnrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        return enrollmentRepository.save(enrollment);
    }

    public Mono<Void> deleteEnrollment(Long id) {
        return enrollmentRepository.deleteById(id);
    }
}

