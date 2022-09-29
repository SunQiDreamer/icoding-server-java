package com.sq.ic.pojo.vo.req.page;

import com.sq.ic.pojo.vo.req.save.PageReqVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentPageReqVo extends PageReqVo {
    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("性别")
    private Short sex;

    @ApiModelProperty("班级id")
    private Short gradeId;
}
