package com.ProjectPlatform.ProjectPlatform.web;

import com.ProjectPlatform.ProjectPlatform.student.StudentService;
import com.ProjectPlatform.ProjectPlatform.web.dto.StudentDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/")
public class StudentRegistrationController {

    private StudentService studentService;

    public StudentRegistrationController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ModelAttribute("User")
    public StudentDto studentDto() {
        return new StudentDto();
    }

    @GetMapping("/")
    public ModelAndView starterPage(){
        return new ModelAndView("starter");
    }

    @GetMapping("/registration")
    public ModelAndView registration(){
        return new ModelAndView("registration");
    }

    @PostMapping("/registration/save")
    public ModelAndView saveUser(@ModelAttribute("user") StudentDto studentDto) {
        studentService.save(studentDto);
        return new ModelAndView("redirect:/registration?success");
    }

}
