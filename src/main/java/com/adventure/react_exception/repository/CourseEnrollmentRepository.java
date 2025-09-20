package com.adventure.react_exception.repository;

import com.adventure.react_exception.domain.CourseEnrollment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseEnrollmentRepository extends ReactiveCrudRepository<CourseEnrollment, Long> {
    Flux<CourseEnrollment> findByStudentId(Long studentId);
    Flux<CourseEnrollment> findByCourseId(Long courseId);
    Mono<CourseEnrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);
}

