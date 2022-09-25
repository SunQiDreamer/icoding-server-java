package com.sq.ic.pojo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("爱好")
public class HobbyVo {
    @ApiModelProperty("爱好名称")
    private String name;
}
