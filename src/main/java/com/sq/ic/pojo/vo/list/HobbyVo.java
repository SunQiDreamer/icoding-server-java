package com.sq.ic.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("爱好")
public class HobbyVo {
    @ApiModelProperty("id")
    private Short id;

    @ApiModelProperty("爱好名称")
    private String name;
}
