package org.skypro.examinerservice.controller;

import org.skypro.examinerservice.model.domain.Question;
import org.skypro.examinerservice.model.service.JavaQuestionService;
import org.skypro.examinerservice.model.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        Question newQuestion = new Question(question, answer);
        javaQuestionService.add(newQuestion);
        return newQuestion;
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        Question newQuestion = new Question(question, answer);
        javaQuestionService.remove(newQuestion);
        return newQuestion;
    }

    @GetMapping("")
    public Collection<Question> getQuestions() {
        return javaQuestionService.getAll();
    }
}
