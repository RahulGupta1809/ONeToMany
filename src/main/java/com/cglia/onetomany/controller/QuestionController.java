package com.cglia.onetomany.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cglia.onetomany.entity.Question;
import com.cglia.onetomany.service.QuestionService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private static final Logger logger =
            LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public List<Question> getAllQuestions() {
        logger.info("GET /questions/all - Fetching all questions");

        List<Question> questions = questionService.getAllQuestions();

        logger.info("Fetched {} questions", questions.size());
        logger.debug("Question List: {}", questions);

        return questions;
    }

    @GetMapping("/{id}")
    public Optional<Question> getQuestionById(@PathVariable Long id) {
        logger.info("GET /questions/{} - Fetching question by id", id);

        Optional<Question> question = questionService.getQuestionById(id);

        if (question.isPresent()) {
            logger.info("Question found for id {}", id);
            logger.debug("Question details: {}", question.get());
        } else {
            logger.warn("No question found for id {}", id);
        }

        return question;
    }

    @Transactional
    @PostMapping("/save")
    public Question createQuestion(@RequestBody Question question) {
        logger.info("POST /questions/save - Creating new question");
        logger.debug("Request Body: {}", question);

        Question savedQuestion = questionService.saveQuestion(question);

        logger.info("Question created successfully with id {}", savedQuestion.getId());
        return savedQuestion;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Question>> updateQuestion(
            @PathVariable("id") Long id,
            @RequestBody Question question) {

        logger.info("PUT /questions/update/{} - Updating question", id);
        logger.debug("Updated data: {}", question);

        Optional<Question> updatedQuestion = questionService.updateQuestion(id, question);

        if (updatedQuestion.isPresent()) {
            logger.info("Question updated successfully for id {}", id);
            return ResponseEntity.ok(updatedQuestion);
        } else {
            logger.warn("Question not found for update, id {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        logger.info("DELETE /questions/delete/{} - Deleting question", id);

        questionService.deleteQuestion(id);

        logger.info("Question deleted successfully for id {}", id);
    }
}

