package com.sq.ic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.SysResource;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.list.SysResourceTreeVo;
import com.sq.ic.pojo.vo.req.page.SysResourcePageReqVo;
import com.sq.ic.pojo.vo.req.save.SysResourceVo;

import java.util.List;

public interface SysResourceService extends IService<SysResource> {

    List<Short> listIds(Integer roleId);

    PageVo<SysResourceVo> list(SysResourcePageReqVo reqVo);

    List<SysResourceTreeVo> listTree();

    List<SysResourceVo> listParents();

    List<SysResource> listByRoleIds(List<Short> roleIds);
}
