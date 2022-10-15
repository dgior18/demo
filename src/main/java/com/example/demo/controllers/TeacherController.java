package com.example.demo.controllers;

import com.example.demo.teacher.Teacher;
import com.example.demo.teacher.TeacherRequest;
import com.example.demo.teacher.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/add/teacher")
    public String addTeacher(@RequestBody TeacherRequest request) {
        return teacherService.addTeacher(request);
    }

    @PatchMapping("/edit/teacher/name")
    public String editTeacherName(@RequestParam("firstName") String firstName,
                                  @RequestParam("idNumber") Long idNumber) {
        return teacherService.editTeacherName(firstName, idNumber);
    }

    @PatchMapping("/edit/teacher/lastName")
    public String editTeacherLastName(@RequestParam("lastName") String lastName,
                                      @RequestParam("idNumber") Long idNumber) {
        return teacherService.editTeacherLastName(lastName, idNumber);
    }

    @PatchMapping("/edit/teacher/email")
    public String editTeacherEmail(@RequestParam("email") String email,
                                   @RequestParam("idNumber") Long idNumber) {
        return teacherService.editTeacherEmail(email, idNumber);
    }


    @PatchMapping("/edit/teacher/idNumber")
    public String editTeacherIdNumber(@RequestParam("newIdNumber") Long newIdNumber,
                                      @RequestParam("idNumber") Long oldIdNumber) {
        return teacherService.editTeacherIdNumber(newIdNumber, oldIdNumber);
    }

    @PatchMapping("/edit/teacher/DOB")
    public String editTeacherDOB(@RequestParam("DOB") String DOB,
                                 @RequestParam("idNumber") Long idNumber) {
        return teacherService.editTeacherDOB(DOB, idNumber);
    }

    @GetMapping("/find/teacher/idNumber")
    public Teacher findByIdNumber(@RequestParam("idNumber") Long idNumber) {
        return teacherService.findByIdNumber(idNumber);
    }

    @GetMapping("/find/teacher/email")
    public Teacher findByEmail(@RequestParam("email") String email) {
        return teacherService.findByEmail(email);
    }

    @GetMapping("/find/teacher/name")
    public List<Teacher> findByName(@RequestParam("name") String name) {
        return teacherService.findByName(name);
    }

    @GetMapping("/find/teacher/lastName")
    public List<Teacher> findByLastName(@RequestParam("lastName") String lastName) {
        return teacherService.findByLastName(lastName);
    }

    @GetMapping("/find/teacher/DOB")
    public List<Teacher> findByDOB(@RequestParam("DOB") String DOB) {
        return teacherService.findByDOB(DOB);
    }

    @DeleteMapping("/delete/teacher")
    public String deleteStudent(@RequestParam("idNumber") Long idNumber) {
        return teacherService.deleteTeacher(idNumber);
    }

}
