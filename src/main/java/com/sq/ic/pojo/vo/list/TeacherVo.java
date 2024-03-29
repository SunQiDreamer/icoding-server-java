package com.sq.ic.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("老师")
public class TeacherVo {

    @ApiModelProperty("id")
    private Short id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private Short sex;

    @ApiModelProperty("班级")
    private List<GradeVo> grades;

    @ApiModelProperty("教的课程")
    private CourseVo course;

    @ApiModelProperty("是否是班主任")
    private Boolean isMain;

    @ApiModelProperty("爱好")
    private List<HobbyVo> hobbies;

}
