package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.common.util.Streams;
import com.sq.ic.pojo.list.SysResourceTreeVo;
import com.sq.ic.pojo.po.SysResource;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.PageJsonVo;
import com.sq.ic.pojo.vo.req.page.SysResourcePageReqVo;
import com.sq.ic.pojo.vo.req.save.SysResourceReqVo;
import com.sq.ic.pojo.vo.req.save.SysResourceVo;
import com.sq.ic.service.SysResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/sysResources")
public class SysResourceController extends BaseController<SysResource, SysResourceReqVo> {

    @Autowired
    private SysResourceService service;

    @GetMapping("/ids")
    @ApiOperation("根据roleId获取资源id")
    public DataJsonVo<List<Short>> ids(Integer roleId) {
        return JsonVos.ok(service.listIds(roleId));
    }

    @GetMapping
    @ApiOperation("分页查询")
    public PageJsonVo<SysResourceVo> list(SysResourcePageReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }

    @GetMapping("/list")
    @ApiOperation("查询所有")
    public DataJsonVo<List<SysResourceVo>> list() {
        List<SysResource> sysResources = service.list();
        List<SysResourceVo> sysResourceVos = Streams.map(sysResources, MapStructs.INSTANCE::po2vo);
        return JsonVos.ok(sysResourceVos);
    }

    @GetMapping("/listTree")
    @ApiOperation("查询所有（树状结构结构展示）")
    public DataJsonVo<List<SysResourceTreeVo>> listTree() {
        return JsonVos.ok(service.listTree());
    }

    @GetMapping("/listParents")
    @ApiOperation("查询所有的父资源（目录，菜单）")
    public DataJsonVo<List<SysResourceVo>> listParents() {
        return JsonVos.ok(service.listParents());
    }

    @Override
    protected IService<SysResource> getService() {
        return service;
    }

    @Override
    protected Function<SysResourceReqVo, SysResource> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
