package com.users.dtos;

public class UserPassUpdate {
	
	private String userEmail;
	
	private String usertempPass;
	
	private String userPass;
	
	private String reEnterPass;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUsertempPass() {
		return usertempPass;
	}

	public void setUsertempPass(String usertempPass) {
		this.usertempPass = usertempPass;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getReEnterPass() {
		return reEnterPass;
	}

	public void setReEnterPass(String reEnterPass) {
		this.reEnterPass = reEnterPass;
	}
	

}
