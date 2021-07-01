package com.xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String FacultyNumber;//教工号
    private String CollegeName;
    private String name;
    private String gender;
    private String password;

}
