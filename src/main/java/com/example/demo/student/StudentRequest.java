package com.example.demo.student;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class StudentRequest {
    private String firstName;
    private String lastName;
    private Long idNumber;
    private String email;
    private String dateOfBirth;
}
