package com.adventure.react_exception.service;

import com.adventure.react_exception.domain.Student;
import com.adventure.react_exception.repository.StudentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Flux<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Mono<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Mono<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Mono<Student> createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Mono<Void> deleteStudent(Long id) {
        return studentRepository.deleteById(id);
    }
}

