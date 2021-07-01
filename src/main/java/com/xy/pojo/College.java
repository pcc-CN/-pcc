package com.xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//学院
@Data
@AllArgsConstructor
@NoArgsConstructor
public class College {
    private String name;
    private float location;
    private float  budget;
    private String profession;
}
