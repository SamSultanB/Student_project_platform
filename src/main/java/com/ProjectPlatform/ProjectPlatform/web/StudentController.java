package com.ProjectPlatform.ProjectPlatform.web;

import com.ProjectPlatform.ProjectPlatform.lecture.Lecture;
import com.ProjectPlatform.ProjectPlatform.lecture.LectureService;
import com.ProjectPlatform.ProjectPlatform.project.*;
import com.ProjectPlatform.ProjectPlatform.user.User;
import com.ProjectPlatform.ProjectPlatform.user.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;

@RestController
public class StudentController {

    private LectureService lectureService;
    private UserService userService;
    private ResultServiceImpl resultServiceImpl;

    private ProjectRepository projectRepository;

    public StudentController(LectureService lectureService, UserService userService, ResultServiceImpl resultServiceImpl, ProjectRepository projectRepository) {
        this.lectureService = lectureService;
        this.userService = userService;
        this.resultServiceImpl = resultServiceImpl;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/home")
    public ModelAndView studentHome(Model model, Principal principal){
        String email = principal.getName();
        Long id = userService.findByEmail(email).getId();
        model.addAttribute("lectures", lectureService.getAllByUsersId(id));
        Lecture lecture = new Lecture();
        model.addAttribute("lecture", lecture);
        return new ModelAndView("home");
    }

    @PostMapping("/home")
    public ModelAndView findLecture(@ModelAttribute("lecture") Lecture lecture, Principal principal){

        String email = principal.getName();
        User user = userService.findByEmail(email);
        user.addLecture(lectureService.getLectureByLectureNameAndPassword(lecture.getLectureName(), lecture.getPassword()));
        lectureService.saveLecture(lectureService.getLectureByLectureNameAndPassword(lecture.getLectureName(), lecture.getPassword()));
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/home/{id}/{name}")
    public ModelAndView lecturePage(@PathVariable("id") Long id, @PathVariable("name") String name, Model model, Principal principal){
        model.addAttribute("projects", lectureService.getLectureById(id).getProjects());
        model.addAttribute("lectureName", name);
        Lecture lecture = new Lecture();
        model.addAttribute("lecture", lecture);
        String email = principal.getName();
        Long Id = userService.findByEmail(email).getId();
        model.addAttribute("lectures", lectureService.getAllByUsersId(Id));
        return new ModelAndView("home");
    }

    @PostMapping("/home/upload/{id}")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id, Principal principal) throws IOException {
        String email = principal.getName();
        Result result = new Result();
        result.setDoneProject(resultServiceImpl.uploadFile(file));
        userService.findByEmail(email).addResult(result);
        projectRepository.findById(id).get().getResults().add(result);
        resultServiceImpl.save(result);
        return new ModelAndView("redirect:/home");
    }



}
