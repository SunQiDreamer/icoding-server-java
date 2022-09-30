package com.sq.ic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.SysUserRole;

public interface SysUserRoleService extends IService<SysUserRole> {
    boolean removeByUserId(Integer id);
}
