package com.adventure.react_exception;

import com.adventure.react_exception.domain.*;
import com.adventure.react_exception.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final ExamRepository examRepo;
    private final CourseEnrollmentRepository enrollmentRepo;
    private final ExamResultRepository resultRepo;
    private final DatabaseClient databaseClient;

    public DataInitializer(StudentRepository studentRepo,
                           CourseRepository courseRepo,
                           ExamRepository examRepo,
                           CourseEnrollmentRepository enrollmentRepo,
                           ExamResultRepository resultRepo, DatabaseClient databaseClient) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.examRepo = examRepo;
        this.enrollmentRepo = enrollmentRepo;
        this.resultRepo = resultRepo;
        this.databaseClient = databaseClient;
    }

    @Override
    public void run(String... args) {

        // Clear existing data
        Mono<Void> clear = clearSequence()
                .then(resultRepo.deleteAll())
                .then(enrollmentRepo.deleteAll())
                .then(examRepo.deleteAll())
                .then(studentRepo.deleteAll())
                .then(courseRepo.deleteAll());

        // Insert sample courses
        Flux<Course> courses = Flux.just(
                new Course(null, "Java Basics", "Introduction to Java", "Mr. Hasan"),
                new Course(null, "Spring Boot", "Spring Boot development", "Ms. Ayesha"),
                new Course(null, "Database Design", "Relational DB concepts", "Mr. Karim")
        ).flatMap(courseRepo::save);

        // Insert sample students
        Flux<Student> students = Flux.just(
                new Student(null, "Zia Ahmed", "zia@example.com"),
                new Student(null, "Mina Akter", "mina@example.com"),
                new Student(null, "Rahim Uddin", "rahim@example.com"),
                new Student(null, "Shahriar Miraj", "miraj@gmail.com")
        ).flatMap(studentRepo::save);

        // Insert exams after courses are saved
        Flux<Exam> exams = courses.collectList().flatMapMany(list -> {
            return Flux.just(
                    new Exam(null, list.get(0).getId(), "Java Basics Midterm", LocalDate.of(2025, 10, 1), 100),
                    new Exam(null, list.get(0).getId(), "Java Basics Final", LocalDate.of(2025, 11, 1), 100),
                    new Exam(null, list.get(1).getId(), "Spring Boot Midterm", LocalDate.of(2025, 10, 5), 100),
                    new Exam(null, list.get(2).getId(), "Database Design Final", LocalDate.of(2025, 11, 10), 100)
            ).flatMap(examRepo::save);
        });

        // Insert enrollments after students and courses are saved
        Flux<CourseEnrollment> enrollments = Mono.zip(students.collectList(), courses.collectList())
                .flatMapMany(tuple -> {
                    var studentList = tuple.getT1();
                    var courseList = tuple.getT2();
                    return Flux.just(
                            new CourseEnrollment(null, studentList.get(0).getId(), courseList.get(0).getId()), // Zia -> Java
                            new CourseEnrollment(null, studentList.get(0).getId(), courseList.get(1).getId()), // Zia -> Spring
                            new CourseEnrollment(null, studentList.get(1).getId(), courseList.get(0).getId()), // Mina -> Java
                            new CourseEnrollment(null, studentList.get(2).getId(), courseList.get(2).getId())  // Rahim -> DB
                    ).flatMap(enrollmentRepo::save);
                });

        // Insert exam results after exams and students are ready
        Flux<ExamResult> results = Mono.zip(exams.collectList(), students.collectList())
                .flatMapMany(tuple -> {
                    var examList = tuple.getT1();
                    var studentList = tuple.getT2();
                    return Flux.just(
                            new ExamResult(null, examList.get(0).getId(), studentList.get(0).getId(), 85),
                            new ExamResult(null, examList.get(1).getId(), studentList.get(0).getId(), 90),
                            new ExamResult(null, examList.get(2).getId(), studentList.get(0).getId(), 88),
                            new ExamResult(null, examList.get(0).getId(), studentList.get(1).getId(), 78),
                            new ExamResult(null, examList.get(3).getId(), studentList.get(2).getId(), 95)
                    ).flatMap(resultRepo::save);
                });

        // Run all operations sequentially
        clear.thenMany(courses)
                .thenMany(students)
                .thenMany(exams)
                .thenMany(enrollments)
                .thenMany(results)
                .subscribe(r -> System.out.println("Sample data inserted: " + r));
    }

    public Mono<Void> clearSequence() {
        return databaseClient.sql("ALTER SEQUENCE students_id_seq RESTART WITH 1").then()
                .then(databaseClient.sql("ALTER SEQUENCE courses_id_seq RESTART WITH 1").then())
                .then(databaseClient.sql("ALTER SEQUENCE exams_id_seq RESTART WITH 1").then())
                .then(databaseClient.sql("ALTER SEQUENCE course_enrollments_id_seq RESTART WITH 1").then())
                .then(databaseClient.sql("ALTER SEQUENCE exam_results_id_seq RESTART WITH 1").then());
    }
}
