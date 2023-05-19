package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.common.util.Streams;
import com.sq.ic.pojo.po.Grade;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.JsonVo;
import com.sq.ic.pojo.vo.list.GradeVo;
import com.sq.ic.pojo.vo.req.save.GradeReqVo;
import com.sq.ic.service.GradeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/grades")
public class GradeController extends BaseController<Grade, GradeReqVo> {

    @Autowired
    private GradeService service;

    @GetMapping("/list")
    @ApiOperation("查询所有")
    public DataJsonVo<List<GradeVo>> list() {
        List<Grade> grades = service.list();
        List<GradeVo> gradeVos = Streams.map(grades, MapStructs.INSTANCE::po2vo);
        return JsonVos.ok(gradeVos);
    }

    @PostMapping("/addMainTeacher")
    @ApiOperation("添加班主任")
    public JsonVo addMainTeacher(GradeReqVo reqVo) {
        return service.addMainTeacher(reqVo);
    }

    @Override
    protected IService<Grade> getService() {
        return service;
    }

    @Override
    protected Function<GradeReqVo, Grade> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
