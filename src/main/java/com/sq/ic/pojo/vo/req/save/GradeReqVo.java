package com.sq.ic.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GradeReqVo {
    @ApiModelProperty("id")
    private Integer id;

    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "班级名字", required = true)
    private String name;

    @ApiModelProperty(value = "班主任id", required = true)
    private Integer mainTeacherId;

}
