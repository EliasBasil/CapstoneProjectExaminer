package org.skypro.examinerservice.util;

public class ExamControllerError {
    private final String code = "NOT_ENOUGH_QUESTIONS: ERROR 400";
    private final String message = "Not enough questions in the storage to show";

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
