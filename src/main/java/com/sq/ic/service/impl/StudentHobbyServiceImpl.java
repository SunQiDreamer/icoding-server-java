package com.sq.ic.service.impl;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.mapper.StudentHobbyMapper;
import com.sq.ic.pojo.po.StudentHobby;
import com.sq.ic.service.StudentHobbyService;

@Service
@Transactional
public class StudentHobbyServiceImpl
                extends ServiceImpl<StudentHobbyMapper, StudentHobby>
                implements StudentHobbyService {

        @Override
        public boolean removeByStudentId(@NotBlank(message = "id不能为空") Integer id) {
                if (baseMapper.selectListByStudentId(id).size() > 0) {
                        return baseMapper.deleteByStudentId(id);
                }
                return true;
        }

}
