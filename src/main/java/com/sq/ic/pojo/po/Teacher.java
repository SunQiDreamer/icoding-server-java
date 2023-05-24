package com.sq.ic.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.sq.ic.common.foreign.anno.ForeignField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Teacher {

    private Integer id;

    private String name;

    // 性别： 0 male, 1 female
    private Short sex;

    // 主教的课程
    @ForeignField(Course.class)
    private Short courseId;

    // 是否是班主任
    private Boolean isMain;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
