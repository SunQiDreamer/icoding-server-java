package com.sq.ic.pojo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class Teacher {
    private int id;

    private String name;

    private Short sex;

    private List<Short> gradeIds;

    private Short courseId;

    private Boolean  
}
