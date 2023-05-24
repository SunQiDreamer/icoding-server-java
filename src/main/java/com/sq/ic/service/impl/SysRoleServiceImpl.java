package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.cache.Caches;
import com.sq.ic.common.enhance.MpLambdaQueryWrapper;
import com.sq.ic.common.enhance.MpPage;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.Streams;
import com.sq.ic.common.util.Strings;
import com.sq.ic.mapper.SysRoleMapper;
import com.sq.ic.mapper.SysUserRoleMapper;
import com.sq.ic.pojo.po.SysRole;
import com.sq.ic.pojo.po.SysRoleResource;
import com.sq.ic.pojo.po.SysUserRole;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.list.SysRoleVo;
import com.sq.ic.pojo.vo.req.page.SysRolePageReqVo;
import com.sq.ic.pojo.vo.req.save.SysRoleReqVo;
import com.sq.ic.service.SysRoleResourceService;
import com.sq.ic.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl
        extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysRoleResourceService roleResourceService;

    @Override
    public List<SysRole> listByUserId(Integer userId) {
        // 1.根据userId 从sys_user_role表中查出roleId
        // 2.roleIds从sys_role中查出SysRole
        if (userId == null || userId <= 0)
            return null;
        List<Short> roleIds = listId(userId);
        if (CollectionUtils.isEmpty(roleIds))
            return null;

        MpLambdaQueryWrapper<SysRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.in(SysRole::getId, roleIds);

        return baseMapper.selectList(wrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Short> listIds(Integer userId) {
        if (userId == null || userId < 0)
            return null;

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

    @Override
    public boolean saveOrUpdate(SysRoleReqVo reqVo) {
        // 保存或者更新角色
        // 更新，查出之前要拥有这个角色的用户，退出重新登录
        // 更新，删除当前角色拥有的所有权限资源，重新添加权限资源
        SysRole role = MapStructs.INSTANCE.reqVo2po(reqVo);
        if (!saveOrUpdate(role))
            return false;

        Short id = reqVo.getId();
        if (id != null && id > 0) { // 有id说明是更新操作
            MpLambdaQueryWrapper<SysUserRole> wrapper = new MpLambdaQueryWrapper<>();
            wrapper.select(SysUserRole::getUserId);
            wrapper.eq(SysUserRole::getRoleId, id);
            List<Object> userIds = userRoleMapper.selectObjs(wrapper);
            if (!CollectionUtils.isEmpty(userIds)) {
                for (Object userId : userIds) {
                    // 将拥有这个角色的用户从缓存中移除（让token失效，用户必须重新登录）
                    Caches.removeToken(Caches.get(userId));
                }
            }
            // 删除当前角色的所有资源信息
            roleResourceService.removeByRoleId(id);
        }

        // 保存角色信息
        String resourceIdStr = reqVo.getResourceIds();
        if (Strings.isEmpty(resourceIdStr))
            return true;

        String[] resourceIds = resourceIdStr.split(",");
        List<SysRoleResource> roleResources = new ArrayList<>();
        for (String resourceId : resourceIds) { // 构建SysUserRole对象
            SysRoleResource roleResource = new SysRoleResource();
            roleResource.setResourceId(Short.parseShort(resourceId));
            roleResources.add(roleResource);
        }

        return roleResourceService.saveBatch(roleResources);
    }

    private List<Short> listId(Integer userId) { // 根据userId 从sys_user_role表中查出roleId
        if (userId == null || userId <= 0)
            return null;

        MpLambdaQueryWrapper<SysUserRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.select(SysUserRole::getRoleId);
        wrapper.eq(SysUserRole::getUserId, userId);

        List<Object> ids = userRoleMapper.selectObjs(wrapper);
        return Streams.map(ids, id -> ((Integer) id).shortValue());
    }
}
