package com.example.demo.teacher;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TeacherRequest {
    private String firstName;
    private String lastName;
    private Long idNumber;
    private String email;
    private String dateOfBirth;
}
