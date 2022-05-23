package com.pzsp2.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    @Override
    Optional<Test> findById(Long id);

    Optional<Test> findByPassword(String link);

    List<Password> findAllBy();
}
