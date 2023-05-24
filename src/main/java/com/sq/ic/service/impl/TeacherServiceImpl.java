package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.enhance.MpLambdaQueryWrapper;
import com.sq.ic.common.enhance.MpPage;
import com.sq.ic.common.exception.CommonException;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.Streams;
import com.sq.ic.common.util.Strings;
import com.sq.ic.mapper.TeacherMapper;
import com.sq.ic.pojo.po.Course;
import com.sq.ic.pojo.po.Teacher;
import com.sq.ic.pojo.po.TeacherGrade;
import com.sq.ic.pojo.po.TeacherHobby;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.list.CourseVo;
import com.sq.ic.pojo.vo.list.GradeVo;
import com.sq.ic.pojo.vo.list.HobbyVo;
import com.sq.ic.pojo.vo.list.TeacherVo;
import com.sq.ic.pojo.vo.req.page.TeacherPageReqVo;
import com.sq.ic.pojo.vo.req.save.TeacherReqVo;
import com.sq.ic.service.CourseService;
import com.sq.ic.service.GradeService;
import com.sq.ic.service.HobbyService;
import com.sq.ic.service.TeacherGradeService;
import com.sq.ic.service.TeacherHobbyService;
import com.sq.ic.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl
        extends ServiceImpl<TeacherMapper, Teacher>
        implements TeacherService {

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private TeacherGradeService teacherGradeService;

    @Autowired
    private TeacherHobbyService teacherHobbyService;

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
        List<TeacherVo> teacherVos = new ArrayList<>();
        for (Teacher teacher : teachers) {
            TeacherVo teacherVo = MapStructs.INSTANCE.po2vo(teacher);

            Course course = courseService.getById(teacher.getCourseId());
            CourseVo courseVo = MapStructs.INSTANCE.po2vo(course);
            teacherVo.setCourse(courseVo);

            List<GradeVo> gradeVos = gradeVosByTeacherId(teacher.getId());
            teacherVo.setGrades(gradeVos);

            List<HobbyVo> hobbyVos = hobbyVosByTeacherId(teacher.getId());
            teacherVo.setHobbies(hobbyVos);

            teacherVos.add(teacherVo);
        }
        return teacherVos;
    }

    private List<GradeVo> gradeVosByTeacherId(Integer teacherId) {
        if (teacherId == null)
            return null;

        return gradeService.listByTeacherId(teacherId);
    }

    private List<HobbyVo> hobbyVosByTeacherId(Integer teacherId) {
        if (teacherId == null)
            return null;

        return hobbyService.listByTeacherId(teacherId);
    }

    @Override
    public boolean saveOrUpdate(TeacherReqVo reqVo) {
        // 保存老师
        Teacher teacher = MapStructs.INSTANCE.req2po(reqVo);
        if (!saveOrUpdate(teacher)) {
            return false;
        }

        // 保存班级
        if (Strings.isEmpty(reqVo.getGradeIds())) {
            throw new CommonException("班级不能为空");
        }

        List<String> gradeIds = Arrays.asList(reqVo.getGradeIds().split(","));
        List<TeacherGrade> teacherGrades = Streams.map(gradeIds, gradeId -> {
            TeacherGrade teacherGrade = new TeacherGrade();
            teacherGrade.setTeacherId(teacher.getId());
            teacherGrade.setGradeId(Short.parseShort(gradeId));
            return teacherGrade;
        });
        if (teacher.getId() != null) {
            // 更新操作，删除teacher_grade 表中的数据
            teacherGradeService.removeByTeacherId(teacher.getId());
        }
        if (!teacherGradeService.saveBatch(teacherGrades)) {
            return false;
        }

        // 保存爱好
        if (!Strings.isEmpty(reqVo.getHobbyIds())) {
            List<String> hobbyIds = Arrays.asList(reqVo.getHobbyIds().split(","));
            List<TeacherHobby> teacherHobbies = Streams.map(hobbyIds, hobbyId -> {
                TeacherHobby teacherHobby = new TeacherHobby();
                teacherHobby.setTeacherId(teacher.getId());
                teacherHobby.setHobbyId(Short.parseShort(hobbyId));
                return teacherHobby;
            });
            if (teacher.getId() != null) {
                // 更新操作，删除teacher_grade 表中的数据
                teacherHobbyService.removeByTeacherId(teacher.getId());
            }
            if (!teacherHobbyService.saveBatch(teacherHobbies)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public TeacherVo getTeacherById(Integer id) {
        Teacher teacher = getById(id);
        TeacherVo teacherVo = MapStructs.INSTANCE.po2vo(teacher);

        Course course = courseService.getById(teacher.getCourseId());
        CourseVo courseVo = MapStructs.INSTANCE.po2vo(course);
        teacherVo.setCourse(courseVo);

        List<GradeVo> gradeVos = gradeVosByTeacherId(teacher.getId());
        teacherVo.setGrades(gradeVos);

        List<HobbyVo> hobbyVos = hobbyVosByTeacherId(teacher.getId());
        teacherVo.setHobbies(hobbyVos);

        return teacherVo;

    }

    @Override
    public boolean updateMainTeacher(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMainTeacher'");
    }
}
