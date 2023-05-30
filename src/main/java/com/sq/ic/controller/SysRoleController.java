package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.Constants;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.common.util.Streams;
import com.sq.ic.pojo.po.SysResource;
import com.sq.ic.pojo.po.SysRole;
import com.sq.ic.pojo.result.CodeMsg;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.JsonVo;
import com.sq.ic.pojo.vo.PageJsonVo;
import com.sq.ic.pojo.vo.list.SysRoleTreeVo;
import com.sq.ic.pojo.vo.list.SysRoleVo;
import com.sq.ic.pojo.vo.req.page.SysRolePageReqVo;
import com.sq.ic.pojo.vo.req.save.SysResourceVo;
import com.sq.ic.pojo.vo.req.save.SysRoleReqVo;
import com.sq.ic.service.SysResourceService;
import com.sq.ic.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/sysRoles")
@Api(tags = "角色")
public class SysRoleController extends BaseController<SysRole, SysRoleReqVo> {

    @Autowired
    private SysRoleService service;

    @Autowired
    private SysResourceService resourceService;

    @GetMapping("/ids")
    @ApiOperation("根据用户id获取角色id")
    @RequiresPermissions(Constants.Permisson.SYS_ROLE_LIST)
    public DataJsonVo<List<Short>> ids(Integer userId) {
        return JsonVos.ok(service.listIds(userId));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据用户id获取角色id")
    // @RequiresPermissions(Constants.Permisson.SYS_ROLE_LIST)
    public DataJsonVo<SysRoleVo> getByRoleId(@PathVariable Integer id) {
        SysRole role = service.getById(id);
        SysRoleVo roleVo = MapStructs.INSTANCE.po2vo(role);

        List<Short> roleIds = new ArrayList<>();
        roleIds.add(role.getId());
        List<SysResource> resources = resourceService.listByRoleIds(roleIds);
        if (resources != null) {
            List<SysResourceVo> resourceVos = Streams.map(resources,
                    MapStructs.INSTANCE::po2vo);
            roleVo.setResources(resourceVos);
        }

        return JsonVos.ok(roleVo);
    }

    @GetMapping("/list")
    @ApiOperation("查询所有")
    // @RequiresPermissions(Constants.Permisson.SYS_ROLE_LIST)
    public DataJsonVo<List<SysRoleVo>> list() {
        List<SysRole> roles = service.list();
        List<SysRoleVo> roleVos = Streams.map(roles, MapStructs.INSTANCE::po2vo);
        return JsonVos.ok(roleVos);
    }

    @GetMapping()
    @ApiOperation("分页查询")
    @RequiresPermissions(Constants.Permisson.SYS_ROLE_LIST)
    public PageJsonVo<SysRoleVo> list(SysRolePageReqVo pageReqVo) {
        return JsonVos.ok(service.list(pageReqVo));
    }

    @Override
    // @RequiresPermissions(value = {
    // Constants.Permisson.SYS_ROLE_ADD,
    // Constants.Permisson.SYS_ROLE_UPDATE
    // }, logical = Logical.AND)
    public JsonVo save(SysRoleReqVo reqVo) {

        System.out.println("reqVo" + reqVo);

        if (service.saveOrUpdate(reqVo)) {
            return JsonVos.ok(CodeMsg.SAVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.SAVE_ERROR);
        }
    }

    @Override
    @RequiresPermissions(Constants.Permisson.SYS_ROLE_REMOVE)
    public JsonVo remove(String id) {
        return super.remove(id);
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
