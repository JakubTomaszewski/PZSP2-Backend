package com.pzsp2.solution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
    @Override
    List<Solution> findAll();

    List<Solution> getAllByTestId(Long testId);

    List<Solution> getAllByTestIdAndUserId(Long testId, Long userId);
}
