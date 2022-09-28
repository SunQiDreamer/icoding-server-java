package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.mapper.GradeMapper;
import com.sq.ic.pojo.list.StudentVo;
import com.sq.ic.pojo.po.Grade;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.JsonVo;
import com.sq.ic.pojo.vo.req.save.GradeReqVo;
import com.sq.ic.service.GradeService;
import com.sq.ic.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GradeServiceImpl
        extends ServiceImpl<GradeMapper, Grade>
        implements GradeService {

    @Override
    public JsonVo addMainTeacher(GradeReqVo reqVo) {
        LambdaQueryWrapper<Grade> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Grade::getId, reqVo.getId());
        Grade grade = baseMapper.selectOne(wrapper);

        Short mainTeacherId = reqVo.getMainTeacherId();
        grade.setMainTeacherId(mainTeacherId);

        LambdaUpdateWrapper<Grade> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Grade::getId, reqVo.getId());
        int record = baseMapper.update(grade, updateWrapper);
        return record > 0 ? JsonVos.ok("添加成功") : JsonVos.error("添加失败");
    }

}
