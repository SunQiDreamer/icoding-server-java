package com.sq.ic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.Student;
import com.sq.ic.pojo.list.StudentVo;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.req.page.StudentPageReqVo;

public interface StudentService extends IService<Student> {
    PageVo<StudentVo> list(StudentPageReqVo pageReqVo);

    StudentVo student(Integer id);
}