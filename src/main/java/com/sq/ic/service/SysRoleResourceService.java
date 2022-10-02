package com.sq.ic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.SysRoleResource;

public interface SysRoleResourceService extends IService<SysRoleResource> {

    boolean removeByRoleId(Short id);
}
