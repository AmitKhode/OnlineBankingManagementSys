package com.om.dto;

import lombok.Data;

@Data
public class BankTransferDTO {

	private String userId;
	private String AccountNumber;
	private Integer amount;
}
