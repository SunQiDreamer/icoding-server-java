package com.sq.ic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.Student;
import com.sq.ic.pojo.po.list.StudentVo;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.req.page.StudentPageReqVo;
import com.sq.ic.pojo.vo.req.save.StudentReqVo;

public interface StudentService extends IService<Student> {
    PageVo<StudentVo> list(StudentPageReqVo pageReqVo);
}