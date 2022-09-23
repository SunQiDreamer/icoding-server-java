package com.sq.ic.pojo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseVo {
    @ApiModelProperty("课程名称")
    private String name;
}
