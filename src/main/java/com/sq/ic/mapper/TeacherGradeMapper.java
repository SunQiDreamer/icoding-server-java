package com.sq.ic.mapper;

import java.util.List;

import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.ic.pojo.po.StudentHobby;
import com.sq.ic.pojo.po.TeacherGrade;

public interface TeacherGradeMapper extends BaseMapper<TeacherGrade> {
    boolean deleteByTeacherId(@NotBlank(message = "id不能为空") Integer id);

    List<StudentHobby> selectListByTeacherId(@NotBlank(message = "id不能为空") Integer id);
}