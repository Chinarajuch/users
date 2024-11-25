package com.users.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.users.dtos.Login;
import com.users.dtos.UserPassUpdate;
import com.users.dtos.UserRequest;
import com.users.dtos.UserResponse;
import com.users.service.ServiceImpl;

@RestController
public class MainController {
	
	private ServiceImpl serviceImpl;
	
	
	
	public MainController(ServiceImpl serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	@PostMapping("/save")
	public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest){
		
		UserResponse saveUser = serviceImpl.saveUser(userRequest);
		return new ResponseEntity(saveUser,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<UserResponse>> getAllUsers(){
		
		List<UserResponse> allUsers = serviceImpl.getAllUsers();
		return new ResponseEntity(allUsers,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<Boolean> changeTempPassword(@RequestBody UserPassUpdate userPassUpdate)
	{
		boolean activateUserAcc = serviceImpl.changeTempPassword(userPassUpdate);
		return new ResponseEntity(activateUserAcc,HttpStatus.ACCEPTED);
	}
	@GetMapping("/userByid/{userId}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Integer userId)
	{
		
		return new ResponseEntity(serviceImpl.getUserById(userId),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/id/{userId}")
	public ResponseEntity<Boolean> deleteUserById(@PathVariable Integer userId)
	{
		return new ResponseEntity(serviceImpl.deleteUserById(userId),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/active/{userId}/{accStatus}")
	public ResponseEntity<Boolean> changeAccountStatus(@PathVariable Integer userId,@PathVariable String accStatus)
	{
		return new ResponseEntity(serviceImpl.changeAccountStatus(userId, accStatus),HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Login login)
	{
		return new ResponseEntity(serviceImpl.login(login),HttpStatus.ACCEPTED);
	}
	@GetMapping("/forget")
	public ResponseEntity<String> forgotPwd(@RequestParam String email)
	{
		return new ResponseEntity(serviceImpl.forgotPwd(email),HttpStatus.ACCEPTED);
	}


}
