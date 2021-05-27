package com.wyc.springlearn.dao;

import com.wyc.springlearn.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao {

    List<Student> getAllStudents();

    Integer insert(Student s);

    Student getById(Integer id);

    Integer updateById(Integer id, Student s);

    Integer deleteById(Integer id);

}