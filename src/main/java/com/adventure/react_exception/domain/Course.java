package com.adventure.react_exception.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("courses")
public class Course {
    @Id
    private Long id;
    private String name;
    private String description;
    private String teacherName;
}