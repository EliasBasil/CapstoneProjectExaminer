package org.skypro.examinerservice.controller;

import org.skypro.examinerservice.util.ExamControllerError;
import org.skypro.examinerservice.util.NotEnoughQuestionsInTheStorage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExamControllerAdvice {
    @ExceptionHandler(NotEnoughQuestionsInTheStorage.class)
    public ResponseEntity<ExamControllerError> handleNotEnoughQuestionsInTheStorageException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExamControllerError());
    }
}
