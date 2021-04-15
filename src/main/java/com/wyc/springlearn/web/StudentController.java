package com.wyc.springlearn.web;

import com.wyc.springlearn.domain.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(tags = "学生管理")
@RequestMapping(value = "/students")
public class StudentController {

    static Map<Long, Student> students = Collections.synchronizedMap(new HashMap<Long, Student>());

    @ApiOperation("获取学生列表")
    @GetMapping("/")
    public List<Student> getStudentList() {
        List<Student> r = new ArrayList<Student>(students.values());
        return r;
    }

    @ApiOperation("创建学生")
    @PostMapping("/")
    public String postStudent(@RequestBody Student student) {
        students.put(student.getId(), student);
        return "success";
    }

    @ApiOperation("获取学生信息")
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return students.get(id);
    }

    @ApiOperation("修改学生信息")
    @PutMapping("/{id}")
    public String putStudent(@PathVariable Long id, @RequestBody Student student) {
        Student s = students.get(id);
        s.setName(student.getName());
        s.setAge(student.getAge());
        students.put(id, s);
        return "success";
    }

    @ApiOperation("删除学生")
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        students.remove(id);
        return "success";
    }

}
