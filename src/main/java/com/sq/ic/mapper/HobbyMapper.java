package com.sq.ic.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.ic.pojo.po.Hobby;
import com.sq.ic.pojo.vo.list.HobbyVo;

public interface HobbyMapper extends BaseMapper<Hobby> {
    List<HobbyVo> selectListByStudentId(Integer studentId);

    List<HobbyVo> selectListByTeacherId(Integer teacherId);
}
