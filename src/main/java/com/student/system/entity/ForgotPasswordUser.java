package com.student.system.entity;

public class ForgotPasswordUser {
	
	private String userName;
	
	private String otp;
	
	private String email;
	
	private String newPassword;

	public ForgotPasswordUser(String userName, String otp, String email, String newPassword) {
		super();
		this.userName = userName;
		this.otp = otp;
		this.email = email;
		this.newPassword = newPassword;
	}

	public ForgotPasswordUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ForgotPasswordUser [userName=" + userName + ", otp=" + otp + ", email=" + email + ", newPassword="
				+ newPassword + "]";
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * @param otp the otp to set
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	

}
