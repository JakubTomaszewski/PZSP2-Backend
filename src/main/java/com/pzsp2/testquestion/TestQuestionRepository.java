package com.pzsp2.testquestion;

import com.pzsp2.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, TestQuestionPK> {
    @Override
    List<TestQuestion> findAll();
}
