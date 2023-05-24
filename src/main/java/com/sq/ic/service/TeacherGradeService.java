package com.sq.ic.service;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.TeacherGrade;

public interface TeacherGradeService extends IService<TeacherGrade> {
    boolean removeByTeacherId(@NotBlank(message = "id不能为空") Integer id);
}