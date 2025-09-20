package com.adventure.react_exception.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("exam_results")
public class ExamResult {
    @Id
    private Long id;
    private Long examId;     // FK -> exams.id
    private Long studentId;  // FK -> students.id
    private int obtainedMarks;
}
