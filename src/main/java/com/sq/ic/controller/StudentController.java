package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.pojo.po.Student;
import com.sq.ic.pojo.result.CodeMsg;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.JsonVo;
import com.sq.ic.pojo.vo.list.StudentVo;
import com.sq.ic.pojo.vo.req.page.StudentPageReqVo;
import com.sq.ic.pojo.vo.req.save.StudentReqVo;
import com.sq.ic.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/students")
public class StudentController extends BaseController<Student, StudentReqVo> {
    @Autowired
    private StudentService service;

    @GetMapping
    @ApiOperation("查询所有")
    public DataJsonVo<List<StudentVo>> list() {
        List<StudentVo> studentVos = service.listStudents();
        return new DataJsonVo<>(studentVos);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public DataJsonVo<List<StudentVo>> page(StudentPageReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取学生")
    public DataJsonVo<StudentVo> student(@PathVariable Integer id) {
        StudentVo studentVo = service.getStudentById(id);
        return new DataJsonVo<>(studentVo);
    }

    @Override
    public JsonVo save(StudentReqVo reqVo) {
        if (service.saveOrUpdate(reqVo)) {
            return JsonVos.ok();
        } else {
            return JsonVos.raise(CodeMsg.SAVE_ERROR);
        }
    }

    @Override
    public JsonVo remove(@NotBlank(message = "id不能为空") @RequestParam String id) {
        if (service.remove(Integer.parseInt(id))) {
            return JsonVos.ok();
        } else {
            return JsonVos.raise(CodeMsg.REMOVE_ERROR);
        }
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
