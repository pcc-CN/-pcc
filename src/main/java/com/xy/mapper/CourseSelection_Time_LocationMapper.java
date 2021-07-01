package com.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xy.pojo.vo.CourseSelection_Time_Location;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSelection_Time_LocationMapper extends BaseMapper<CourseSelection_Time_LocationMapper> {
    @Select({"select  StudentID, SerialNumber, CourseID, semester, year, courseName, timeid,startTime,endTime,date,building,room from course_selection natural join course_start_list natural join time natural join location where" +
            " StudentID =#{id}"})
    List<CourseSelection_Time_Location> getcourseSelectionTL(String id);

}
