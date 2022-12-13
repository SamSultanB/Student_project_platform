package com.ProjectPlatform.ProjectPlatform.web;

import com.ProjectPlatform.ProjectPlatform.lecture.Lecture;
import com.ProjectPlatform.ProjectPlatform.lecture.LectureService;
import com.ProjectPlatform.ProjectPlatform.project.Project;
import com.ProjectPlatform.ProjectPlatform.project.ProjectRepository;
import com.ProjectPlatform.ProjectPlatform.project.ResultServiceImpl;
import com.ProjectPlatform.ProjectPlatform.user.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
public class LecturerController {

    private LectureService lectureService;
    private UserService userService;
    private ProjectRepository projectRepository;
    private ResultServiceImpl resultService;

    public LecturerController(LectureService lectureService, UserService userService, ProjectRepository projectRepository, ResultServiceImpl resultService) {
        this.lectureService = lectureService;
        this.userService = userService;
        this.projectRepository = projectRepository;
        this.resultService = resultService;
    }

    @GetMapping("/lecturer-home")
    public ModelAndView lecturerHome(Model model, Principal principal){
        String email = principal.getName();
        Long id = userService.findByEmail(email).getId();
        model.addAttribute("lectures", lectureService.getAllByUsersId(id));
        return new ModelAndView("lecturerHome");
    }


    @GetMapping("/lecturer-home/new-lecture")
    public ModelAndView newLecture(Model model){
        Lecture lecture = new Lecture();
        model.addAttribute("lecture", lecture);
        return new ModelAndView("createLecture");
    }

    @PostMapping("/lecturer-home")
    public ModelAndView saveLecture(@ModelAttribute("lecture") Lecture lecture, Principal principal){
        String email = principal.getName();
        userService.findByEmail(email).addLecture(lecture);
        lectureService.saveLecture(lecture);
        return new ModelAndView("redirect:/lecturer-home");
    }

    @GetMapping("/lecturer-home/{id}")
    public ModelAndView deleteLecture(@PathVariable Long id, Principal principal){
        Lecture lecture = lectureService.getLectureById(id);
        String email = principal.getName();
        lecture.getUsers().clear();
        userService.findByEmail(email).removeLecture(id);
            lectureService.deleteLectureById(id);
            return new ModelAndView("redirect:/lecturer-home");
    }

    @GetMapping("/lecturer-home/lecture/{id}")
    public ModelAndView enterLecture(Model model, @PathVariable Long id){
        model.addAttribute("id", id);
        model.addAttribute("rid", id);
        model.addAttribute("projects", lectureService.getLectureById(id).getProjects());
        return new ModelAndView("lecture");
    }

    @GetMapping("/lecturer-home/new-project/{id}")
    public ModelAndView newProject(Model model, @PathVariable Long id){
        Project project = new Project();
        model.addAttribute("project", project);
        model.addAttribute("id", id);
        return new ModelAndView("addProject");
    }

    @PostMapping("/lecturer-home/lecture/{id}")
    public ModelAndView saveProject(@ModelAttribute("project") Project project, @PathVariable Long id){
        Lecture lecture = lectureService.getLectureById(id);
        lecture.getProjects().add(project);
        lectureService.saveLecture(lecture);
        return new ModelAndView("redirect:/lecturer-home/lecture/{id}");
    }

    @GetMapping("/lecturer-home/lecture/delete/{id}/{rid}")
    public ModelAndView deleteProject(@PathVariable("id") Long id, @PathVariable("rid") Long rid){
        Project project = projectRepository.getReferenceById(id);
        projectRepository.deleteById(id);
        lectureService.getLectureById(rid).getProjects().remove(project);
        return new ModelAndView("redirect:/lecturer-home/lecture/{rid}");
    }

    @GetMapping("/lecturer-home/uploads/{id}")
    public ModelAndView getUploads(@PathVariable("id") Long id, Model model){
//        model.addAttribute("doneProjects", resultService.findAllByProjectsId(id));
        return new ModelAndView("uploads");
    }



}
