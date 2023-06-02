package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.cache.Caches;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.shiro.TokenFilter;
import com.sq.ic.common.util.Constants;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.pojo.po.SysUser;
import com.sq.ic.pojo.result.CodeMsg;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.JsonVo;
import com.sq.ic.pojo.vo.LoginVo;
import com.sq.ic.pojo.vo.PageJsonVo;
import com.sq.ic.pojo.vo.list.SysUserVo;
import com.sq.ic.pojo.vo.req.LoginReqVo;
import com.sq.ic.pojo.vo.req.page.SysUserPageReqVo;
import com.sq.ic.pojo.vo.req.page.SysUserReqVo;
import com.sq.ic.service.SysUserService;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
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

    @PostMapping("/login")
    @ApiOperation("登录")
    public DataJsonVo<LoginVo> login(LoginReqVo reqVo, HttpServletRequest request) {
        if (CaptchaUtil.ver(reqVo.getCaptcha(), request)) {
            return JsonVos.ok(service.login(reqVo));
        }
        return JsonVos.raise(CodeMsg.WRONG_CAPTCHA);
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public JsonVo logout(@RequestHeader(TokenFilter.HEADER_TOKEN) String token) {
        Caches.removeToken(token);
        return JsonVos.ok();
    }

    @Override
    // @RequiresPermissions(value = {
    // Constants.Permisson.SYS_USER_ADD,
    // Constants.Permisson.SYS_USER_UPDATE
    // }, logical = Logical.AND)
    public JsonVo save(SysUserReqVo reqVo) {
        if (service.saveOrUpdate(reqVo)) {
            return JsonVos.ok(CodeMsg.SAVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.SAVE_ERROR);
        }
    }

    @Override
    // @RequiresPermissions(Constants.Permisson.SYS_USER_REMOVE)
    public JsonVo remove(String id) {
        return super.remove(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(Constants.Permisson.SYS_USER_LIST)
    public PageJsonVo<SysUserVo> list(SysUserPageReqVo pageReqVo) {
        return JsonVos.ok(service.list(pageReqVo));
    }

    @GetMapping("/list")
    @ApiOperation("查询所有")
    // @RequiresPermissions(Constants.Permisson.SYS_USER_LIST)
    public DataJsonVo<List<SysUserVo>> list() {
        List<SysUserVo> sysUserVos = service.listUser();
        return JsonVos.ok(sysUserVos);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取用户")
    // @RequiresPermissions(Constants.Permisson.SYS_USER_LIST)
    public DataJsonVo<SysUserVo> user(@PathVariable Integer id) {
        SysUserVo userVo = service.user(id);
        return new DataJsonVo<>(userVo);
    }

    @Override
    protected IService<SysUser> getService() {
        return service;
    }

    @Override
    protected Function<SysUserReqVo, SysUser> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
