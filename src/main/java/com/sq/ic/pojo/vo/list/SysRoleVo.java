package com.sq.ic.pojo.vo.list;

import java.util.List;

import com.sq.ic.pojo.vo.req.save.SysResourceVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("系统角色")
public class SysRoleVo {
    @ApiModelProperty("id")
    private Short id;

    @ApiModelProperty("名称")
    private String name;

    private List<SysResourceVo> resources;
}
