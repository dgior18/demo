package com.example.demo.teacher;

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
public interface TeacherRepository
        extends JpaRepository<Teacher, Long> {


    Optional<Teacher> findByIdNumber(Long idNumber);

    Optional<Teacher> findByEmail(String email);

    List<Teacher> findByFirstName(String firstName);

    List<Teacher> findByLastName(String lastName);

    List<Teacher> findByDateOfBirth(LocalDate localDate);

    @Transactional
    @Modifying
    @Query("UPDATE Teacher  t " +
            "SET t.group = :group WHERE t.idNumber = :idNumber")
    int addTeacherIntoGroup(@Param(value = "group") Class group, @Param(value = "idNumber") Long idNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Teacher  t " +
            "SET t.group = null WHERE t.idNumber = :idNumber and t.group= :group")
    int deleteTeacherFromGroup(@Param(value = "group") Class group, @Param(value = "idNumber") Long idNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Teacher  t " +
            "SET t.firstName = :firstName WHERE t.idNumber = :idNumber")
    int updateFirstName(@Param(value = "firstName") String firstName, @Param(value = "idNumber") Long idNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Teacher  t " +
            "SET t.lastName = :lastName WHERE t.idNumber = :idNumber")
    int updateLastName(@Param(value = "lastName") String lastName, @Param(value = "idNumber") Long idNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Teacher  t " +
            "SET t.email = :email WHERE t.idNumber = :idNumber")
    int updateEmail(@Param(value = "email") String email, @Param(value = "idNumber") Long idNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Teacher  t " +
            "SET t.idNumber = :newIdNumber WHERE t.idNumber = :idNumber")
    int updateIdNumber(@Param(value = "newIdNumber") Long newIdNumber, @Param(value = "idNumber") Long idNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Teacher  t " +
            "SET t.dateOfBirth = :dateOfBirth WHERE t.idNumber = :idNumber")
    int updateDOB(@Param(value = "dateOfBirth") String dateOfBirth, @Param(value = "idNumber") Long idNumber);

}
