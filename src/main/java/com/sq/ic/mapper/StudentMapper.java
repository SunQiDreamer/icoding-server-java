package com.sq.ic.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.ic.pojo.po.Student;
import com.sq.ic.pojo.vo.list.StudentVo;

public interface StudentMapper extends BaseMapper<Student> {

    List<StudentVo> selectByKeyword(String keyword);

}
