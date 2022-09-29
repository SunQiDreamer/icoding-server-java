package com.sq.ic.pojo.dto;

import com.sq.ic.pojo.po.SysResource;
import com.sq.ic.pojo.po.SysRole;
import com.sq.ic.pojo.po.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserDto {
    private SysUser user;

    private List<SysRole> roles;

    private List<SysResource> resources;
}
