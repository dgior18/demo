package com.example.demo.group;

import com.example.demo.student.Student;
import com.example.demo.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Class {

    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_sequence"
    )
    private Long id;
    private String name;
    @Column(unique = true)
    private Long groupId;
    @OneToMany
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private List<Student> students;
    @OneToMany
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private List<Teacher> teachers;

    public Class(String name,
                 Long groupId) {
        this.name = name;
        this.groupId = groupId;
    }
}
