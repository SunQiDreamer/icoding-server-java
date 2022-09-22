package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.pojo.po.Teacher;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.req.save.TeacherReqVo;
import com.sq.ic.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController<Teacher, TeacherReqVo> {

    @Autowired
    private TeacherService service;

//    public DataJsonVo

    @Override
    protected IService<Teacher> getService() {
        return service;
    }

    @Override
    protected Function<TeacherReqVo, Teacher> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
