package org.skypro.examinerservice.util;

public class WrongQuestionAmountException extends RuntimeException {
    public WrongQuestionAmountException() {
        super("The number of requested questions is wrong");
    }
}
