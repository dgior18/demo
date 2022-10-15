package com.example.demo.controllers;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRequest;
import com.example.demo.student.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add/student")
    public String addStudent(@RequestBody StudentRequest request) {
        return studentService.addStudent(request);
    }

    @PatchMapping("/edit/student/name")
    public String editStudentName(@RequestParam("firstName") String firstName,
                                  @RequestParam("idNumber") Long idNumber) {
        return studentService.editStudentName(firstName, idNumber);
    }

    @PatchMapping("/edit/student/lastName")
    public String editStudentLastName(@RequestParam("lastName") String lastName,
                                      @RequestParam("idNumber") Long idNumber) {
        return studentService.editStudentLastName(lastName, idNumber);
    }

    @PatchMapping("/edit/student/email")
    public String editStudentEmail(@RequestParam("email") String email,
                                   @RequestParam("idNumber") Long idNumber) {
        return studentService.editStudentEmail(email, idNumber);
    }


    @PatchMapping("/edit/student/idNumber")
    public String editStudentIdNumber(@RequestParam("newIdNumber") Long newIdNumber,
                                      @RequestParam("idNumber") Long oldIdNumber) {
        return studentService.editStudentIdNumber(newIdNumber, oldIdNumber);
    }

    @PatchMapping("/edit/student/DOB")
    public String editStudentDOB(@RequestParam("DOB") String DOB,
                                 @RequestParam("idNumber") Long idNumber) {
        return studentService.editStudentDOB(DOB, idNumber);
    }

    @GetMapping("/find/student/idNumber")
    public Student findByIdNumber(@RequestParam("idNumber") Long idNumber) {
        return studentService.findByIdNumber(idNumber);
    }

    @GetMapping("/find/student/email")
    public Student findByEmail(@RequestParam("email") String email) {
        return studentService.findByEmail(email);
    }

    @GetMapping("/find/student/name")
    public List<Student> findByName(@RequestParam("name") String name) {
        return studentService.findByName(name);
    }

    @GetMapping("/find/student/lastName")
    public List<Student> findByLastName(@RequestParam("lastName") String lastName) {
        return studentService.findByLastName(lastName);
    }

    @GetMapping("/find/student/DOB")
    public List<Student> findByDOB(@RequestParam("DOB") String DOB) {
        return studentService.findByDOB(DOB);
    }

    @DeleteMapping("/delete/student")
    public String deleteStudent(@RequestParam("idNumber") Long idNumber) {
        return studentService.deleteStudent(idNumber);
    }

}
