package com.cglia.onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cglia.onetomany.entity.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
//Main Class
}


