package com.student.system.service;

import com.student.system.entity.EmailDetails;

public interface EmailService {
	
	public String sendSimpleMail(EmailDetails details);
	
	public String sendEmailWithAttachment(EmailDetails details);
	

}
