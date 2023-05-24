package com.sq.ic.pojo.po;

import com.sq.ic.common.foreign.anno.ForeignField;

import lombok.Data;

@Data
public class TeacherHobby {

    private Integer teacherId;

    @ForeignField(Hobby.class)
    private Short hobbyId;

}
