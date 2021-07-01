package com.xy.pojo.vo;

import com.xy.pojo.CourseStartList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//开课表与时间表联合
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStartListTime extends CourseStartList {
  private   String startTime;
  private   String endTime;
  private   String date;
}
