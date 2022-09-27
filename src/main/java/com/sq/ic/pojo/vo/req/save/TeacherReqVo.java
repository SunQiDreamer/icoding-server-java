package com.sq.ic.pojo.vo.req.save;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TeacherReqVo {
    @NotBlank(message = "不能为空")
    private String name;

    @Range(min = 0, max = 1)
    private Short sex;

    @NotNull
    private Short courseId;

    private Boolean isMain;

    private String hobbyIds;
}
