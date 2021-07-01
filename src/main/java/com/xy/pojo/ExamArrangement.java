package com.xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamArrangement {
    private String SerialNumber;
    private String CourseID;
    private String semester;
    private String    year;
    private float    location;
    private String timeid;
}
