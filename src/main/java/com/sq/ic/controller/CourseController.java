package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.common.util.Streams;
import com.sq.ic.pojo.po.Course;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.list.CourseVo;
import com.sq.ic.pojo.vo.req.save.CourseReqVo;
import com.sq.ic.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/courses")
public class CourseController extends BaseController<Course, CourseReqVo> {

    @Autowired
    private CourseService service;

    @GetMapping
    @ApiOperation("查询所有")
    public DataJsonVo<List<CourseVo>> list() {
        List<CourseVo> courseVos = Streams.map(service.list(), MapStructs.INSTANCE::po2vo);
        return JsonVos.ok(courseVos);
    }

    @Override
    protected IService<Course> getService() {
        return service;
    }

    @Override
    protected Function<CourseReqVo, Course> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
