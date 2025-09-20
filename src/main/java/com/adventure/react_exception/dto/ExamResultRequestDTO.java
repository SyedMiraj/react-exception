package com.adventure.react_exception.dto;

import com.adventure.react_exception.error.annotation.ValidMarks;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ExamResultRequestDTO {

    @Min(value = 0, message = "Obtained marks must be >= 0")
    private int obtainedMarks;

    @ValidMarks
    private int totalMarks;
}
