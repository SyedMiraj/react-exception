package com.adventure.react_exception.repository;

import com.adventure.react_exception.domain.ExamResult;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExamResultRepository extends ReactiveCrudRepository<ExamResult, Long> {
    Flux<ExamResult> findByExamId(Long examId);
    Flux<ExamResult> findByStudentId(Long studentId);
    Mono<ExamResult> findByExamIdAndStudentId(Long examId, Long studentId);
}

