package com.adventure.react_exception.service;

import com.adventure.react_exception.domain.Course;
import com.adventure.react_exception.repository.CourseRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Flux<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Mono<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Flux<Course> getCoursesByTeacher(String teacherName) {
        return courseRepository.findByTeacherName(teacherName);
    }

    public Mono<Course> createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Mono<Void> deleteCourse(Long id) {
        return courseRepository.deleteById(id);
    }
}
