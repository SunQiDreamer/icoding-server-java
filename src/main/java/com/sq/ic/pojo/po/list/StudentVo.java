package com.sq.ic.pojo.po.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("学生")
public class StudentVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("no")
    private Integer no;

    @ApiModelProperty("gradeId")
    private Integer gradeId;

    @ApiModelProperty("性别 0: male 1: female")
    private Short sex;
}