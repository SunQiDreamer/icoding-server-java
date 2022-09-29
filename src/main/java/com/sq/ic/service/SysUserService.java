package com.sq.ic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.SysUser;
import com.sq.ic.pojo.result.CodeMsg;
import com.sq.ic.pojo.vo.LoginVo;
import com.sq.ic.pojo.vo.req.LoginReqVo;

public interface SysUserService extends IService<SysUser> {

    LoginVo login(LoginReqVo reqVo);
}
