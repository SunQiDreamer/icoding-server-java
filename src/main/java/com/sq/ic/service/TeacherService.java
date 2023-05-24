package com.sq.ic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.Teacher;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.list.TeacherVo;
import com.sq.ic.pojo.vo.req.page.TeacherPageReqVo;
import com.sq.ic.pojo.vo.req.save.TeacherReqVo;

import java.util.List;

public interface TeacherService extends IService<Teacher> {
    PageVo<TeacherVo> list(TeacherPageReqVo reqVo);

    List<TeacherVo> teachers();

    boolean saveOrUpdate(TeacherReqVo reqVo);

    TeacherVo getTeacherById(Integer id);

    boolean updateMainTeacher(Integer id);
}
