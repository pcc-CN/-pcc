package com.xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInformation {
    private String studentID;
    private String College;
    private String profession;
    private String name;
    private String gender;
    private String password;
}
