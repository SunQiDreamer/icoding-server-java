package com.sq.ic.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("学生")
public class StudentVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("no")
    private Integer no;

    @ApiModelProperty("班级")
    private GradeVo grade;

    @ApiModelProperty("性别 0: male 1: female")
    private Short sex;

    @ApiModelProperty("爱好")
    private List<HobbyVo> hobbyVos;
}