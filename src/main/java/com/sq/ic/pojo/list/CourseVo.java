package com.sq.ic.pojo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("课程")
public class CourseVo {
    @ApiModelProperty("课程id")
    private Short id;

    @ApiModelProperty("课程名称")
    private String name;
}
