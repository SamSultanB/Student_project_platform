package com.ProjectPlatform.ProjectPlatform.lecture;

import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface LectureService {


    List<Lecture> getAllLectures();

    List<Lecture> getAllByUsersId(Long id);

    Lecture saveLecture(Lecture lecture);

    Lecture getLectureById(Long id);

    Lecture getLectureByLectureNameAndPassword(String lectureName, String password);

    void deleteLectureById(Long id);
}
