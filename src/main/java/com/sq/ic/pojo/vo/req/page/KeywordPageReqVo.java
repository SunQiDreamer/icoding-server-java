package com.sq.ic.pojo.vo.req.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class KeywordPageReqVo extends PageReqVo {
    @ApiModelProperty("搜索关键字")
    private String keyword;
}
