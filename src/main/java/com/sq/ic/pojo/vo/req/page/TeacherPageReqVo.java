package com.sq.ic.pojo.vo.req.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherPageReqVo extends KeywordPageReqVo {

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("课程Id")
    private Short courseId;

    @ApiModelProperty("性别")
    private Short sex;

    @ApiModelProperty("是否是班主任")
    private Boolean isMain;
}
