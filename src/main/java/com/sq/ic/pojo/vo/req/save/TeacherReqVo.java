package com.sq.ic.pojo.vo.req.save;

import lombok.Data;

import java.util.List;

@Data
public class TeacherReqVo {
    private String name;

    private Short sex;

    private Short courseId;

    private Boolean isMain;

    private List<Short> hobbyIds;
}
