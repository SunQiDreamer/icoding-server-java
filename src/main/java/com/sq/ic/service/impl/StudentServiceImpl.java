package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.enhance.MpLambdaQueryWrapper;
import com.sq.ic.common.enhance.MpPage;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.mapper.StudentMapper;
import com.sq.ic.pojo.po.Student;
import com.sq.ic.pojo.list.StudentVo;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.req.page.StudentPageReqVo;
import com.sq.ic.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl
        extends ServiceImpl<StudentMapper, Student>
        implements StudentService {
    @Override
    @Transactional(readOnly = true)
    public PageVo<StudentVo> list(StudentPageReqVo query) {
        // 查询条件
        MpLambdaQueryWrapper<Student> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(query.getName(), Student::getName);

        if (query.getSex() != null) {
            wrapper.eq(Student::getSex, query.getSex());
        }

        // 排序
        wrapper.orderByDesc(Student::getId);

        // 查询
        return baseMapper
                .selectPage(new MpPage<Student>(query), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }
}
