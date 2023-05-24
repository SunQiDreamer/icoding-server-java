package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.enhance.MpLambdaQueryWrapper;
import com.sq.ic.common.enhance.MpPage;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.Streams;
import com.sq.ic.mapper.StudentMapper;
import com.sq.ic.pojo.po.Grade;
import com.sq.ic.pojo.po.Hobby;
import com.sq.ic.pojo.po.Student;
import com.sq.ic.pojo.po.StudentHobby;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.list.GradeVo;
import com.sq.ic.pojo.vo.list.HobbyVo;
import com.sq.ic.pojo.vo.list.StudentVo;
import com.sq.ic.pojo.vo.req.page.StudentPageReqVo;
import com.sq.ic.pojo.vo.req.save.StudentReqVo;
import com.sq.ic.service.GradeService;
import com.sq.ic.service.HobbyService;
import com.sq.ic.service.StudentHobbyService;
import com.sq.ic.service.StudentService;

import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

@Service
@Transactional
public class StudentServiceImpl
        extends ServiceImpl<StudentMapper, Student>
        implements StudentService {

    @Autowired
    private StudentHobbyService studentHobbyService;

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private GradeService gradeService;

    @Override
    @Transactional(readOnly = true)
    public List<StudentVo> listStudents() {
        List<Student> students = list();
        List<StudentVo> studentVos = Streams.map(students, (student) -> {
            StudentVo studentVo = MapStructs.INSTANCE.po2vo(student);

            Grade grade = gradeService.getById(student.getGradeId());
            GradeVo gradeVo = MapStructs.INSTANCE.po2vo(grade);
            studentVo.setGrade(gradeVo);

            List<HobbyVo> hobbyVos = hobbyVosByStudentId(student.getId());
            studentVo.setHobbyVos(hobbyVos);

            return studentVo;
        });
        return studentVos;
    }

    public List<HobbyVo> hobbyVosByStudentId(Integer studentId) {
        if (studentId == null)
            return null;

        return hobbyService.listByStudentId(studentId);
    }

    @Override
    public boolean saveOrUpdate(StudentReqVo reqVo) {
        // 保存student
        Student student = MapStructs.INSTANCE.reqVo2po(reqVo);
        if (!saveOrUpdate(student)) {
            return false;
        }

        String hobbyIdsString = reqVo.getHobbyIds();
        if (Strings.isEmpty(hobbyIdsString)) {
            return true;
        }
        // 保存hobby
        String[] hobbyIds = hobbyIdsString.split(",");
        List<StudentHobby> studentHobbies = new ArrayList<>();
        Integer studentId = student.getId();
        for (String hobbyId : hobbyIds) {
            StudentHobby studentHobby = new StudentHobby();
            studentHobby.setStudentId(studentId);
            studentHobby.setHobbyId(Short.parseShort(hobbyId));
            studentHobbies.add(studentHobby);
        }
        return studentHobbyService.saveBatch(studentHobbies);
    }

    // @Override
    // @Transactional(readOnly = true)
    // public PageVo<StudentVo> list(StudentPageReqVo query) {
    // return baseMapper.selectList(query);

    // 查询条件
    // MpLambdaQueryWrapper<Student> wrapper = new MpLambdaQueryWrapper<>();
    // wrapper.like(query.getName(), Student::getName);

    // if (query.getSex() != null) {
    // wrapper.eq(Student::getSex, query.getSex());
    // }

    // if (query.getGradeId() != null) {
    // wrapper.eq(Student::getGradeId, query.getGradeId());
    // }
    // // 排序
    // wrapper.orderByDesc(Student::getId);

    // MpPage<Student> page = baseMapper.selectPage(new MpPage<Student>(query),
    // wrapper);
    // List<StudentVo> studentVos = new ArrayList<>();
    // page.getRecords().forEach(student -> studentVos.add(getStudentVo(student)));
    // return page.commonBuldVo(studentVos);
    // }

    @Override
    public StudentVo getStudentById(Integer id) {
        Student student = baseMapper.selectById(id);

        StudentVo studentVo = MapStructs.INSTANCE.po2vo(student);

        Grade grade = gradeService.getById(student.getGradeId());
        GradeVo gradeVo = MapStructs.INSTANCE.po2vo(grade);
        studentVo.setGrade(gradeVo);

        List<HobbyVo> hobbyVos = hobbyVosByStudentId(student.getId());
        studentVo.setHobbyVos(hobbyVos);

        return studentVo;
    }

    @Override
    public PageVo<StudentVo> list(StudentPageReqVo pageReqVo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public boolean remove(@NotBlank(message = "id不能为空") Integer id) {
        // 删除学生
        if (!removeById(id))
            return false;

        if (!studentHobbyService.removeByStudentId(id))
            return false;

        return true;
    }

}
