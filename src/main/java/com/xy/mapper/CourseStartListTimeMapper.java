package com.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xy.pojo.vo.CourseStartListTime;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseStartListTimeMapper extends BaseMapper<CourseStartListTime> {
    @Select("select * from course_start_list natural join time")
    List<CourseStartListTime> getCourseStartListTime();
}
