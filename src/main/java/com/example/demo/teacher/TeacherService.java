package com.example.demo.teacher;

import com.example.demo.group.ClassService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class TeacherService {

    private final static String TEACHER_NOT_FOUND_MSG =
            "Teacher with ID number %d not found";

    private final TeacherRepository teacherRepository;
    private final ClassService classService;

    private void teacherExist(TeacherRequest request, String errorMessage) {
        boolean teacherExist = teacherRepository
                .findByIdNumber(request.getIdNumber())
                .isPresent();

        if (teacherExist) {
            log.error(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
    }

    private void teacherNotExist(Long idNumber) {
        boolean teacherExist = teacherRepository
                .findByIdNumber(idNumber)
                .isPresent();

        if (!teacherExist) {
            log.error(String.format(TEACHER_NOT_FOUND_MSG, idNumber));
            throw new IllegalStateException(String.format(TEACHER_NOT_FOUND_MSG, idNumber));
        }
    }


    public String addTeacher(TeacherRequest request) {

        String errorMessage = String.format("Teacher with ID number %d already added.", request.getIdNumber());

        teacherExist(request, errorMessage);

        var teacher = new Teacher(request.getFirstName(),
                request.getLastName(),
                request.getIdNumber(),
                request.getEmail(),
                request.getDateOfBirth());

        teacherRepository.save(teacher);

        return String.format("Teacher with ID number %d added successfully.", request.getIdNumber());
    }

    public String addTeacherIntoGroup(Long teacherIdNumber, Long groupId) {

        var group = classService.findByGroupId(groupId);
        teacherRepository.addTeacherIntoGroup(group, teacherIdNumber);

        return "Added Teacher into group";
    }

    public String deleteTeacherFromGroup(Long teacherIdNumber, Long groupId) {

        var group = classService.findByGroupId(groupId);
        teacherRepository.deleteTeacherFromGroup(group, teacherIdNumber);

        return "Teacher deleted from group successfully";
    }

    public String deleteTeacher(Long idNumber) {

        teacherNotExist(idNumber);

        var teacher = teacherRepository.findByIdNumber(idNumber).get();

        teacherRepository.delete(teacher);

        return "Teacher deleted successfully";
    }

    public String editTeacherName(String firstName, Long idNumber) {

        var teacher = findByIdNumber(idNumber);
        teacher.setFirstName(firstName);

        teacherRepository.updateFirstName(firstName, idNumber);

        log.info("Edited teacher name. New name is " + firstName);
        return "Edited";
    }

    public String editTeacherLastName(String lastName, Long idNumber) {

        var teacher = findByIdNumber(idNumber);
        teacher.setLastName(lastName);

        teacherRepository.updateLastName(lastName, idNumber);

        log.info("Edited teacher last name. New last name is " + lastName);
        return "Edited";
    }

    public String editTeacherEmail(String email, Long idNumber) {

        var teacher = findByIdNumber(idNumber);
        teacher.setEmail(email);

        teacherRepository.updateEmail(email, idNumber);

        log.info("Edited teacher email. New email is " + email);
        return "Edited";
    }

    public String editTeacherIdNumber(Long newIdNumber, Long oldIdNumber) {

        var teacher = findByIdNumber(oldIdNumber);
        teacher.setIdNumber(newIdNumber);

        teacherRepository.updateIdNumber(newIdNumber, oldIdNumber);

        log.info("Edited teacher ID number. New ID number is " + newIdNumber);
        return "Edited";
    }

    public String editTeacherDOB(String DOB, Long idNumber) {

        var teacher = findByIdNumber(idNumber);
        teacher.setDateOfBirth(LocalDate.parse(DOB));

        teacherRepository.updateDOB(DOB, idNumber);

        log.info("Edited teacher date of birth. New date of birth is " + LocalDate.parse(DOB));
        return "Edited";
    }

    public Teacher findByIdNumber(Long idNumber) {

        teacherNotExist(idNumber);

        var teacher = teacherRepository.findByIdNumber(idNumber).get();

        return teacher;
    }

    public Teacher findByEmail(String email) {
        boolean teacherExist = teacherRepository
                .findByEmail(email)
                .isPresent();

        if (!teacherExist) {
            log.info("Teacher not found.");
        }

        return teacherRepository.findByEmail(email).get();
    }

    public List<Teacher> findByName(String name) {

        var teachers = teacherRepository.findByFirstName(name);

        if (teachers.isEmpty()) {
            log.info("Student not found.");
        }
        return teachers;

    }

    public List<Teacher> findByLastName(String lastName) {

        var teachers = teacherRepository.findByLastName(lastName);

        if (teachers.isEmpty()) {
            log.info("Student not found.");
        }

        return teachers;
    }

    public List<Teacher> findByDOB(String DOB) {

        var teachers = teacherRepository.findByDateOfBirth(LocalDate.parse(DOB));

        if (teachers.isEmpty()) {
            log.info("Student not found.");
        }

        return teachers;
    }

}
