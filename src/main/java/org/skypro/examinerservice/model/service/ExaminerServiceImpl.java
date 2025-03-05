package org.skypro.examinerservice.model.service;

import org.skypro.examinerservice.model.domain.Question;
import org.skypro.examinerservice.util.WrongQuestionAmountException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final JavaQuestionService questionService;

    public ExaminerServiceImpl(JavaQuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size() || amount < 0) {
            throw new WrongQuestionAmountException();
        } else {
            Set<Question> result = new HashSet<>();
            while (result.size() < amount) {
                result.add(questionService.getRandomQuestion());
            }
            return result;
        }
    }
}
