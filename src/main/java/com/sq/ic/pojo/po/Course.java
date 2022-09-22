package com.sq.ic.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.sq.ic.pojo.po.Teacher;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Course {

    private Short id;

    private String name;

    private List<Short> teacherIds;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
