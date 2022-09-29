package com.sq.ic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    List<SysRole> listByUserId(Integer id);
}
