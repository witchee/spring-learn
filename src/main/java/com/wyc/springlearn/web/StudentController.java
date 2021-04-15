package com.wyc.springlearn.web;

import com.wyc.springlearn.domain.Student;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    static Map<Long, Student> students = Collections.synchronizedMap(new HashMap<Long, Student>());

    @GetMapping("/")
    public List<Student> getStudentList() {
        List<Student> r = new ArrayList<Student>(students.values());
        return r;
    }

    @PostMapping("/")
    public String postStudent(@RequestBody Student student) {
        students.put(student.getId(), student);
        return "success";
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return students.get(id);
    }

    @PutMapping("/{id}")
    public String putStudent(@PathVariable Long id, @RequestBody Student student) {
        Student s = students.get(id);
        s.setName(student.getName());
        s.setAge(student.getAge());
        students.put(id, s);
        return "success";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        students.remove(id);
        return "success";
    }

}
