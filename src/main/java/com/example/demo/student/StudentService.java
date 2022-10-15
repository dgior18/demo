package com.example.demo.student;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StudentService {


    private final static String STUDENT_NOT_FOUND_MSG =
            "Student with ID number %d not found";

    private final StudentRepository studentRepository;

    private void studentExist(StudentRequest request, String errorMessage) {
        boolean studentExist = studentRepository
                .findByIdNumber(request.getIdNumber())
                .isPresent();

        if (studentExist) {
            log.error(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
    }

    private void studentNotExist(Long idNumber) {
        boolean studentExist = studentRepository
                .findByIdNumber(idNumber)
                .isPresent();

        if (!studentExist) {
            log.error(String.format(STUDENT_NOT_FOUND_MSG, idNumber));
            throw new IllegalStateException(String.format(STUDENT_NOT_FOUND_MSG, idNumber));
        }
    }


    public String addStudent(StudentRequest request) {

        String errorMessage = String.format("Student with ID number %d already added.", request.getIdNumber());

        studentExist(request, errorMessage);

        var student = new Student(request.getFirstName(),
                request.getLastName(),
                request.getIdNumber(),
                request.getEmail(),
                request.getDateOfBirth());

        studentRepository.save(student);

        return String.format("Student with ID number %d added successfully.", request.getIdNumber());
    }

    public String deleteStudent(Long idNumber) {

        studentNotExist(idNumber);

        var student = studentRepository.findByIdNumber(idNumber).get();

        studentRepository.delete(student);

        return "Student deleted successfully";
    }

    public String editStudentName(String firstName, Long idNumber) {

        var student = findByIdNumber(idNumber);
        student.setFirstName(firstName);

        studentRepository.updateFirstName(firstName, idNumber);

        log.info("Edited student name. New name is " + firstName);
        return "Edited";
    }

    public String editStudentLastName(String lastName, Long idNumber) {

        var student = findByIdNumber(idNumber);
        student.setLastName(lastName);

        studentRepository.updateLastName(lastName, idNumber);

        log.info("Edited student last name. New last name is " + lastName);
        return "Edited";
    }

    public String editStudentEmail(String email, Long idNumber) {

        var student = findByIdNumber(idNumber);
        student.setEmail(email);

        studentRepository.updateEmail(email, idNumber);

        log.info("Edited student email. New email is " + email);
        return "Edited";
    }

    public String editStudentIdNumber(Long newIdNumber, Long oldIdNumber) {

        var student = findByIdNumber(oldIdNumber);
        student.setIdNumber(newIdNumber);

        studentRepository.updateIdNumber(newIdNumber, oldIdNumber);

        log.info("Edited student ID number. New ID number is " + newIdNumber);
        return "Edited";
    }

    public String editStudentDOB(String DOB, Long idNumber) {

        var student = findByIdNumber(idNumber);
        student.setDateOfBirth(LocalDate.parse(DOB));

        studentRepository.updateDOB(DOB, idNumber);

        log.info("Edited student date of birth. New date of birth is " + LocalDate.parse(DOB));
        return "Edited";
    }

    public Student findByIdNumber(Long idNumber) {

        studentNotExist(idNumber);

        var student = studentRepository.findByIdNumber(idNumber).get();

        return student;
    }

    public Student findByEmail(String email) {
        boolean studentExist = studentRepository
                .findByEmail(email)
                .isPresent();

        if (!studentExist) {
            log.info("Student not found.");
        }

        return studentRepository.findByEmail(email).get();
    }

    public List<Student> findByName(String name) {

        var students = studentRepository.findByFirstName(name);

        if (students.isEmpty()) {
            log.info("Student not found.");
        }
        return students;

    }

    public List<Student> findByLastName(String lastName) {

        var students = studentRepository.findByLastName(lastName);

        if (students.isEmpty()) {
            log.info("Student not found.");
        }

        return students;
    }

    public List<Student> findByDOB(String DOB) {

        var students = studentRepository.findByDateOfBirth(LocalDate.parse(DOB));

        if (students.isEmpty()) {
            log.info("Student not found.");
        }

        return students;
    }

}
