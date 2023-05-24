package com.sq.ic.pojo.vo.req.save;

import com.sq.ic.common.validator.BoolNumber;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class StudentReqVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty(value = "name")
    @NotBlank
    private String name;

    @BoolNumber
    @ApiModelProperty(value = "性别")
    private Short sex; // 0 male 1 female

    @ApiModelProperty("no")
    private Integer no;

    @ApiModelProperty("gradeId")
    private Integer gradeId;

    @ApiModelProperty(value = "hobbyIds")
    private String hobbyIds;

}
