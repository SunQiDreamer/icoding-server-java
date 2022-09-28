package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.mapper.SysUserMapper;
import com.sq.ic.pojo.po.SysUser;
import com.sq.ic.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl
        extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {
}
