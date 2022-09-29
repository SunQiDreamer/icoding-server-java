package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.common.util.Streams;
import com.sq.ic.pojo.list.SysRoleVo;
import com.sq.ic.pojo.po.SysRole;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.req.page.SysRolePageReqVo;
import com.sq.ic.pojo.vo.req.save.SysRoleReqVo;
import com.sq.ic.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/sysRoles")
@Api(tags = "角色")
public class SysRoleController extends BaseController<SysRole, SysRoleReqVo> {

    @Autowired
    private SysRoleService service;

    @GetMapping("/ids")
    @ApiOperation("根据用户id获取角色id")
    public DataJsonVo<List<Short>> ids(Integer userId) {
        return JsonVos.ok(service.listIds(userId));
    }

    @GetMapping("/list")
    @ApiOperation("查询所有")
    public DataJsonVo<List<SysRoleVo>> list() {
        List<SysRole> roles = service.list();
        List<SysRoleVo> roleVos = Streams.map(roles, MapStructs.INSTANCE::po2vo);
        return JsonVos.ok(roleVos);
    }

    @GetMapping()
    @ApiOperation("分页查询")
    public DataJsonVo<List<SysRoleVo>> list(SysRolePageReqVo pageReqVo) {
        List<SysRole> roles = service.list(pageReqVo);
        List<SysRoleVo> roleVos = Streams.map(roles, MapStructs.INSTANCE::po2vo);
        return JsonVos.ok(roleVos);
    }

    @Override
    protected IService<SysRole> getService() {
        return service;
    }

    @Override
    protected Function<SysRoleReqVo, SysRole> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
