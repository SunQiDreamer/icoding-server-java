package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.cache.Caches;
import com.sq.ic.common.enhance.MpLambdaQueryWrapper;
import com.sq.ic.common.enhance.MpPage;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.Constants;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.common.util.Streams;
import com.sq.ic.common.util.Strings;
import com.sq.ic.mapper.SysUserMapper;
import com.sq.ic.pojo.dto.SysUserDto;
import com.sq.ic.pojo.list.SysUserVo;
import com.sq.ic.pojo.po.SysResource;
import com.sq.ic.pojo.po.SysRole;
import com.sq.ic.pojo.po.SysUser;
import com.sq.ic.pojo.po.SysUserRole;
import com.sq.ic.pojo.result.CodeMsg;
import com.sq.ic.pojo.vo.LoginVo;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.req.LoginReqVo;
import com.sq.ic.pojo.vo.req.page.SysUserPageReqVo;
import com.sq.ic.pojo.vo.req.page.SysUserReqVo;
import com.sq.ic.service.SysResourceService;
import com.sq.ic.service.SysRoleService;
import com.sq.ic.service.SysUserRoleService;
import com.sq.ic.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SysUserServiceImpl
        extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysResourceService resourceService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Override
    public LoginVo login(LoginReqVo reqVo) {
        // 查出用户
        MpLambdaQueryWrapper<SysUser> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName, reqVo.getUsername());
        SysUser user = baseMapper.selectOne(wrapper);

        if (user == null) { // 用户不存在
            return JsonVos.raise(CodeMsg.WRONG_USERNAME);
        }

        if (!user.getPassword().equals(reqVo.getPassword())) { // 密码不正确
            return JsonVos.raise(CodeMsg.WRONG_PASSWORD);
        }

        if (user.getStatus() == Constants.SysUserStatus.LOCKED) { // 账号锁定
            return JsonVos.raise(CodeMsg.USER_LOCKED);
        }

        // 更新登录时间
        user.setLoginTime(new Date());
        baseMapper.updateById(user);

        SysUserDto dto = new SysUserDto();
        dto.setUser(user);
        // 根据userId查询所有的SysRole
        List<SysRole> roles = roleService.listByUserId(user.getId());

        if (!CollectionUtils.isEmpty(roles)) {
            dto.setRoles(roles);

            List<Short> roleIds = Streams.map(roles, SysRole::getId);
            // roleId查询所有的SysResource
            List<SysResource> resources = resourceService.listByRoleIds(roleIds);
            dto.setResources(resources);
        }
        // 生成Token，发送Token给用户
        String token = UUID.randomUUID().toString();
        // 添加缓存
        Caches.putToken(token, dto);

        // 返回给客户端的具体数据
        LoginVo vo = MapStructs.INSTANCE.po2loginVo(user);
        vo.setToken(token);
        return vo;
    }

    @Override
    public boolean saveOrUpdate(SysUserReqVo reqVo) {
        SysUser user = MapStructs.INSTANCE.reqVo2po(reqVo);

        if (!saveOrUpdate(user)) return false;

        Integer id = reqVo.getId();
        if (id != null && id > 0) { // 更新
            // 将更新成功的用户token从缓存中删除
            String token = Caches.get(id);
            Caches.removeToken(token);

            //删除当前用户的所有角色信息
            userRoleService.removeByUserId(reqVo.getId());
        }
        // 保存角色信息
        String roleIdsStr = reqVo.getRoleIds();
        if (Strings.isEmpty(roleIdsStr)) return true;

        // 更新user_role表
        String[] roleIds = roleIdsStr.split(",");
        List<SysUserRole> userRoles = new ArrayList<>();
        Integer userId = user.getId();
        for (String roleId : roleIds) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(Short.parseShort(roleId));
            userRoles.add(userRole);
        }
        return userRoleService.saveBatch(userRoles);
    }

    @Override
    public PageVo<SysUserVo> list(SysUserPageReqVo pageReqVo) {
        MpLambdaQueryWrapper<SysUser> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(pageReqVo.getKeyword(), SysUser::getUserName, SysUser::getNickName);
        wrapper.orderByDesc(SysUser::getId);
        MpPage<SysUser> page = baseMapper.selectPage(new MpPage<>(pageReqVo), wrapper);
        return page.buildVo(MapStructs.INSTANCE::po2vo);
    }
}
