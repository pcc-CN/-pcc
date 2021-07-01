package com.xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//选课表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSelection {

    private String StudentID;
    private String    SerialNumber;
    private String CourseID;
    private String semester;
    private String    year;
    private String courseName;
    private float  timeid;
}
