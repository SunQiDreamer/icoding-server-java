package com.sq.ic.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.sq.ic.common.foreign.anno.ForeignField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Teacher {
    private Integer id;

    private String name;

    //    性别： 0 male, 1 female
    private Short sex;

    // 教的班级
    private String gradeIds;

    // 主教的课程
    @ForeignField(Course.class)
    private Short courseId;

    // 是否是班主任
    private Boolean isMain;

    // 爱好
    private String hobbyIds;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
