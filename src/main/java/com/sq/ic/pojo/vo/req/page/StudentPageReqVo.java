package com.sq.ic.pojo.vo.req.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentPageReqVo extends KeywordPageReqVo {
    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("性别")
    private Short sex;

    @ApiModelProperty("班级id")
    private Short gradeId;

    @ApiModelProperty("爱好id")
    private String hobbyIds;
}
