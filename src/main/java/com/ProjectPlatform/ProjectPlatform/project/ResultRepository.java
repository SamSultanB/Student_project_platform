package com.ProjectPlatform.ProjectPlatform.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> getAllByProjectId(Long id);
    List<Result> getAllByUserId(Long id);
    Result getByUserIdAndProjectId(Long userId, Long projectId);
}
