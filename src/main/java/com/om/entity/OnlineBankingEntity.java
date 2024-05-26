package com.om.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineBankingEntity {

	@Id
	private String userId;
	private String password;
	private String accountNumber;
	private String userType;
	private String cardNumber;
}
