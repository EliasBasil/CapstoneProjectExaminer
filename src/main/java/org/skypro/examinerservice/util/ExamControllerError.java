package org.skypro.examinerservice.util;

public class ExamControllerError {
    private final String code = "Wrong Question Amount: ERROR 400";
    private final String message = "The number of requested questions is wrong";

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
