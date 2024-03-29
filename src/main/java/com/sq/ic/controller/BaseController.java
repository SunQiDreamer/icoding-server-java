package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.pojo.result.CodeMsg;
import com.sq.ic.pojo.vo.JsonVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.function.Function;

@Validated
public abstract class BaseController<Po, ReqVo> {
    protected abstract IService<Po> getService();

    protected abstract Function<ReqVo, Po> getFunction();

    @PostMapping("/remove")
    @ApiOperation("删除一条或多条数据")
    public JsonVo remove(@NotBlank(message = "id不能为空") @RequestParam String id) {
        if (getService().removeByIds(Arrays.asList(id.split(",")))) {
            return JsonVos.ok(CodeMsg.REMOVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.REMOVE_ERROR);
        }
    }

    @PostMapping("/save")
    @ApiOperation("添加或更新")
    public JsonVo save(ReqVo reqVo) {

        System.out.println("添加或更新");
        System.out.println(reqVo);

        Po po = getFunction().apply(reqVo);
        if (getService().saveOrUpdate(po)) {
            return JsonVos.ok(CodeMsg.SAVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.SAVE_ERROR);
        }
    }
}
