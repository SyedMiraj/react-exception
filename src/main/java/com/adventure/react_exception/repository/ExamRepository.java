package com.adventure.react_exception.repository;

import com.adventure.react_exception.domain.Exam;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ExamRepository extends ReactiveCrudRepository<Exam, Long> {
    Flux<Exam> findByCourseId(Long courseId);
}

