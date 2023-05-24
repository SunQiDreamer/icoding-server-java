package com.sq.ic.service.impl;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.mapper.TeacherHobbyMapper;
import com.sq.ic.pojo.po.TeacherHobby;
import com.sq.ic.service.TeacherHobbyService;

@Service
@Transactional
public class TeacherHobbyServiceImpl
        extends ServiceImpl<TeacherHobbyMapper, TeacherHobby>
        implements TeacherHobbyService {

    @Override
    public boolean removeByTeacherId(@NotBlank(message = "id不能为空") Integer id) {
        if (baseMapper.selectListByTeacherId(id).size() > 0) {
            return baseMapper.deleteByTeacherId(id);
        }
        return true;
    }

}
