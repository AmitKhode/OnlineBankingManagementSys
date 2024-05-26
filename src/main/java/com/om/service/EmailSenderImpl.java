package com.om.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOtpByMail(String email,Integer otp) {
		System.out.println(email+" ye controller ka input hai");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("OTP for verification of your card");
		String body = "Your OTP for verification is "+ otp;
		message.setText(body);
		
		javaMailSender.send(message);
	}
}
