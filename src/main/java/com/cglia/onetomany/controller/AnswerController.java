package com.cglia.onetomany.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cglia.onetomany.entity.Answer;
import com.cglia.onetomany.service.AnswerService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private static final Logger logger =
            LoggerFactory.getLogger(AnswerController.class);

    @Autowired
    private AnswerService answerService;

    @GetMapping("/all")
    public List<Answer> getAllAnswers() {
        logger.info("GET /answers/all - Fetching all answers");

        List<Answer> answers = answerService.getAllAnswers();

        logger.info("Fetched {} answers", answers.size());
        logger.debug("Answer List: {}", answers);

        return answers;
    }

    @GetMapping("/{id}")
    public Optional<Answer> getAnswerById(@PathVariable Long id) {
        logger.info("GET /answers/{} - Fetching answer by id", id);

        Optional<Answer> answer = answerService.getAnswerById(id);

        if (answer.isPresent()) {
            logger.info("Answer found for id {}", id);
            logger.debug("Answer details: {}", answer.get());
        } else {
            logger.warn("No answer found for id {}", id);
        }

        return answer;
    }

    @PostMapping("/save")
    public Answer createAnswer(@RequestBody Answer answer) {
        logger.info("POST /answers/save - Creating new answer");
        logger.debug("Request Body: {}", answer);

        Answer savedAnswer = answerService.saveAnswer(answer);

        logger.info("Answer created successfully with id {}", savedAnswer.getId());
        return savedAnswer;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Answer>> updateAnswer(
            @PathVariable("id") Long id,
            @RequestBody Answer answer) {

        logger.info("PUT /answers/update/{} - Updating answer", id);
        logger.debug("Updated data: {}", answer);

        Optional<Answer> updatedAnswer = answerService.updateAnswer(id, answer);

        if (updatedAnswer.isPresent()) {
            logger.info("Answer updated successfully for id {}", id);
            return ResponseEntity.ok(updatedAnswer);
        } else {
            logger.warn("Answer not found for update, id {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        logger.info("DELETE /answers/delete/{} - Deleting answer", id);

        answerService.deleteAnswer(id);

        logger.info("Answer deleted successfully for id {}", id);
    }
}
