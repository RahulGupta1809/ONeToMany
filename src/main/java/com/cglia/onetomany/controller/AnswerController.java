package com.cglia.onetomany.controller;

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

	@Autowired
	private AnswerService answerService;

	@GetMapping("/all")
	public List<Answer> getAllAnswers() {
		return answerService.getAllAnswers();
	}

	@GetMapping("/{id}")
	public Optional<Answer> getAnswerById(@PathVariable Long id) {
		return answerService.getAnswerById(id);
	}

	@PostMapping("/save")
	public Answer createAnswer(@RequestBody Answer answer) {
		return answerService.saveAnswer(answer);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Optional<Answer>> updateAnswer(@PathVariable("id") Long id, @RequestBody Answer answer) {
		Optional<Answer> updatedAnswer = answerService.updateAnswer(id, answer);
		if (updatedAnswer != null) {
			return ResponseEntity.ok(updatedAnswer);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public void deleteAnswer(@PathVariable Long id) {
		answerService.deleteAnswer(id);
	}
}
