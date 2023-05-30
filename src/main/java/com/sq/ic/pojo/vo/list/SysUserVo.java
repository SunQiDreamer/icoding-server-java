package com.sq.ic.pojo.vo.list;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("系统用户")
public class SysUserVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("账号的状态【0是正常，1是锁定】")
    private Short status;

    @ApiModelProperty("角色")
    private List<SysRoleVo> roles;

    @ApiModelProperty("最后一次登录的时间")
    private Long loginTime;
}
