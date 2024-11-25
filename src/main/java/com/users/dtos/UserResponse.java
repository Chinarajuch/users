package com.users.dtos;

import java.time.LocalDate;

public class UserResponse {

	
	
private int userId;
	
	private String userFullName;
	
	private String userEmailId;
	
	private String gender;
	
	private LocalDate dateOfBirth;
	
	private int userSsn;
	
	private long userPhoneNumber;
	
	

	public long getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(long userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getUserSsn() {
		return userSsn;
	}

	public void setUserSsn(int userSsn) {
		this.userSsn = userSsn;
	}

	
}
