package com.sq.ic.pojo.po;

import com.sq.ic.common.foreign.anno.ForeignField;

import lombok.Data;

@Data
public class TeacherGrade {

    private Integer teacherId;

    @ForeignField(Grade.class)
    private Short GradeId;

}