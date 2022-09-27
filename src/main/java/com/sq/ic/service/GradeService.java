package com.sq.ic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.list.StudentVo;
import com.sq.ic.pojo.po.Grade;
import com.sq.ic.pojo.po.Hobby;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.JsonVo;
import com.sq.ic.pojo.vo.req.save.GradeReqVo;

import java.util.List;

public interface GradeService extends IService<Grade> {
    JsonVo addMainTeacher(GradeReqVo reqVo);

}
