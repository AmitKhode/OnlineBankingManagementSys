package com.om.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="account_details")
public class AccountDetailsEntity {

	@Id
	private String cardNumber;
	private String accountNumber;
	private Long balance;
}
