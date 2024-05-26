package com.om.service;

public interface EmailSender {

	void sendOtpByMail(String email, Integer otp);

}