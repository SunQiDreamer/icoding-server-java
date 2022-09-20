package com.sq.ic.pojo.vo.req.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentPageReqVo extends KeywordPageReqVo {
    @ApiModelProperty("学生id")
    private Integer studentId;
}
