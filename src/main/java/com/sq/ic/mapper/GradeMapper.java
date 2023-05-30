package com.sq.ic.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.ic.pojo.po.Grade;
import com.sq.ic.pojo.vo.list.GradeVo;

public interface GradeMapper extends BaseMapper<Grade> {

    List<GradeVo> selectListByTeacherId(Integer teacherId);

    GradeVo getGradeById(Integer id);
}
