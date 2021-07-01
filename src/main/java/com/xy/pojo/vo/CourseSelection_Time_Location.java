package com.xy.pojo.vo;


import com.xy.pojo.CourseSelection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//选课表与时间地点表联合
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSelection_Time_Location extends CourseSelection {
    private String startTime;
    private String endTime;
    private String date;
    private String building;
    private String room;
}
