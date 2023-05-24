package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.mapper.HobbyMapper;
import com.sq.ic.pojo.po.Hobby;
import com.sq.ic.pojo.vo.list.HobbyVo;
import com.sq.ic.service.HobbyService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class HobbyServiceImpl
        extends ServiceImpl<HobbyMapper, Hobby>
        implements HobbyService {

    @Override
    public List<Hobby> list(List<String> hobbyIds) {
        List<Hobby> hobbies = baseMapper.selectBatchIds(hobbyIds);
        return hobbies;
    }

    @Override
    public List<HobbyVo> listByStudentId(Integer studentId) {
        return baseMapper.selectListByStudentId(studentId);
    }

    @Override
    public List<HobbyVo> listByTeacherId(Integer teacherId) {
        return baseMapper.selectListByTeacherId(teacherId);
    }

}
