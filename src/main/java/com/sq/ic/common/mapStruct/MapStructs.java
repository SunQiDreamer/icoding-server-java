package com.sq.ic.common.mapStruct;

import com.sq.ic.pojo.list.*;
import com.sq.ic.pojo.po.*;
import com.sq.ic.pojo.vo.req.page.SysUserReqVo;
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
    
    Grade reqVo2po(GradeReqVo gradeReqVo);
    
    Teacher reqVo2po(TeacherReqVo teacherReqVo);

    TeacherVo po2vo(Teacher teacher);

    Course reqVo2po(CourseReqVo courseReqVo);

    CourseVo po2vo(Course course);

    Hobby reqVo2po(HobbyReqVo hobbyReqVo);

    HobbyVo po2vo(Hobby hobby);

    GradeVo po2vo(Grade grade);

    StudentVo po2vo(Student student);

    Student reqVo2po(StudentReqVo vo);

    SysResource reqVo2po(SysResourceReqVo sysResourceReqVo);

    SysResourceVo po2vo(SysResource sysResource);

    SysUser reqVo2po(SysUserReqVo sysUserReqVo);
}
