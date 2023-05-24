package com.sq.ic.service.impl;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.mapper.TeacherGradeMapper;
import com.sq.ic.pojo.po.TeacherGrade;
import com.sq.ic.service.TeacherGradeService;

@Service
@Transactional
public class TeacherGradeServiceImpl
        extends ServiceImpl<TeacherGradeMapper, TeacherGrade>
        implements TeacherGradeService {

    @Override
    public boolean removeByTeacherId(@NotBlank(message = "id不能为空") Integer id) {
        if (baseMapper.selectListByTeacherId(id).size() > 0) {
            return baseMapper.deleteByTeacherId(id);
        }
        return true;
    }

}
