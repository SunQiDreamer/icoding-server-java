package com.sq.ic.pojo.vo.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class GradeVo {
    @NotBlank
    @ApiModelProperty("班级名称")
    private String name;

    @NotNull
    @ApiModelProperty("id")
    private Short id;

    @ApiModelProperty("班主任")
    private TeacherVo mainTeacher;

}
