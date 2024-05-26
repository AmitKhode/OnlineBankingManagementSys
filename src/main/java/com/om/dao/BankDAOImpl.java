package com.om.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.om.entity.AccountDetailsEntity;
import com.om.entity.OnlineBankingEntity;
import com.om.entity.UserDetailEntity;
import com.om.repository.AccountDetailsRepository;
import com.om.repository.BankRepository;
import com.om.repository.OnlineBankingRepository;

@Repository
public class BankDAOImpl implements BankDAO {

	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private AccountDetailsRepository accountDetailsRepository;
	@Autowired
	private OnlineBankingRepository onlineBankingRepository;

	@Override
	public Boolean checkCardExists(UserDetailEntity user1) {
		
		
			return	bankRepository.existsById(user1.getCardNumber());
		
	}

	@Override
	public Optional<UserDetailEntity> checkPinCorrect(UserDetailEntity user1) {
		
		Optional<UserDetailEntity> userToVerify =  bankRepository.findById(user1.getCardNumber());
		
		return userToVerify;
	}

	@Override
	public Integer updatePin(UserDetailEntity user1) {
		return bankRepository.updatePin(user1.getCardNumber(),user1.getPin());
		
	}

	@Override
	public Optional<AccountDetailsEntity> getBalance(String cardNumber) {
		return accountDetailsRepository.findById(cardNumber);
		
	}

	@Override
	public void depositBalance(AccountDetailsEntity user1) {
		accountDetailsRepository.updateBalance(user1.getCardNumber(),user1.getBalance());
		
	}

	@Override
	public void withdrawBalance(AccountDetailsEntity user1) {
		accountDetailsRepository.withdrawBalance(user1.getCardNumber(),user1.getBalance());
		
	}

	@Override
	public  Optional<UserDetailEntity> getEmail(UserDetailEntity user1) {
		return bankRepository.findById(user1.getCardNumber());
		
	}

	@Override
	public  Optional<OnlineBankingEntity> getCardNumber(OnlineBankingEntity entity1) {
		
		return onlineBankingRepository.findById(entity1.getUserId());
		
	}

	@Override
	public AccountDetailsEntity getUserByAccountNumber(String accountNumber) {
		AccountDetailsEntity u1=  accountDetailsRepository.findByAccountNumber(accountNumber);
		System.out.println(u1);
		return u1;
		
	}

	@Override
	public Optional<OnlineBankingEntity> getOnlineUser(OnlineBankingEntity entity1) {
		return onlineBankingRepository.findById(entity1.getUserId());
		
	}

	

	 
}
