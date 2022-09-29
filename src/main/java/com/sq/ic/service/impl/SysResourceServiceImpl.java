package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.enhance.MpLambdaQueryWrapper;
import com.sq.ic.common.enhance.MpPage;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.Constants;
import com.sq.ic.common.util.Streams;
import com.sq.ic.mapper.SysResourceMapper;
import com.sq.ic.mapper.SysRoleResourceMapper;
import com.sq.ic.pojo.list.SysResourceTreeVo;
import com.sq.ic.pojo.po.SysResource;
import com.sq.ic.pojo.po.SysRoleResource;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.req.page.SysResourcePageReqVo;
import com.sq.ic.pojo.vo.req.save.SysResourceVo;
import com.sq.ic.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysResourceServiceImpl
        extends ServiceImpl<SysResourceMapper, SysResource>
        implements SysResourceService {

    @Autowired
    private SysRoleResourceMapper roleResourceMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Short> listIds(Integer roleId) { //    根据roleId获取resourceId
        if (roleId == null || roleId <= 0) return null;

        MpLambdaQueryWrapper<SysRoleResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.select(SysRoleResource::getResourceId);
        wrapper.eq(SysRoleResource::getRoleId, roleId);

        List<Object> ids = roleResourceMapper.selectObjs(wrapper);
        return Streams.map(ids, (id) -> ((Integer) id).shortValue());
    }

    private List<Short> listIds(List<Short> roleIds) {
        MpLambdaQueryWrapper<SysRoleResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.select(SysRoleResource::getResourceId);
        wrapper.in(SysRoleResource::getRoleId, roleIds);

        List<Object> ids = roleResourceMapper.selectObjs(wrapper);
        return Streams.map(ids, (id) -> ((Integer) id).shortValue());
    }

    @Override
    public PageVo<SysResourceVo> list(SysResourcePageReqVo reqVo) { //获取resources
        MpLambdaQueryWrapper<SysResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(reqVo.getKeyword(),
                SysResource::getName,
                SysResource::getUri,
                SysResource::getPermission);
        wrapper.orderByDesc(SysResource::getId);
        return baseMapper
                .selectPage(new MpPage<>(reqVo), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysResourceTreeVo> listTree() {
        List<SysResourceTreeVo> vos = new ArrayList<>();

        Map<Short, SysResourceTreeVo> doneVos = new HashMap<>();

        MpLambdaQueryWrapper<SysResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.orderByAsc(SysResource::getType);
        // 里面存放的是从数据库中查出来的PO
        List<SysResource> pos = baseMapper.selectList(wrapper);
        for (SysResource po : pos) {
            SysResourceTreeVo vo = po2treeVo(po);

            doneVos.put(vo.getId(), vo);

            Short type = po.getType();
            if (type == Constants.SysResourceType.DIR) {
                vos.add(vo);
            } else {
                SysResourceTreeVo parentVo = doneVos.get(po.getParentId());
                List<SysResourceTreeVo> children = parentVo.getChildren();
                if (children == null) {
                    parentVo.setChildren(children = new ArrayList<>());
                }
                children.add(vo);
            }
        }

        return vos;
    }

    @Override
    public List<SysResourceVo> listParents() {
        MpLambdaQueryWrapper<SysResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.ne(SysResource::getType, Constants.SysResourceType.BTN);
        wrapper.orderByAsc(SysResource::getType).orderByDesc(SysResource::getId);
        return Streams.map(baseMapper.selectList(wrapper), MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysResource> listByRoleIds(List<Short> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) return null;

        List<Short> resourceIds = listIds(roleIds);
        if (CollectionUtils.isEmpty(resourceIds)) return null;

        MpLambdaQueryWrapper<SysResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.in(SysResource::getId, resourceIds);
        return baseMapper.selectList(wrapper);
    }

    private SysResourceTreeVo po2treeVo(SysResource po) {
        SysResourceTreeVo treeVo = new SysResourceTreeVo();
        treeVo.setId(po.getId());
        treeVo.setTitle(po.getName());
        return treeVo;
    }
}
