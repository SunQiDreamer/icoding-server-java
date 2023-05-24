package com.sq.ic.service;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.Student;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.list.StudentVo;
import com.sq.ic.pojo.vo.req.page.StudentPageReqVo;
import com.sq.ic.pojo.vo.req.save.StudentReqVo;

public interface StudentService extends IService<Student> {
    PageVo<StudentVo> list(StudentPageReqVo pageReqVo);

    StudentVo getStudentById(Integer id);

    boolean saveOrUpdate(StudentReqVo reqVo);

    List<StudentVo> listStudents();

    boolean remove(@NotBlank(message = "id不能为空") Integer id);
}