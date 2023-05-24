package com.sq.ic.mapper;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.ic.pojo.po.StudentHobby;

public interface StudentHobbyMapper extends BaseMapper<StudentHobby> {
    boolean deleteByStudentId(@NotBlank(message = "id不能为空") Integer id);

    List<StudentHobby> selectListByStudentId(@NotBlank(message = "id不能为空") Integer id);
}
