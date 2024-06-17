package com.student.system.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.student.system.entity.*;

public interface UserService {
	
	public Boolean verifyUser(User user);
	
	public Page<Student> findPaginated(int page,int size);
	
	public Boolean addNewUser(User user) throws Exception;
	
	public Student findStudentById(Integer studentId);
	
	public Student saveStudent(Student student);
	
	public Boolean deleteStudent(Integer studentId);
	
	public List<Student> findStudentByFirstName(String firstName);
	
	public Page<User> findPaginatedUsers(int page,int size);
	
	public Boolean deleteUser(Integer userId);
	
	public Boolean verifyUserByUserName(String username);
}
