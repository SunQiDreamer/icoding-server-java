package com.sq.ic.pojo.po;

import com.sq.ic.common.foreign.anno.ForeignField;
import lombok.Data;

@Data
public class Student {

    private Integer id;

    private String name;

    private Integer no;

    @ForeignField(Grade.class)
    private Integer gradeId;
}
