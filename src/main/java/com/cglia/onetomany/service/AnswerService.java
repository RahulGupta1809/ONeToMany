package com.cglia.onetomany.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cglia.onetomany.entity.Answer;
import com.cglia.onetomany.repository.AnswerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Optional<Answer> getAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Optional<Answer> updateAnswer(Long id, Answer answer) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()) {
            answer.setId(id);
            return Optional.of(answerRepository.save(answer));
        } else {
            return Optional.empty();
        }
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
}
