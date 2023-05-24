package com.sq.ic.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.sq.ic.common.foreign.anno.ForeignField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student {

    private Integer id;

    private String name;

    private Integer no;

    // 性别： 0 male, 1 female
    private Short sex;

    @ForeignField(Grade.class)
    private Short gradeId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
