package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.enhance.MpLambdaQueryWrapper;
import com.sq.ic.mapper.StudentMapper;
import com.sq.ic.pojo.po.Student;
import com.sq.ic.pojo.po.list.StudentVo;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.req.page.StudentPageReqVo;
import com.sq.ic.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl
        extends ServiceImpl<StudentMapper, Student>
        implements StudentService {
    @Override
    public PageVo<StudentVo> list(StudentPageReqVo pageReqVo) {
        MpLambdaQueryWrapper<Student> wrapper = new MpLambdaQueryWrapper<>();


    }
}
