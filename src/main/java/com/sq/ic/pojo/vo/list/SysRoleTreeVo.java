package com.sq.ic.pojo.vo.list;

import java.util.List;

import lombok.Data;

@Data
public class SysRoleTreeVo {

    private Short id;

    private String title;

    private String key;

    private List<SysResourceTreeVo> children;

}
