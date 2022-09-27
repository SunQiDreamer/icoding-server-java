package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.enhance.MpLambdaQueryWrapper;
import com.sq.ic.common.enhance.MpPage;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.mapper.StudentMapper;
import com.sq.ic.pojo.po.Hobby;
import com.sq.ic.pojo.po.Student;
import com.sq.ic.pojo.list.StudentVo;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.req.page.StudentPageReqVo;
import com.sq.ic.service.HobbyService;
import com.sq.ic.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl
        extends ServiceImpl<StudentMapper, Student>
        implements StudentService {

    @Autowired
    private HobbyService hobbyService;

    @Override
    @Transactional(readOnly = true)
    public PageVo<StudentVo> list(StudentPageReqVo query) {
        // 查询条件
        MpLambdaQueryWrapper<Student> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(query.getName(), Student::getName);

        if (query.getSex() != null) {
            wrapper.eq(Student::getSex, query.getSex());
        }

        if (query.getGradeId() != null) {
            wrapper.eq(Student::getGradeId, query.getGradeId());
        }
        // 排序
        wrapper.orderByDesc(Student::getId);

        MpPage<Student> page = baseMapper.selectPage(new MpPage<Student>(query), wrapper);
        List<StudentVo> studentVos = new ArrayList<>();
        for (Student student : page.getRecords()) {
            StudentVo studentVo = MapStructs.INSTANCE.po2vo(student);

            String hobbyIdStr = student.getHobbyIds();
            if (hobbyIdStr != null) {
                List<String> hobbyIds = List.of(hobbyIdStr.split(","));
                List<Hobby> hobbies = hobbyService.listByIds(hobbyIds);
                studentVo.setHobbies(hobbies);
            }
            studentVos.add(studentVo);
        }
        return page.commonBuldVo(studentVos);
    }

    @Override
    public StudentVo student(Integer id) {
        Student student = baseMapper.selectById(id);
        StudentVo studentVo = MapStructs.INSTANCE.po2vo(student);

        String hobbyIdStr = student.getHobbyIds();
        if (hobbyIdStr != null) {
            List<String> hobbyIds = List.of(hobbyIdStr.split(","));
            List<Hobby> hobbies = hobbyService.listByIds(hobbyIds);
            studentVo.setHobbies(hobbies);
        }
        return studentVo;
    }
}
