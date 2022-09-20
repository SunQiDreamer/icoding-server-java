package com.sq.ic.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentReqVo {
    @ApiModelProperty("id")
    private Integer id;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "name", required = true)
    private String name;

    @NotNull(message = "学号不能为空")
    @ApiModelProperty("no")
    private Integer no;

    @NotNull(message = "班级id不能为空")
    @ApiModelProperty("gradeId")
    private Integer gradeId;

}
