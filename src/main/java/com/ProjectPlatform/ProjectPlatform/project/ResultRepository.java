package com.ProjectPlatform.ProjectPlatform.project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
//    List<Result> getAllByProjectsId(Long id);
}
