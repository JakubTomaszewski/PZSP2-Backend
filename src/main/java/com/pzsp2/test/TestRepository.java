package com.pzsp2.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
  @Override
  List<Test> findAll();

  Test getTestByTestId(Long id);
}
