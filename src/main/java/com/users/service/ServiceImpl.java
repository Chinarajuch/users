package com.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.users.dtos.Login;
import com.users.dtos.UserPassUpdate;
import com.users.dtos.UserRequest;
import com.users.dtos.UserResponse;
import com.users.entities.UserRegister;
import com.users.repo.UserRepo;
import com.users.utils.EmailUtils;

@Service
public class ServiceImpl implements ServiceInter {

	private UserRepo userRepo;
	
	private EmailUtils emailUtils;
	
	public ServiceImpl(UserRepo userRepo,EmailUtils emailUtils)
	{
		this.userRepo=userRepo;
		this.emailUtils=emailUtils;
	}
	
	@Override
	public UserResponse saveUser(UserRequest userRequest) {
		
		UserRegister us=new UserRegister();
		us.setActiveSwitch("In-active");
		us.setUserPassword(generatePassword());
		BeanUtils.copyProperties(userRequest, us);
		UserRegister save = userRepo.save(us);
		UserResponse response=new UserResponse();
		BeanUtils.copyProperties(us, response);
		String url="http://localhost:8080/active/"+save.getUserId()+"/Active";
		String mailBody = emailUtils.createMailBody(userRequest.getUserFullName(),"temporary password is:"+save.getUserPassword(),url,"active your account :");
		try {
		emailUtils.sendMail(save.getUserEmailId(),"Registration Successful", mailBody);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return response;
	}

	@Override
	public List<UserResponse> getAllUsers() {
		List<UserRegister> all = userRepo.findAll();
		List<UserResponse> responses=new ArrayList<>();
		
		for(UserRegister user:all)
		{
			UserResponse us=new UserResponse();
			BeanUtils.copyProperties(user,us);
			responses.add(us);
		}
		return responses;
	}

	@Override
	public boolean changeTempPassword(UserPassUpdate userPassUpdate) {
		
		UserRegister us=new UserRegister();
		us.setUserEmailId(userPassUpdate.getUserEmail());
		us.setUserPassword(userPassUpdate.getUsertempPass());
		Example<UserRegister> example=Example.of(us);
		
		List<UserRegister> all = userRepo.findAll(example);
		if(!all.isEmpty())
		{
			UserRegister userRegister = all.get(0);
			userRegister.setActiveSwitch("Active");
			userRegister.setUserPassword(userPassUpdate.getUserPass());
			userRepo.save(userRegister);
			
			return true;
		}
		
		
		return false;
	}

	@Override
	public UserResponse getUserById(Integer userId) {
		Optional<UserRegister> byId = userRepo.findById(userId);
		UserResponse res=new UserResponse();
		if(byId.isPresent())
		{
			BeanUtils.copyProperties(byId.get(), res);
			return res;
		}
		return res;
	}

	@Override
	public boolean deleteUserById(Integer userId) {
		Optional<UserRegister> byId = userRepo.findById(userId);
		if(byId.isPresent())
		{
			userRepo.deleteById(userId);
			return true;
		}
		return false;
	}

	@Override
	public boolean changeAccountStatus(Integer userId, String accStatus) {
		
		Optional<UserRegister> byId = userRepo.findById(userId);
		if(byId.isPresent())
		{
			UserRegister userRegister = byId.get();
			userRegister.setActiveSwitch(accStatus);
			userRepo.save(userRegister);
			return true;
		}
		
		return false;
	}

	@Override
	public String login(Login login) {
		UserRegister us=new UserRegister();
		us.setUserEmailId(login.getUserEmailId());
		us.setUserPassword(login.getUserPassword());
		Example<UserRegister> example=Example.of(us);
		List<UserRegister> all = userRepo.findAll(example);
		System.out.println(all);
		if(!all.isEmpty())
		{
			UserRegister userRegister = all.get(0);
			if(userRegister.getActiveSwitch().equals("Active"))
				return "Login Successful";
			else
				return "Unable To Login Because Your Account is in deActive";
		}
		return "Invalid Credentials";
	}

	@Override
	public String forgotPwd(String email) {
		
		Optional<UserRegister> byUserEmailId = userRepo.findByUserEmailId(email);
		if(byUserEmailId.isPresent())
		{
			UserRegister userRegister = byUserEmailId.get();
			String url="http://localhost:8080/login";
			String mailBody = emailUtils.createMailBody(userRegister.getUserFullName(),"Recover password is:"+userRegister.getUserPassword(),url,"Login your account :");
			try {
			emailUtils.sendMail(email,"Recovery Password", mailBody);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
			return "YOUR RECOVER PASSWORD IS SENT TO YOUR MAIL";
		}
		return "NO USER FOUND WITH THIS EMAIL ID TO RECOVER PASSWORD";
	}
	String generatePassword()
	{
		String s="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		StringBuffer pass=new StringBuffer();
		Random random=new Random();
		for(int i=1;i<=6;i++)
		{
			int nextInt = random.nextInt(s.length());
			pass.append(s.charAt(nextInt));
		}
		
		return pass.toString();
		
		
	}

}
