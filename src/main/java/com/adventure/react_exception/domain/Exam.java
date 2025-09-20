package com.adventure.react_exception.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("exams")
public class Exam {
    @Id
    private Long id;
    private Long courseId;   // FK -> courses.id
    private String title;
    private LocalDate examDate; // could be LocalDateTime if supported
    private int totalMarks;
}
