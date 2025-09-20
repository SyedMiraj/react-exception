package com.adventure.react_exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String code;    // e.g., "STUDENT_NOT_FOUND"
    private String message; // user-friendly message
}

