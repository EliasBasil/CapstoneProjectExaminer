package org.skypro.examinerservice.controller;

import org.skypro.examinerservice.util.ExamControllerError;
import org.skypro.examinerservice.util.WrongQuestionAmountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExamControllerAdvice {
    @ExceptionHandler(WrongQuestionAmountException.class)
    public ResponseEntity<ExamControllerError> handleWrongQuestionAmountException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExamControllerError());
    }
}
