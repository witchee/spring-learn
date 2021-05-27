package com.wyc.springlearn.web;

import com.wyc.springlearn.dao.StudentDao;
import com.wyc.springlearn.domain.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "学生管理")
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @ApiOperation("获取学生列表")
    @GetMapping("/")
    public List<Student> getStudentList() {
        return studentDao.getAllStudents();
    }

    @ApiOperation("创建学生")
    @PostMapping("/")
    public Integer postStudent(@RequestBody Student student) {
        return studentDao.insert(student);
    }

    @ApiOperation("获取学生信息")
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return studentDao.getById(id);
    }

    @ApiOperation("修改学生信息")
    @PutMapping("/{id}")
    public Integer putStudent(@PathVariable Integer id, @RequestBody Student student) {
        return studentDao.updateById(id, student);
    }

    @ApiOperation("删除学生")
    @DeleteMapping("/{id}")
    public Integer deleteStudent(@PathVariable Integer id) {
        return studentDao.deleteById(id);
    }

}
