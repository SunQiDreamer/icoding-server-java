package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.pojo.po.Student;
import com.sq.ic.pojo.po.list.StudentVo;
import com.sq.ic.pojo.vo.JsonVo;
import com.sq.ic.pojo.vo.PageJsonVo;
import com.sq.ic.pojo.vo.req.page.StudentPageReqVo;
import com.sq.ic.pojo.vo.req.save.StudentReqVo;
import com.sq.ic.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping("/students")
@Api(tags = "学生")
public class StudentController extends BaseController<Student, StudentReqVo> {
    @Autowired
    private StudentService service;

    @PostMapping("/save")
    @ApiOperation("保存")
    public JsonVo save(StudentReqVo student) {
        return super.save(student);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public PageJsonVo list(StudentPageReqVo pageReqVo) {
//        service.list(pageReqVo);

    }


    @Override
    protected IService<Student> getService() {
        return service;
    }

    @Override
    protected Function<StudentReqVo, Student> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
