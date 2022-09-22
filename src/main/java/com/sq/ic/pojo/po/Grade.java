package com.sq.ic.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Grade {

    private Integer id;

    private String name;

    // 所有学生
    private List<Short> studentIds;

    // 所有任课老师
    private List<Short> teacherIds;

    // 班主任
    private Short mainTeacherId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
