package com.test.scm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.scm.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.uEmail=:email")
	public User getUserByUserEmail(@Param("email") String email);
	
	@Query("select u from User u where u.uName=:name")
	public User getUserByUserName(@Param("name") String name);
}
