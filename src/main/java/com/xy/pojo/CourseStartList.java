package com.xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//历年开课表
public class CourseStartList {
    private String CourseID;
    private String SerialNumber;
    private String year;
    private String semester;
    private float  location;
    private float  timeid;
    private String courseName;
    private float  credits;
}
