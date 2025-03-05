package org.skypro.examinerservice.model.service;

import org.skypro.examinerservice.model.domain.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
