package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.enhance.MpLambdaQueryWrapper;
import com.sq.ic.common.enhance.MpPage;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.Streams;
import com.sq.ic.mapper.TeacherMapper;
import com.sq.ic.pojo.list.TeacherVo;
import com.sq.ic.pojo.po.Grade;
import com.sq.ic.pojo.po.Teacher;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.req.page.TeacherPageReqVo;
import com.sq.ic.service.GradeService;
import com.sq.ic.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherServiceImpl
        extends ServiceImpl<TeacherMapper, Teacher>
        implements TeacherService {

    @Autowired
    private GradeService gradeService;

    @Override
    @Transactional(readOnly = true)
    public PageVo<TeacherVo> list(TeacherPageReqVo query) {
        MpLambdaQueryWrapper<Teacher> wrapper = new MpLambdaQueryWrapper<>();
        if (!query.getName().isEmpty()) {
            wrapper.like(query.getName(), Teacher::getName);
        }
        if (query.getCourseId() != null) {
            wrapper.eq(Teacher::getCourseId, query.getCourseId());
        }
        if (query.getIsMain() != null) {
            wrapper.eq(Teacher::getIsMain, query.getIsMain());
        }
        if (query.getSex() != null) {
            wrapper.eq(Teacher::getSex, query.getSex());
        }
        return baseMapper
                .selectPage(new MpPage<>(query), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    public List<TeacherVo> teachers() {
        List<Teacher> teachers = list();
        for (Teacher teacher : teachers) {
            
        }
    }
}
