package com.om.service;

import com.om.dto.BankTransferDTO;
import com.om.dto.OnlineUserDTO;
import com.om.dto.PinGenerateDTO;
import com.om.dto.UserBalanceDTO;
import com.om.valueobject.UserBalance;
import com.om.valueobject.VerificationOTP;

public interface BankService {

	Boolean checkCardExists(String cardNumber);

	Boolean checkPinCorrect(PinGenerateDTO pinGenerateDTO);

	Boolean updatePin(PinGenerateDTO pinGenerateDTO);

	UserBalance checkBalance(String cardNumber);

	UserBalance depositBalance(UserBalanceDTO userBalanceDTO);

	UserBalance withdrawBalance(UserBalanceDTO userBalanceDTO);

	VerificationOTP sendMailOTP(String cardNumber);

	UserBalance getUserBalance(String userId);

	UserBalance sendMoney(BankTransferDTO bankTransferDTO);

	Boolean loginCheckUser(OnlineUserDTO onlineUserDTO);

}