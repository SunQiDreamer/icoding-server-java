package com.sq.ic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.list.SysUserVo;
import com.sq.ic.pojo.po.SysUser;
import com.sq.ic.pojo.result.CodeMsg;
import com.sq.ic.pojo.vo.LoginVo;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.req.LoginReqVo;
import com.sq.ic.pojo.vo.req.page.SysUserPageReqVo;
import com.sq.ic.pojo.vo.req.page.SysUserReqVo;

import java.util.List;

public interface SysUserService extends IService<SysUser> {

    LoginVo login(LoginReqVo reqVo);

    boolean saveOrUpdate(SysUserReqVo reqVo);

    PageVo<SysUserVo> list(SysUserPageReqVo pageReqVo);
}
