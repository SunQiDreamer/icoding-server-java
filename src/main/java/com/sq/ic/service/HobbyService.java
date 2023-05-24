package com.sq.ic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.pojo.po.Hobby;
import com.sq.ic.pojo.vo.list.HobbyVo;

import java.util.List;

public interface HobbyService extends IService<Hobby> {
    List<Hobby> list(List<String> hobbyIds);

    List<HobbyVo> listByStudentId(Integer studentId);

    List<HobbyVo> listByTeacherId(Integer teacherId);
}
