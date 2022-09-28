package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.pojo.po.SysUser;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.LoginVo;
import com.sq.ic.pojo.vo.req.page.SysUserReqVo;
import com.sq.ic.service.SysUserService;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;

@RestController
@RequestMapping("/sysUsers")
@Api(tags = "用户")
public class SysUserController extends BaseController<SysUser, SysUserReqVo> {

    @Autowired
    private SysUserService service;

    @GetMapping("/captcha")
    @ApiOperation("生成验证码")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtil.out(request, response);
    }

//    @PostMapping("/login")
//    @ApiOperation("登录")
//    public DataJsonVo<LoginVo> login()

    @Override
    protected IService<SysUser> getService() {
        return service;
    }

    @Override
    protected Function<SysUserReqVo, SysUser> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
