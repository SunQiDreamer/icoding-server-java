package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.validation.annotation.Validated;

import java.util.function.Function;

@Validated
public abstract class BaseController<Po,ReqVo> {
    protected abstract IService<Po> getService();
    protected abstract Function<ReqVo, Po> getFunction();

}
