package com.cglia.onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cglia.onetomany.entity.Question;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
