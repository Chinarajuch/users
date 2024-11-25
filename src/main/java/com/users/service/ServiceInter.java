package com.users.service;

import java.util.List;

import com.users.dtos.Login;
import com.users.dtos.UserPassUpdate;
import com.users.dtos.UserRequest;
import com.users.dtos.UserResponse;

public interface ServiceInter {
	
	
	UserResponse saveUser(UserRequest userRequest);
	
	List<UserResponse> getAllUsers();
	
	public boolean changeTempPassword(UserPassUpdate userPassUpdate);
	
	public UserResponse getUserById(Integer userId);
	
	public boolean deleteUserById(Integer userId);
	
	public boolean changeAccountStatus(Integer userId, String accStatus);
	
	public String login(Login login);
	
	public String forgotPwd(String email); 

}
