package com.sq.ic.common.mapStruct;

import com.sq.ic.pojo.po.Grade;
import com.sq.ic.pojo.po.Student;
import com.sq.ic.pojo.list.GradeVo;
import com.sq.ic.pojo.list.StudentVo;
import com.sq.ic.pojo.vo.req.save.GradeReqVo;
import com.sq.ic.pojo.vo.req.save.StudentReqVo;
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
}
