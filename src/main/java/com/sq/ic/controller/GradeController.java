package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.pojo.po.Grade;
import com.sq.ic.pojo.vo.req.save.GradeReqVo;
import com.sq.ic.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping("/grades")
public class GradeController extends BaseController<Grade, GradeReqVo> {

    @Autowired
    private GradeService service;

    @Override
    protected IService<Grade> getService() {
        return service;
    }

    @Override
    protected Function<GradeReqVo, Grade> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
