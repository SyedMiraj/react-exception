package com.adventure.react_exception.service;

import com.adventure.react_exception.domain.Exam;
import com.adventure.react_exception.repository.ExamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Flux<Exam> getExamsByCourse(Long courseId) {
        return examRepository.findByCourseId(courseId);
    }

    public Mono<Exam> getExamById(Long id) {
        return examRepository.findById(id);
    }

    public Mono<Exam> createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Mono<Void> deleteExam(Long id) {
        return examRepository.deleteById(id);
    }
}
