package com.sq.ic.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.ic.common.mapStruct.MapStructs;
import com.sq.ic.common.util.JsonVos;
import com.sq.ic.common.util.Streams;
import com.sq.ic.pojo.po.Grade;
import com.sq.ic.pojo.result.CodeMsg;
import com.sq.ic.pojo.vo.DataJsonVo;
import com.sq.ic.pojo.vo.JsonVo;
import com.sq.ic.pojo.vo.list.GradeVo;
import com.sq.ic.pojo.vo.req.save.GradeReqVo;
import com.sq.ic.service.GradeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/grades")
public class GradeController extends BaseController<Grade, GradeReqVo> {

    @Autowired
    private GradeService service;

    @GetMapping("/list")
    @ApiOperation("查询所有")
    public DataJsonVo<List<GradeVo>> list() {
        List<GradeVo> gradeVos = service.listGradeVos();
        return JsonVos.ok(gradeVos);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询班级")
    public DataJsonVo<GradeVo> grade(@PathVariable Integer id) {
        GradeVo gradeVo = service.getGradeById(id);
        return new DataJsonVo<>(gradeVo);
    }

    @Override
    public JsonVo save(GradeReqVo reqVo) {
        if (service.saveOrUpdate(reqVo)) {
            return JsonVos.ok();
        }

        return JsonVos.raise(CodeMsg.SAVE_ERROR);
    }

    @PostMapping("/addMainTeacher")
    @ApiOperation("添加班主任")
    public JsonVo addMainTeacher(GradeReqVo reqVo) {
        if (service.addMainTeacher(reqVo)) {
            return JsonVos.ok("添加成功");
        } else {
            return JsonVos.error("添加失败");
        }
    }

    @Override
    protected IService<Grade> getService() {
        return service;
    }

    @Override
    protected Function<GradeReqVo, Grade> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
