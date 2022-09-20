package com.sq.ic.controller;

import com.sq.ic.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;

//    @PostMapping("/save")
//    public Student save(Student student) {
//
//    }

}
