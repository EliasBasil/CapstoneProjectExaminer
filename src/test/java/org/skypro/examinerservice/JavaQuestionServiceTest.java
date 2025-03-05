package org.skypro.examinerservice;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.examinerservice.model.domain.Question;
import org.skypro.examinerservice.model.service.JavaQuestionService;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @Mock
    Random random;

    @InjectMocks
    JavaQuestionService javaQuestionService;

    @BeforeEach
    public void setUp(){
        ReflectionTestUtils.setField(javaQuestionService, "random", random);
    }

    @Test
    void whenAddQuestionToEmptyStorage_StorageContainsThisQuestion() {
        Set<Question> testSet = new HashSet<>();
        Question testQuestion = new Question("Что есть Java?", "Это язык программирования");
        testSet.add(testQuestion);
        javaQuestionService.add("Что есть Java?", "Это язык программирования");
        Assertions.assertEquals(javaQuestionService.getAll(), testSet);
    }

    @Test
    void whenAddQuestionToNonEmptyStorage_StorageContainsPreviousAndNewQuestions() {
        Set<Question> testSet = new HashSet<>();
        Question testQuestion = new Question("Что есть Java?", "Это язык программирования");
        Question secondTestQuestion = new Question("Правда ли, что Java - лучший язык?", "Это истина");
        testSet.add(testQuestion);
        testSet.add(secondTestQuestion);
        javaQuestionService.add("Что есть Java?", "Это язык программирования");
        javaQuestionService.add("Правда ли, что Java - лучший язык?", "Это истина");
        Assertions.assertEquals(javaQuestionService.getAll(), testSet);
    }

    @Test
    void whenAddAlreadyExistingQuestion_NothingHappens() {
        Set<Question> testSet = new HashSet<>();
        Question testQuestion = new Question("Что есть Java?", "Это язык программирования");
        testSet.add(testQuestion);
        javaQuestionService.add("Что есть Java?", "Это язык программирования");
        javaQuestionService.add("Что есть Java?", "Это язык программирования");
        Assertions.assertEquals(javaQuestionService.getAll(), testSet);
        Assertions.assertEquals(javaQuestionService.getStorageSize(), 1);
    }

    @Test
    void whenRemoveFromEmptyStorage_NothingHappens() {
        Question question = javaQuestionService.remove(new Question("Что есть Java?", "Это язык программирования"));
        Assertions.assertNull(question);
        Assertions.assertTrue(javaQuestionService.getAll().isEmpty());
    }

    @Test
    void whenRemoveExistingQuestion_QuestionIsRemoved() {
        javaQuestionService.add("Что есть Java?", "Это язык программирования");
        javaQuestionService.remove(new Question("Что есть Java?", "Это язык программирования"));
        Assertions.assertTrue(javaQuestionService.getAll().isEmpty());
    }

    @Test
    void whenStorageIsEmpty_getAllIsEmpty() {
        Assertions.assertTrue(javaQuestionService.getAll().isEmpty());
    }

    @Test
    void whenStorageIsNotEmpty_getAllReturnsAllQuestionsFromStorage() {
        Set<Question> testSet = new HashSet<>();
        Question firstTestQuestion = new Question("Что есть Java?", "Это язык программирования");
        Question secondTestQuestion = new Question("Правда ли, что Java - лучший язык?", "Это истина");
        Question thirdTestQuestion = new Question("Как расшифровывается int?", "Integer");
        testSet.add(firstTestQuestion);
        testSet.add(secondTestQuestion);
        testSet.add(thirdTestQuestion);
        javaQuestionService.add("Что есть Java?", "Это язык программирования");
        javaQuestionService.add("Правда ли, что Java - лучший язык?", "Это истина");
        javaQuestionService.add("Как расшифровывается int?", "Integer");
        Assertions.assertEquals(javaQuestionService.getAll(), testSet);
    }

    @Test
    void whenRandomReturnsOne_thenGetRandomQuestionReturnsZerothQuestion() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);
        javaQuestionService.add("Что есть Java?", "Это язык программирования");
        javaQuestionService.add("Правда ли, что Java - лучший язык?", "Это истина");
        javaQuestionService.add("Как расшифровывается int?", "Integer");
        Question result = javaQuestionService.getRandomQuestion();
        Assertions.assertEquals(new Question("Правда ли, что Java - лучший язык?", "Это истина"), result);
    }

    @Test
    void whenRandomReturnsOne_thenGetRandomQuestionReturnsFirstQuestion() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(1);
        javaQuestionService.add("Что есть Java?", "Это язык программирования");
        javaQuestionService.add("Правда ли, что Java - лучший язык?", "Это истина");
        javaQuestionService.add("Как расшифровывается int?", "Integer");
        Question result = javaQuestionService.getRandomQuestion();
        Assertions.assertEquals(new Question("Что есть Java?", "Это язык программирования"), result);
    }

    @Test
    void whenRandomReturnsOne_thenGetRandomQuestionReturnsSecondQuestion() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(2);
        javaQuestionService.add("Что есть Java?", "Это язык программирования");
        javaQuestionService.add("Правда ли, что Java - лучший язык?", "Это истина");
        javaQuestionService.add("Как расшифровывается int?", "Integer");
        Question result = javaQuestionService.getRandomQuestion();
        Assertions.assertEquals(new Question("Как расшифровывается int?", "Integer"), result);
    }
}
