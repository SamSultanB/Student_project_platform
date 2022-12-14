package com.ProjectPlatform.ProjectPlatform.project;

import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface ResultService {
    List<Result> getAllByProjectId(Long id);
    List<Result> getAllByUserId(Long id);
    Result getByUserIdAndProjectId(Long userId, Long projectId);
    Result getById(Long id);
}
