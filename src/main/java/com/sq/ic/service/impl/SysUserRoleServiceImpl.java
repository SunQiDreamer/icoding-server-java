package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.enhance.MpLambdaQueryWrapper;
import com.sq.ic.mapper.SysUserRoleMapper;
import com.sq.ic.pojo.po.SysUserRole;
import com.sq.ic.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserRoleServiceImpl
        extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements SysUserRoleService {

    @Override
    public boolean removeByUserId(Integer userId) {
        if (userId == null || userId < 0) return false;
        MpLambdaQueryWrapper<SysUserRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, userId);
        return baseMapper.delete(wrapper) > 0;
    }
}
