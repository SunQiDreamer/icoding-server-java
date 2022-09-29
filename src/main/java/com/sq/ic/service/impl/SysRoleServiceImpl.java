package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.enhance.MpLambdaQueryWrapper;
import com.sq.ic.common.enhance.MpPage;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.Streams;
import com.sq.ic.mapper.SysRoleMapper;
import com.sq.ic.mapper.SysUserRoleMapper;
import com.sq.ic.pojo.list.SysRoleVo;
import com.sq.ic.pojo.po.SysRole;
import com.sq.ic.pojo.po.SysUserRole;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.req.page.SysRolePageReqVo;
import com.sq.ic.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl
        extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {


    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysRole> listByUserId(Integer userId) {
        // 1.根据userId 从sys_user_role表中查出roleId
        // 2.roleIds从sys_role中查出SysRole
        if (userId == null || userId <= 0) return null;
        List<Short> roleIds = listId(userId);
        if (CollectionUtils.isEmpty(roleIds)) return null;

        MpLambdaQueryWrapper<SysRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.in(SysRole::getId, roleIds);

        return baseMapper.selectList(wrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Short> listIds(Integer userId) {
        if (userId == null || userId < 0) return null;

        MpLambdaQueryWrapper<SysUserRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.select(SysUserRole::getRoleId);
        wrapper.eq(SysUserRole::getUserId, userId);
        List<Object> ids = userRoleMapper.selectObjs(wrapper);
        return Streams.map(ids, (id) -> ((Integer) id).shortValue());
    }

    @Override
    @Transactional(readOnly = true)
    public PageVo<SysRoleVo> list(SysRolePageReqVo pageReqVo) {
        MpLambdaQueryWrapper<SysRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(pageReqVo.getKeyword(), SysRole::getName);
        wrapper.orderByDesc(SysRole::getId);
        return baseMapper
                .selectPage(new MpPage<>(pageReqVo), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    private List<Short> listId(Integer userId) {  // 根据userId 从sys_user_role表中查出roleId
        if (userId == null || userId <= 0) return null;

        MpLambdaQueryWrapper<SysUserRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.select(SysUserRole::getRoleId);
        wrapper.eq(SysUserRole::getUserId, userId);

        List<Object> ids = userRoleMapper.selectObjs(wrapper);
        return Streams.map(ids, id -> ((Integer) id).shortValue());
    }
}
