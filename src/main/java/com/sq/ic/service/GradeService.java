package com.sq.ic.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.Grade;
import com.sq.ic.pojo.vo.list.GradeVo;
import com.sq.ic.pojo.vo.req.save.GradeReqVo;

public interface GradeService extends IService<Grade> {
    boolean addMainTeacher(GradeReqVo reqVo);

    List<GradeVo> listGradeVos();

    List<GradeVo> listByTeacherId(Integer teacherId);

    boolean saveOrUpdate(GradeReqVo reqVo);

    GradeVo getGradeById(Integer id);
}
