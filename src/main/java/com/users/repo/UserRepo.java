package com.users.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.users.entities.UserRegister;

public interface UserRepo extends JpaRepository<UserRegister,Integer> {
	
	Optional<UserRegister> findByUserEmailId(String email);

}
