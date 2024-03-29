package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.pojo.po.Teacher;
import com.sq.ic.pojo.result.CodeMsg;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.JsonVo;
import com.sq.ic.pojo.vo.list.StudentVo;
import com.sq.ic.pojo.vo.list.TeacherVo;
import com.sq.ic.pojo.vo.req.page.TeacherPageReqVo;
import com.sq.ic.pojo.vo.req.save.TeacherReqVo;
import com.sq.ic.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/teachers")
public class TeacherController extends BaseController<Teacher, TeacherReqVo> {

    @Autowired
    private TeacherService service;

    @GetMapping
    @ApiOperation("查询所有")
    public DataJsonVo<List<TeacherVo>> list() {
        List<TeacherVo> teacherVos = service.teachers();
        return JsonVos.ok(teacherVos);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public DataJsonVo<List<TeacherVo>> page(TeacherPageReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取老师")
    public DataJsonVo<TeacherVo> student(@PathVariable Integer id) {
        TeacherVo teacherVo = service.getTeacherById(id);
        return new DataJsonVo<>(teacherVo);
    }

    @Override
    public JsonVo save(TeacherReqVo reqVo) {
        if (service.saveOrUpdate(reqVo)) {
            return JsonVos.ok();
        } else {
            return JsonVos.raise(CodeMsg.SAVE_ERROR);
        }

    }

    @Override
    protected IService<Teacher> getService() {
        return service;
    }

    @Override
    protected Function<TeacherReqVo, Teacher> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
