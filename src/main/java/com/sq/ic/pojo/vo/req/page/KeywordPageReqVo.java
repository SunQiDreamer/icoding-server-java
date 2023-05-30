package com.sq.ic.pojo.vo.req.page;

import com.sq.ic.pojo.vo.req.save.PageReqVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class KeywordPageReqVo extends PageReqVo {
    @ApiModelProperty("搜索关键词")
    private String keyword;
}
