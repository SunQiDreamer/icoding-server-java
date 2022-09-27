package com.sq.ic.pojo.vo.req.save;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class HobbyReqVo {
    @NotNull(message = "不能为空")
    private String name;

}
