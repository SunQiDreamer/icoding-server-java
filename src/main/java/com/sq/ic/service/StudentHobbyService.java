package com.sq.ic.service;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.StudentHobby;

public interface StudentHobbyService extends IService<StudentHobby> {
    boolean removeByStudentId(@NotBlank(message = "id不能为空") Integer id);
}