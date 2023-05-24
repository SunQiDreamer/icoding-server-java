package com.sq.ic.mapper;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.ic.pojo.po.TeacherHobby;

public interface TeacherHobbyMapper extends BaseMapper<TeacherHobby> {
    boolean deleteByTeacherId(@NotBlank(message = "id不能为空") Integer id);

    List<TeacherHobby> selectListByTeacherId(@NotBlank(message = "id不能为空") Integer id);
}
