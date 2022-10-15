package com.example.demo.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Student {


    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private Long idNumber;
    @Column(unique = true)
    private String email;
    private LocalDate dateOfBirth;
//    @OneToMany
//    @JoinColumn(name = "user_id")

    public Student(String firstName,
                   String lastName,
                   Long idNumber,
                   String email,
                   String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }


}