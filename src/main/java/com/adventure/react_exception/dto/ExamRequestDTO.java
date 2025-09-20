package com.adventure.react_exception.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExamRequestDTO {

    @NotBlank(message = "Exam name is required")
    private String examName;

    @Future(message = "Exam date must be in the future")
    private LocalDate examDate;

    @Min(value = 1, message = "Total marks must be at least 1")
    private int totalMarks;
}
