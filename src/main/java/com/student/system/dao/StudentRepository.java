package com.student.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.system.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

	public Student findStudentById(Integer id);
	
	public List<Student> findStudentByFirstName(String firstName);
}
