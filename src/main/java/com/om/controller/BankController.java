package com.om.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.om.dto.BankTransferDTO;
import com.om.dto.OnlineUserDTO;
import com.om.dto.PinGenerateDTO;
import com.om.dto.UserBalanceDTO;
import com.om.service.BankService;
import com.om.valueobject.UserBalance;
import com.om.valueobject.VerificationOTP;

@RestController
@RequestMapping("omkar")
@CrossOrigin
public class BankController {

	@Autowired
	private BankService bankServiceImpl;	
	
	@GetMapping(value="checkifexists/{cardNumber}")
	public Boolean checkIfExists(@PathVariable String cardNumber) {
//		VerifyBoolean verifyUser = new VerifyBoolean();
		return bankServiceImpl.checkCardExists(cardNumber);
//		return verifyUser;
		
	}
	
	@PostMapping(value="verify")
	public Boolean verifyPin(@RequestBody PinGenerateDTO pinGenerateDTO) {
		return bankServiceImpl.checkPinCorrect(pinGenerateDTO);
	}
	
	@PostMapping(value="generatepin")
	public Boolean  UpdatePin(@RequestBody PinGenerateDTO pinGenerateDTO) {
		return bankServiceImpl.updatePin(pinGenerateDTO);
	}
	
	@GetMapping(value="getbalance/{cardNumber}")
	public UserBalance checkBalance(@PathVariable String cardNumber) {
		System.out.println("we are inside getbalance");
		return bankServiceImpl.checkBalance(cardNumber);
	}
	
	@PostMapping(value="depositamount")
	public UserBalance depositBalance(@RequestBody UserBalanceDTO userBalanceDTO) {
		return bankServiceImpl.depositBalance(userBalanceDTO);
	}
	
	@PostMapping(value="withdrawamount")
	public UserBalance withdrawBalance(@RequestBody UserBalanceDTO userBalanceDTO) {
		System.out.println(userBalanceDTO);
		return bankServiceImpl.withdrawBalance(userBalanceDTO);
	}
	
	@GetMapping(value="generatepin/{cardNumber}")
	public VerificationOTP sendMailOTP(@PathVariable String cardNumber) {
		System.out.println(cardNumber+" ye controller ka input hai");
		return bankServiceImpl.sendMailOTP(cardNumber);
		
	}
	
	@PostMapping(value="verifyonline")
	public Boolean loginCheckUser(@RequestBody OnlineUserDTO onlineUserDTO) {
		return bankServiceImpl.loginCheckUser(onlineUserDTO);
	}
	
	
	@GetMapping(value="getbalanceonline/{userId}")
	public UserBalance getUserBalance(@PathVariable String userId) {
		return bankServiceImpl.getUserBalance(userId);
	}
	
	@PostMapping(value="fundtransfer")
	public UserBalance sendMoney(@RequestBody BankTransferDTO bankTransferDTO) {
		System.out.println(bankTransferDTO);
		return bankServiceImpl.sendMoney(bankTransferDTO);
	}
	
	
	
	
	
	
	
}
