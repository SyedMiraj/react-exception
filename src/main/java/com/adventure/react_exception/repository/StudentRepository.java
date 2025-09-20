package com.adventure.react_exception.repository;

import com.adventure.react_exception.domain.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {
    Mono<Student> findByEmail(String email);
}

