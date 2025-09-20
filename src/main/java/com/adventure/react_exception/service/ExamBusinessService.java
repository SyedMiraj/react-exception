package com.adventure.react_exception.service;

import com.adventure.react_exception.domain.Exam;
import com.adventure.react_exception.repository.CourseRepository;
import com.adventure.react_exception.repository.ExamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExamBusinessService {

    private final ExamRepository examRepo;
    private final CourseRepository courseRepo;

    public ExamBusinessService(ExamRepository examRepo, CourseRepository courseRepo) {
        this.examRepo = examRepo;
        this.courseRepo = courseRepo;
    }

    public Mono<Exam> scheduleExam(Exam exam) {
        return courseRepo.findById(exam.getCourseId())
                .switchIfEmpty(Mono.error(new RuntimeException("Course not found")))
                .then(examRepo.findByCourseId(exam.getCourseId())
                        .filter(existing -> existing.getExamDate().equals(exam.getExamDate()))
                        .hasElements()
                        .flatMap(conflict -> conflict
                                ? Mono.error(new RuntimeException("Exam already scheduled on this date"))
                                : examRepo.save(exam)));
    }
}