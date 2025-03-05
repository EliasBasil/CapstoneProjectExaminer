package org.skypro.examinerservice.util;

public class NotEnoughQuestionsInTheStorage extends RuntimeException {
    public NotEnoughQuestionsInTheStorage() {
        super("Not enough questions in the storage to show");
    }
}
