package com.sq.ic.common.mapStruct;

import com.sq.ic.pojo.list.*;
import com.sq.ic.pojo.po.*;
import com.sq.ic.pojo.vo.req.save.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ReqVo -> Po
 * Po -> Vo
 */
@Mapper(uses = {
        MapStructFormatter.class
})
public interface MapStructs {
    MapStructs INSTANCE = Mappers.getMapper(MapStructs.class);

    Student reqVo2po(StudentReqVo vo);

    StudentVo po2vo(Student po);

    Grade reqVo2po(GradeReqVo gradeReqVo);

    GradeVo po2vo(Grade po);

    Teacher reqVo2po(TeacherReqVo teacherReqVo);

    TeacherVo po2vo(Teacher teacher);

    Course reqVo2po(CourseReqVo courseReqVo);

    CourseVo po2vo(Course course);

    HobbyVo po2vo(Hobby hobby);

    Hobby reqVo2po(HobbyReqVo hobbyReqVo);
}
