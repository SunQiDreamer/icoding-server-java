package com.sq.ic.pojo.po;

import com.sq.ic.common.foreign.anno.ForeignField;
import lombok.Data;

@Data
public class SysUserRole {
    @ForeignField(SysRole.class)
    private Short roleId;

    @ForeignField(SysUser.class)
    private Integer userId;
}
