package com.xy.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//课程名单(老师的)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseList {
    private String FacultyNumber;
    private String StudentID;
    private String    SerialNumber;
    private String CourseID;
    private String semester;
    private String    year;
    private String CourseName;
    private String StuName;
    private float  grades;

}
