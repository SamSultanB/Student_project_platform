package com.ProjectPlatform.ProjectPlatform.lecture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    Lecture getLectureByLectureNameAndPassword(String lectureName, String password);
    List<Lecture> getAllByUsersId(Long id);
}
