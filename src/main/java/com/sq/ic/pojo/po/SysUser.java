package com.sq.ic.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class SysUser {
    private Integer id;

    private String nickName;

    private String userName;

    private String password;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 最后一次登录的时间
     */
    private Date loginTime;
    /**
     * 账号的状态，0是正常，1是锁定
     */
    private Short status;
}
