package com.xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherStartList {
    private String CourseID;
    private String SerialNumber;
    private String FacultyNumber;
    private String name;
    private String CollegeName;
}
