package com.sq.ic.pojo.po;

import com.sq.ic.common.foreign.anno.ForeignField;

import lombok.Data;

@Data
public class StudentHobby {

    // @ForeignField(Student.class)
    private Integer studentId;

    @ForeignField(Hobby.class)
    private Short hobbyId;

}