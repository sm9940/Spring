package com.example.springquiz.controller;

import com.example.springquiz.dao.StudentDao;
import com.example.springquiz.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentDao studentDao;

    @GetMapping("/student")
    public List<Student> main(){
        List<Student> list = studentDao.selectList();
        return list;
    }
}
