package com.cglia.onetomany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cglia.onetomany.entity.Question;
import com.cglia.onetomany.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

	@Autowired
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	public List<Question> getAllQuestions() {
		return questionRepository.findAll();
	}

	public Optional<Question> getQuestionById(Long id) {
		return questionRepository.findById(id);
	}

	public Question saveQuestion(Question question) {
		return questionRepository.save(question);
	}

	public Optional<Question> updateQuestion(Long id, Question question) {
		Optional<Question> optionalQuestion = questionRepository.findById(id);
		if (optionalQuestion.isPresent()) {
			question.setId(id);
			return Optional.of(questionRepository.save(question));
		} else {
			return Optional.empty();
		}
	}

	public void deleteQuestion(Long id) {
		questionRepository.deleteById(id);
	}
}
