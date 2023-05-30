package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.mapper.GradeMapper;
import com.sq.ic.mapper.TeacherMapper;
import com.sq.ic.pojo.po.Grade;
import com.sq.ic.pojo.po.Teacher;
import com.sq.ic.pojo.vo.list.GradeVo;
import com.sq.ic.pojo.vo.list.TeacherVo;
import com.sq.ic.pojo.vo.req.save.GradeReqVo;
import com.sq.ic.service.GradeService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GradeServiceImpl
        extends ServiceImpl<GradeMapper, Grade>
        implements GradeService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public boolean addMainTeacher(GradeReqVo reqVo) {
        LambdaQueryWrapper<Grade> wrapper = new LambdaQueryWrapper<Grade>();
        wrapper.eq(Grade::getId, reqVo.getId());
        Grade grade = baseMapper.selectOne(wrapper);

        Integer mainTeacherId = reqVo.getMainTeacherId();
        grade.setMainTeacherId(mainTeacherId);

        LambdaUpdateWrapper<Grade> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Grade::getId, reqVo.getId());
        int record = baseMapper.update(grade, updateWrapper);
        return record > 0;
    }

    @Override
    public List<GradeVo> listGradeVos() {
        List<Grade> grades = list();
        List<GradeVo> gradeVos = new ArrayList<>();
        for (Grade grade : grades) {
            GradeVo gradeVo = MapStructs.INSTANCE.po2vo(grade);

            Teacher teacher = teacherMapper.selectById(grade.getMainTeacherId());
            TeacherVo teacherVo = MapStructs.INSTANCE.po2vo(teacher);

            gradeVo.setMainTeacher(teacherVo);
            gradeVos.add(gradeVo);
        }

        return gradeVos;
    }

    @Override
    public List<GradeVo> listByTeacherId(Integer teacherId) {
        return baseMapper.selectListByTeacherId(teacherId);
    }

    @Override
    public boolean saveOrUpdate(GradeReqVo reqVo) {
        Grade grade = MapStructs.INSTANCE.reqVo2po(reqVo);
        if (!saveOrUpdate(grade)) {
            return false;
        }

        Teacher teacher = new Teacher();
        teacher.setId(grade.getMainTeacherId());
        teacher.setIsMain(true);

        teacherMapper.updateById(teacher);

        return true;
    }

    @Override
    public GradeVo getGradeById(Integer id) {
        return baseMapper.getGradeById(id);
    }

}
