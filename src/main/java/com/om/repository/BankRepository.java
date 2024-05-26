package com.om.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.om.entity.AccountDetailsEntity;
import com.om.entity.UserDetailEntity;

import jakarta.transaction.Transactional;

@Repository
public interface BankRepository extends JpaRepository<UserDetailEntity, String> {

	@Transactional
	@Modifying
	@Query("update UserDetailEntity e set e.pin =:pin where e.cardNumber =:cardNumber")
	public Integer updatePin(String cardNumber, Integer pin);

	@Transactional
	public String findEmailByCardNumber(String cardNumber);

	

	

	

}
