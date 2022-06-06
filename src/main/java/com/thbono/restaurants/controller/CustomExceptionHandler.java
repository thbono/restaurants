package com.thbono.restaurants.controller;

import com.thbono.restaurants.domain.service.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<Map<String, Object>> processValidationError(
      final ValidationException ex, final HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(json(ex, HttpStatus.BAD_REQUEST, request));
  }

  private Map<String, Object> json(
      final Throwable error, final HttpStatus status, final HttpServletRequest request) {
    final Map<String, Object> errorInfo = new LinkedHashMap<>();

    errorInfo.put("timestamp", LocalDateTime.now());
    errorInfo.put("status", status.value());
    errorInfo.put("error", error.getClass().getSimpleName());
    errorInfo.put("message", error.getMessage());
    errorInfo.put("path", request.getServletPath());

    return errorInfo;
  }
}
