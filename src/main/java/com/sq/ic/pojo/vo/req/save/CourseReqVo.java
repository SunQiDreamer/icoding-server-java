package com.sq.ic.pojo.vo.req.save;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CourseReqVo {
    @NotBlank(message = "不能为空")
    private String name;

}
