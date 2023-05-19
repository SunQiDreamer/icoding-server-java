package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.Streams;
import com.sq.ic.pojo.po.Hobby;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.list.HobbyVo;
import com.sq.ic.pojo.vo.req.save.HobbyReqVo;
import com.sq.ic.service.HobbyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/hobby")
public class HobbyController extends BaseController<Hobby, HobbyReqVo> {

    @Autowired
    private HobbyService service;

    @GetMapping
    @ApiOperation("查询所有")
    public DataJsonVo<List<HobbyVo>> list() {
        List<Hobby> hobbies = service.list();
        List<HobbyVo> hobbyVos = Streams.map(hobbies, MapStructs.INSTANCE::po2vo);
        return new DataJsonVo<>(hobbyVos);
    }

    @Override
    protected IService<Hobby> getService() {
        return service;
    }

    @Override
    protected Function<HobbyReqVo, Hobby> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
