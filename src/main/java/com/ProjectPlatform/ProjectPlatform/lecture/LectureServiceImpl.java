package com.ProjectPlatform.ProjectPlatform.lecture;

import com.ProjectPlatform.ProjectPlatform.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LectureServiceImpl implements LectureService {

    private LectureRepository lectureRepository;
    private UserService userService;

    public LectureServiceImpl(LectureRepository lectureRepository, UserService userService) {
        this.lectureRepository = lectureRepository;
        this.userService = userService;
    }


    @Override
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    @Override
    public List<Lecture> getAllByUsersId(Long id) {
        return lectureRepository.getAllByUsersId(id);
    }

    @Override
    public Lecture saveLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }


    @Override
    public Lecture getLectureById(Long id) {
        return lectureRepository.findById(id).get();
    }

    @Override
    public Lecture getLectureByLectureNameAndPassword(String  lectureName, String password) {
        return lectureRepository.getLectureByLectureNameAndPassword(lectureName, password);
    }

    @Override
    public void deleteLectureById(Long id) {
        lectureRepository.deleteById(id);
    }

}
