package com.sq.ic.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.ic.pojo.po.SysUser;
import com.sq.ic.pojo.vo.list.SysUserVo;

public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUserVo> listUser();

    SysUserVo user(Integer id);

}
