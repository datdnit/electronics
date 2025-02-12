package com.hau.identity_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
}