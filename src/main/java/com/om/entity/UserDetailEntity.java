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
@Table(name = "user_details")
public class UserDetailEntity {

	@Id
	private String cardNumber;
	private Integer pin;
	private String AcctNumber;
	private String email;
}
