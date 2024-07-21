package com.banking.dtos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "creditcard")
@Setter
@Getter
public class CreditCardDTO extends BaseDTO {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accounts", nullable = false)
	private AccountDTO accounts;
	@Column(name = "account_id")
	private Long accountId;
	
	
	@Column(name = "credit_limit")
	private Long creditLimit;
	@Column(name = "issue_date")
	private String date;
	@Column(name = "credit_card_number")
	private String creditCardNumber;
	@Column(name = "month_expiry")
	private String monthExpiry;
	@Column(name = "year")
	private String year;
	@Column(name = "cvv")
	private String cvv;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "users", nullable = false)
	private UserDTO user;
	
	@Column(name = "user_id")
	private Long userId;
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
