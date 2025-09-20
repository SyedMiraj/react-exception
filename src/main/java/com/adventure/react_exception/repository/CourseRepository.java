package com.adventure.react_exception.repository;

import com.adventure.react_exception.domain.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CourseRepository extends ReactiveCrudRepository<Course, Long> {
    Flux<Course> findByTeacherName(String teacherName);
}

