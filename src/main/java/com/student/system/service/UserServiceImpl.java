package com.student.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.student.system.dao.StudentRepository;
import com.student.system.dao.UserRepository;
import com.student.system.entity.*;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private StudentRepository studentDao;
	
	@Override
	public Boolean verifyUser(User user){
		try
		{
			User foundUser=userDao.findUserByuserName(user.getUserName());
			String password=user.getPassword();
			Boolean result=foundUser.getPassword().equalsIgnoreCase(password);
			if(result==false)
			{
				return false;
			}
			else 
			{
				return true;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		
		
	}

	@Override
	public Page<Student> findPaginated(int page, int size) {
		@SuppressWarnings("unused")
		Page<Student> pageInfo=studentDao.findAll(PageRequest.of(page, size));
		return studentDao.findAll(PageRequest.of(page, size));
	}

	@Override
	public Boolean addNewUser(User user) throws Exception{
		User result=userDao.save(user);	
		if(result!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public Student findStudentById(Integer studentId) {
		Student foundStudent=studentDao.findStudentById(studentId);
		return foundStudent;
	}

	@Override
	public Student saveStudent(Student student) {
		Student saved=studentDao.save(student);
		return student;
	}

	@Override
	public Boolean deleteStudent(Integer studentId) {
		Boolean status=false;
		try
		{
			studentDao.deleteById(studentId);
			status=true;
			return status;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status=false;
		}
		return status;
	}

	@Override
	public List<Student> findStudentByFirstName(String firstName) {
		List<Student> results=studentDao.findStudentByFirstName(firstName);
		return results;
	}

	@Override
	public Page<User> findPaginatedUsers(int page, int size) {
		return userDao.findAll(PageRequest.of(page, size));
	}

	@Override
	public Boolean deleteUser(Integer userId) {
		Boolean status=false;
		try
		{
			userDao.deleteById(userId);
			status=true;
			return status;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status=false;
		}
		return status;
	}

	@Override
	public Boolean verifyUserByUserName(String username) {
		try
		{
			User found=userDao.findUserByuserName(username);
			if(found==null)
			{
				return false;
			}
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}

	
	

}
