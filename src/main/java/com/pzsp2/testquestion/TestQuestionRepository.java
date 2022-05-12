package com.pzsp2.testquestion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, TestQuestionPK> {
  @Override
  List<TestQuestion> findAll();
}
