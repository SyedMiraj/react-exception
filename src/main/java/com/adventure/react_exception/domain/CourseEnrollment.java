package com.adventure.react_exception.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("course_enrollments")
public class CourseEnrollment {
    @Id
    private Long id;
    private Long studentId;  // FK -> students.id
    private Long courseId;   // FK -> courses.id
}
