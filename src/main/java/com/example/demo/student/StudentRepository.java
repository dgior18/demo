package com.example.demo.student;


import com.example.demo.group.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface StudentRepository
        extends JpaRepository<Student, Long> {

    Optional<Student> findByIdNumber(Long idNumber);

    Optional<Student> findByEmail(String email);

    List<Student> findByFirstName(String firstName);

    List<Student> findByLastName(String lastName);

    List<Student> findByDateOfBirth(LocalDate localDate);


    @Transactional
    @Modifying
    @Query("UPDATE Student  s " +
            "SET s.group = :group WHERE s.idNumber = :idNumber")
    int addStudentIntoGroup(@Param(value = "group") Class group, @Param(value = "idNumber") Long idNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Student  s " +
            "SET s.group = null WHERE s.idNumber = :idNumber and s.group= :group")
    int deleteStudentFromGroup(@Param(value = "group") Class group, @Param(value = "idNumber") Long idNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Student  s " +
            "SET s.firstName = :firstName WHERE s.idNumber = :idNumber")
    int updateFirstName(@Param(value = "firstName") String firstName, @Param(value = "idNumber") Long idNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Student  s " +
            "SET s.lastName = :lastName WHERE s.idNumber = :idNumber")
    int updateLastName(@Param(value = "lastName") String lastName, @Param(value = "idNumber") Long idNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Student  s " +
            "SET s.email = :email WHERE s.idNumber = :idNumber")
    int updateEmail(@Param(value = "email") String email, @Param(value = "idNumber") Long idNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Student  s " +
            "SET s.idNumber = :newIdNumber WHERE s.idNumber = :idNumber")
    int updateIdNumber(@Param(value = "newIdNumber") Long newIdNumber, @Param(value = "idNumber") Long idNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Student  s " +
            "SET s.dateOfBirth = :dateOfBirth WHERE s.idNumber = :idNumber")
    int updateDOB(@Param(value = "dateOfBirth") String dateOfBirth, @Param(value = "idNumber") Long idNumber);

}
