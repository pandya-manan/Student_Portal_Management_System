package com.student.system.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.student.system.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	
	public User findUserByuserName(String userName);

	
}
