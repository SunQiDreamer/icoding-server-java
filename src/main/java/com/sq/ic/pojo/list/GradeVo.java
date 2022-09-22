package com.sq.ic.pojo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GradeVo {
    @ApiModelProperty("班级名称")
    private String name;

    @ApiModelProperty("学生数")
    private Short studentCount;

}
