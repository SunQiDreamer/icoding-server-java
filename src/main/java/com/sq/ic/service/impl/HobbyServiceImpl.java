package com.sq.ic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.ic.mapper.HobbyMapper;
import com.sq.ic.pojo.po.Hobby;
import com.sq.ic.service.HobbyService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Component
public class HobbyServiceImpl
        extends ServiceImpl<HobbyMapper, Hobby>
        implements HobbyService {

}
