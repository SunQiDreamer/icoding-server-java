package com.sq.ic.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysRole {

    private Short id;
    /**
     * 角色名称
     */
    private String name;

    // @TableField(fill = FieldFill.INSERT)
    // private LocalDateTime createTime;

    // @TableField(fill = FieldFill.INSERT_UPDATE)
    // private LocalDateTime updateTime;
}
