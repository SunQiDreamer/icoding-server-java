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

    @ForeignField(Grade.class)
    private Integer gradeId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
