package com.pzsp2.test.solution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
    @Override
    List<Solution> findAll();

    List<Solution> getAllByIdTestId(Long testId);

    List<Solution> findAllByIdIn(List<SolutionPK> ids);

    List<Solution> getAllByIdTestIdAndIdUserId(Long testId, Long userId);
}
