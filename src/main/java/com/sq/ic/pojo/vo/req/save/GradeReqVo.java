package com.sq.ic.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GradeReqVo {

    @NotBlank(message = "能不能为空")
    @ApiModelProperty(value = "班级名字", required = true)
    private String name;

}
