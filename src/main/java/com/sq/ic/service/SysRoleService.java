package com.sq.ic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.list.SysRoleVo;
import com.sq.ic.pojo.po.SysRole;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.req.page.SysRolePageReqVo;
import com.sq.ic.pojo.vo.req.save.SysRoleReqVo;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    List<SysRole> listByUserId(Integer id);

    List<Short> listIds(Integer userId);

    PageVo<SysRoleVo> list(SysRolePageReqVo pageReqVo);

    boolean saveOrUpdate(SysRoleReqVo reqVo);
}
