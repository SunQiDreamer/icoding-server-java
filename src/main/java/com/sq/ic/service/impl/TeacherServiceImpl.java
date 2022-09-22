package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.mapper.TeacherMapper;
import com.sq.ic.pojo.po.Grade;
import com.sq.ic.pojo.po.Teacher;
import com.sq.ic.service.GradeService;
import com.sq.ic.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherServiceImpl
        extends ServiceImpl<TeacherMapper, Teacher>
        implements TeacherService {

    @Autowired
    private GradeService gradeService;




}
