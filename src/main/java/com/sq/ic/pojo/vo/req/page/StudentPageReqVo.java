package com.sq.ic.pojo.vo.req.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentPageReqVo extends KeywordPageReqVo {
    @ApiModelProperty("学生id")
    private Integer studentId;

    @ApiModelProperty("学号")
    private Integer no;

    @ApiModelProperty("名字")
    private String name;
}
