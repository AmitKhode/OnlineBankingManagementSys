package com.om.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.om.entity.AccountDetailsEntity;

import jakarta.transaction.Transactional;

public interface AccountDetailsRepository extends JpaRepository<AccountDetailsEntity, String> {
	
	@Transactional
	@Modifying
	@Query("update AccountDetailsEntity e set e.balance= e.balance + :balance  where e.cardNumber= :cardNumber")
	public void updateBalance(String cardNumber, Long balance);

	@Transactional
	@Modifying
	@Query("update AccountDetailsEntity e set e.balance= e.balance - :balance where e.cardNumber= :cardNumber")
	public void withdrawBalance(String cardNumber, Long balance);

	@Transactional
	@Query("from AccountDetailsEntity e  where e.accountNumber= :accountNumber")
	public AccountDetailsEntity findByAccountNumber(String accountNumber);

}
