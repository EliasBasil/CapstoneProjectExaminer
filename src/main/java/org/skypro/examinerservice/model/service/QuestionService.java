package org.skypro.examinerservice.model.service;

import org.skypro.examinerservice.model.domain.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();

    int getStorageSize();

}
