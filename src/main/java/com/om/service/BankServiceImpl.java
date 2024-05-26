package com.om.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.om.dao.BankDAO;
import com.om.dto.BankTransferDTO;
import com.om.dto.OnlineUserDTO;
import com.om.dto.PinGenerateDTO;
import com.om.dto.UserBalanceDTO;
import com.om.entity.AccountDetailsEntity;
import com.om.entity.OnlineBankingEntity;
import com.om.entity.UserDetailEntity;
import com.om.valueobject.UserBalance;
import com.om.valueobject.VerificationOTP;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDAO bankDAOImpl;
	
	@Autowired
	private EmailSender emailSender;
	
	

	@Override
	public Boolean checkCardExists(String cardNumber) {
		UserDetailEntity user1 = new UserDetailEntity();
		user1.setCardNumber(cardNumber);
		return bankDAOImpl.checkCardExists(user1);
		
	}

	@Override
	public Boolean checkPinCorrect(PinGenerateDTO pinGenerateDTO) {
		UserDetailEntity user1 = new UserDetailEntity();
		user1.setCardNumber(pinGenerateDTO.getCardNumber());
		Optional<UserDetailEntity> userToVerify = bankDAOImpl.checkPinCorrect(user1);
		if(userToVerify.isPresent()) {	
			UserDetailEntity user2 = userToVerify.get();
			if((user2.getPin()).equals(pinGenerateDTO.getPin()))return true;
			else return false;
		}
		else return false;
		
	}

	@Override
	public Boolean updatePin(PinGenerateDTO pinGenerateDTO) {
		
		UserDetailEntity user1= new UserDetailEntity();
		user1.setCardNumber(pinGenerateDTO.getCardNumber());
		user1.setPin(pinGenerateDTO.getPin());
		
		Integer rowsUpdated = bankDAOImpl.updatePin(user1);
		if(rowsUpdated ==1)return true;
		return false;
	}

	@Override
	public UserBalance checkBalance(String cardNumber) {
		UserDetailEntity user1 = new UserDetailEntity();
		user1.setCardNumber(cardNumber);
		Optional<AccountDetailsEntity> user2 = bankDAOImpl.getBalance(cardNumber);
		UserBalance userBalance = new UserBalance();
		if(user2.isPresent()) {
			AccountDetailsEntity user3= user2.get();
			userBalance.setBalance(user3.getBalance());
		}
		else {
			userBalance.setBalance((long) 0);
		}
		return userBalance;
	}

	@Override
	public UserBalance depositBalance(UserBalanceDTO userBalanceDTO) {
		AccountDetailsEntity user1 = new AccountDetailsEntity();
		user1.setBalance(userBalanceDTO.getAmount());
		user1.setCardNumber(userBalanceDTO.getCardNumber());
		
		bankDAOImpl.depositBalance(user1);
		
		UserBalance userBalance = new UserBalance();
		AccountDetailsEntity user2 = bankDAOImpl.getBalance(userBalanceDTO.getCardNumber()).get();
		 userBalance.setBalance(user2.getBalance());
		 return userBalance;
				
	}

	@Override
	public UserBalance withdrawBalance(UserBalanceDTO userBalanceDTO) {
		AccountDetailsEntity user1 = new AccountDetailsEntity();
		user1.setBalance(userBalanceDTO.getAmount());
		user1.setCardNumber(userBalanceDTO.getCardNumber());
		Long balance = checkBalance(userBalanceDTO.getCardNumber()).getBalance();
		UserBalance user2 = new UserBalance();
		if(userBalanceDTO.getAmount()<= balance) {
		bankDAOImpl.withdrawBalance(user1);
		user2.setBalance(balance-userBalanceDTO.getAmount());
		}
		else  {
			return null;
		}
		
//		user2.setBalance(balance-userBalanceDTO.getAmount());
		return user2;
	}

	@Override
	public VerificationOTP sendMailOTP(String cardNumber) {
		System.out.println(cardNumber+" ye controller ka input hai");
		UserDetailEntity user1 = new UserDetailEntity();
		user1.setCardNumber(cardNumber);
		
		Optional<UserDetailEntity> optional = bankDAOImpl.getEmail(user1);
		
		String email=optional.get().getEmail();
		Integer otp = (int) (Math.random()*10000);
		
		emailSender.sendOtpByMail(email, otp);
		
		VerificationOTP  verificationOTP = new VerificationOTP();
		verificationOTP.setOtp(otp);
		return verificationOTP;
	}

	@Override
	public UserBalance getUserBalance(String userId) {
		OnlineBankingEntity entity1 = new OnlineBankingEntity();
		entity1.setUserId(userId);
		OnlineBankingEntity entity2 =  bankDAOImpl.getCardNumber(entity1).get();
		return  (this.checkBalance(entity2.getCardNumber()));
		
	}

	
	@Override
	public UserBalance sendMoney(BankTransferDTO bankTransferDTO) {
		OnlineBankingEntity entity1 = new OnlineBankingEntity();
		entity1.setUserId(bankTransferDTO.getUserId());
		
		AccountDetailsEntity entityToReciveMoney= bankDAOImpl.getUserByAccountNumber(bankTransferDTO.getAccountNumber());
		System.out.println(entityToReciveMoney);
		OnlineBankingEntity entityToSendMoney =  bankDAOImpl.getCardNumber(entity1).get(); 
		
		UserBalanceDTO toDepositBalance = new UserBalanceDTO();
		toDepositBalance.setCardNumber(entityToReciveMoney.getCardNumber());
		toDepositBalance.setAmount((long)bankTransferDTO.getAmount());
		UserBalance u1=  this.depositBalance(toDepositBalance);
		
		
		
		UserBalanceDTO toWithDrawBalance = new UserBalanceDTO();
		toWithDrawBalance.setCardNumber(entityToSendMoney.getCardNumber());
		toWithDrawBalance.setAmount((long)bankTransferDTO.getAmount());
		
		return this.withdrawBalance(toWithDrawBalance);
		
		
	}

	@Override
	public Boolean loginCheckUser(OnlineUserDTO onlineUserDTO) {
		OnlineBankingEntity entity1 = new OnlineBankingEntity();
		entity1.setUserId(onlineUserDTO.getUserId());
		entity1 = bankDAOImpl.getOnlineUser(entity1).get();
		
		if(entity1.getPassword().equals(onlineUserDTO.getPassword())) return true;
		return false;
	}
	
	
	
}
