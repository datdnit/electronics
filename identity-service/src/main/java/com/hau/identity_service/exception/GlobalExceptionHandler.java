package com.hau.identity_service.exception;

import com.hau.identity_service.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Objects>> runtimeExceptionHandler() {
        ApiResponse<Objects> apiResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCode.UNCATEGORIZED.getMessage(), null);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<Objects>> handlingAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<Objects> apiResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), errorCode.getMessage(),null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Objects>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        ErrorCode errorCode = getErrorCodeFromMessage(fieldError != null ? fieldError.getDefaultMessage() : "UNCATEGORIZED");
        ApiResponse<Objects> apiResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), errorCode.getMessage(), null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorCode getErrorCodeFromMessage(String message) {
        try {
            return ErrorCode.valueOf(message);
        } catch (IllegalArgumentException e) {
            return ErrorCode.UNCATEGORIZED;
        }
    }
}