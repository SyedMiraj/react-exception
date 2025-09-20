package com.adventure.react_exception.service;

import com.adventure.react_exception.domain.ExamResult;
import com.adventure.react_exception.repository.ExamResultRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExamResultService {

    private final ExamResultRepository examResultRepository;

    public ExamResultService(ExamResultRepository examResultRepository) {
        this.examResultRepository = examResultRepository;
    }

    public Flux<ExamResult> getResultsByExam(Long examId) {
        return examResultRepository.findByExamId(examId);
    }

    public Flux<ExamResult> getResultsByStudent(Long studentId) {
        return examResultRepository.findByStudentId(studentId);
    }

    public Mono<ExamResult> getResult(Long examId, Long studentId) {
        return examResultRepository.findByExamIdAndStudentId(examId, studentId);
    }

    public Mono<ExamResult> recordResult(ExamResult result) {
        return examResultRepository.save(result);
    }

    public Mono<Void> deleteResult(Long id) {
        return examResultRepository.deleteById(id);
    }
}
