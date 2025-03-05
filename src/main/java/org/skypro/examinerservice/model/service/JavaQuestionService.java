package org.skypro.examinerservice.model.service;

import org.skypro.examinerservice.model.domain.Question;
import org.skypro.examinerservice.util.NotEnoughQuestionsInTheStorage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<Question>();
    private final Random random;

    public JavaQuestionService() {
        random = new Random();
    }

    public JavaQuestionService(Random random) {
        this.random = random;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new NotEnoughQuestionsInTheStorage();
        } else {
            int rand = random.nextInt(questions.size());
            return new ArrayList<>(questions).get(rand);
        }
    }

    @Override
    public int getStorageSize() {
        return questions.size();
    }
}
