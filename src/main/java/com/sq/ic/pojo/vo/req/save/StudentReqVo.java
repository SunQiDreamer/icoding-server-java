package com.sq.ic.pojo.vo.req.save;

import com.sq.ic.common.validator.BoolNumber;
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

    @BoolNumber
    @ApiModelProperty(value = "性别", required = true)
    private Short sex; // 0 male 1 female

    @NotNull(message = "学号不能为空")
    @ApiModelProperty("no")
    private Integer no;

    @NotNull(message = "班级id不能为空")
    @ApiModelProperty("gradeId")
    private Integer gradeId;

}
