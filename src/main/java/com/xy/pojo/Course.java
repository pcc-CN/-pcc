package com.xy.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//课程
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private String CourseID;
    private String SerialNumber;
    private String CourseName;
    private String College;
    private float    Credit;
    private String FacultyNumber;
}
