package com.example.demo.teacher;

import com.example.demo.group.Class;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Teacher {

    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private Long idNumber;
    @Column(unique = true)
    private String email;
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Class group;

    public Teacher(String firstName,
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
