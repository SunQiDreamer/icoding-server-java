package com.sq.ic.pojo.po;

import com.sq.ic.common.foreign.anno.ForeignField;
import lombok.Data;

@Data
public class SysResource {

    private Short id;


    private String name;
    /**
     * 链接地址
     */
    private String uri;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 资源类型（0是目录，1是菜单，2是按钮）
     */
    private Short type;
    /**
     * 图标
     */
    private String icon;
    /**
     * 序号
     */
    private Short sn;
    /**
     * 父资源id
     */
    @ForeignField(SysResource.class)
    private Short parentId;
}
