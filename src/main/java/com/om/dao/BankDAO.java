package com.om.dao;

import java.util.Optional;

import com.om.entity.AccountDetailsEntity;
import com.om.entity.OnlineBankingEntity;
import com.om.entity.UserDetailEntity;

public interface BankDAO {

	Boolean checkCardExists(UserDetailEntity user1);

	Optional<UserDetailEntity> checkPinCorrect(UserDetailEntity user1);

	Integer updatePin(UserDetailEntity user1);

	Optional<AccountDetailsEntity> getBalance(String cardNumber);

	void depositBalance(AccountDetailsEntity user1);

	void withdrawBalance(AccountDetailsEntity user1);

	Optional<UserDetailEntity> getEmail(UserDetailEntity user1);

	Optional<OnlineBankingEntity> getCardNumber(OnlineBankingEntity entity1);

	AccountDetailsEntity getUserByAccountNumber(String accountNumber);

	Optional<OnlineBankingEntity> getOnlineUser(OnlineBankingEntity entity1);

}