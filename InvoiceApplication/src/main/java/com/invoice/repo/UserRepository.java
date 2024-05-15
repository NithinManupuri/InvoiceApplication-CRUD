package com.invoice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.entity.UserEntity;

public interface UserRepository  extends JpaRepository<UserEntity,Integer>{

	
	public UserEntity findByEmailAndPassword(String email,String password);
	public UserEntity findByEmailOrPasswordOrUserName(String email,String password,String userName);

	
	
	
}
