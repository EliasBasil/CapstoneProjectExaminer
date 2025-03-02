package org.skypro.examinerservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.examinerservice.model.domain.Question;
import org.skypro.examinerservice.model.service.ExaminerServiceImpl;
import org.skypro.examinerservice.model.service.JavaQuestionService;
import org.skypro.examinerservice.util.NotEnoughQuestionsInTheStorage;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTest {

    @Mock
    JavaQuestionService javaQuestionService;

    @InjectMocks
    ExaminerServiceImpl examinerService;

    @Test
    void whenStorageIsNotEmpty_thenGetQuestionsReturnsTheRightSet() {
        Mockito.when(javaQuestionService.getAll())
                .thenReturn(Set.of(new Question("Что есть Java?", "Это язык программирования"),
                        new Question("Правда ли, что Java - лучший язык?", "Это истина")));
        Mockito.when(javaQuestionService.getRandomQuestion())
                .thenReturn(new Question("Что есть Java?", "Это язык программирования"))
                .thenReturn(new Question("Правда ли, что Java - лучший язык?", "Это истина"));

        Set<Question> testSet = new HashSet<>();
        Question testQuestion = new Question("Что есть Java?", "Это язык программирования");
        Question secondTestQuestion = new Question("Правда ли, что Java - лучший язык?", "Это истина");
        testSet.add(testQuestion);
        testSet.add(secondTestQuestion);

        Set<Question> result = examinerService.getQuestions(2);
        Assertions.assertEquals(testSet, result);
    }

    @Test
    void whenAmountIsBiggerThanStorageSize_thenThrowsNotEnoughQuestionsInTheStorage() {
        Mockito.when(javaQuestionService.getAll())
                .thenReturn(Set.of(new Question("Что есть Java?", "Это язык программирования"),
                        new Question("Правда ли, что Java - лучший язык?", "Это истина")));
        Assertions.assertThrows(NotEnoughQuestionsInTheStorage.class, () -> examinerService.getQuestions(5));
    }

    @Test
    void whenAmountIsNegative_thenThrowsNotEnoughQuestionsInTheStorage() {
        Assertions.assertThrows(NotEnoughQuestionsInTheStorage.class, () -> examinerService.getQuestions(-1));
    }
}
