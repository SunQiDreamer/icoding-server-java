package com.sq.ic.common.mapStruct;

import com.sq.ic.pojo.po.*;
import com.sq.ic.pojo.vo.LoginVo;
import com.sq.ic.pojo.vo.list.*;
import com.sq.ic.pojo.vo.req.page.SysUserReqVo;
import com.sq.ic.pojo.vo.req.save.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

        LoginVo po2loginVo(SysUser user);

        SysRole reqVo2po(SysRoleReqVo sysRoleReqVo);

        SysRoleVo po2vo(SysRole sysRole);

        @Mapping(source = "loginTime", target = "loginTime", qualifiedBy = MapStructFormatter.Date2Millis.class)
        SysUserVo po2vo(SysUser sysUser);

        Teacher req2po(TeacherReqVo reqVo);
}
